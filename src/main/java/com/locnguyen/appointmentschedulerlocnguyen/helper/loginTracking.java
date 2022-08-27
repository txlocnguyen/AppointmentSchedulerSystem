package com.locnguyen.appointmentschedulerlocnguyen.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class loginTracking {
    private static final String loginTrackingFile = "login_activity.txt";
    //track login attempts and log them to a file in the root folder of the project
    public static void loginTracking(boolean matchingUsrAndPw, String usrName){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(loginTrackingFile, true));
            String logLine;
            if(matchingUsrAndPw){
                logLine = "SUCCESS! Username: " + usrName + " successfully login into the system at: " + ZonedDateTime.now(ZoneOffset.UTC) + " UTC\n";
            }
            else{
                logLine = "FAILURE! Username: " + usrName + " failed to login into the system at: " + ZonedDateTime.now(ZoneOffset.UTC) + " UTC\n";
            }
            writer.append(logLine);
            writer.flush();
            writer.close();
        } catch(IOException error){
            error.printStackTrace();
        }
    }
}
