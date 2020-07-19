package com.neo4j.contest;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Contest")
public class Contest {

	@Id
	@GeneratedValue
	private Long id;

	@Property(name = "name")
	private String name;
	private String date;
	private String hour;
	private String place;

	public Contest() {
	}

	public Contest(String name, String date, String hour, String place) {
		this.name = name;
		this.date = date;
		this.hour = hour;
		this.place = place;
	}

	public Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getHour() { return hour;}

	public String getPlace() { return place;}

	@Relationship(type = "Contest_Player")
	private Set<Player> players = new HashSet<>();

	public void addTeam(Player player) {
		players.add(player);
	}

	@Override
	public String toString() {
		return "Contest [id=" + id + ", name=" + name + ", date=" + date + ", hour=" + hour + ", place=" + place + ", players=" + players +"]";
	}
}
