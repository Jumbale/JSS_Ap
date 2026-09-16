package org.example.contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

import static javafx.scene.paint.Color.BLUE;

public class Exams {
    @FXML
    public ImageView adminProfile;
    @FXML
    public Label adminName;
    @FXML
    public Label TotalExamsLabel;
    @FXML
    public Label UpcomingExamsLabel;
    @FXML
    public Label OngoingExamsLabel;
    @FXML
    public Label CompletedExamsLabel;
    @FXML
    public Label AllExams;
    @FXML
    public Label upcomingExam;
    @FXML
    public Label ongoingExam;
    @FXML
    public Label completedExam;
    @FXML
    public TableView examTable;
    @FXML
    public TableColumn SerialNumber;
    @FXML
    public TableColumn ExamName;
    @FXML
    public TableColumn Grade;
    @FXML
    public TableColumn Term;
    @FXML
    public TableColumn ExamDate;
    @FXML
    public TableColumn Status;
    @FXML
    public TableColumn Actions;
    @FXML
    public Button AddExamButton;

    @FXML
    public void initialize(){
        FontIcon plusIcon=new FontIcon("fas-plus");
        plusIcon.setIconColor(BLUE);
        AddExamButton.setGraphic(plusIcon);
    }

    public void addExam(MouseEvent mouseEvent) throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/ExamRegister.fxml"));
            Parent root=loader.load();

            Stage stage=new Stage();
            Scene scene=new Scene(root);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(AddExamButton.getScene().getWindow());
            stage.initStyle(StageStyle.DECORATED);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Exam Dialog ");
            stage.showAndWait();

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
