package com.project.Json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonSubTypes;

@JsonSubTypes({ @JsonSubTypes.Type(JsonStudents.class),
		@JsonSubTypes.Type(JsonSpotkania.class),
		@JsonSubTypes.Type(JsonNotes.class) })
public class JsonGrupyProjektowe {
	private int id;
	private String name;
	private String subject;
	private String repo;
	private List<JsonStudents> students;
	private List<JsonSpotkania> meetings;
	private List<JsonNotes> notes;
	public int getId() {
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRepo() {
		return repo;
	}
	public void setRepo(String repo) {
		this.repo = repo;
	}
	public List<JsonStudents> getStudents() {
		return students;
	}
	public void setStudents(List<JsonStudents> students) {
		this.students = students;
	}
	public List<JsonSpotkania> getMeetings() {
		return meetings;
	}
	public void setMeetings(List<JsonSpotkania> meetings) {
		this.meetings = meetings;
	}
	public List<JsonNotes> getNotes() {
		return notes;
	}
	public void setNotes(List<JsonNotes> notes) {
		this.notes = notes;
	}
}
