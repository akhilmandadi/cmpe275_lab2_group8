package com.cmpe275.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.cmpe275.service.SponsorService;

@Controller
@RequestMapping(value = "/sponsor")
public class SponsorController {

	@Autowired
	private SponsorService SponsorService;

	@PostMapping
	public ResponseEntity<Object> createSponsor(HttpServletRequest req) {
		return SponsorService.createNewSponsor(req);
	}
	
	@GetMapping
	public ResponseEntity<Object> getSponsor(HttpServletRequest req) {
		return SponsorService.getSponsor(req);
	}


}
