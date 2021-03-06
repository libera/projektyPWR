package com.project.Json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonSubTypes;

import com.project.Json.JsonGrupy;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
@JsonSubTypes({ @JsonSubTypes.Type(JsonGrupy.class) })
public class JsonKursy {
	private List<JsonGrupy> courses;

	public List<JsonGrupy> getCourses() {
		return courses;
	}

	public void setCourses(List<JsonGrupy> courses) {
		this.courses = courses;
	}
}
