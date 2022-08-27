package com.locnguyen.appointmentschedulerlocnguyen.database;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class countriesDAO {
    //lookup country name within the local database using a particular country ID
    public static String getCtryNameByCtryID(int ctryID) {
        String ctryName = "";
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Country FROM countries WHERE Country_ID = " + ctryID);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                ctryName = resultSet.getString("Country");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return ctryName;
    }

    //lookup country ID within the local database using a particular country name
    public static int getCtryIDByCtryName(String ctryName) {
        int ctryID = 0;
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Country_ID FROM countries WHERE Country = '" + ctryName + "'");
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                ctryID = resultSet.getInt("Country_ID");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return ctryID;
    }

    //lookup a list of all country names that is currently inside the local database
    public static ObservableList<String> getAllCtryNames() {
        ObservableList<String> allCtry = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Country FROM countries");
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                allCtry.add(resultSet.getString("Country"));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return allCtry;
    }
}
