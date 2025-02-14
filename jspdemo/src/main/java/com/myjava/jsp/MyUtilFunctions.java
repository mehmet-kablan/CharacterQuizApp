package com.myjava.jsp;

import java.util.ArrayList;
import java.util.List;

public class MyUtilFunctions{
	
	
	
	public static boolean isNumeric(String str) {
		  return str.matches(".*\\d.*");  //match a number with optional '-' and decimal.
		}
	
	
	public static String CompareEpisodes(String str1, String str2) {
		
		char[] str1Char = str1.toCharArray();
		char[] str2Char = str2.toCharArray();
		List<Character> str1Ints = new ArrayList<Character>();
		List<Character> str2Ints = new ArrayList<Character>();
		for(char CharREF : str1Char) {
			
			if(Character.isDigit(CharREF)) {
				str1Ints.add(CharREF);  
			}
			
		}
		for(char CharREF : str2Char) {
			
			if(Character.isDigit(CharREF)) {
				str2Ints.add(CharREF);  
			}
			
		}
		
		int result1 = 0;
        for (char ch : str1Ints) {
            result1 = result1 * 10 + (ch - '0'); 
        }
		
        int result2 = 0;
        for (char ch : str2Ints) {
            result2 = result2 * 10 + (ch - '0'); 
        }
		
		if(result1 > result2) {return "Greater";}
		else if(result1 < result2 ) {return "Lesser";}
		else {return "Equal";}
	}
	
	
	
	
	
	
	
	
	
	
	
}