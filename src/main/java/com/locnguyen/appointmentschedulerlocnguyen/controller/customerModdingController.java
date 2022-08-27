package com.locnguyen.appointmentschedulerlocnguyen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class customerModdingController {

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
    private ComboBox<?> locationCountryComboBox;

    @FXML
    private ComboBox<?> locationDivisionComboBox;

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

}

