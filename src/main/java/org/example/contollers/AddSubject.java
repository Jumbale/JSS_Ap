package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.AddNewSubject;
import org.example.services.ApiServices;
import org.example.utils.CheckEmptyFields;

import java.util.Optional;

public class AddSubject {
    @FXML
    public TextField subjectCode;
    @FXML
    public TextField subjectName;
    @FXML
    public TextField department;
    @FXML
    public Button ButtonCancel;
    public Button addSubjectButton;

    private Runnable LoadSubjects;

    public void Save(MouseEvent mouseEvent) {
        String []fields={subjectCode.getText(),subjectName.getText(),department.getText()};

        boolean verifyFields= CheckEmptyFields.CheckFields(fields);

        if(verifyFields){
            String SubjectCode=subjectCode.getText();
            String SubjectCName=subjectName.getText();
            String Department=department.getText();

            AddNewSubject addNewSubject=new AddNewSubject(SubjectCode,SubjectCName,Department);
            ApiServices apiServices=new ApiServices();
            apiServices.createNewSubject(addNewSubject)
                    .thenAccept(result->{
                        String message=result.getAddNewSubjectResponse().getMessage();
                        int statusCode=result.getStatuscode();

                        Platform.runLater(()->{
                            if(statusCode>=400){
                                Alert alert=new Alert(Alert.AlertType.ERROR );
                                alert.setTitle("Add subject error");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);
                                alert.show();
                            }else{
                                Alert alert=new Alert(Alert.AlertType.INFORMATION );
                                alert.setTitle("Academic Details");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);

                                Optional<ButtonType> modal=alert.showAndWait();
                                if(modal.isPresent()&& modal.get()==ButtonType.OK){
                                    Stage stage=(Stage)addSubjectButton.getScene().getWindow();
                                    LoadSubjects.run();
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

    public void setOnLoadSubjects(Runnable Subjects){
        this.LoadSubjects=Subjects;
    }
}
