package org.example.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ViewResults {
    @FXML
    public ComboBox<String> academicYear;
    @FXML
    public ComboBox Year;
    @FXML
    public ComboBox classViewResults;
    @FXML
    public ComboBox Exams;
    @FXML
    public ComboBox subjects;
    @FXML
    public Button viewMarks;
    @FXML
    public Label totalStudents;
    @FXML
    public Label marksEntered;
    @FXML
    public Label AverageScore;
    @FXML
    public Label highestSCore;
    @FXML
    public Label lowestScore;
    @FXML
    public Label marksOutOf;
    @FXML
    public Label hundredTotalMarks;
    @FXML
    public TableView studentMarks;
    @FXML
    public TableColumn AdmissionNumber;
    @FXML
    public TableColumn studentName;
    @FXML
    public TableColumn Gender;
    @FXML
    public TableColumn Marks;
}
