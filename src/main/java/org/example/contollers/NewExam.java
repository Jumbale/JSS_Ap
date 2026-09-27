package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.NewExamRegistration;
import org.example.services.ApiServices;
import org.example.utils.CheckEmptyFields;

import java.time.LocalDate;
import java.util.Optional;

public class NewExam {


    @FXML
    public ComboBox <String>ExamName;
    @FXML
    public ComboBox <String>ExamStatus;

    @FXML
    public ComboBox <String>TermName;

    @FXML
    public DatePicker ExamDate;
   @FXML
    public Button cancelButton;
   @FXML
    public Button saveExamButton;

    @FXML
    public void initialize(){
        ExamStatus.getItems().addAll("ongoing","upcoming","completed");
        TermName.getItems().addAll("Term One","Term Two","Term Three");
        ExamName.getItems().addAll("Back To School","MidTerm","EndTerm");
    }
    public void save(MouseEvent mouseEvent) {
        LocalDate localDate=ExamDate.getValue();


        String examName=ExamName.getValue();
        String termName=TermName.getValue();
        String examStatus=ExamStatus.getValue();

        String[]fields={examName,termName,examStatus, String.valueOf(localDate)};

        boolean checkedFields= CheckEmptyFields.CheckFields(fields);

        if(checkedFields){
            String date=localDate.toString();
            NewExamRegistration examData=new NewExamRegistration(examName,termName,examStatus,date);
            ApiServices apiServices=new ApiServices();
            apiServices.addNewExam(examData)
                    .thenAccept(results->{
                        String message=results.getNewExamRegistrationResponse().getMessage();
                        int statusCode=results.getStatusCode();

                        Platform.runLater(()->{
                            if(statusCode>=400){
                                Alert alert=new Alert(Alert.AlertType.ERROR );
                                alert.setTitle("Error Dialog");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);
                                alert.show();
                            }else{
                                Alert alert=new Alert(Alert.AlertType.INFORMATION );
                                alert.setTitle("Success Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText(message);
                                alert.initStyle(StageStyle.UTILITY);

                                Optional<ButtonType> okButton=alert.showAndWait();
                                if(okButton.isPresent()&&okButton.get()==ButtonType.OK){
                                    Stage stage=(Stage)saveExamButton.getScene().getWindow();
                                    stage.close();
                                }
                            }
                        });
                    });

        }else{
            return;
        }


    }

    public void closeModal(MouseEvent mouseEvent) {
        Stage stage=(Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
}
