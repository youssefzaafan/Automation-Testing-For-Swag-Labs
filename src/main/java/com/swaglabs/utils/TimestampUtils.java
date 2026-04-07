package com.swaglabs.utils;


import java.text.SimpleDateFormat;
import java.util.Date;


public class TimestampUtils {
    //For generating unique data each run
    public static String getCurrentTimestamp() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return dateFormat.format(currentDate);
    }

}
