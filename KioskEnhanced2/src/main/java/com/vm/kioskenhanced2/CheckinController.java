/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class CheckinController implements Initializable {

    @FXML
    private GridPane cardGrid;
    @FXML
    private Button previousScreen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void previousLang() throws Exception {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Mrn.fxml"));
        Stage window = (Stage) previousScreen.getScene().getWindow();
        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setMaximized(true);
        window.setScene(new Scene(root));
        window.show();
    }

    public void callCardScene(List<CardData> card) {
        int columns = 0;
        int rows = 5;
        try {
            for (int i = 0; i < card.size(); i++) {

                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.setLocation(getClass().getResource("/fxml/card.fxml"));

                VBox cardBox = fXMLLoader.load();

                CardDataController cDataController = fXMLLoader.getController();
                cDataController.setData(card.get(i));

                if (columns == 3) {
                    columns = 0;
                    ++rows;
                }
                columns++;
                cardGrid.add(cardBox, columns, rows);
                GridPane.setMargin(cardBox, new Insets(14));
                GridPane.setHalignment(cardBox, HPos.LEFT);
                System.out.println("Grid Pane Row and Column values : " + GridPane.getRowIndex(cardBox) + " " + GridPane.getColumnIndex(cardBox));
            }

        } catch (IOException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
