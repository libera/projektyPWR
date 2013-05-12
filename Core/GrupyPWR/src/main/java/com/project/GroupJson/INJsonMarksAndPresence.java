package com.project.GroupJson;

import org.codehaus.jackson.annotate.JsonAutoDetect;


@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class INJsonMarksAndPresence {
	
	
	//Encja obecnosc_czastkowe
	private int studentid;
	private int presenceid;
	
	//Encja oceny
	private int markid;

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
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
