package com.cmpe275.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String firstname;
	private String lastname;
	private String email;
	private String description;
	private Address address;
	
	@ManyToOne
	(fetch = FetchType.LAZY)
	private Sponsor sponsor;

	@ManyToMany
	private List<Player> opponents;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname.trim();
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public List<Player> getOpponents() {
		return opponents;
	}

	public void setOpponents(List<Player> opponents) {
		this.opponents = opponents;
	}

}
