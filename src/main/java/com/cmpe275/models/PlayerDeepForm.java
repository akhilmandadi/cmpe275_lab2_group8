package com.cmpe275.models;

import java.util.List;

import com.cmpe275.entity.Address;

public class PlayerDeepForm {

	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String description;
	private AddressModel address;
	private SponsorShallowForm sponsor;
	private List<PlayerShallowForm> opponents;
	
	public PlayerDeepForm() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public SponsorShallowForm getSponsor() {
		return sponsor;
	}

	public void setSponsor(SponsorShallowForm sponsor) {
		this.sponsor = sponsor;
	}

	public List<PlayerShallowForm> getOpponents() {
		return opponents;
	}

	public void setOpponents(List<PlayerShallowForm> opponents) {
		this.opponents = opponents;
	}

}
