package com.project.Json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonSubTypes;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
@JsonSubTypes({ @JsonSubTypes.Type(JsonMarksAndPresence.class) })
public class JsonStudents {

	// Encja Studenci_do_grup_projektowych
	private int id;
	// Encja studenci
	private String firstname;
	private String surname;
	private String index;
	private String mail;

	// Encja Studenci_do_grup_projektowych
	private String finalmark;
	private String position;
	private List<JsonMarksAndPresence> marksandpresence;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getmail() {
		return mail;
	}
	public void setmail(String mail2) {
		this.mail = mail2;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getFinalmark() {
		return finalmark;
	}
	public void setFinalmark(String finalmark) {
		this.finalmark = finalmark;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<JsonMarksAndPresence> getMarksandpresence() {
		return marksandpresence;
	}
	public void setMarksandpresence(List<JsonMarksAndPresence> marksandpresence) {
		this.marksandpresence = marksandpresence;
	}


}
