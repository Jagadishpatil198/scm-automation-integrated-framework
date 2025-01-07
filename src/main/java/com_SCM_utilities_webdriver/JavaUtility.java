package com_SCM_utilities_webdriver;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class JavaUtility {

	    // Class-level SimpleDateFormat to avoid re-creating it every time
	    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	    /**
	     * Generates a random number between 1 and 5000.
	     * 
	     * @return A random number between 1 and 5000.
	     */
	    public int getRandomNumber() {
	        // Using ThreadLocalRandom for better performance in multi-threaded environments
	        return ThreadLocalRandom.current().nextInt(1, 5001);  // upper bound is exclusive
	    }

	    /**
	     * Gets the current system date in the format yyyy-MM-dd.
	     * 
	     * @return The current system date in yyyy-MM-dd format.
	     */
	    public String getSystemDateYYYYMMDD() {
	        Date dateObj = new Date();
	        return DATE_FORMAT.format(dateObj);
	    }

	    /**
	     * Gets a date that is a specified number of days from the current system date.
	     * 
	     * @param days The number of days to add (positive) or subtract (negative).
	     * @return The date that is the specified number of days from the current date in yyyy-MM-dd format.
	     */
	    public String getRequiredDateYYYYMMDD(int days) {
	        Calendar calendar = Calendar.getInstance();  // Get current calendar instance
	        calendar.add(Calendar.DAY_OF_MONTH, days);   // Add the given number of days to the current date
	        return DATE_FORMAT.format(calendar.getTime());  // Format and return the resulting date
	    }
	}


