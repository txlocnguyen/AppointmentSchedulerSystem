package com.locnguyen.appointmentschedulerlocnguyen.database;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/***
 * Public class contactsDAO
 * @author Loc Nguyen
 */
public class contactsDAO {
    /***
     * Lookup contact ID in the local database using a particular contact name
     * @param cntName
     * @return contact ID
     */
    public static int getCntIDByCntName(String cntName) {
        int cntID = 0;
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Contact_ID FROM contacts WHERE Contact_Name = ?");
            sqlStatement.setString(1, cntName);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                cntID = resultSet.getInt("Contact_ID");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return cntID;
    }
    /***
     * Lookup contact name in the local database using a particular contact ID
     * @param cntID
     * @return contact name
     */
    public static String getCntNameByCntID(int cntID) {
        String cntName = "";
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Contact_Name FROM contacts WHERE Contact_ID = ?");
            sqlStatement.setInt(1, cntID);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                cntName = resultSet.getString("Contact_Name");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return cntName;
    }
    /***
     * Lookup a list of all contact names currently in the local database
     * @return list of contact names
     */
    public static ObservableList<String> getAllCntsName() {
        ObservableList<String> allContacts = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Contact_Name FROM contacts");
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                allContacts.add(resultSet.getString("Contact_Name"));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return allContacts;
    }
}
