package org.example.contollers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.GradeResults;
import org.example.services.ApiServices;
import org.example.utils.CheckEmptyFields;
import org.example.utils.SharedData;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

import static javafx.scene.paint.Color.RED;

public class ResultsView {
    @FXML
    public ComboBox<String> academicYear;


    @FXML
    public ComboBox <String> Exams;

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
    public TableColumn<GradeResults,String> AdmissionNumber;

    @FXML
    public TableColumn<GradeResults,String> Gender;
    @FXML
    public TableView <GradeResults>studentMarksTable;
    @FXML
    public TableColumn<GradeResults,String> StudentName;
    @FXML
    public TableColumn <GradeResults,String> Subject;
    @FXML
    public TableColumn <GradeResults,String> Score;
    @FXML
    public TableColumn<GradeResults,String> Remarks;
    @FXML
    public TableColumn <GradeResults,Void>Actions;
    @FXML
    public Button viewMarksButton;
    @FXML
    public ComboBox <String>Term;
    @FXML
    public Button resultsSummary;

    private String admNumber;

    @FXML
    public void initialize(){

        studentMarksTable.setPlaceholder(new Label("No Student Found!"));
        academicYear.getItems().addAll("2027/2028","2028/2029","2029/2030","2030/2031","2031/2032","2032/2033","2033/2034");
        Term.getItems().addAll("Term One","Term Two","Term Three");
        Exams.getItems().addAll("Back To School","MidTerm","EndTerm");

        Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        AdmissionNumber.setCellValueFactory(new PropertyValueFactory<>("admission_number"));
        Subject.setCellValueFactory(new PropertyValueFactory<>("subject_name"));
        StudentName.setCellValueFactory(cellData->{
            GradeResults gradeResults=cellData.getValue();

            if(gradeResults==null){
               return new  SimpleStringProperty("");
            }

            String firstName=gradeResults.getFirst_name()!=null? gradeResults.getFirst_name():"";
            String lastName=gradeResults.getLast_name()!=null? gradeResults.getLast_name():"";

            return new SimpleStringProperty(firstName+" "+lastName);
        });

        Score.setCellValueFactory(new PropertyValueFactory<>("score"));
        Remarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

    }


    public void viewResults(MouseEvent mouseEvent) {
        String AcademicYear=academicYear.getValue();
        String term=Term.getValue();
        String exams=Exams.getValue();

        String []fields={AcademicYear,term,exams};

        boolean checkedFields= CheckEmptyFields.CheckFields(fields);

        if(checkedFields){
            ApiServices apiServices=new ApiServices();
            apiServices.fetchGradeResults(AcademicYear,term,exams)
                    .thenAccept(results->{
                        int statusCode= results.getStatusCode();

                        Platform.runLater(()->{
                            if(statusCode>=400){
                                Alert alert=new Alert(Alert.AlertType.ERROR );
                                alert.setTitle("Error Dialog");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText("Failed!");
                                alert.show();
                            }else{
                                ObservableList <GradeResults> gradeResults= FXCollections.observableArrayList(results.getGradeResults());



                                studentMarksTable.setItems(gradeResults);

                            }
                            actions();




                        });

                        for(GradeResults grades:results.getGradeResults()){
                            this.admNumber= grades.getAdmission_number();
                        }

                        SharedData.getInstance().setExam(exams);
                        SharedData.getInstance().setTerm(term);
                        SharedData.getInstance().setAcademicYear(AcademicYear);
                        SharedData.getInstance().setAdmissionNumber(admNumber);
                    });



        }else{
            return;
        }


    }
    public void actions(){

        Actions.setCellFactory(params->new TableCell<>() {
            private final Button generateReport = new Button("Details");

            HBox hBox = new HBox();

            {
                FontIcon reportIcon = new FontIcon("fas-file-pdf");
                reportIcon.setIconColor(RED);

                generateReport.setGraphic(reportIcon);
                generateReport.setStyle("-fx-background-color:green;-fx-text-fill:#fff;" +
                        "-fx-font-family:FreeSans;");


                hBox.getChildren().addAll(generateReport);
                hBox.setAlignment(Pos.CENTER);


            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                    }else{
                        setGraphic(hBox);
                    }


            }

        });
    }

    public void openResultsSummaryModal(MouseEvent mouseEvent) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/resultsSummary.fxml"));
            Parent  root=loader.load();

            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(resultsSummary.getScene().getWindow());
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.showAndWait();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
