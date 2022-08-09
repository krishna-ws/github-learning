/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nukaraju Rekadi
 */
public class LanguageController implements Initializable {

    @FXML
    Button English, التخصصي;

    @FXML
    Label timeFormat, dateFormat;

    @FXML
    VBox dateBox;

    private SimpleDateFormat format;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            format = new SimpleDateFormat("hh:mm aa");
//            AnimationTimer timer = new AnimationTimer() {
//                @Override
//                public void handle(long now) {
//                    //timeFormat.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));
//                    
//                }
//            };
//            timer.start();
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e
                    -> timeFormat.setText(format.format(new Date()))
            ),
                    new KeyFrame(Duration.seconds(1))
            );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
            dateFormat.setText(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now()));

            //dateBox.getChildren().addAll(timeFormat, dateFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void RegistrationPage() throws Exception {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Registration.fxml"));
        Stage window = (Stage) English.getScene().getWindow();
        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setMaximized(true);
        window.setScene(new Scene(root));
        window.show();

    }
}
