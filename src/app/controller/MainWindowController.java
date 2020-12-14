
/**
 * Created by PZON_SM on 09.12.2020.
 **/
package app.controller;

import app.model.WeatherManager;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
    void buttonOnAction(javafx.event.ActionEvent actionEvent) {
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
            try{
                errors.setText("");
                this.citySet = cityNameInput.getText().trim();
                cityNameInput.setText(this.citySet.toUpperCase());
                weatherManager = new WeatherManager(this.citySet);
                displayWeather();
                bottomSet(false);
                invis.requestFocus();
            }catch (Exception e){
                city.setText("Error");
                city.setTextFill(Color.TOMATO);
                showError("No location found, please try again");
                reset();
                invis.requestFocus();
            }
        }
    }

    private void reset() {
        bottomSet(false);
        day.setText("");
        desc.setText("");
        img.setImage(null);
        temperature.setText("");
        windSpeed.setText("");
        cloudiness.setText("");
        pressure.setText("");
        humidity.setText("");
    }

    private void displayWeather() {
    }

    private void showError(String txt)  {
        city.setText(txt);
        city.setTextFill(Color.RED);
        city.setStyle("-fx-border-color: red; -fx-border-radius: 13; -fx-border-width: 2; -fx-padding: 2;");
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), city);
        fadeIn.setToValue(1);
        fadeIn.setFromValue(0);
        fadeIn.play();

        fadeIn.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.play();
            pause.setOnFinished(event2 -> {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), city);
                fadeOut.setToValue(0);
                fadeOut.setFromValue(1);
                fadeOut.play();
            });
        });
        cityNameInput.setText("");

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

        try{
            displayWeather();
        }catch (Exception e){
            city.setText("Error - internet connection");
            city.setTextFill(Color.DARKSALMON);
            showError("No internet connection");
            reset();
            findCityButton.setDisable(true);
            cityNameInput.setText("");
        }

        cityNameInput.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER){
                setPressed();
            }
        });
    }
}

