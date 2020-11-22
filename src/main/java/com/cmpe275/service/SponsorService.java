package com.cmpe275.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmpe275.Exception.CustomException;
import com.cmpe275.entity.Address;
import com.cmpe275.entity.Sponsor;
import com.cmpe275.models.PlayerShallowForm;
import com.cmpe275.models.SponsorDeepForm;
import com.cmpe275.repo.SponsorRepo;


@Service
public class SponsorService {

	@Autowired
	private SponsorRepo sponsorRepo;

	public ResponseEntity<Object> createNewSponsor(HttpServletRequest req) {
		Sponsor sponsor;
		try {
		
			sponsor = buildSponsorFromData(req);
			Sponsor s = sponsorRepo.save(sponsor);
			return new ResponseEntity<>(s, HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}

	public Sponsor buildSponsorFromData(HttpServletRequest req) throws CustomException {
		Sponsor sponsor = new Sponsor();
		try {
			String name = req.getParameter("name");
			if (name != null)
				sponsor.setName(name);
			String description = req.getParameter("description");
			if (description != null)
				sponsor.setDescription(description);
			Address address = new Address();
			String street = req.getParameter("street");
			if (street != null)
				address.setStreet(street);
			String city = req.getParameter("city");
			if (city != null)
				address.setCity(city);
			String state = req.getParameter("state");
			if (state != null)
				address.setState(state);
			String zip = req.getParameter("zip");
			if (zip != null)
				address.setZip(zip);

			sponsor.setAddress(address);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return sponsor;
	}
	public SponsorDeepForm convertSponsorObjectToDeepForm(Sponsor sponsor) {
		SponsorDeepForm sponsorDeepForm = new SponsorDeepForm();
		sponsorDeepForm.setId(sponsor.getId());
		sponsorDeepForm.setName(sponsor.getName());
		sponsorDeepForm.setDescription(sponsor.getDescription());
		sponsorDeepForm.setAddress(sponsor.getAddress());
		if (sponsor.getPlayers()!= null) {
			List<PlayerShallowForm> playerList = new ArrayList<PlayerShallowForm>();
			sponsor.getPlayers().forEach((p) -> {
				PlayerShallowForm playerShallowForm = new PlayerShallowForm(p.getId(), p.getFirstname(),
						p.getLastname(), p.getEmail(), p.getDescription(), p.getAddress());
				playerList.add(playerShallowForm);
			});
			sponsorDeepForm.setPlayers(playerList);       
		}
		

		return sponsorDeepForm;
	}

	public ResponseEntity<Object> getSponsorById(Long sponsorId) {
		try {
			if (sponsorId == null)
				throw new CustomException("sponsorId  is Invalid", HttpStatus.BAD_REQUEST);
			Optional<Sponsor> sponsor = sponsorRepo.findById(sponsorId);
			if (!sponsor.isPresent()) {
				return new ResponseEntity<>("sponsor id is Invalid", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(convertSponsorObjectToDeepForm(sponsor.get()), HttpStatus.OK);
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Object> deleteSponsorById(Long sponsorId) {
		try {
			if (sponsorId == null)
				throw new CustomException("sponsorId  is Invalid", HttpStatus.BAD_REQUEST);
			Optional<Sponsor> sponsor = sponsorRepo.findById(sponsorId);
			if (!sponsor.isPresent()) {
				return new ResponseEntity<>("sponsor id is Invalid", HttpStatus.BAD_REQUEST);
			} else {
				SponsorDeepForm temp=convertSponsorObjectToDeepForm(sponsor.get());
				sponsorRepo.deleteById(sponsorId);
				return new ResponseEntity<>(temp, HttpStatus.OK);
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}
	public Sponsor  buildNewSponsorFromData(HttpServletRequest req) throws CustomException {
		Sponsor sponsor = new Sponsor();
		try {
			String name = req.getParameter("name");
			if (name != null)
				sponsor.setName(name);
			String description = req.getParameter("description");
			sponsor.setDescription(description);
			Address address = new Address();
			String street = req.getParameter("street");
			address.setStreet(street);
			String city = req.getParameter("city");
			address.setCity(city);
			String state = req.getParameter("state");
			address.setState(state);
			String zip = req.getParameter("zip");
			address.setZip(zip);
			sponsor.setAddress(address);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return sponsor;
	}
	
	public ResponseEntity<Object> updateSponsorById( HttpServletRequest req, Long sponsorId) {
		Sponsor new_sponsor;
		try {
			if (sponsorId == null)
				throw new CustomException("sponsorId  is Invalid", HttpStatus.BAD_REQUEST);
			Optional<Sponsor> sponsor = sponsorRepo.findById(sponsorId);
			if (!sponsor.isPresent()) {
				return new ResponseEntity<>("sponsor id is Invalid", HttpStatus.BAD_REQUEST);
			} else {
				new_sponsor = buildNewSponsorFromData(req);
				Sponsor s = sponsorRepo.save(new_sponsor);
				return new ResponseEntity<>(convertSponsorObjectToDeepForm(s), HttpStatus.OK);
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}
	
}
