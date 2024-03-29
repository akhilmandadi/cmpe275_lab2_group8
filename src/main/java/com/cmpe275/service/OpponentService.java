package com.cmpe275.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmpe275.Exception.CustomException;
import com.cmpe275.entity.Player;
import com.cmpe275.repo.PlayerRepo;

@Service
public class OpponentService {

	/*
	 * Add Opponent implementation makes the two players with the given IDs opponents with each other
	 */
	@Autowired
	private PlayerRepo playerRepo;

	public ResponseEntity<Object> addOpponent(Long id1, Long id2) {
		try {
			if (id1 == id2) {
				throw new CustomException("Player ids same", HttpStatus.BAD_REQUEST);
			}

			Optional<Player> player1 = playerRepo.getById(id1);
			if (player1.isEmpty()) {
				throw new CustomException("First Player id Invalid", HttpStatus.NOT_FOUND);
			}

			Optional<Player> player2 = playerRepo.getById(id2);
			if (player2.isEmpty()) {
				throw new CustomException("Second Player id Invalid", HttpStatus.NOT_FOUND);
			}

			Player p1 = player1.get();
			Player p2 = player2.get();

			List<Player> opponents1 = player1.get().getOpponents();
			if (opponents1.contains(p2)) {
				throw new CustomException("Players are already opponents", HttpStatus.BAD_REQUEST);
			}

			p1.getOpponents().add(p2);
			p2.getOpponents().add(p1);
			playerRepo.save(p1);
			playerRepo.save(p2);
			
			return new ResponseEntity<>("Opponents Created", HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 *  delete opponent removes the opponent relation between the two players
	 */
	public ResponseEntity<Object> deleteOpponent(Long id1, Long id2) {
		try {
			if (id1 == id2) {
				throw new CustomException("Player ids same", HttpStatus.BAD_REQUEST);
			}

			Optional<Player> player1 = playerRepo.getById(id1);
			if (player1.isEmpty()) {
				throw new CustomException("First Player id Invalid", HttpStatus.NOT_FOUND);
			}

			Optional<Player> player2 = playerRepo.getById(id2);
			if (player2.isEmpty()) {
				throw new CustomException("Second Player id Invalid", HttpStatus.NOT_FOUND);
			}

			Player p1 = player1.get();
			Player p2 = player2.get();

			List<Player> opponents1 = player1.get().getOpponents();
			if (!opponents1.contains(p2)) {
				throw new CustomException("Players are not opponents", HttpStatus.BAD_REQUEST);
			}

			p1.getOpponents().remove(p2);
			p2.getOpponents().remove(p1);
			playerRepo.save(p1);
			playerRepo.save(p2);
			
			return new ResponseEntity<>("Opponents Removed", HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}

}
