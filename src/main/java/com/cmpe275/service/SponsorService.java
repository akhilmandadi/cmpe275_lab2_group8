package com.cmpe275.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmpe275.Exception.CustomException;
import com.cmpe275.entity.Address;
import com.cmpe275.entity.Player;
import com.cmpe275.entity.Sponsor;
import com.cmpe275.repo.PlayerRepo;
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
	public ResponseEntity<Object> getSponsor(HttpServletRequest req) {
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


}
