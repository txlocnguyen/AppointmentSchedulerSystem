package com.locnguyen.appointmentschedulerlocnguyen.database;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import com.locnguyen.appointmentschedulerlocnguyen.models.customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
/***
 * Public class customersDAO
 * @author Loc Nguyen
 */
public class customersDAO {
    /***
     * Replace a customer data in the local database with newly updated data
     * @param customerID
     * @param customerName
     * @param customerPhone
     * @param customerAddress
     * @param customerPostalCode
     * @param customerDivisionID
     * @param customerUpdatedBy
     */
    public static void modifyingCustomer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionID, String customerUpdatedBy) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?");
            sqlStatement.setString(1, customerName);
            sqlStatement.setString(2, customerAddress);
            sqlStatement.setString(3, customerPostalCode);
            sqlStatement.setString(4, customerPhone);
            sqlStatement.setString(5, ZonedDateTime.now(ZoneOffset.UTC).format(dateTimeFormatter));
            sqlStatement.setString(6, customerUpdatedBy);
            sqlStatement.setInt(7, customerDivisionID);
            sqlStatement.setInt(8, customerID);
            sqlStatement.executeUpdate();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    /***
     * Increments new customer ID by 1 using the current max value of customer ID in the local database and returns the new customer ID.
     * @return new customer ID
     */
    public static int getNewCustomerID() {
        int newCustomerID = 1;
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT MAX(Customer_ID) FROM customers");
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                newCustomerID = resultSet.getInt(1) + 1;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return newCustomerID;
    }
    /***
     * Lookup a list of all customers currently in the local database and return their data
     * @return list of all customers currently in the local database
     */
    public static ObservableList<customers> getAllCustomers() {
        ObservableList<customers> allCust = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM customers");
            ResultSet resultSet = sqlStatement.executeQuery();

            while (resultSet.next()) {
                allCust.add(new customers(resultSet.getInt("Customer_ID"), resultSet.getString("Customer_Name"), resultSet.getString("Address"), resultSet.getString("Postal_Code"), resultSet.getString("Phone"), resultSet.getInt("Division_ID")));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return allCust;
    }
    /***
     * Insert data for a new customer into the local database
     * @param customerName
     * @param customerID
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhone
     * @param customerDivisionID
     * @param customerCreatedBy
     * @param customerUpdatedBy
     */
    public static void insertNewCustomer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionID, String customerCreatedBy, String customerUpdatedBy) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try{
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sqlStatement.setInt(1, customerID);
            sqlStatement.setString(2, customerName);
            sqlStatement.setString(3, customerAddress);
            sqlStatement.setString(4, customerPostalCode);
            sqlStatement.setString(5, customerPhone);
            sqlStatement.setString(6, ZonedDateTime.now(ZoneOffset.UTC).format(dateTimeFormatter));
            sqlStatement.setString(7, customerCreatedBy);
            sqlStatement.setString(8, ZonedDateTime.now(ZoneOffset.UTC).format(dateTimeFormatter));
            sqlStatement.setString(9, customerUpdatedBy);
            sqlStatement.setInt(10, customerDivisionID);
            sqlStatement.executeUpdate();
        } catch(SQLException error){
            error.printStackTrace();
        }
    }
    /***
     * Remove a customer data from the local database using a particular customer ID
     * @param customerID
     */
    public static void removeCustomer(int customerID) {
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("DELETE FROM customers WHERE Customer_ID = ?");
            sqlStatement.setInt(1, customerID);
            sqlStatement.executeUpdate();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    /***
     * Lookup a customer ID in the local database using a particular customer name
     * @param name
     * @return customer ID
     */
    public static int getCustomerIDByCustomerName(String name){
        int customerID = 0;
        try{
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Customer_ID FROM customers WHERE Customer_Name = ?");
            sqlStatement.setString(1, name);
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()){
                customerID = resultSet.getInt("Customer_ID");
            }
        } catch(Exception error){
            error.printStackTrace();
        }
        return customerID;
    }
    /***
     * Lookup a list of all customers' name currently in the local database
     * @return list of all customers' name currently in the local database
     */
    public static ObservableList<String> getAllCustomerNames() {
        ObservableList<String> allCustomerNames = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Customer_Name FROM customers");
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                allCustomerNames.add(resultSet.getString("Customer_Name"));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return allCustomerNames;
    }
}
