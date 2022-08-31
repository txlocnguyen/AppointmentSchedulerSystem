package com.locnguyen.appointmentschedulerlocnguyen.controller;

import com.locnguyen.appointmentschedulerlocnguyen.Main.Main;
import com.locnguyen.appointmentschedulerlocnguyen.database.appointmentsDAO;
import com.locnguyen.appointmentschedulerlocnguyen.database.usersDAO;
import com.locnguyen.appointmentschedulerlocnguyen.helper.loginTracking;
import com.locnguyen.appointmentschedulerlocnguyen.models.appointment;
import javafx.collections.ObservableList;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
/***
 * Public controller class loginScreenController
 * @author Loc Nguyen
 */
public class loginScreenController implements Initializable {
    @FXML
    private Button exitBttn;

    @FXML
    private Label labelForPassword;

    @FXML
    private Label labelForUsername;

    @FXML
    private Button loginBttn;

    @FXML
    private Label mainTitleLabel;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Label timeZoneData;

    @FXML
    private Label timeZoneLabel;

    @FXML
    private TextField userNameTxtField;

    ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle", Locale.getDefault());

    /***
     * Initialize method. Change language to French based on the user's computer default language.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeZoneData.setText(ZoneId.systemDefault().toString());
        mainTitleLabel.setText(rb.getString("mainTitle"));
        labelForUsername.setText(rb.getString("username"));
        labelForPassword.setText(rb.getString("password"));
        loginBttn.setText(rb.getString("loginBtn"));
        exitBttn.setText(rb.getString("exitBtn"));
        timeZoneLabel.setText(rb.getString("timeZone"));
    }
    /***
     * Exit the program when exit button is clicked.
     */
    @FXML
    public void exitProgram(){
        System.exit(0);
    }
    /***
     * Lookup all appointments currently in the local database and alert user if there is an upcoming appointment within 15 minutes.
     */
    public void getAllApptsIn15Min(){
        ObservableList<appointment> allAppts = appointmentsDAO.getAllAppointments();
        LocalDateTime past15minutes = LocalDateTime.now().minusMinutes(15);
        LocalDateTime future15minutes = LocalDateTime.now().plusMinutes(15);
        LocalDateTime apptStartTime;
        int count = 0;
        int apptFoundID = 0;
        LocalDateTime apptFoundTime = null;
        boolean apptFoundOrNot = false;
        for(appointment appt : allAppts){
            apptStartTime = appt.getApptStart();
            apptFoundOrNot = false;
            if((apptStartTime.isBefore(future15minutes) || apptStartTime.isEqual(future15minutes)) && (apptStartTime.isAfter(past15minutes) || apptStartTime.isEqual(past15minutes))){
                apptFoundOrNot = true;
                apptFoundID = appt.getApptID();
                apptFoundTime = apptStartTime;
            }
            if(apptFoundOrNot){
                    String upcomingApptMsg = rb.getString("upcomingApptMsgTime") + apptFoundTime + rb.getString("upcomingApptMsgID") + apptFoundID;
                    ButtonType ok = new ButtonType(rb.getString("ok"), ButtonBar.ButtonData.OK_DONE);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, upcomingApptMsg, ok);
                    alert.setTitle(rb.getString("upcomingApptTitle"));
                    alert.setHeaderText(rb.getString("upcomingApptTitle"));
                    alert.showAndWait();
                count++;
            }
        }
        if(count == 0){
                String noUpcomingApptMsg = rb.getString("noUpcomingApptMsg");
                ButtonType ok = new ButtonType(rb.getString("ok"), ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, noUpcomingApptMsg, ok);
                alert.setTitle(rb.getString("noUpcomingApptTitle"));
                alert.setHeaderText(rb.getString("noUpcomingApptTitle"));
                alert.showAndWait();
        }
    }
    /***
     * Login to the system when login button is clicked.
     * @param e
     * @throws SQLException
     */
    @FXML
    public void loginToSystem(ActionEvent e) throws SQLException {
        String usrname = userNameTxtField.getText();
        String pw = passwordTxtField.getText();
        loginTracking.logLoginActivity(usersDAO.handleLogin(usrname, pw), usrname);
        try{
            if(usersDAO.handleLogin(usrname, pw)){
                getAllApptsIn15Min();
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/mainMenu.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else{
                    ButtonType ok = new ButtonType(rb.getString("ok"), ButtonBar.ButtonData.OK_DONE);
                    Alert alert = new Alert(Alert.AlertType.ERROR, rb.getString("loginError"), ok);
                    alert.setTitle(rb.getString("loginErrorTitle"));
                    alert.setHeaderText(rb.getString("loginErrorTitle"));
                    alert.showAndWait();
            }
        } catch(Exception error){
            error.printStackTrace();
        }
    }


}

