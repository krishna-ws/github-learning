/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 *
 * @author Nukaraju Rekadi
 */
public class MrnController implements Initializable {

    @FXML
    private Button submit;

    @FXML
    private Button previousScreen, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btClear;

    @FXML
    private TextField textField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBox;

    private List<CardData> cards = new ArrayList<>();

    private KeypadController keypad;

    private NumberPadController numberpad;

    private FXMLLoader loader;

    private String buttonValue;

    @FXML
    public void previousLang() throws Exception {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Registration.fxml"));
        Stage window = (Stage) previousScreen.getScene().getWindow();

        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setMaximized(true);

        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void SubmitPage() throws Exception {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        String jsonResponse = sendRequest(textField.getText());

        cards = parseJsonStringData(jsonResponse);
        System.out.println(cards.toString());

        @SuppressWarnings("LocalVariableHidesMemberVariable")
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/checkIn.fxml"));
        Parent root = loader.load();

        CheckinController cinController = loader.getController();

        cinController.callCardScene(cards);
        Stage window = (Stage) submit.getScene().getWindow();

        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setMaximized(true);

        window.setScene(new Scene(root));
        window.show();
    }

    public String sendRequest(String mrnNo) throws IOException {
        String content = null;

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

//            HttpGet httpget = new HttpGet("http://202.65.159.118:9097/laboratory/1884/dept-users/3925");
            HttpGet httpget = new HttpGet("http://202.65.159.118:9097/service/1884/getServicesBydeptId/3903");
            System.out.println("Request Type: " + httpget.getMethod());
            HttpPost post = new HttpPost();

            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
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
            }
            return content;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textField.setFocusTraversable(true);
        submit.disableProperty().bind(
                Bindings.isEmpty(textField.textProperty()));
    }

    @FXML
    public void processNumber(ActionEvent event) {
        String str = ((Button) event.getSource()).getText();
        textField.setText(textField.getText() + str);
    }

    public List<CardData> parseJsonStringData(String jsonResponse) {
        List<CardData> cds = new ArrayList<>();
        CardData data = null;
        try {
            JSONParser parse = new JSONParser();

            JSONObject jobj = (JSONObject) parse.parse(jsonResponse);

            JSONArray jsonarr = (JSONArray) jobj.get("services");

            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

            for (int i = 0; i < jsonarr.size(); i++) {
                JSONObject jsonobj = (JSONObject) jsonarr.get(i);

                System.out.println("Elements under results array");
                System.out.println("\nPlace id: " + jsonobj.get("serviceEngName"));
                //System.out.println("Types: " + jsonobj_1.get("roles"));

                data = new CardData();
                data.setImageSrc("/images/checkin.png");
                data.setPatientName("PatientName : " + jsonobj.get("serviceEngName"));
                data.setDoctorName("DoctorName : " + jsonobj.get("serviceArName"));
                data.setDepartmentName("DepartmentName : " + jsonobj.get("servicePrefix"));
                data.setApptTime("ApptTime : " + sdf.format(new Date()));
                cds.add(data);
            }

            System.out.println("List size:" + cds.size());
            return cds;
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
