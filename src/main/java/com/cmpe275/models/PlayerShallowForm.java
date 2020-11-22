package com.cmpe275.models;

import com.cmpe275.entity.Address;

public class PlayerShallowForm {

	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String description;
	private Address address;

	public PlayerShallowForm(Long id, String firstname, String lastName, String email, String description,
			Address address) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastName;
		this.email = email;
		this.description = description;
		this.address = address;
	}

	public PlayerShallowForm() {

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
