package com_SCM_utilities_listeners;



import java.util.Date;

public class CaptureTimeHelper {

    /**
     * Returns the current time in a formatted string with underscores instead of spaces and colons.
     * This method formats the current date and time into a readable format suitable for file naming or logging.
     * 
     * @return A string representing the formatted current time.
     */
    public String getCurrentTime() {
        // Get the current date and time
        String formattedTime = new Date().toString()
            .replace(" ", "_")   // Replace spaces with underscores
            .replace(":", "_");  // Replace colons with underscores to make it file-system safe
        
        return formattedTime;
    }
}
