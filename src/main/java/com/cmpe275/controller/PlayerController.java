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

	@PostMapping
	public ResponseEntity<Object> createPlayer(HttpServletRequest req) {
		return playerService.createNewPlayer(req);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updatePlayer(HttpServletRequest req,@PathVariable("id") long id) {
		return playerService.updateExistingPlayer(req,(long)id);
	}

}
