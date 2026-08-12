package org.example.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class EnterMarks {
    @FXML
    public ComboBox academicYear;
    @FXML
    public ComboBox term;
    @FXML
    public ComboBox classMarks;
    @FXML
    public ComboBox exam;
    @FXML
    public ComboBox subject;
    @FXML
    public Button addMarks;
    @FXML
    public TableView viewResultsTable;
    @FXML
    public TableColumn admissionNumber;
    @FXML
    public TableColumn studentName;
    @FXML
    public TableColumn geneder;
    @FXML
    public TableColumn marks;

    public void addMarks(MouseEvent mouseEvent) {
    }
}
