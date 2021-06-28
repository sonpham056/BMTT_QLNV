package app.bus.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.github.lgooddatepicker.components.DatePicker;

import app.constant.SystemConstants;

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
	
	public static void checkAgeValid(DatePicker dpDateOfBirth) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = df.parse(dpDateOfBirth.getComponentDateTextField().getText());
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
		int userChooseYear = Integer.parseInt(df2.format(date));
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if (currentYear - userChooseYear <= SystemConstants.PERMITTEDAGE) {
			throw new Exception("You are not old enough to use this application!\n Your age must be bigger than " + SystemConstants.PERMITTEDAGE);
		}
		
	}
}
