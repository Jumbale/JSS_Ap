package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.ScoresAndComments;
import org.example.services.ApiServices;
import org.example.utils.CheckEmptyFields;
import org.example.utils.SharedData;

import java.util.Optional;

public class MarksForm {
    @FXML
    public ComboBox<String> academicYear;
    @FXML
    public ComboBox <String> term;
    @FXML
    public ComboBox <String> exam;
    @FXML
    public TextField AdmNumber;
    @FXML
    public TextField subjectCode;
    @FXML
    public TextField StudentScore;
    @FXML
    public TextField SchoolName;
    @FXML
    public TextArea Comment;
    @FXML
    public Button saveButton;
    @FXML
    public TextField SubjectName;

    private String admNumber;



    @FXML
    public void initialize(){
        academicYear.getItems().addAll("2027/2028","2028/2029","2029/2030","2030/2031","2031/2032","2032/2033","2033/2034");
        term.getItems().addAll("Term One","Term Two","Term Three");
       // term.getItems().addAll();
       exam.getItems().addAll("Back To School","MidTerm","EndTerm");







    }

    public void setAdmissionNumber(String admission){
        this.admNumber=admission;

        AdmNumber.setText(admNumber);
    }

    public void save(MouseEvent mouseEvent) {
        String yearName=academicYear.getValue();
        String termName=term.getValue();
        String Exam=exam.getValue();
        String AdmissionNumber=AdmNumber.getText();
        String SubjectCode=subjectCode.getText();
        String Score=StudentScore.getText();
        String schoolName=SchoolName.getText();
        String Remarks=Comment.getText();
        String subjectName=SubjectName.getText();


        String []fields={termName,Exam,AdmissionNumber,SubjectCode,
                         Score,schoolName,Remarks,subjectName};

        boolean checkedFields= CheckEmptyFields.CheckFields(fields);

        if(checkedFields){
            ScoresAndComments scoresAndComments=new ScoresAndComments(AdmissionNumber,SubjectCode,subjectName,termName,yearName,
                                                                        Score,Remarks,schoolName,Exam);
            ApiServices apiServices=new ApiServices();
            apiServices.addScoresAndComments(scoresAndComments)
                    .thenAccept(results->{
                        String message=results.getScoresAndCommentsResponse().getMessage();
                        int statusCode=results.getStatuscode();

                        System.out.println(message);

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
                                    Stage stage=(Stage)saveButton.getScene().getWindow();
                                    stage.close();
                                }
                            }



                        });
                    });

            SharedData.getInstance().setSchool(schoolName);

        }else{
            return;
        }

    }
}
