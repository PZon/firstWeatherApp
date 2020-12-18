
/**
 * Created by PZON_SM on 09.12.2020.
 **/
package app.controller;

import app.model.ImgManager;
import app.model.WeatherManager;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
            }catch (Exception e){
                city.setText("Error");
                city.setTextFill(Color.DARKRED);
                showError("No location found, please try again");
                reset();
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
        city.setTextFill(Color.LIGHTSKYBLUE);
    }

    private void showError(String txt)  {
        errors.setText(txt);
        errors.setTextFill(Color.RED);
        errors.setStyle("-fx-border-color: red; -fx-border-radius: 13; -fx-border-width: 2; -fx-padding: 2;");
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), errors);
        fadeIn.setToValue(1);
        fadeIn.setFromValue(0);
        fadeIn.play();

        fadeIn.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.play();
            pause.setOnFinished(event2 -> {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), errors);
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
        weatherManager = new WeatherManager(citySet);

        try{
            displayWeather();
        }catch (Exception e){
            city.setText("Error - internet connection");
            city.setTextFill(Color.DARKRED);
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

    public void displayWeather(){
        weatherManager.getSomeData();
        day.setText(weatherManager.getDay().toUpperCase());
        city.setText(weatherManager.getCity().toUpperCase());
        temperature.setText(weatherManager.getTemperature().toString()+" °C");
        humidity.setText(weatherManager.getHumidity()+" %");
        pressure.setText(weatherManager.getPressure()+" hpa");
        windSpeed.setText(weatherManager.getWindSpeed()+" m/s");
        cloudiness.setText(weatherManager.getCloudiness()+ "%");
        desc.setText(weatherManager.getDescription().toUpperCase());
        img.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getIcon()))));
    }
}

