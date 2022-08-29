package com.locnguyen.appointmentschedulerlocnguyen.controller;

import com.locnguyen.appointmentschedulerlocnguyen.Main.Main;
import com.locnguyen.appointmentschedulerlocnguyen.database.countriesDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.customersDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.firstLevelDivisionsDAO;
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
import java.util.Optional;
import java.util.ResourceBundle;
/***
 * Public controller class customerModdingController.
 * @author Loc Nguyen
 */
public class customerModdingController implements Initializable {

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
    private Label editCustomerMainLabel;

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
    /***
     * Initialize method and fill the dropdown menus with data from the database.
     * @param u
     * @param rb
     */
    @Override
    public void initialize(URL u, ResourceBundle rb) {
        locationCountryComboBox.setItems(countriesDAO.getAllCtryNames());
        locationDivisionComboBox.setItems(firstLevelDivisionsDAO.getDivNameByDivCountryID(countriesDAO.getCtryIDByCtryName(locationCountryComboBox.getSelectionModel().getSelectedItem())));
    }
    /***
     * Handles filling the form with data of the selected customer.
     * @param cust
     */
    public void fillForm(customers cust){
        customerIDTxtField.setText(String.valueOf(cust.getCustomerID()));
        nameTxtField.setText(cust.getCustomerName());
        addressTxtField.setText(cust.getCustomerAddress());
        postalCodeTxtField.setText(cust.getCustomerPostalCode());
        phoneTxtField.setText(cust.getCustomerPhone());
        locationDivisionComboBox.setValue(firstLevelDivisionsDAO.getDivNameByDivID(cust.getCustomerDivisionID()));
        locationCountryComboBox.setValue(countriesDAO.getCtryNameByCtryID(firstLevelDivisionsDAO.getDivCountryIDByDivName(locationDivisionComboBox.getSelectionModel().getSelectedItem())));
        locationDivisionComboBox.setItems(firstLevelDivisionsDAO.getDivNameByDivCountryID(countriesDAO.getCtryIDByCtryName(locationCountryComboBox.getSelectionModel().getSelectedItem())));
    }
    /***
     * Handle filling the dropdown menu for first level divisions when country is selected.
     */
    public void countrySelected() {
        locationDivisionComboBox.setValue(null);
        locationDivisionComboBox.setItems(firstLevelDivisionsDAO.getDivNameByDivCountryID(countriesDAO.getCtryIDByCtryName(locationCountryComboBox.getSelectionModel().getSelectedItem())));
    }
    /***
     * Handle button click for cancel button and return to main menu.
     * @param e
     */
    public void cancelClicked(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Modifying Customer");
        alert.setHeaderText("Cancel Modifying Customer?");
        alert.setContentText("Are you sure you want to cancel modifying this customer and go back to main screen ?");
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
     * Handle button click for save button and save the changes of the modified customer to the database.
     * @param e
     */
    public void saveClicked(ActionEvent e) {
        try {
            int custID = Integer.parseInt(customerIDTxtField.getText());
            String custName = nameTxtField.getText();
            String custAddress = addressTxtField.getText();
            String custPostalCode = postalCodeTxtField.getText();
            String custPhone = phoneTxtField.getText();
            int custDivisionID = firstLevelDivisionsDAO.getDivIDByDivName(locationDivisionComboBox.getSelectionModel().getSelectedItem());
            String custUpdatedBy = usersDAO.getUserCurrentlyInSession().getUserName();
            if(custName.isBlank() || custAddress.isBlank() || custPostalCode.isBlank() || custPhone.isBlank() || locationCountryComboBox.getValue() == null || locationDivisionComboBox.getValue() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Modifying Customer");
                alert.setHeaderText("Error Modifying Customer");
                alert.setContentText("Please make sure all fields are filled out. Please try again.");
                alert.showAndWait();
            }
            else{
                customersDAO.modifyingCustomer(custID, custName, custAddress, custPostalCode, custPhone, custDivisionID, custUpdatedBy);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Modifying Customer");
                alert.setHeaderText("Success Modifying Customer");
                alert.setContentText("Customer has been updated successfully.");
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

