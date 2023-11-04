package com.projects.PassGuard.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHandler {


    public static String timeNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();  // Get the current date and time
        // Format the date and time as a MySQL-accepted string
        String mysqlFormattedDate = sdf.format(currentDate);
        return mysqlFormattedDate;
    }


}
