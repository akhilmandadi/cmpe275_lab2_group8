package com.cmpe275.models;

import com.cmpe275.entity.Address;

public class SponsorShallowForm {
	private long id;
	private String name;
	private String description;
	private Address address;

	public SponsorShallowForm() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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