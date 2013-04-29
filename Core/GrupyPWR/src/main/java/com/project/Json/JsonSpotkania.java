package com.project.Json;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class JsonSpotkania {

	private int id;
	private String date;
	private String name;
	private int weight;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
