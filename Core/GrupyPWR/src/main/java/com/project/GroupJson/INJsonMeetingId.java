package com.project.GroupJson;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonSubTypes;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSubTypes({ @JsonSubTypes.Type(INJsonMarksAndPresence.class) })
public class INJsonMeetingId {

	private int meetingid;
	
	private Date date;
	
	private List<INJsonMarksAndPresence>marksandpresence;

	public int getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(int meetingid) {
		this.meetingid = meetingid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<INJsonMarksAndPresence> getMarksandpresence() {
		return marksandpresence;
	}

	public void setMarksandpresence(List<INJsonMarksAndPresence> marksandpresence) {
		this.marksandpresence = marksandpresence;
	}

}
