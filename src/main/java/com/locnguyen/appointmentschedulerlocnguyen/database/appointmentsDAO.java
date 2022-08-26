package com.locnguyen.appointmentschedulerlocnguyen.database;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import com.locnguyen.appointmentschedulerlocnguyen.models.appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class appointmentsDAO {
    //get data from local database and return a list of all appointments stored in there.
    public static ObservableList<appointment> getAllAppointments() {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS;");
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()) {
                int appointmentID = resultSet.getInt("Appointment_ID");
                String appointmentTitle  = resultSet.getString("Title");
                String appointmentDescription = resultSet.getString("Description");
                String appointmentLocation = resultSet.getString("Location");
                String appointmentType = resultSet.getString("Type");
                String appointmentStart = String.valueOf(resultSet.getTimestamp("Start")).substring(0, 19);
                String appointmentEnd = String.valueOf(resultSet.getTimestamp("End")).substring(0, 19);
                int appointmentCustomerID = resultSet.getInt("Customer_ID");
                int appointmentUserID = resultSet.getInt("User_ID");
                int appointmentContactID = resultSet.getInt("Contact_ID");
                Timestamp convertedStart = Timestamp.valueOf(LocalDateTime.parse(appointmentStart, dateTimeFormatter).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).format(dateTimeFormatter));
                Timestamp convertedEnd = Timestamp.valueOf(LocalDateTime.parse(appointmentEnd, dateTimeFormatter).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).format(dateTimeFormatter));
                appointments.add(new appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, convertedStart, convertedEnd, appointmentCustomerID, appointmentUserID, appointmentContactID));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return appointments;
    }

    //insert data for new appointment into local database.
    public static void insertNewAppointment(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, Timestamp apptStart, Timestamp apptEnd, int apptCustomerID, int apptUserID, int apptContactID, String apptCreatedBy, String apptUpdatedBy) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("INSERT INTO APPOINTMENTS (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            sqlStatement.setInt(1, apptID);
            sqlStatement.setString(2, apptTitle);
            sqlStatement.setString(3, apptDescription);
            sqlStatement.setString(4, apptLocation);
            sqlStatement.setString(5, apptType);
            sqlStatement.setTimestamp(6, apptStart);
            sqlStatement.setTimestamp(7, apptEnd);
            sqlStatement.setInt(8, apptCustomerID);
            sqlStatement.setInt(9, apptUserID);
            sqlStatement.setInt(10, apptContactID);
            sqlStatement.setString(11, ZonedDateTime.now(ZoneOffset.UTC).format(dateTimeFormatter));
            sqlStatement.setString(12, apptCreatedBy);
            sqlStatement.setString(13, ZonedDateTime.now(ZoneOffset.UTC).format(dateTimeFormatter));
            sqlStatement.setString(14, apptUpdatedBy);
            sqlStatement.execute();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    //increments new appointment ID by 1 using the current max value of appointment ID in the local database and returns the new appointment ID.
    public static int newAppointmentID(){
        int newID = 1;
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT MAX(Appointment_ID) AS MAX_ID FROM APPOINTMENTS;");
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()) {
                newID = resultSet.getInt("MAX_ID") + 1;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return newID;
    }

    //delete appointment from local database using a particular appointment ID
    public static void deleteAppointmentByID(int apptID) {
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?;");
            sqlStatement.setInt(1, apptID);
            sqlStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    //delete every appointments from local database that is connected to a particular customer
    public static void deleteAppointmentsByCustomerID(int apptCustomerID) {
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("DELETE FROM APPOINTMENTS WHERE Customer_ID = ?;");
            sqlStatement.setInt(1, apptCustomerID);
            sqlStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    //allow modifying and updating appointment data in local database
    public static void modifyAppointment(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, Timestamp apptStart, Timestamp apptEnd,String apptUpdatedBy, int apptContactID) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Contact_ID = ? WHERE Appointment_ID = ?;");
            sqlStatement.setString(1, apptTitle);
            sqlStatement.setString(2, apptDescription);
            sqlStatement.setString(3, apptLocation);
            sqlStatement.setString(4, apptType);
            sqlStatement.setTimestamp(5, apptStart);
            sqlStatement.setTimestamp(6, apptEnd);
            sqlStatement.setString(7, ZonedDateTime.now(ZoneOffset.UTC).format(dateTimeFormatter));
            sqlStatement.setString(8, apptUpdatedBy);
            sqlStatement.setInt(9, apptContactID);
            sqlStatement.setInt(10, apptID);
            sqlStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }



}
