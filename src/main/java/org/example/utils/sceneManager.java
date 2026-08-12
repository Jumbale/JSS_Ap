package org.example.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sceneManager {

    private static  Scene scene;
    private  static  Stage stage;

    public static void initialize(Stage primaryStage, Parent root){

        stage=primaryStage;
        scene=new Scene(root);

        String css=sceneManager.class.getResource("/org/example/dashboard.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage(){
        return stage;
    }

    public static void navigateDashboard(String fxml) throws IOException {
        Parent root= FXMLLoader.load(sceneManager.class.getResource(fxml));
        scene.setRoot(root);
    }
}
