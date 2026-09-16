package org.example.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.utils.SharedData;

public class MarksForm {
    @FXML
    public ComboBox academicYear;
    @FXML
    public ComboBox term;
    @FXML
    public ComboBox exam;
    @FXML
    public TextField AdmNumber;
    @FXML
    public TextField SubjectCode;
    @FXML
    public TextField StudentScore;
    @FXML
    public TextField SchoolName;
    @FXML
    public TextArea Comment;
    @FXML
    public Button saveButton;

    private String admNumber;

    @FXML
    public void initialize(){
        //academicYear.getItems().addAll();
       // term.getItems().addAll();
       // exam.getItems().addAll();







    }

    public void setAdmissionNumber(String admission){
        this.admNumber=admission;

        AdmNumber.setText(admNumber);
    }

    public void save(MouseEvent mouseEvent) {

    }
}
