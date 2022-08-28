package com.locnguyen.appointmentschedulerlocnguyen.controller;

import com.locnguyen.appointmentschedulerlocnguyen.Main.Main;
import com.locnguyen.appointmentschedulerlocnguyen.database.appointmentsDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.contactsDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.customersDAO;
import com.locnguyen.appointmentschedulerlocnguyen.models.appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class reportScreenController implements Initializable {

    @FXML
    private TableColumn<appointment, Integer> apptByContactApptID;

    @FXML
    private ComboBox<String> apptByContactComboBox;

    @FXML
    private TableColumn<appointment, Integer> apptByContactContactID;

    @FXML
    private TableColumn<appointment, Integer> apptByContactCustomerID;

    @FXML
    private TableColumn<appointment, String> apptByContactDescription;

    @FXML
    private TableColumn<appointment, String> apptByContactEndDate;

    @FXML
    private Label apptByContactLabel;

    @FXML
    private TableColumn<appointment, String> apptByContactLocation;

    @FXML
    private TableColumn<appointment, String> apptByContactStartDate;

    @FXML
    private TableView<appointment> apptByContactTable;

    @FXML
    private TableColumn<appointment, String> apptByContactTitle;

    @FXML
    private Label apptByContactTotal;

    @FXML
    private Label apptByContactTotalLabel;

    @FXML
    private TableColumn<appointment, String> apptByContactType;

    @FXML
    private TableColumn<appointment, Integer> apptByContactUserID;

    @FXML
    private TableColumn<appointment, Integer> apptByCustomerApptID;

    @FXML
    private ComboBox<String> apptByCustomerComboBox;

    @FXML
    private TableColumn<appointment, Integer> apptByCustomerContactID;

    @FXML
    private TableColumn<appointment, Integer> apptByCustomerCustomerID;

    @FXML
    private TableColumn<appointment, String> apptByCustomerDescription;

    @FXML
    private TableColumn<appointment, String> apptByCustomerEndDate;

    @FXML
    private Label apptByCustomerLabel;

    @FXML
    private TableColumn<appointment, String> apptByCustomerLocation;

    @FXML
    private TableColumn<appointment, String> apptByCustomerStartDate;

    @FXML
    private TableView<appointment> apptByCustomerTable;

    @FXML
    private TableColumn<appointment, String> apptByCustomerTitle;

    @FXML
    private Label apptByCustomerTotal;

    @FXML
    private Label apptByCustomerTotalLabel;

    @FXML
    private TableColumn<appointment, String> apptByCustomerType;

    @FXML
    private TableColumn<appointment, Integer> apptByCustomerUserID;

    @FXML
    private TableColumn<appointment, Integer> apptByMonthApptID;

    @FXML
    private ComboBox<String> apptByMonthComboBox;

    @FXML
    private TableColumn<appointment, Integer> apptByMonthContactID;

    @FXML
    private TableColumn<appointment, Integer> apptByMonthCustomerID;

    @FXML
    private TableColumn<appointment, String> apptByMonthDescription;

    @FXML
    private TableColumn<appointment, String> apptByMonthEndDate;

    @FXML
    private Label apptByMonthLabel;

    @FXML
    private TableColumn<appointment, String> apptByMonthLocation;

    @FXML
    private TableColumn<appointment, String> apptByMonthStartDate;

    @FXML
    private TableView<appointment> apptByMonthTable;

    @FXML
    private TableColumn<appointment, String> apptByMonthTitle;

    @FXML
    private Label apptByMonthTotal;

    @FXML
    private Label apptByMonthTotalLabel;

    @FXML
    private TableColumn<appointment, String> apptByMonthType;

    @FXML
    private TableColumn<appointment, Integer> apptByMonthUserID;

    @FXML
    private TableColumn<appointment, Integer> apptByTypeApptID;

    @FXML
    private ComboBox<String> apptByTypeComboBox;

    @FXML
    private TableColumn<appointment, Integer> apptByTypeContactID;

    @FXML
    private TableColumn<appointment, Integer> apptByTypeCustomerID;

    @FXML
    private TableColumn<appointment, String> apptByTypeDescription;

    @FXML
    private TableColumn<appointment, String> apptByTypeEndDate;

    @FXML
    private Label apptByTypeLabel;

    @FXML
    private TableColumn<appointment, String> apptByTypeLocation;

    @FXML
    private TableColumn<appointment, String> apptByTypeStartDate;

    @FXML
    private TableView<appointment> apptByTypeTable;

    @FXML
    private TableColumn<appointment, String> apptByTypeTitle;

    @FXML
    private Label apptByTypeTotal;

    @FXML
    private Label apptByTypeTotalLabel;

    @FXML
    private TableColumn<appointment, String> apptByTypeType;

    @FXML
    private TableColumn<appointment, Integer> apptByTypeUserID;

    ObservableList<appointment> apptByContactList;
    ObservableList<appointment> apptByCustomerList;
    ObservableList<appointment> apptByMonthList;
    ObservableList<appointment> apptByTypeList;

    // Initialize the report screen and fill all 4 tables with data from local database
    @Override
    public void initialize(URL u, ResourceBundle rb){
        apptByTypeApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptByTypeTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptByTypeDescription.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptByTypeLocation.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptByTypeType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptByTypeStartDate.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptByTypeEndDate.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptByTypeCustomerID.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
        apptByTypeUserID.setCellValueFactory(new PropertyValueFactory<>("apptUserID"));
        apptByTypeContactID.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        apptByMonthApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptByMonthTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptByMonthDescription.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptByMonthLocation.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptByMonthType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptByMonthStartDate.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptByMonthEndDate.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptByMonthCustomerID.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
        apptByMonthUserID.setCellValueFactory(new PropertyValueFactory<>("apptUserID"));
        apptByMonthContactID.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        apptByContactApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptByContactTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptByContactDescription.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptByContactLocation.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptByContactType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptByContactStartDate.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptByContactEndDate.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptByContactCustomerID.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
        apptByContactUserID.setCellValueFactory(new PropertyValueFactory<>("apptUserID"));
        apptByContactContactID.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        apptByCustomerApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptByCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptByCustomerDescription.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptByCustomerLocation.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptByCustomerType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptByCustomerStartDate.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptByCustomerEndDate.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptByCustomerCustomerID.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
        apptByCustomerUserID.setCellValueFactory(new PropertyValueFactory<>("apptUserID"));
        apptByCustomerContactID.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        apptByTypeList = appointmentsDAO.getAllAppointments();
        apptByContactList = appointmentsDAO.getAllAppointments();
        apptByMonthList = appointmentsDAO.getAllAppointments();
        apptByCustomerList = appointmentsDAO.getAllAppointments();
        apptByTypeTable.setItems(apptByTypeList);
        apptByContactTable.setItems(apptByContactList);
        apptByMonthTable.setItems(apptByMonthList);
        apptByCustomerTable.setItems(apptByCustomerList);
        apptByTypeComboBox.setItems(appointmentsDAO.getAllAppointmentTypes());
        apptByMonthComboBox.getItems().addAll("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
        apptByContactComboBox.setItems(contactsDAO.getAllCntsName());
        apptByCustomerComboBox.setItems(customersDAO.getAllCustomerNames());
        apptByTypeTotal.setText(String.valueOf(apptByTypeList.size()));
        apptByMonthTotal.setText(String.valueOf(apptByMonthList.size()));
        apptByContactTotal.setText(String.valueOf(apptByContactList.size()));
        apptByCustomerTotal.setText(String.valueOf(apptByCustomerList.size()));
    }

    //handle button click to go back to the main screen
    @FXML
    public void handleBackButton(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Go back to the main menu");
        alert.setHeaderText("Do you wish to go back to the main menu?");
        alert.setContentText("Click OK to go back to the main menu or click CANCEL to stay on the report screen.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/mainMenu.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException error) {
                error.printStackTrace();
            }
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();;
        }
    }

    //group appointment by type when user select an option from the type drop-down list
    public void groupApptByType(){
        apptByTypeList = appointmentsDAO.getAppointmentsByType(apptByTypeComboBox.getSelectionModel().getSelectedItem());
        apptByTypeTable.setItems(apptByTypeList);
        apptByTypeTotal.setText(String.valueOf(apptByTypeList.size()));
    }

    //group appointment by month when user select an option from the month drop-down list
    public void groupApptByMonth(){
        apptByMonthList = appointmentsDAO.getAppointmentsByMonth(apptByMonthComboBox.getSelectionModel().getSelectedItem());
        apptByMonthTable.setItems(apptByMonthList);
        apptByMonthTotal.setText(String.valueOf(apptByMonthList.size()));
    }

    //group appointment by contact when user select an option from the contact drop-down list
    public void groupApptByContact(){
        apptByContactList = appointmentsDAO.getAppointmentsByContactID(contactsDAO.getCntIDByCntName(apptByContactComboBox.getSelectionModel().getSelectedItem()));
        apptByContactTable.setItems(apptByContactList);
        apptByContactTotal.setText(String.valueOf(apptByContactList.size()));
    }

    //group appointment by customer when user select an option from the customer drop-down list
    public void groupApptByCustomer(){
        apptByCustomerList = appointmentsDAO.getAppointmentsByCustomerID(customersDAO.getCustomerIDByCustomerName(apptByCustomerComboBox.getSelectionModel().getSelectedItem()));
        apptByCustomerTable.setItems(apptByCustomerList);
        apptByCustomerTotal.setText(String.valueOf(apptByCustomerList.size()));
    }


}

