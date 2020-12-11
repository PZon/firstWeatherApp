/**
 * Created by PZON_SM on 02.11.2020.
 **/
module WeatherApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    opens app;
    opens app.controller;
    opens app.view;
   // opens app.model;
}