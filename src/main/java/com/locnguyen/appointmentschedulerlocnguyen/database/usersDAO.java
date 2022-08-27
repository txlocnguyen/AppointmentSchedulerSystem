package com.locnguyen.appointmentschedulerlocnguyen.database;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import com.locnguyen.appointmentschedulerlocnguyen.models.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;

public class usersDAO {
    private static users usrCurrentlyInSession;
    private static Locale usrLocale;
    private static ZoneId usrCurrentTimeZone;

    //handle login into the system using the combo of username and password
    public static boolean handleLogin(String usr, String pw){
        try{
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM users WHERE User_Name='" + usr + "' AND Password='" + pw + "'");
            ResultSet resultSet = sqlStatement.executeQuery();
            if(!resultSet.next()){
                return false;
            }
            else{
                usrCurrentlyInSession = new users(resultSet.getInt("User_ID"), resultSet.getString("User_Name"));
                usrLocale = Locale.getDefault();
                usrCurrentTimeZone = ZoneId.systemDefault();
                return true;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return false;
    }

    public static users getUserCurrentlyInSession(){
        return usrCurrentlyInSession;
    }
    public static Locale getUserLocale(){
        return usrLocale;
    }
    public static ZoneId getUserCurrentTimeZone(){
        return usrCurrentTimeZone;
    }
}
