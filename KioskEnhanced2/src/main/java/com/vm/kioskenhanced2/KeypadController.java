/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Admin
 */
public class KeypadController {

    @FXML
    private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btClear;

    //Button[] btArray = {bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9};

    String appendData;

    @FXML
    public String processNumber(ActionEvent event) {
//        appendData += ((Button) event.getSource()).getText();
//        System.out.println("appendData : " + appendData);
        return "12345";
    }

    @FXML
    public void processClear() {

    }

    void processNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
