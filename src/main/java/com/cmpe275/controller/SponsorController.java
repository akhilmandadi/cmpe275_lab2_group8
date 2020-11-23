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

//	API route for createsponsor service
	@PostMapping
	public ResponseEntity<Object> createSponsor(HttpServletRequest request) {
		return SponsorService.createNewSponsor(request);
	}
	
//	API route for get sponsor service
	@GetMapping("/{id}")
	public ResponseEntity<Object> getSponsor(HttpServletRequest request, @PathVariable("id") long id) {
		return SponsorService.getSponsorById((long) id);
	}

//	API route for delete sponsor service
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSponsor(@PathVariable("id") long id) {
		return SponsorService.deleteSponsorById((long) id);
	}
    
//    API route for update sponsor service
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSponsor(HttpServletRequest request, @PathVariable("id") long id) {
		return SponsorService.updateSponsorById(request, (long) id);
	}
}
