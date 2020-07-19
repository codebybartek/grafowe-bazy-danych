package com.neo4j.contest;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = "Player")
public class Player {

	@Id
	@GeneratedValue
	private Long id;

	@Property(name = "name")
	private String name;
	private int age;
	private double record;
	private int number;
	private int position;
	
	public Player() {
	}

	public Player(String name, int age, double record, int number, int position) {
		this.name = name;
		this.age = age;
		this.record = record;
		this.number = number;
		this.position = position;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void increasePos() {
		this.position++;
	}

	public void decreasePos() {
		this.position--;
	}

	public int getAge() { return age; }

	public double getRecord() { return record; }

	public int getNumber() { return number; }

	public int getPosition() { return position; }

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", position= " + position + ", record:" + record +  ", number:" + number + ", age:" + age + "]";
	}
}
