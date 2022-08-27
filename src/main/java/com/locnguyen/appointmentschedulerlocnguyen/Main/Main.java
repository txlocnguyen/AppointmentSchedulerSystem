package com.locnguyen.appointmentschedulerlocnguyen.Main;

import com.locnguyen.appointmentschedulerlocnguyen.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/loginScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Login Screen");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception err) {
            err.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}
