package com.project.Json;
import org.codehaus.jackson.annotate.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class JsonMarksAndPresence {

	// Encja obecnosci
	private int meetingid;
	private int presenceid;
	private int present;

	// Encja oceny_czastkowe
	private int markid;
	private String mark;
	public int getMeetingid() {
		return meetingid;
	}
	public void setMeetingid(int meetingid) {
		this.meetingid = meetingid;
	}
	public int getPresenceid() {
		return presenceid;
	}
	public void setPresenceid(int presenceid) {
		this.presenceid = presenceid;
	}
	public int getPresent() {
		return present;
	}
	public void setPresent(int present) {
		this.present = present;
	}
	public int getMarkid() {
		return markid;
	}
	public void setMarkid(int markid) {
		this.markid = markid;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}

	
}
