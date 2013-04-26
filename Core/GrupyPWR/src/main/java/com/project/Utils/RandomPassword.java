package com.project.Utils;

import java.util.UUID;

public class RandomPassword {

	public static String Random() {

		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

}
