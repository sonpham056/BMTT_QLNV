package app.bus.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidateCheck {
	public static void checkEmail(String email) throws Exception {
		 String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	     if (!email.matches(regex)) {
	    	 throw new Exception("Wrong email type!");
	     }
	}
	
	@SuppressWarnings("unused")
	public static void checkDateValid(String date) throws ParseException   {
		 SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
		 Date check = sdfrmt.parse(date);
	}
}
