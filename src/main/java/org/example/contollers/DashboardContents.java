package org.example.contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class DashboardContents {
    @FXML

    public Button addSubject;
    public Button academicYear;
    private String email;
    @FXML
    public VBox addStudent;
    @FXML
    public VBox addClass;
    @FXML
    public VBox addTeacher;
    @FXML
    public VBox markAttendance;
    @FXML
    public VBox collectFees;
    @FXML
    public VBox genReports;
    @FXML
    public Label adminName;





    public void setAdminName(String name){
        this.email=name;
        System.out.print(adminName);
        System.out.print("this "+email);


        adminName.setText(email);

        System.out.print("label contains "+adminName.getText());
    }

    public void registerStudentModal(MouseEvent mouseEvent) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/registerStudent.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(addStudent.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.centerOnScreen();
            stage.sizeToScene();
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Register New Student");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void addClassModal(MouseEvent mouseEvent) throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/addClass.fxml"));
            Parent root=loader.load();
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setScene(scene);

            stage.initOwner(addClass.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setTitle("Add Class");
            stage.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    public void addTeacherModal(MouseEvent mouseEvent) throws IOException {

        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/addTeacher.fxml"));
            Parent root=loader.load();
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.initOwner(addTeacher.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Add Teacher");
            stage.showAndWait();
        }catch(IOException e){
            e.printStackTrace();

        }

    }


    public void addSubject(MouseEvent mouseEvent) {
    }

    public void addAcademicYear(MouseEvent mouseEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/AcademicYear.fxml"));
            Parent root = loader.load();
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(academicYear.getScene().getWindow());
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Academic Years Details");
            stage.showAndWait();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
