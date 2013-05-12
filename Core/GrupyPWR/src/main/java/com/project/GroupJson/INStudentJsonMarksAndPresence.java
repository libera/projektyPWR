package com.project.GroupJson;

public class INStudentJsonMarksAndPresence {

	
	//Encja spotkania
	private int meetingid;
	
	//Encja obecnosc_czastkowe
	private int presenceid;
	
	//Encja oceny 
	private int markid;

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

	public int getMarkid() {
		return markid;
	}

	public void setMarkid(int markid) {
		this.markid = markid;
	}
}
