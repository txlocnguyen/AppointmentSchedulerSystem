package com.locnguyen.appointmentschedulerlocnguyen.controller;

import com.locnguyen.appointmentschedulerlocnguyen.Main.Main;
import com.locnguyen.appointmentschedulerlocnguyen.database.appointmentsDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.contactsDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.usersDAO;
import com.locnguyen.appointmentschedulerlocnguyen.models.customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
/***
 * Public controller class appointmentAddingController
 * @author Loc Nguyen
 */
public class appointmentAddingController implements Initializable {

    @FXML
    private Label addApptMainLabel;

    @FXML
    private Label apptDateLabel;

    @FXML
    private DatePicker apptDatePicker;

    @FXML
    private ComboBox<String> apptEndTimeHourComboBox;

    @FXML
    private ComboBox<String> apptEndTimeMinuteComboBox;

    @FXML
    private Label apptIDLabel;

    @FXML
    private TextField apptIDTxtField;

    @FXML
    private ComboBox<String> apptStartTimeHourComboBox;

    @FXML
    private ComboBox<String> apptStartTimeMinuteComboBox;

    @FXML
    private Button cancelBttn;

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private Label contactLabel;

    @FXML
    private Label customerIDLabel;

    @FXML
    private TextField customerIDTxtField;

    @FXML
    private TextField descTxtField;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label endTimeLabel;
    @FXML
    private TextField locationTxtField;

    @FXML
    private Label locationLabel;

    @FXML
    private Button saveBttn;

    @FXML
    private Label startTimeLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField titleTxtField;

    @FXML
    private TextField typeTxtField;

    @FXML
    private Label typeLabel;

    @FXML
    private Label userIDLabel;

    @FXML
    private TextField userIDTxtField;
    /***
     * Initilize method and fill out the combo boxes with the appropriate values. Prevent date picker from selecting past dates and weekends.
     * @param u
     * @param rb
     * Lambda expression is used to prevent the user using the date picker to select past dates and weekends.
     */
    @Override
    public void initialize(URL u, ResourceBundle rb){
        apptDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0 || date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY);
            }
        });
        apptStartTimeHourComboBox.getItems().addAll("05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22");
        apptStartTimeMinuteComboBox.getItems().addAll("00", "15", "30", "45");
        apptEndTimeHourComboBox.getItems().addAll("05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22");
        apptEndTimeMinuteComboBox.getItems().addAll("00", "15", "30", "45");
        userIDTxtField.setText(String.valueOf(usersDAO.getUserCurrentlyInSession().getUserId()));
        contactComboBox.setItems(contactsDAO.getAllCntsName());
    }
    /***
     * Fill out the form with customer ID information that the user selected when choosing to add appointment
     * @param cust
     */
    public void fillForm(customers cust){
        customerIDTxtField.setText(String.valueOf(cust.getCustomerID()));
    }
    /***
     * Handle button click for the cancel button and return to main menu
     * @param e
     */
    public void cancelClicked(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Adding Appointment");
        alert.setHeaderText("Cancel adding appointment?");
        alert.setContentText("Are you sure you want to cancel adding an appointment and go back to main screen ?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/mainMenu.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }
    /***
     * Handle checking for overlap appointments when scheduling a new appointment
     * @param start
     * @param end
     * @param apptID
     * @throws SQLException
     * @return true if there is an overlap, return false if there's no overlap
     */
    public Boolean checkForOverlapAppts(LocalDateTime start, LocalDateTime end, int apptID) throws SQLException {
        return appointmentsDAO.checkForConflict(apptID, start, end);
    }
    /***
     * Handle checking for out of business hours when scheduling a new appointment
     * @param start
     * @param end
     * @param apptDate
     * @return true if the appointment is out of business hours, return false if it's not.
     */
    public Boolean checkForOOBHAppts(LocalDateTime start, LocalDateTime end, LocalDate apptDate) {
        ZonedDateTime apptStartTimeConverted = ZonedDateTime.of(start, usersDAO.getUserCurrentTimeZone());
        ZonedDateTime apptEndTimeConverted = ZonedDateTime.of(end, usersDAO.getUserCurrentTimeZone());
        ZonedDateTime officeHoursStart = ZonedDateTime.of(apptDate, LocalTime.of(8,0),ZoneId.of("America/New_York"));
        ZonedDateTime officeHoursEnd = ZonedDateTime.of(apptDate, LocalTime.of(22,0),ZoneId.of("America/New_York"));
        return (apptStartTimeConverted.isBefore(officeHoursStart) || apptEndTimeConverted.isAfter(officeHoursEnd) || apptStartTimeConverted.isAfter(officeHoursEnd) || apptEndTimeConverted.isBefore(officeHoursStart) || apptEndTimeConverted.isBefore(apptStartTimeConverted));
    }
    /***
     * Handle button click for the save button and add the appointment to the database
     * @param e
     * @throws SQLException
     */
    public void saveClicked(ActionEvent e) throws SQLException {
        try{
            int apptID = appointmentsDAO.newAppointmentID();
            String apptTitle = titleTxtField.getText();
            String apptDescription = descTxtField.getText();
            String apptLocation = locationTxtField.getText();
            String apptType = typeTxtField.getText();
            int custID = Integer.parseInt(customerIDTxtField.getText());
            int userID = Integer.parseInt(userIDTxtField.getText());
            int contactID = contactsDAO.getCntIDByCntName(String.valueOf(contactComboBox.getSelectionModel().getSelectedItem()));
            String apptCreatedBy = usersDAO.getUserCurrentlyInSession().getUserName();
            if(apptTitle.isBlank() || apptDescription.isBlank() || apptLocation.isBlank() || apptType.isBlank() || contactComboBox.getValue() == null || apptDatePicker.getValue() == null || apptStartTimeHourComboBox.getValue() == null || apptEndTimeHourComboBox.getValue() == null || apptStartTimeMinuteComboBox.getValue() == null || apptEndTimeMinuteComboBox.getValue() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Adding Appointment");
                alert.setHeaderText("Error Adding Appointment");
                alert.setContentText("Please make sure all fields are filled out. Please try again");
                alert.showAndWait();
            } else{
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalDate apptDate = apptDatePicker.getValue();
                LocalDateTime apptStartLocal = LocalDateTime.of(apptDate, LocalTime.from(LocalTime.parse(apptStartTimeHourComboBox.getSelectionModel().getSelectedItem() + ":" + apptStartTimeMinuteComboBox.getSelectionModel().getSelectedItem(), dateTimeFormatter)));
                LocalDateTime apptEndLocal = LocalDateTime.of(apptDate, LocalTime.from(LocalTime.parse(apptEndTimeHourComboBox.getSelectionModel().getSelectedItem() + ":" + apptEndTimeMinuteComboBox.getSelectionModel().getSelectedItem(), dateTimeFormatter)));
                Timestamp apptStartConverted = Timestamp.valueOf(apptStartLocal.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());
                Timestamp apptEndConverted = Timestamp.valueOf(apptEndLocal.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());
                Timestamp apptStartTime = Timestamp.valueOf(apptDate + " " + apptStartTimeHourComboBox.getSelectionModel().getSelectedItem() + ":" + apptStartTimeMinuteComboBox.getSelectionModel().getSelectedItem() + ":00");
                Timestamp apptEndTime = Timestamp.valueOf(apptDate + " " + apptEndTimeHourComboBox.getSelectionModel().getSelectedItem() + ":" + apptEndTimeMinuteComboBox.getSelectionModel().getSelectedItem() + ":00");
                Timestamp startSQL = Timestamp.valueOf(LocalDateTime.of(apptDate, LocalTime.parse(apptStartTimeHourComboBox.getSelectionModel().getSelectedItem() + ":" + apptStartTimeMinuteComboBox.getSelectionModel().getSelectedItem())).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());
                Timestamp endSQL = Timestamp.valueOf(LocalDateTime.of(apptDate, LocalTime.parse(apptEndTimeHourComboBox.getSelectionModel().getSelectedItem() + ":" + apptEndTimeMinuteComboBox.getSelectionModel().getSelectedItem())).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());
                if(apptStartTime.after(apptEndTime)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Adding Appointment");
                    alert.setHeaderText("Error Adding Appointment");
                    alert.setContentText("Please make sure the start time is before the end time. Please try again");
                    alert.showAndWait();
                }
                else if(checkForOverlapAppts(apptStartConverted.toLocalDateTime(), apptEndConverted.toLocalDateTime(), apptID)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Adding Appointment");
                    alert.setHeaderText("Error Adding Appointment");
                    alert.setContentText("There is an overlap with another appointment. Please try again");
                    alert.showAndWait();
                }
                else if(checkForOOBHAppts(apptStartLocal, apptEndLocal, apptDate)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Adding Appointment");
                    alert.setHeaderText("Error Adding Appointment");
                    alert.setContentText("The appointment is out of business hours (8:00 AM to 10:00 PM EST). Please try again");
                    alert.showAndWait();
                }
                else{
                    appointmentsDAO.insertNewAppointment(apptID, apptTitle, apptDescription, apptLocation, apptType, startSQL, endSQL, custID, userID, contactID, apptCreatedBy, apptCreatedBy);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Added Successfully");
                    alert.setHeaderText("Appointment Added Successfully");
                    alert.setContentText("Appointment has been added.");
                    alert.showAndWait();
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/mainMenu.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } catch(Exception error){
            error.printStackTrace();
        }
    }


}
