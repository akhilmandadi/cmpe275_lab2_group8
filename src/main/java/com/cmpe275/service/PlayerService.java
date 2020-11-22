package com.cmpe275.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmpe275.Exception.CustomException;
import com.cmpe275.entity.Address;
import com.cmpe275.entity.Player;
import com.cmpe275.repo.PlayerRepo;
import com.cmpe275.repo.SponsorRepo;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepo playerRepo;

	@Autowired
	private SponsorRepo sponsorRepo;

	public ResponseEntity<Object> createNewPlayer(HttpServletRequest req) {
		Player player;
		try {
			player = buildPlayerFromData(req);
			Player p = playerRepo.save(player);
			return new ResponseEntity<>(p, HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}

	public Player buildPlayerFromData(HttpServletRequest req) throws CustomException {
		Player player = new Player();
		try {
			if (playerRepo.findByEmail(req.getParameter("email")).isPresent()) {
				throw new CustomException("Player Already exists with given Email", HttpStatus.BAD_REQUEST);
			}
			String firstname = req.getParameter("firstname");
			if (firstname != null)
				player.setFirstname(firstname);
			String lastname = req.getParameter("lastname");
			if (lastname != null)
				player.setLastname(lastname);
			String email = req.getParameter("email");
			if (email != null)
				player.setEmail(email);
			String description = req.getParameter("description");
			if (description != null)
				player.setDescription(description);

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

			player.setAddress(address);

			if (req.getParameter("sponsor") != null) {
				String sponsorName = req.getParameter("sponsor");
				if (sponsorRepo.findByName(sponsorName).isPresent()) {
					player.setSponsor(sponsorRepo.findByName(sponsorName).get());
				} else {
					throw new CustomException("Sponsor doesnt exist with given name", HttpStatus.BAD_REQUEST);
				}
			}
		} catch (CustomException e) {
			throw new CustomException(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return player;
	}
	
	public Player checkPlayerIsExisting(HttpServletRequest req,long id) throws CustomException {
		Player player = new Player();
		try {
			if (!playerRepo.getById(id).isPresent()) {
				throw new CustomException("Player does not exist with given Id", HttpStatus.NOT_FOUND);
			}
			String firstname = req.getParameter("firstname");
			if (firstname != null)
				player.setFirstname(firstname);
			String lastname = req.getParameter("lastname");
			if (lastname != null)
				player.setLastname(lastname);
			String email = req.getParameter("email");
			if (email != null)
				player.setEmail(email);
			String description = req.getParameter("description");
			if (description != null)
				player.setDescription(description);

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

			player.setAddress(address);

			if (req.getParameter("sponsor") != null) {
				String sponsorName = req.getParameter("sponsor");
				if (sponsorRepo.findByName(sponsorName).isPresent()) {
					player.setSponsor(sponsorRepo.findByName(sponsorName).get());
				} else {
					throw new CustomException("Sponsor doesnt exist with given name", HttpStatus.BAD_REQUEST);
				}
			}
		} catch (CustomException e) {
			throw new CustomException(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return player;
	}

	
	public ResponseEntity<Object> updateExistingPlayer(HttpServletRequest req,long id) {
		Player player;
		try {
			player = checkPlayerIsExisting(req,id);
			Player p = playerRepo.save(player);
			return new ResponseEntity<>(p, HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}

}
