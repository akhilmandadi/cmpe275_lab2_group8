package com.cmpe275.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cmpe275.entity.Player;
import com.cmpe275.service.PlayerService;

@Controller
@RequestMapping(value = "/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

//	API route for creating a player
	@PostMapping
	public ResponseEntity<Object> createPlayer(HttpServletRequest req) {
		return playerService.createNewPlayer(req);
	}
//API route for update player
	@PutMapping("/{id}")
	public ResponseEntity<Object> updatePlayer(HttpServletRequest req, @PathVariable("id") long id) {
		return playerService.updateExistingPlayer(req, (long) id);
	}

//	API route for get player
	@GetMapping("/{id}")
	public ResponseEntity<Object> getPlayer(HttpServletRequest request, @PathVariable("id") long id) {
		return playerService.getPlayerById((long) id);
	}

//	API route for delete player
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePlayer(HttpServletRequest request, @PathVariable("id") long id) {
		return playerService.deletePlayerById((long) id);
	}
}
