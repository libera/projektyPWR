package com.project.Utils;

import java.util.Random;

public class RandomPassword {
	
	public static String Random() {
		String haslo;
		
		Random rand = new Random();
		long numNoRange = rand.nextLong();
		numNoRange = (numNoRange + 100000) & 0xff;
		haslo = Long.toString(numNoRange);
		return haslo;
	}

}
