package com.locnguyen.appointmentschedulerlocnguyen.database;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/***
 * Public class countriesDAO
 * @author Loc Nguyen
 */
public class countriesDAO {
    /***
     * Lookup country name within the local database using a particular country ID
     * @param ctryID
     * @return country name
     */
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
    /***
     * Lookup country ID within the local database using a particular country name
     * @param ctryName
     * @return country ID
     */
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
    /***
     * Lookup a list of all country names that is currently inside the local database
     * @return country names
     */
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
