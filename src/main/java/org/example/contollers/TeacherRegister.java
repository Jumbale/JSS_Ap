package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.AddNewTeacher;
import org.example.services.ApiServices;
import org.example.utils.CheckEmptyFields;

import java.util.Optional;

public class TeacherRegister {
    @FXML
    public TextField Username;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public ComboBox<String> Role;
    @FXML
    public ComboBox <String>Gender;
    @FXML
    public TextField phoneNumber;
    @FXML
    public ComboBox <String>Department;
    @FXML
    public TextField Email;
    @FXML
    public ComboBox <String> Subject;
    @FXML
    public TextField tscNumber;
    @FXML
    public PasswordField Password;
    @FXML
    public Button ButtonCancel;
    @FXML
    public Button addTeacherButton;

    public void initialize(){
        Role.getItems().addAll("teacher");
        Gender.getItems().addAll("male","female");
        Subject.getItems().addAll("English","Kiswahili","Mathematics","Integrated Science" +
                "Social Studies","Agriculture and nutrition","Pre-Technical Studies","Creative Arts and Sports" +
                "Health Education","Religious Education","Life Skills Education"
        );
        Department.getItems().addAll("Languages","Mathematics","Science & Technology","Humanities" +
                "Agriculture","Creative Arts & Sports","Guidance and Counselling");
    }




    public void AddTeacher(MouseEvent mouseEvent) {
        String teacherUsername = Username.getText();
        String teacherFirstname = firstName.getText();
        String teacherLastname = lastName.getText();
        String role = Role.getValue();
        String gender = Gender.getValue();
        String department = Department.getValue();
        String teacherPhoneNumber = phoneNumber.getText();
        String subject = Subject.getValue();
        String email = Email.getText();
        String TSC = tscNumber.getText();
        String password = Password.getText();

        String[] teacherData = {"teacherUsername", "teacherFirstname", "teacherLastname" +
                "role", "gender", "department", "teacherPhoneNumber", "subject" +
                "TSC", "password", "email"};

        boolean checkFields = CheckEmptyFields.CheckFields(teacherData);

        if (checkFields){


            AddNewTeacher addNewTeacher = new AddNewTeacher(teacherUsername, teacherFirstname, teacherLastname,
                    role, gender, department, teacherPhoneNumber, subject, email, TSC, password);

            ApiServices apiServices = new ApiServices();
            apiServices.addNewTeacher(addNewTeacher)
                .thenAccept(result -> {
                    String message = result.getAddedTeacher().getMessage();
                    int statusCode = result.getStatusCode();

                    Platform.runLater(() -> {
                        if (statusCode >= 400) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Add Teacher");
                            alert.setHeaderText(null);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setContentText(message);
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Add Teacher");
                            alert.setHeaderText(null);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setContentText(message);

                            Optional<ButtonType> closeModal=alert.showAndWait();
                            if(closeModal.isPresent()&&closeModal.get()==ButtonType.OK){
                                Stage stage=(Stage)addTeacherButton.getScene().getWindow();
                                stage.close();
                            }

                        }
                    });


                });

    }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Add Teacher");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("Fill all fields");
            alert.showAndWait();

        }
    }
    public void closedRegister(MouseEvent mouseEvent) {
        Stage stage=(Stage)ButtonCancel.getScene().getWindow();
        stage.close();
    }
}
