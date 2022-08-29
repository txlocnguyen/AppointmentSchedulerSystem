package com.locnguyen.appointmentschedulerlocnguyen.database;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/***
 * Public class firstLevelDivisionsDAO
 * @author Loc Nguyen
 */
public class firstLevelDivisionsDAO {
    /***
     * Lookup division ID in the local database using a particular division name
     * @param divName
     * @return division ID
     */
    public static int getDivIDByDivName(String divName){
        int divID = 0;
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Division_ID FROM first_level_divisions WHERE Division = ?");
            sqlStatement.setString(1, divName);
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()){
                divID = resultSet.getInt("Division_ID");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return divID;
    }
    /***
     * Lookup division name in the local database using a particular division ID
     * @param divID
     * @return division name
     */
    public static String getDivNameByDivID(int divID){
        String divName = "";
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE Division_ID = ?");
            sqlStatement.setInt(1, divID);
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()){
                divName = resultSet.getString("Division");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return divName;
   }
    /***
     * Lookup division country ID in the local database using a particular division name
     * @param divName
     * @return division country ID
     */
    public static int getDivCountryIDByDivName(String divName){
        int divCountryID = 0;
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Country_ID FROM first_level_divisions WHERE Division = ?");
            sqlStatement.setString(1, divName);
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()) {
                divCountryID = resultSet.getInt("Country_ID");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return divCountryID;
    }
    /***
     * Lookup a list of division name in the local database using a particular country ID
     * @param divCountryID
     * @return list of division names
     */
    public static ObservableList<String> getDivNameByDivCountryID(int divCountryID){
        ObservableList<String> divName = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE Country_ID = ?");
            sqlStatement.setInt(1, divCountryID);
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()){
                divName.add(resultSet.getString("Division"));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return divName;
    }
    /***
     * Lookup a list contains all division names currently in the local database
     * @return list of division names
     */
    public static ObservableList<String> getAllDivNames(){
        ObservableList<String> divName = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Division FROM first_level_divisions");
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()){
                divName.add(resultSet.getString("Division"));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return divName;
    }
}
