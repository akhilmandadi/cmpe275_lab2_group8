package com.cmpe275.models;

import com.cmpe275.entity.Address;
import com.cmpe275.entity.Sponsor;
import lombok.AllArgsConstructor;
import net.bytebuddy.dynamic.scaffold.MethodGraph;

import java.util.LinkedList;
import java.util.List;

public class SponsorDeepForm {
	private int id;
	private String name;
	private String description;
	private Address address;
	private List<PlayerShallowForm> players;

	public SponsorDeepForm() {

	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<PlayerShallowForm> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerShallowForm> players) {
		this.players = players;
	}
}