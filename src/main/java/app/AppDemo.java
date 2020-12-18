package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by PZON_SM on 02.11.2020.
 **/
public class AppDemo extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));
        stage.setTitle("AppWeather by PZon");

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
       // stage.getScene().getStylesheets().addAll(getClass().getResource("style/style.css").toExternalForm());
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.sizeToScene();
    }
}
