package com.project.Utils;

import java.util.Random;

public class RandomPassword {
	
	public static String Random() {
		String haslo;
		 Random rand = new Random(); 
		 
		  
		   rand.nextLong();
		   haslo = rand.toString();
		   
		   return haslo;
		
	}

}
