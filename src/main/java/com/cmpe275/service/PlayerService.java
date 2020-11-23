package com.cmpe275.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmpe275.Exception.CustomException;
import com.cmpe275.entity.Address;
import com.cmpe275.entity.Player;
import com.cmpe275.entity.Sponsor;
import com.cmpe275.models.AddressModel;
import com.cmpe275.models.PlayerDeepForm;
import com.cmpe275.models.PlayerShallowForm;
import com.cmpe275.models.SponsorDeepForm;
import com.cmpe275.models.SponsorShallowForm;
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
			return new ResponseEntity<>(convertPlayerObjectToDeepForm(p), HttpStatus.OK);
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
				Long sponsorId = Long.parseLong(req.getParameter("sponsor"));
				if (sponsorRepo.getById(sponsorId).isPresent()) {
					player.setSponsor(sponsorRepo.getById(sponsorId).get());
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

	public PlayerDeepForm convertPlayerObjectToDeepForm(Player player) {
		PlayerDeepForm playerDeepForm = new PlayerDeepForm();
		playerDeepForm.setId(player.getId());
		playerDeepForm.setEmail(player.getEmail());
		playerDeepForm.setFirstname(player.getFirstname());
		playerDeepForm.setLastname(player.getLastname());
		AddressModel address = new AddressModel();
		if (player.getAddress() != null) {
			if (player.getAddress().getStreet() != null)
				address.setStreet(player.getAddress().getStreet());
			if (player.getAddress().getCity() != null)
				address.setCity(player.getAddress().getCity());
			if (player.getAddress().getState() != null)
				address.setState(player.getAddress().getState());
			if (player.getAddress().getZip() != null)
				address.setZip(player.getAddress().getZip());
		}
		playerDeepForm.setAddress(address);
		playerDeepForm.setDescription(player.getDescription());
		if (player.getSponsor() != null) {
			SponsorShallowForm sponsorShallowForm = new SponsorShallowForm();
			sponsorShallowForm.setId(player.getSponsor().getId());
			sponsorShallowForm.setName(player.getSponsor().getName());
			sponsorShallowForm.setDescription(player.getSponsor().getDescription());

			AddressModel sponsorAddress = new AddressModel();
			if (player.getSponsor().getAddress() != null) {
				if (player.getSponsor().getAddress().getStreet() != null)
					sponsorAddress.setStreet(player.getSponsor().getAddress().getStreet());
				if (player.getSponsor().getAddress().getCity() != null)
					sponsorAddress.setCity(player.getSponsor().getAddress().getCity());
				if (player.getSponsor().getAddress().getState() != null)
					sponsorAddress.setState(player.getSponsor().getAddress().getState());
				if (player.getSponsor().getAddress().getZip() != null)
					sponsorAddress.setZip(player.getSponsor().getAddress().getZip());
			}
			sponsorShallowForm.setAddress(sponsorAddress);

			playerDeepForm.setSponsor(sponsorShallowForm);
		}
		if (player.getOpponents() != null) {
			List<PlayerShallowForm> playerList = new ArrayList<PlayerShallowForm>();
			player.getOpponents().forEach((p) -> {
				
				AddressModel opponentAddress = new AddressModel();
				if (p.getAddress() != null) {
					if (p.getAddress().getStreet() != null)
						opponentAddress.setStreet(p.getAddress().getStreet());
					if (p.getAddress().getCity() != null)
						opponentAddress.setCity(p.getAddress().getCity());
					if (p.getAddress().getState() != null)
						opponentAddress.setState(p.getAddress().getState());
					if (p.getAddress().getZip() != null)
						opponentAddress.setZip(p.getAddress().getZip());
				}
				
				PlayerShallowForm playerShallowForm = new PlayerShallowForm(p.getId(), p.getFirstname(),
						p.getLastname(), p.getEmail(), p.getDescription(), opponentAddress);
				playerList.add(playerShallowForm);
			});
			playerDeepForm.setOpponents(playerList);
		}
		return playerDeepForm;
	}

	public ResponseEntity<Object> getPlayerById(Long playerId) {
		try {
			if (playerId == null)
				throw new CustomException("Player Id is Invalid", HttpStatus.BAD_REQUEST);
			Optional<Player> player = playerRepo.findById(playerId);
			if (!player.isPresent()) {
				return new ResponseEntity<>("Player Id Invalid", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(convertPlayerObjectToDeepForm(player.get()), HttpStatus.OK);
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Object> deletePlayerById(Long playerId) {
		try {
			if (playerId == null)
				throw new CustomException("sponsorId  is Invalid", HttpStatus.BAD_REQUEST);
			Optional<Player> player = playerRepo.findById(playerId);
			if (!player.isPresent()) {
				return new ResponseEntity<>("sponsor id is Invalid", HttpStatus.BAD_REQUEST);
			} else {
				PlayerDeepForm temp = convertPlayerObjectToDeepForm(player.get());
				if (player.get().getOpponents() != null) {
					player.get().getOpponents().forEach((p) -> {
						p.getOpponents().remove(player.get());
						playerRepo.save(p);
					});
				}
				if (player.get().getSponsor() != null && player.get().getSponsor().getPlayers() != null) {
					player.get().getSponsor().getPlayers().remove(player.get());
					sponsorRepo.save(player.get().getSponsor());
				}
				playerRepo.deleteById(playerId);
				return new ResponseEntity<>(temp, HttpStatus.OK);
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}
}
