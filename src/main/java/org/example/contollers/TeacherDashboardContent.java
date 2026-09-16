package org.example.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.utils.SharedData;

public class TeacherDashboardContent {
    @FXML
    public Label WelcomeText;

    @FXML
    public void initialize(){
        WelcomeText.setText("Welcome "+SharedData.getInstance().getTeacherEmail()+"!");
    }
}


