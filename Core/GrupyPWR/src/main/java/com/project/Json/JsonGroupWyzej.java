package com.project.Json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class JsonGroupWyzej {

	private List<JsonGroupZajeciowe> dates;

	public List<JsonGroupZajeciowe> getDates() {
		return dates;
	}

	public void setDates(List<JsonGroupZajeciowe> dates) {
		this.dates = dates;
	}
	
	
}
