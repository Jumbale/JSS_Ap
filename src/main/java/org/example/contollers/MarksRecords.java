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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.ClassLevelStudents;
import org.example.services.ApiServices;
import org.example.utils.SharedData;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

import static javafx.scene.paint.Color.WHITE;
import static javax.swing.UIManager.get;

public class MarksRecords {

    @FXML
    public Button addMarks;


    @FXML
    public TableColumn marks;
    @FXML
    public Label ClassNameLabel;
    @FXML
    public Label TotalStudentsLabel;
    @FXML
    public TableView<ClassLevelStudents> ClassLevelResultsTable;
    @FXML
    public TableColumn <ClassLevelStudents,String>AdmissionNumber;
    @FXML
    public TableColumn<ClassLevelStudents,String> StudentName;
    @FXML
    public TableColumn <ClassLevelStudents,String>Gender;
    @FXML
    public TableColumn<ClassLevelStudents,Void> Actions;

    //private String studentFullName;

    @FXML
    public void initialize(){

       // String StudentName=

        ClassLevelResultsTable.setPlaceholder(new Label("No Student Found"));

        AdmissionNumber.setCellValueFactory(new PropertyValueFactory<>("admission_number"));
        StudentName.setCellValueFactory(cellData->{
            ClassLevelStudents classLevelStudents=cellData.getValue();
            if(classLevelStudents==null){
                return new SimpleStringProperty("");
            }

            String firstName=classLevelStudents.getFirst_name()!=null? classLevelStudents.getFirst_name():"";
            String lastName= classLevelStudents.getLast_name()!=null? classLevelStudents.getLast_name() : "";

            return new SimpleStringProperty(firstName+" "+lastName);
        });
        Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));




        fetchClassLevelStudents();
        Actions();

    }

    public void fetchClassLevelStudents(){
        String teacherEmail=SharedData.getInstance().getTeacherEmail();




        ApiServices apiServices=new ApiServices();
        apiServices.fetchLoggedTeacherStudents(teacherEmail)
                .thenAccept(results->{
                   //System.out.println(results.getClassLevelStudents()[0].);
                    //results.getClassLevelStudents().

                    for(ClassLevelStudents details:results.getClassLevelStudents()){
                        //this.studentFullName=details.getFirst_name()+" "+details.getLast_name();
                    }

                    ObservableList<ClassLevelStudents> classStudents= FXCollections.observableArrayList(results.getClassLevelStudents());

                    Platform.runLater(()->{
                        ClassLevelResultsTable.setItems(classStudents);

                    });
                });


    }

    public void Actions(){
        Actions.setCellFactory(column->new TableCell<>(){
            private final Button editMarks=new Button("Edit");
            private final Button addMarks=new Button("Add Marks");

            private final HBox box=new  HBox();
            {
                box.getChildren().addAll(editMarks,addMarks);
                box.setSpacing(10);
                box.setAlignment(Pos.CENTER);


                FontIcon editIcon=new FontIcon("fas-edit");
                editIcon.setIconColor(WHITE);
                editMarks.setGraphic(editIcon);

                FontIcon addIcon=new FontIcon("fas-plus");
                addIcon.setIconColor(WHITE);
                addMarks.setGraphic(addIcon);

                editMarks.setStyle("-fx-background-color:orange;"+"-fx-text-fill:white;");
                addMarks.setStyle("-fx-background-color:blue;"+"-fx-text-fill:white;");


                addMarks.setOnAction(event->{
                    ClassLevelStudents student=getTableView().getItems().get(getIndex());

                    AddStudentMarks(student);

                });
            }

            protected void updateItem(Void item, boolean empty){
                super.updateItem(item,empty);

                if(empty){
                    setGraphic(null);
                }else{
                    setGraphic(box);
                }

            }
        });
    }

    public void AddStudentMarks(ClassLevelStudents student){

        System.out.println(student.getAdmission_number());
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/MarksForm.fxml"));
            Parent root=loader.load();

            MarksForm marksForm=loader.getController();
            marksForm.setAdmissionNumber(student.getAdmission_number());

            Stage stage=new Stage();
            Scene scene=new Scene(root);

            stage.setTitle("Add Marks");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(ClassLevelResultsTable.getScene().getWindow());
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
