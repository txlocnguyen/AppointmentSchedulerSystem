package com.locnguyen.appointmentschedulerlocnguyen.controller;

import com.locnguyen.appointmentschedulerlocnguyen.Main.Main;
import com.locnguyen.appointmentschedulerlocnguyen.database.appointmentsDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.customersDAO;
import com.locnguyen.appointmentschedulerlocnguyen.models.appointment;
import com.locnguyen.appointmentschedulerlocnguyen.models.customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {

    @FXML
    private Button addApptBttn;

    @FXML
    private Button addCustomerBttn;

    @FXML
    private Label apptLabel;

    @FXML
    private TableColumn<appointment, Integer> apptTableApptID;

    @FXML
    private TableColumn<appointment, Integer> apptTableContactID;

    @FXML
    private TableColumn<appointment, Integer> apptTableCustomerID;

    @FXML
    private TableColumn<appointment, String> apptTableDescription;

    @FXML
    private TableColumn<appointment, String> apptTableEndDate;

    @FXML
    private TableColumn<appointment, String> apptTableLocation;

    @FXML
    private TableColumn<appointment, String> apptTableStartDate;

    @FXML
    private TableColumn<appointment, String> apptTableTitle;

    @FXML
    private TableColumn<appointment, String> apptTableType;

    @FXML
    private TableColumn<appointment, Integer> apptTableUserID;

    @FXML
    private TableView<appointment> apptTableView;

    @FXML
    private Label customerLabel;

    @FXML
    private TableColumn<customers, String> customerTableAddress;

    @FXML
    private TableColumn<customers, Integer> customerTableDivisionID;

    @FXML
    private TableColumn<customers, Integer> customerTableID;

    @FXML
    private TableColumn<customers, String> customerTableName;

    @FXML
    private TableColumn<customers, String> customerTablePhoneNo;

    @FXML
    private TableColumn<customers, String> customerTablePostalCode;

    @FXML
    private TableView<customers> customerTableView;

    @FXML
    private Button deleteApptBttn;

    @FXML
    private Button deleteCustomerBttn;

    @FXML
    private Button logOutBttn;

    @FXML
    private AnchorPane mainTitleLabel;

    @FXML
    private Button modApptBttn;

    @FXML
    private Button modCustomerBttn;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private RadioButton radioBttnAll;

    @FXML
    private RadioButton radioBttnMonth;

    @FXML
    private RadioButton radioBttnWeek;

    @FXML
    private Button reportsBttn;

    @FXML
    private ToggleGroup viewApptGroup;

    ObservableList<appointment> apptList;
    ObservableList<customers> custList;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("Languages", Locale.getDefault());
    //initialize method, fill both tables with data pulled from local database
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptTableApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptTableTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptTableDescription.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptTableLocation.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptTableType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptTableStartDate.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptTableEndDate.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptTableCustomerID.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
        apptTableUserID.setCellValueFactory(new PropertyValueFactory<>("apptUserID"));
        apptTableContactID.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        customerTableID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerTableName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTableAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerTablePostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerTablePhoneNo.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customerTableDivisionID.setCellValueFactory(new PropertyValueFactory<>("customerDivisionID"));
        apptList = appointmentsDAO.getAllAppointments();
        apptTableView.setItems(apptList);
        custList = customersDAO.getAllCustomers();
        customerTableView.setItems(custList);
    }

    //handle button click event for opening reports screen
    public void reportsClicked(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/reportScreen.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //handle button click event for logging out
    public void logoutClicked(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString("logOutConfirm"));
        alert.setHeaderText(resourceBundle.getString("logOutConfirm"));
        alert.setContentText(resourceBundle.getString("logOutMsg"));
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/loginScreen.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException error) {
                error.printStackTrace();
            }
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    //handle button click event for editing appointment
    public void modApptClicked() throws IOException{
        appointment appt = apptTableView.getSelectionModel().getSelectedItem();
        if(appt != null){
            Stage stage = (Stage) modApptBttn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/appointmentModding.fxml"));
            Parent root = loader.load();
            appointmentModdingController appointmentModdingController = loader.getController();
            appointmentModdingController.fillForm(appt);
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("noApptSelected"));
            alert.setHeaderText(resourceBundle.getString("noApptSelected"));
            alert.setContentText(resourceBundle.getString("noApptSelectedMod"));
            alert.showAndWait();
        }
    }

    //handle button click event for adding appointment
    public void addApptClicked() throws IOException{
        customers cust = customerTableView.getSelectionModel().getSelectedItem();
        if(cust != null){
            Stage stage = (Stage) addApptBttn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/appointmentAdding.fxml"));
            Parent root = loader.load();
            appointmentAddingController appointmentAddingController = loader.getController();
            appointmentAddingController.fillForm(cust);
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("noCustSelected"));
            alert.setHeaderText(resourceBundle.getString("noCustSelected"));
            alert.setContentText(resourceBundle.getString("noCustSelectedAdd"));
            alert.showAndWait();
        }
    }

    //handle clicking radio button to view appointments by month
    public void viewMonthClicked(){
        monthComboBox.setVisible(true);
        monthComboBox.setValue("");
        monthComboBox.getItems().addAll("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
    }

    //handle button click event for adding customer
    public void addCustomerClicked(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/customerAdding.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //handle clicking radio button to view all appointments
    public void viewAllClicked(){
        monthComboBox.setVisible(false);
        apptList = appointmentsDAO.getAllAppointments();
        apptTableView.setItems(apptList);
    }

    //handle grouping appointments by month after select a particular month in the drop-down combo box
    public void groupApptByMonth(){
        apptList = appointmentsDAO.getAppointmentsByMonth(monthComboBox.getSelectionModel().getSelectedItem());
        apptTableView.setItems(apptList);
    }

    //handle button click event for deleting appointment
    public void deleteApptClicked(ActionEvent e){
        appointment appt = apptTableView.getSelectionModel().getSelectedItem();
        if(appt != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(resourceBundle.getString("apptDelConfirm"));
            alert.setHeaderText(resourceBundle.getString("apptDelConfirm"));
            alert.setContentText(resourceBundle.getString("apptDelMsg") + appt.getApptID());
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                appointmentsDAO.deleteAppointmentByID(appt.getApptID());
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/mainMenu.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException error) {
                    error.printStackTrace();
                }
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("noApptSelected"));
            alert.setHeaderText(resourceBundle.getString("noApptSelected"));
            alert.setContentText(resourceBundle.getString("noApptSelectedDel"));
            alert.showAndWait();
        }
    }

    //handle clicking radio button to view appointments by week
    public void viewWeekClicked(){
        monthComboBox.setVisible(false);
        groupApptByWeek();
    }

    //handle button click event for deleting customer
    public void deleteCustomerClicked(ActionEvent e){
        customers cust = customerTableView.getSelectionModel().getSelectedItem();
        if(cust != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(resourceBundle.getString("custDelConfirm"));
            alert.setHeaderText(resourceBundle.getString("custDelConfirm"));
            alert.setContentText(resourceBundle.getString("custDelMsg") + cust.getCustomerID());
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                appointmentsDAO.deleteAppointmentsByCustomerID(cust.getCustomerID());
                customersDAO.removeCustomer(cust.getCustomerID());
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/mainMenu.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException error) {
                    error.printStackTrace();
                }
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("noCustSelected"));
            alert.setHeaderText(resourceBundle.getString("noCustSelected"));
            alert.setContentText(resourceBundle.getString("noCustSelectedDel"));
            alert.showAndWait();
        }
    }


    //handle button click event for editing customer
    public void modCustomerClicked() throws IOException{
        customers cust = customerTableView.getSelectionModel().getSelectedItem();
        if(cust != null){
            Stage stage = (Stage) modCustomerBttn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/customerModding.fxml"));
            Parent root = loader.load();
            customerModdingController customerModdingController = loader.getController();
            customerModdingController.fillForm(cust);
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("noCustSelected"));
            alert.setHeaderText(resourceBundle.getString("noCustSelected"));
            alert.setContentText(resourceBundle.getString("noCustSelectedMod"));
            alert.showAndWait();
        }
    }

    //handle grouping appointments by week after clicking radio button to view appointments by week
    public void groupApptByWeek(){
        monthComboBox.setVisible(false);
        apptList = appointmentsDAO.getAppointmentsByWeek();
        apptTableView.setItems(apptList);
    }
}

