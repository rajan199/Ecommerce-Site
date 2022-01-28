package com.application.Infibeam.response;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) throws ParseException {
		
		// TODO Auto-generated method stub

//		  String passwd = "aaaa"; 
//	      String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
//	      System.out.println(passwd.matches(pattern));
	      
	      Pattern pattern = Pattern.compile("^\\d{10}$");
	      Matcher matcher = pattern.matcher("2055550asda5");
	      
	      System.out.println(matcher.matches());
	}

}
