package org.example.contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.example.utils.SharedData;

import java.io.IOException;

public class TeachersDashboard {
    @FXML
    public javafx.scene.layout.BorderPane BorderPane;
    @FXML
    public GridPane reportsNav;
    @FXML
    public GridPane classesNav;
    @FXML
    public GridPane examsNav;
    @FXML
    public GridPane viewResults;
    @FXML
    public GridPane Profile;
    @FXML
    public GridPane Logout;
    @FXML
    public GridPane dashboardNav;
    @FXML
    public void initialize(){
        System.out.println("Logged In teacher: "+SharedData.getInstance().getTeacherEmail());
    }

    public void dashBoard(MouseEvent mouseEvent) throws IOException {
        clear();
        dashboardNav.getStyleClass().add("active");

        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/DashboardTeacher.fxml"));

            Parent root=loader.load();
            BorderPane.setCenter(root);

        }catch(IOException e) {
            e.printStackTrace();

        }


    }

    public void ExamNav(MouseEvent mouseEvent) {
        clear();
        examsNav.getStyleClass().add("active");
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/DashboardTeacher.fxml"));

            Parent root=loader.load();
            BorderPane.setCenter(root);

        }catch(IOException e) {
            e.printStackTrace();

        }


    }


    public void Profile(MouseEvent mouseEvent) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/DashboardTeacher.fxml"));

            Parent root=loader.load();
            BorderPane.setCenter(root);

        }catch(IOException e) {
            e.printStackTrace();

        }



    }

    public void Logout(MouseEvent mouseEvent) {
    }

    public void profile(MouseEvent mouseEvent) {
    }

    public void Reports(MouseEvent mouseEvent) {
    }

    public void viewResults(MouseEvent mouseEvent) {
        clear();
        viewResults.getStyleClass().add("active");
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/ViewResults.fxml"));

            Parent root=loader.load();
            BorderPane.setCenter(root);

        }catch(IOException e) {
            e.printStackTrace();

        }



    }

    public void EnterResults(MouseEvent mouseEvent) {
        clear();
        examsNav.getStyleClass().add("active");

        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/EnterMarks.fxml"));

            Parent root=loader.load();
            BorderPane.setCenter(root);

        }catch(IOException e) {
            e.printStackTrace();

        }



    }

    public void myClass(MouseEvent mouseEvent) {
    }

    public void clear(){
        dashboardNav.getStyleClass().remove("active");
        Logout.getStyleClass().remove("active");
        Profile.getStyleClass().remove("active");
        viewResults.getStyleClass().remove("active");
        examsNav.getStyleClass().remove("active");
        classesNav.getStyleClass().remove("active");
        reportsNav.getStyleClass().remove("active");
    }
}
