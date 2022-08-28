package com.locnguyen.appointmentschedulerlocnguyen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class appointmentModdingController {

    @FXML
    private Label apptDateLabel;

    @FXML
    private DatePicker apptDatePicker;

    @FXML
    private ComboBox<?> apptEndTimeHourComboBox;

    @FXML
    private ComboBox<?> apptEndTimeMinuteComboBox;

    @FXML
    private Label apptIDLabel;

    @FXML
    private TextField apptIDTxtField;

    @FXML
    private ComboBox<?> apptStartTimeHourComboBox;

    @FXML
    private ComboBox<?> apptStartTimeMinuteComboBox;

    @FXML
    private Button cancelBttn;

    @FXML
    private ComboBox<?> contactComboBox;

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
    private ComboBox<?> divisionComboBox;

    @FXML
    private Label editApptMainLabel;

    @FXML
    private Label endTimeLabel;

    @FXML
    private ComboBox<?> locationComboBox;

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

}
