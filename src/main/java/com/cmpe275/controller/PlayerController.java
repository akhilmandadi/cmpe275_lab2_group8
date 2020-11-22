package com.cmpe275.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.cmpe275.service.PlayerService;

@Controller
@RequestMapping(value = "/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@PostMapping
	public ResponseEntity<Object> createPlayer(HttpServletRequest req) {
		return playerService.createNewPlayer(req);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPlayer(HttpServletRequest request, @PathVariable("id") long id) {
		return playerService.getPlayerById((long) id);
	}
    
   @DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePlayer(HttpServletRequest request, @PathVariable("id") long id) {
			return playerService.deletePlayerById((long) id);
    }
}
