/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Admin
 */
public class PharmacyMrnController implements Initializable {

    @FXML
    private Button verify;

    @FXML
    private Button previousScreen, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btClear;

    @FXML
    private TextField textField;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.setFocusTraversable(true);
        verify.disableProperty().bind(
                Bindings.isEmpty(textField.textProperty()));
    }

    @FXML
    public void processNumber(ActionEvent event) {
        String str = ((Button) event.getSource()).getText();
        textField.setText(textField.getText() + str);
    }

    @FXML
    public void verifyPage() throws Exception {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        String jsonResponse = sendRequest(textField.getText());

        parseJsonStringData(jsonResponse);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/checkIn.fxml"));
        Parent root = loader.load();

        CheckinController cinController = loader.getController();

        //cinController.callCardScene(cards);
        Stage window = (Stage) verify.getScene().getWindow();

        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setMaximized(true);

        window.setScene(new Scene(root));
        window.show();
    }

    public String sendRequest(String mrnNo) throws IOException, URISyntaxException {
        String content = null;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

//            HttpGet httpget = new HttpGet("http://202.65.159.118:9097/laboratory/1884/dept-users/3925");
            //HttpGet httpget = new HttpGet("http://202.65.159.118:8092/kiosk-pharmacy-new/2234/authenticate/88474");
            URIBuilder builder = new URIBuilder()
                    .setScheme("http")
                    .setHost("202.65.159.118")
                    .setPort(8092)
                    .setPath("/kiosk-pharmacy-new/2234/authenticate/{mrnNo}")
                    .addParameter("mrnNo", mrnNo);
            HttpGet httpget = new HttpGet();
            URI uri = builder.build();
            httpget.setURI(uri);
            System.out.println("Request Type: " + httpget.getMethod());

            CloseableHttpResponse response = httpclient.execute(httpget);

            try {
                System.out.println(response.getProtocolVersion());
                System.out.println(response.getStatusLine().getStatusCode());
                System.out.println(response.getStatusLine().getReasonPhrase());
                System.out.println(response.getStatusLine().toString());

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new RuntimeException("Failed with HTTP error code : " + statusCode);
                }

                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    content = EntityUtils.toString(httpEntity);
                    System.out.println(content);
                }
            } finally {
                response.close();
            }
            return content;
        } finally {
            httpclient.close();
        }
    }

    public List<CardData> parseJsonStringData(String jsonResponse) {

        CardData data = null;
        try {
            JSONParser parser = new JSONParser();

            //JSONObject jobj = (JSONObject) parser.parse(jsonResponse);
            JSONArray jsonarr = (JSONArray) parser.parse(jsonResponse);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

            for (int i = 0; i < jsonarr.size(); i++) {
                JSONObject jsonobj = (JSONObject) jsonarr.get(i);

                System.out.println("status : " + jsonobj.get("status"));
                System.out.println("message:" + jsonobj.get("messages"));
                System.out.println("Elements under kioskInfo array");
                JSONArray jsonKiosk = (JSONArray) jsonobj.get("kioskInfo");
                System.out.println("KioskInfo : " + jsonKiosk);

            }

            //System.out.println("List size:" + cds.size());
            return null;
        } catch (ParseException ex) {
            Logger.getLogger(MrnController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @FXML
    private void checkInput(KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            keyEvent.consume();
            textField.setStyle("-fx-border-color: red");
        }
    }

}
