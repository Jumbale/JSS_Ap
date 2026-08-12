package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.utils.sceneManager;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("/org/example/Signup.fxml"));
        primaryStage.setTitle("Auth");
        primaryStage.setResizable(false);


        sceneManager.initialize(primaryStage,root);
    }

    public static void main(String[] args) {
        // Launches the JavaFX lifecycle behind the scenes
        launch(args);
    }
}