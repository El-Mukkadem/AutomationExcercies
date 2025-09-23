package com.automationExercises.utils.logs;

public class timeManager {

    //For screenshots - logs - reports
    public static String getTimeStamp(){
        return new java.text.SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new java.util.Date());

    }
    //for unique data
    public static String getSimpleTimeStamp(){
        return Long.toString(System.currentTimeMillis());
    }
}
