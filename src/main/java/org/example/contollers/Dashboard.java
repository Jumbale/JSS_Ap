package org.example.contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Dashboard {


    private String adminEmail;

    @FXML
    private  javafx.scene.layout.BorderPane BorderPane;
    @FXML
    private GridPane dashboardNav;
    @FXML
    public GridPane studentNav;
    @FXML
    public GridPane parentNav;
    @FXML
    public GridPane teacherNav;
    @FXML
    public GridPane classesNav;
    @FXML
    public GridPane attendanceNav;
    @FXML
    public GridPane examsNav;
    @FXML
    public GridPane feesNav;
    @FXML
    public GridPane documentsNav;
    @FXML
    public GridPane reportsNav;
    @FXML
    public GridPane usersNav;
    @FXML
    public GridPane rolesNav;
    @FXML
    public GridPane settingsNav;
    @FXML
    public ImageView adminProfile;
    @FXML
    public Label adminName;
    @FXML
    public Label totalTeachers;
    @FXML
    public Label totalParents;
    @FXML
    public Label totalClasses;
    @FXML
    public Label fees;
    @FXML
    public Label attendance;
    @FXML
    public TableColumn admissionNo;
    @FXML
    public TableColumn name;
    @FXML
    public TableColumn admissionDate;
    @FXML
    public VBox addStudent;
    @FXML
    public VBox addTeacher;
    @FXML
    public VBox addClass;
    @FXML
    public VBox markAttendance;
    @FXML
    public VBox collectFees;
    @FXML
    public VBox genReports;
    @FXML
    public VBox uploadFile;
    @FXML
    public VBox addEvent;
    @FXML
    public VBox addNotification;

    @FXML
    private Label activeTeachers;
    @FXML
    private Label termFees;
    @FXML
    private Label notifications;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn studentAdm;
    @FXML
    private TableColumn fullName;
    @FXML
    private TableColumn grade;
    @FXML
    private TableColumn gender;
    @FXML
    private TableColumn action;
    @FXML
    private Label termAssessment;
    @FXML
    private Label meetings;
    @FXML
    private Label sportsReg;
    @FXML
    private Label termBreak;
    @FXML
    private TextField searchBar;
    @FXML
    private Label totalStudents;


    @FXML
    public void initialize(){
        System.out.println(adminEmail);
    }
    public void setAdminEmail(String email) throws IOException {
        this.adminEmail=email;
        System.out.println("Dashboard "+adminEmail);

        System.out.println(this);





    }

    @FXML
    public  void dashBoard(MouseEvent mouseEvent) throws IOException {
        System.out.println(adminEmail);
        System.out.println(this);
        try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/dashboardContent.fxml"));
                Parent root=loader.load();

                DashboardContents dashboardContents=loader.getController();
                dashboardContents.setAdminName(adminEmail);


                restTabs();

                dashboardNav.getStyleClass().add("active");


                //System.out.println("Dashboard");

                   System.out.println("after");
                   // Parent dashboard = FXMLLoader.load(Dashboard.class.getResource("/org/example/dashboardContent.fxml"));


                    BorderPane.setCenter(root);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void studentNav(MouseEvent mouseEvent) throws IOException {
        restTabs();
        studentNav.getStyleClass().add("active");
        try {
            Parent student = FXMLLoader.load(Dashboard.class.getResource("/org/example/student.fxml"));
            BorderPane.setCenter(student);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void TeachersNav(MouseEvent mouseEvent) throws IOException {
        restTabs();
        teacherNav.getStyleClass().add("active");
        try {
            Parent student = FXMLLoader.load(Dashboard.class.getResource("/org/example/Teachers.fxml"));
            BorderPane.setCenter(student);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void ExamNav(MouseEvent mouseEvent) throws IOException {
        restTabs();
        examsNav.getStyleClass().add("active");

        try {
            Parent student = FXMLLoader.load(Dashboard.class.getResource("/org/example/Exams.fxml"));
            BorderPane.setCenter(student);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void Fees(MouseEvent mouseEvent) throws IOException {
        restTabs();
        feesNav.getStyleClass().add("active");

        try {
            Parent student = FXMLLoader.load(Dashboard.class.getResource("/org/example/Fees.fxml"));
            BorderPane.setCenter(student);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void restTabs(){
        dashboardNav.getStyleClass().remove("active");

        dashboardNav.getStyleClass().remove("active");

        studentNav.getStyleClass().remove("active");

        teacherNav.getStyleClass().remove("active");

        examsNav.getStyleClass().remove("active");

        feesNav.getStyleClass().remove("active");

    }



}

