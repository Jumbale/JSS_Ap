package org.example.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.utils.CheckEmptyFields;

import java.time.LocalDate;

public class newExam {

    public TextField ExamName;
    @FXML
    public TextField TermName;
    @FXML
    public ComboBox <String>ExamStatus;


    @FXML
    public DatePicker ExamDate;
   @FXML
    public Button cancelButton;
   @FXML
    public Button saveExamButton;

    @FXML
    public void initialize(){
        ExamStatus.getItems().addAll("ongoing","upcoming","completed");
    }
    public void save(MouseEvent mouseEvent) {
        LocalDate localDate=ExamDate.getValue();

        String date=localDate.toString();
        String examName=ExamName.getText();
        String termName=TermName.getText();
        String examStatus=ExamStatus.getValue();

        String[]fields={examName,termName,examStatus,date};

        boolean checkedFields= CheckEmptyFields.CheckFields(fields);

        if(checkedFields){

        }else{
            return;
        }


    }

    public void closeModal(MouseEvent mouseEvent) {
    }
}
