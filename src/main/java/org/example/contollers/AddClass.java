package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.AddNewClass;
import org.example.services.ApiServices;
import org.example.utils.CheckEmptyFields;

import java.util.Optional;

public class AddClass {

    @FXML
    public TextField teacherName;
    @FXML
    public TextField tscNumber;
    @FXML
    public Button ButtonCancel;
    @FXML
    public Button addClassButton;

    @FXML
    public TextField TeacherEmail;
    @FXML
    public ComboBox<String> gradeName;
    public Button ButtonRegister;

    @FXML
    public void initialize(){
        gradeName.getItems().addAll("Grade 7","Grade 8","Grade 9");
    }

    public void closeAddClass(MouseEvent mouseEvent) {
    }

    public void addClass(MouseEvent mouseEvent) {
        String className=gradeName.getValue();
        String teacherTSC=tscNumber.getText();


        String []dataFields={className,teacherTSC};

        boolean checkFields= CheckEmptyFields.CheckFields(dataFields);

        if(checkFields){
            AddNewClass addNewClass=new AddNewClass(className,teacherTSC);

            ApiServices apiServices=new ApiServices();
            apiServices.createClass(addNewClass)
                    .thenAccept(result->{
                        String message=result.getNewClass().getMessage();
                        int statusCode=result.getStatusCode();

                        Platform.runLater(()->{
                            if(statusCode>=400){
                                Alert alert=new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Add Class Error");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);
                                alert.showAndWait();
                            }else{
                                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Allocate Class");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);

                                Optional<ButtonType> closeModal=alert.showAndWait();
                                if(closeModal.isPresent()&& closeModal.get()==ButtonType.OK){
                                    Stage stage=(Stage)addClassButton.getScene().getWindow();
                                    stage.close();
                                }

                            }
                        });
                    });
        }

    }

    public void CancelButton(MouseEvent mouseEvent) {
        Stage stage=(Stage)ButtonCancel.getScene().getWindow();
        stage.close();
    }
}
