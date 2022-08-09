/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nukaraju Rekadi
 */
public class RegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    Button Registration, Appointments, Pharmacy;

    @FXML
    private Button previousScreen;

    @FXML
    public void MrnPage() throws Exception {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Mrn.fxml"));
        Stage window = (Stage) Appointments.getScene().getWindow();
        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setMaximized(true);
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void previousLang() throws Exception {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Language.fxml"));
        Stage window = (Stage) previousScreen.getScene().getWindow();
        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setMaximized(true);
        window.setScene(new Scene(root));
        window.show();
    }
}
