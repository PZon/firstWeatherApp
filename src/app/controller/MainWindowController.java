
/**
 * Created by PZON_SM on 09.12.2020.
 **/
package app.controller;

import app.model.WeatherManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    WeatherManager weatherManager;
    String citySet;

    public MainWindowController() {
        this.citySet = "Krakow".toUpperCase();
    }

    @FXML
    private TextField cityNameInput;

    @FXML
    private Button findCityButton;

    @FXML
    private Button setButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Pane weatherInfoPane;

    @FXML
    private Label city;

    @FXML
    private Label day;

    @FXML
    private ImageView img;

    @FXML
    private Label temperature;

    @FXML
    private Label desc;

    @FXML
    private Label errors;

    @FXML
    private TextField invis;

    @FXML
    private Label windSpeed;

    @FXML
    private Label cloudiness;

    @FXML
    private Label pressure;

    @FXML
    private Label humidity;

    @FXML
    void ButtonOnAction(javafx.event.ActionEvent actionEvent) {
        String initialCity = city.getText();

        if(actionEvent.getSource() == findCityButton){
            cityNameInput.setText("");
            bottomSet(true);
            cityNameInput.requestFocus();
        }else if(actionEvent.getSource() == setButton){
            setPressed();
        }else if(actionEvent.getSource() == cancelButton){
            cityNameInput.setText(initialCity);
            bottomSet(false);
            invis.requestFocus();
        }
    }

    private void setPressed() {
        if(cityNameInput.getText().equals("")){
            showError("City name can not be empty");
            return;
        }else{

        }
    }

    private void showError(String txt) {
        errors.setText(txt);
        errors.setTextFill(Color.RED);
    }

    private void bottomSet(boolean b) {
        cityNameInput.setDisable(!b);
        setButton.setVisible(b);
        findCityButton.setVisible(!b);
        cancelButton.setVisible(b);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cityNameInput.setText(citySet);
        cityNameInput.setDisable(true);
        setButton.setVisible(false);
        cancelButton.setVisible(false);
        errors.setText("");
        invis.requestFocus();
        weatherManager = new WeatherManager(citySet);
    }
}

