package org.example;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.utils.SharedData;
import org.example.utils.sceneManager;

import java.io.IOException;

public class Main extends Application {
    private static HostServices hostServices;


    @Override
    public void start(Stage primaryStage) throws IOException {
        //getHostServices().showDocument(SharedData.getInstance().getUrl());
        hostServices=getHostServices();
        Parent root=FXMLLoader.load(getClass().getResource("/org/example/Signup.fxml"));
        primaryStage.setTitle("Auth");
        primaryStage.setResizable(false);


        sceneManager.initialize(primaryStage,root);


    }
    public static void openWebPage(String url) throws Exception{
        if(hostServices!=null){
            hostServices.showDocument(url);
        }
    }
    public static void main(String[] args) {
        // Launches the JavaFX lifecycle behind the scenes
        launch(args);
    }
}