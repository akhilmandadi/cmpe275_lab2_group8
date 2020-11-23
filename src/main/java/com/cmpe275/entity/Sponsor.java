package com.cmpe275.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Sponsor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	private String name;
	private String description;
	private Address address;

	@OneToMany(mappedBy = "sponsor")
	private List<Player> players;

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
		this.name = name.trim();
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
