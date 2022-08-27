package com.locnguyen.appointmentschedulerlocnguyen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class mainMenuController {

    @FXML
    private Button addApptBttn;

    @FXML
    private Button addCustomerBttn;

    @FXML
    private Label apptLabel;

    @FXML
    private TableColumn<?, ?> apptTableApptID;

    @FXML
    private TableColumn<?, ?> apptTableContactID;

    @FXML
    private TableColumn<?, ?> apptTableCustomerID;

    @FXML
    private TableColumn<?, ?> apptTableDescription;

    @FXML
    private TableColumn<?, ?> apptTableEndDate;

    @FXML
    private TableColumn<?, ?> apptTableLocation;

    @FXML
    private TableColumn<?, ?> apptTableStartDate;

    @FXML
    private TableColumn<?, ?> apptTableTitle;

    @FXML
    private TableColumn<?, ?> apptTableType;

    @FXML
    private TableColumn<?, ?> apptTableUserID;

    @FXML
    private TableView<?> apptTableView;

    @FXML
    private Label customerLabel;

    @FXML
    private TableColumn<?, ?> customerTableAddress;

    @FXML
    private TableColumn<?, ?> customerTableDivisionID;

    @FXML
    private TableColumn<?, ?> customerTableID;

    @FXML
    private TableColumn<?, ?> customerTableName;

    @FXML
    private TableColumn<?, ?> customerTablePhoneNo;

    @FXML
    private TableColumn<?, ?> customerTablePostalCode;

    @FXML
    private TableView<?> customerTableView;

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
    private ComboBox<?> monthComboBox;

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

}

