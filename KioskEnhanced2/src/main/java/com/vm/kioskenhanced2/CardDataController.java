/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class CardDataController {

    @FXML
    private ImageView ckImage;

    @FXML
    private Label patientName;

    @FXML
    private Label doctorName;

    @FXML
    private Label departmentName;

    @FXML
    private Label appointmentTime;

    @FXML
    private Button checkinButton;

    @FXML
    public void checkinPage() throws Exception {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/token.fxml"));
        Stage window = (Stage) checkinButton.getScene().getWindow();
        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setMaximized(true);
        window.setScene(new Scene(root));
        window.show();
    }

    public void setData(CardData cData) {

        Image image = new Image(getClass().getResourceAsStream(cData.getImageSrc()));
        ckImage.setImage(image);

        patientName.setText(cData.getPatientName());
        doctorName.setText(cData.getDoctorName());
        departmentName.setText(cData.getDepartmentName());
        appointmentTime.setText(cData.getApptTime());

    }

}
