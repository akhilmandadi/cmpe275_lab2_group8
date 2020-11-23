package com.cmpe275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cmpe275.service.OpponentService;

@Controller
@RequestMapping(value = "/opponents")
public class OpponentController {

	@Autowired
	private OpponentService opponentService;
    
//	add Opponent API Route
	@PutMapping("/{id1}/{id2}")
	public ResponseEntity<Object> addOpponent(@PathVariable("id1") long id1, @PathVariable("id2") long id2) {
		return opponentService.addOpponent((long) id1, (long)id2);
	}
	
//	Delete Opponent API Route
	@DeleteMapping("/{id1}/{id2}")
	public ResponseEntity<Object> deleteOpponent(@PathVariable("id1") long id1, @PathVariable("id2") long id2) {
		return opponentService.deleteOpponent((long) id1, (long)id2);
	}


}
