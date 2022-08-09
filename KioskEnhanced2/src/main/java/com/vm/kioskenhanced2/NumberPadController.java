/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Admin
 */
public class NumberPadController {

    @FXML
    AnchorPane anchorPane;

    public String callKeypad() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/keypad.fxml"));
            Parent root = loader.load();
            System.out.println("root --->" + root);

            KeypadController keypadController = loader.getController();

            String appendData = keypadController.processNumber(new ActionEvent());
            System.out.println("numberpadcontroller-->appenddata-->" + appendData);
            anchorPane.getChildren().add(root);

            return appendData;
        } catch (IOException ex) {
            Logger.getLogger(NumberPadController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
