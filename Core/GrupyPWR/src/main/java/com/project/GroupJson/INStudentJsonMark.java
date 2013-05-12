package com.project.GroupJson;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonSubTypes;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSubTypes({ @JsonSubTypes.Type(INStudentJsonMarksAndPresence.class) })
public class INStudentJsonMark {
	private List<INStudentJsonMarksAndPresence> marksandpresence;

	public List<INStudentJsonMarksAndPresence> getMarksandpresence() {
		return marksandpresence;
	}

	public void setMarksandpresence(
			List<INStudentJsonMarksAndPresence> marksandpresence) {
		this.marksandpresence = marksandpresence;
	}
}
