package com.project.Json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonSubTypes;

@JsonSubTypes({ @JsonSubTypes.Type(JsonGrupyProjektowe.class),
		@JsonSubTypes.Type(JsonNieWGrupie.class) })
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class JsonGroupZajeciowe {

	private int id;
	private String code;
	private String name;
	List<JsonGrupyProjektowe> groups;
	List<JsonNieWGrupie> notingroup;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JsonGrupyProjektowe> getGroups() {
		return groups;
	}

	public void setGroups(List<JsonGrupyProjektowe> groups) {
		this.groups = groups;
	}

	public List<JsonNieWGrupie> getNotingroup() {
		return notingroup;
	}

	public void setNotingroup(List<JsonNieWGrupie> notingroup) {
		this.notingroup = notingroup;
	}
}
