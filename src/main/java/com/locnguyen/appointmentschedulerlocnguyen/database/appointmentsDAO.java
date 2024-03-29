package com.locnguyen.appointmentschedulerlocnguyen.database;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import com.locnguyen.appointmentschedulerlocnguyen.models.appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/***
 * Public class appointmentsDAO
 * @author Loc Nguyen
 */
public class appointmentsDAO {
    /***
     * Lookup for any appointments in the local database that are coming up in the next 15 minutes UTC time
     * @return appointments
     */
    public static ObservableList<appointment> upComingAppointment15Minutes() {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime futureTimeAfter15Minutes = currentTime.plusMinutes(15);
        int currentUserID = usersDAO.getUserCurrentlyInSession().getUserId();

        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS WHERE Start >= ? AND Start <= ? AND User_ID = ?;");
            sqlStatement.setTimestamp(1, Timestamp.valueOf(currentTime));
            sqlStatement.setTimestamp(2, Timestamp.valueOf(futureTimeAfter15Minutes));
            sqlStatement.setInt(3, currentUserID);
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()){
                int apptID = resultSet.getInt("Appointment_ID");
                String apptTitle = resultSet.getString("Title");
                String apptDescription = resultSet.getString("Description");
                String apptLocation = resultSet.getString("Location");
                String apptType = resultSet.getString("Type");
                LocalDateTime apptStart = Timestamp.valueOf(LocalDateTime.parse(String.valueOf(resultSet.getTimestamp("Start")).substring(0, 19), dateTimeFormatter).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).format(dateTimeFormatter)).toLocalDateTime();
                LocalDateTime apptEnd = Timestamp.valueOf(LocalDateTime.parse(String.valueOf(resultSet.getTimestamp("End")).substring(0, 19), dateTimeFormatter).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).format(dateTimeFormatter)).toLocalDateTime();
                int apptCustomerID = resultSet.getInt("Customer_ID");
                int apptUserID = resultSet.getInt("User_ID");
                int apptContactID = resultSet.getInt("Contact_ID");
                appointments.add(new appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, apptStart, apptEnd, apptCustomerID, apptUserID, apptContactID));

            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return appointments;
    }
    /***
     * Look for all appointments in the local database within 1 week from now
     * @return appointments
     */
    public static ObservableList<appointment> getAppointmentsByWeek() {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = ZonedDateTime.now(usersDAO.getUserCurrentTimeZone()).withZoneSameInstant(ZoneOffset.UTC).format(dateTimeFormatter);
        String futureTimeIn1Week = ZonedDateTime.now(usersDAO.getUserCurrentTimeZone()).plusWeeks(1).withZoneSameInstant(ZoneOffset.UTC).format(dateTimeFormatter);
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS AS appt LEFT JOIN CUSTOMERS AS cust ON appt.Customer_ID = cust.Customer_ID WHERE Start >= ? AND Start <= ?;");
            sqlStatement.setString(1, currentTime);
            sqlStatement.setString(2, futureTimeIn1Week);
            ResultSet resultSet = sqlStatement.executeQuery();
            while(resultSet.next()){
                int apptID = resultSet.getInt("Appointment_ID");
                String apptTitle = resultSet.getString("Title");
                String apptDescription = resultSet.getString("Description");
                String apptLocation = resultSet.getString("Location");
                String apptType = resultSet.getString("Type");
                LocalDateTime apptStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int apptCustomerID = resultSet.getInt("Customer_ID");
                int apptUserID = resultSet.getInt("User_ID");
                int apptContactID = resultSet.getInt("Contact_ID");
                appointments.add(new appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, apptStart, apptEnd, apptCustomerID, apptUserID, apptContactID));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return appointments;
    }
    /***
     * Increments new appointment ID by 1 using the current max value of appointment ID in the local database and returns the new appointment ID.
     * @return newID
     */
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
    /***
     * Delete appointment from local database using a particular appointment ID
     * @param apptID
     */
    public static void deleteAppointmentByID(int apptID) {
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?;");
            sqlStatement.setInt(1, apptID);
            sqlStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
    /***
     * Check for any conflict or duplicate appointments in the local database within a particular timeframe using appointment ID
     * @param apptID
     * @return boolean
     * @throws SQLException
     * @param start
     * @param end
     */
    public static boolean checkForConflict(int apptID, LocalDateTime start, LocalDateTime end) throws SQLException {
        PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS WHERE Appointment_ID != ? AND (? BETWEEN Start AND End OR ? BETWEEN Start AND End OR ? > End AND ? < Start);");
        sqlStatement.setInt(1, apptID);
        sqlStatement.setTimestamp(2, Timestamp.valueOf(start));
        sqlStatement.setTimestamp(3, Timestamp.valueOf(end));
        sqlStatement.setTimestamp(4, Timestamp.valueOf(end));
        sqlStatement.setTimestamp(5, Timestamp.valueOf(start));
        ResultSet resultSet = sqlStatement.executeQuery();
        return resultSet.next();
    }
    /***
     * Look for all appointments in the local database using a particular type
     * @param type
     * @return appointments
     */
    public static ObservableList<appointment> getAppointmentsByType(String type) {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS WHERE Type = ?;");
            sqlStatement.setString(1, type);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                int apptID = resultSet.getInt("Appointment_ID");
                String apptTitle = resultSet.getString("Title");
                String apptDescription = resultSet.getString("Description");
                String apptLocation = resultSet.getString("Location");
                String apptType = resultSet.getString("Type");
                LocalDateTime apptStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int apptCustomerID = resultSet.getInt("Customer_ID");
                int apptUserID = resultSet.getInt("User_ID");
                int apptContactID = resultSet.getInt("Contact_ID");
                appointments.add(new appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, apptStart, apptEnd, apptCustomerID, apptUserID, apptContactID));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return appointments;
    }
    /***
     * Lookup a list of all types of appointments in the local database
     * @return a list of all types of appointments
     */
    public static ObservableList<String> getAllAppointmentTypes() {
        ObservableList<String> allTypes = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT Type FROM APPOINTMENTS;");
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                String type = resultSet.getString("Type");
                allTypes.add(type);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return allTypes;
    }
    /***
     * Delete all appointments from local database that are connected to a particular customer
     * @param apptCustomerID
     */
    public static void deleteAppointmentsByCustomerID(int apptCustomerID) {
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("DELETE FROM APPOINTMENTS WHERE Customer_ID = ?;");
            sqlStatement.setInt(1, apptCustomerID);
            sqlStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
    /***
     * Look for all appointments in the local database using a particular customer ID
     * @param customerID
     * @return appointments
     */
    public static ObservableList<appointment> getAppointmentsByCustomerID(int customerID) {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS WHERE Customer_ID = ?;");
            sqlStatement.setInt(1, customerID);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                int apptID = resultSet.getInt("Appointment_ID");
                String apptTitle = resultSet.getString("Title");
                String apptDescription = resultSet.getString("Description");
                String apptLocation = resultSet.getString("Location");
                String apptType = resultSet.getString("Type");
                LocalDateTime apptStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int apptCustomerID = resultSet.getInt("Customer_ID");
                int apptUserID = resultSet.getInt("User_ID");
                int apptContactID = resultSet.getInt("Contact_ID");
                appointments.add(new appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, apptStart, apptEnd, apptCustomerID, apptUserID, apptContactID));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return appointments;
    }
    /***
     * Allow modifying and updating appointment data in local database
     * @param apptID
     * @param apptTitle
     * @param apptDescription
     * @param apptLocation
     * @param apptType
     * @param apptStart
     * @param apptEnd
     * @param apptUpdatedBy
     * @param apptContactID
     */
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
    /***
     * Insert data for new appointment into local database
     * @param apptTitle
     * @param apptID
     * @param apptDescription
     * @param apptLocation
     * @param apptType
     * @param apptStart
     * @param apptEnd
     * @param apptCreatedBy
     * @param apptContactID
     * @param apptCustomerID
     * @param apptUserID
     */
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
    /***
     * Look for all appointments in the local database using a particular contact ID
     * @param contactID
     * @return appointments
     */
    public static ObservableList<appointment> getAppointmentsByContactID(int contactID) {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS WHERE Contact_ID = ?;");
            sqlStatement.setInt(1, contactID);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                int apptID = resultSet.getInt("Appointment_ID");
                String apptTitle = resultSet.getString("Title");
                String apptDescription = resultSet.getString("Description");
                String apptLocation = resultSet.getString("Location");
                String apptType = resultSet.getString("Type");
                LocalDateTime apptStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int apptCustomerID = resultSet.getInt("Customer_ID");
                int apptUserID = resultSet.getInt("User_ID");
                int apptContactID = resultSet.getInt("Contact_ID");
                appointments.add(new appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, apptStart, apptEnd, apptCustomerID, apptUserID, apptContactID));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return appointments;
    }
    /***
     * Look for all appointments in the local database using a particular month of the year
     * @param month
     * @return appointments
     */
    public static ObservableList<appointment> getAppointmentsByMonth(String month) {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        try {
            PreparedStatement sqlStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS WHERE MONTHNAME(Start) = ?;");
            sqlStatement.setString(1, month);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                int apptID = resultSet.getInt("Appointment_ID");
                String apptTitle = resultSet.getString("Title");
                String apptDescription = resultSet.getString("Description");
                String apptLocation = resultSet.getString("Location");
                String apptType = resultSet.getString("Type");
                LocalDateTime apptStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int apptCustomerID = resultSet.getInt("Customer_ID");
                int apptUserID = resultSet.getInt("User_ID");
                int apptContactID = resultSet.getInt("Contact_ID");
                appointments.add(new appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, apptStart, apptEnd, apptCustomerID, apptUserID, apptContactID));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return appointments;
    }
    /***
     * Get data from local database and return a list of all appointments stored in there.
     * @return appointments
     */
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
                LocalDateTime convertedStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime convertedEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerID = resultSet.getInt("Customer_ID");
                int appointmentUserID = resultSet.getInt("User_ID");
                int appointmentContactID = resultSet.getInt("Contact_ID");
                appointments.add(new appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, convertedStart, convertedEnd, appointmentCustomerID, appointmentUserID, appointmentContactID));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return appointments;
    }


}
