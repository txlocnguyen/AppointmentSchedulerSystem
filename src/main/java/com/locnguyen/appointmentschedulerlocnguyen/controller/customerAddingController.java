package com.locnguyen.appointmentschedulerlocnguyen.controller;

import com.locnguyen.appointmentschedulerlocnguyen.Main.Main;
import com.locnguyen.appointmentschedulerlocnguyen.database.countriesDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.customersDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.firstLevelDivisionsDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.usersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerAddingController implements Initializable {

    @FXML
    private Label addCustomerMainLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressTxtField;

    @FXML
    private Button cancelBttn;

    @FXML
    private Label customerIDLabel;

    @FXML
    private TextField customerIDTxtField;

    @FXML
    private ComboBox<String> locationCountryComboBox;

    @FXML
    private ComboBox<String> locationDivisionComboBox;

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTxtField;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField phoneTxtField;

    @FXML
    private Label postalCodeLabel;

    @FXML
    private TextField postalCodeTxtField;

    @FXML
    private Button saveBttn;

    //initialize method and fill out dropdown menus with country and first level divisions from local database
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
           locationCountryComboBox.setItems(countriesDAO.getAllCtryNames());
           locationDivisionComboBox.setItems(firstLevelDivisionsDAO.getDivNameByDivCountryID(countriesDAO.getCtryIDByCtryName(locationCountryComboBox.getSelectionModel().getSelectedItem())));
    }

    //handle button click for cancel button and return to main menu
    public void cancelClicked(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Adding Customer");
        alert.setHeaderText("Cancel adding customer?");
        alert.setContentText("Are you sure you want to cancel adding a customer and go back to main screen ?");
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

    //handle filling the dropdown menu for first level divisions when country is selected
    public void countrySelected() {
        locationDivisionComboBox.setItems(firstLevelDivisionsDAO.getDivNameByDivCountryID(countriesDAO.getCtryIDByCtryName(locationCountryComboBox.getSelectionModel().getSelectedItem())));
    }

    //handle button click for save button and add customer to local database
    @FXML
    public void saveClicked(ActionEvent e) {
        try {
            int custID = customersDAO.getNewCustomerID();
            String custName = nameTxtField.getText();
            String custAddress = addressTxtField.getText();
            String custPostalCode = postalCodeTxtField.getText();
            String custPhone = phoneTxtField.getText();
            String custCreatedBy = usersDAO.getUserCurrentlyInSession().getUserName();
            int custDivisionID = firstLevelDivisionsDAO.getDivIDByDivName(locationDivisionComboBox.getSelectionModel().getSelectedItem());
            if (custName.isBlank() || custAddress.isBlank() || custPostalCode.isBlank() || custPhone.isBlank() || locationCountryComboBox.getValue() == null || locationDivisionComboBox.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Adding Customer");
                alert.setHeaderText("Error Adding Customer");
                alert.setContentText("Please make sure all fields are filled out");
                alert.showAndWait();
            } else {
                customersDAO.insertNewCustomer(custID, custName, custAddress, custPostalCode, custPhone, custDivisionID, custCreatedBy, custCreatedBy);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Adding Customer");
                alert.setHeaderText("Success Adding Customer");
                alert.setContentText("Customer added successfully");
                alert.showAndWait();
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

        } catch (Exception error) {
            error.printStackTrace();
        }
    }


}

