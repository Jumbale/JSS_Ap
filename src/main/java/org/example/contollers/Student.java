package org.example.contollers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.FetchStudent;
import org.example.services.ApiServices;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

import static javafx.scene.paint.Color.WHITE;

public class Student {

    @FXML
    public TableColumn admission_number;
    @FXML
    public TableColumn grade2;
    @FXML
    public ImageView adminProfile;
    @FXML
    public Label studentName;
    @FXML
    public Label admissionNumber;
    @FXML
    public Label studentGrade1;
    @FXML
    public Label editableStatus;
    @FXML
    public Label admDate;
    @FXML
    public Label studentStatus;
    @FXML
    public Label academicYear;
    @FXML
    public Label studentFullname;
    @FXML
    public Label studentDOB;
    @FXML
    public Label studentGender;
    @FXML
    public Label studentNationality;
    @FXML
    public Label studentReligion;
    @FXML
    public Label studentResidence;
    @FXML
    public Label parentFullName;
    @FXML
    public Label parentPhonenumber;
    @FXML
    public Label parentEmail;
    @FXML
    public Label parentOccupation;
    @FXML
    public Label studentGrade2;
    @FXML
    public Label studentTeacher;
    @FXML
    public Label academicYear2;
    @FXML
    public Label studentHouse;
    public Label studentCategory;
    @FXML
    private TableView <FetchStudent>tableStudents;
    @FXML
    public TableColumn  <FetchStudent,String> firstName;
    @FXML
    public TableColumn  <FetchStudent,String> lastName;
    @FXML
    public TableColumn  <FetchStudent,String> Gender;
    @FXML
    public TableColumn  <FetchStudent,String> Grade;
    @FXML
    public TableColumn   <FetchStudent,String>Residence;
    @FXML
    public TableColumn  <FetchStudent,String> Nationality;
    @FXML
    public TableColumn <FetchStudent,Integer> id;
    @FXML
    public TableColumn <FetchStudent,String> admissionNumber2;
    @FXML
    public TableColumn<FetchStudent,Void> Actions;


    public void initialize(){
        tableStudents.setPlaceholder(new Label("No student Found"));

        id.setCellFactory(col->new TableCell<FetchStudent,Integer>(){
            @Override
                protected void updateItem(Integer item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setText(null);
                    }else{
                        setText(String.valueOf(getIndex()+1));
                    }
            }
        });
        admissionNumber2.setCellValueFactory( new PropertyValueFactory<>("admission_number"));
        firstName.setCellValueFactory( new PropertyValueFactory<>("first_name"));
        lastName.setCellValueFactory( new PropertyValueFactory<>("last_name"));
        Gender.setCellValueFactory( new PropertyValueFactory<>("gender"));
        Grade.setCellValueFactory( new PropertyValueFactory<>("grade"));
        Residence.setCellValueFactory( new PropertyValueFactory<>("residence"));
        Nationality.setCellValueFactory( new PropertyValueFactory<>("nationality"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadStudents();
        ActionButtons();

    }

    public void  loadStudents(){
        ApiServices apiservices=new ApiServices();
        apiservices.getStudentData()
                .thenAccept(result->{
                    //String  firstName=result.getStudentData()[0].getFirst_name();
                   // String  lastName=result.getStudentData()[0].getLast_name();
                   // System.out.println(firstName);
                   // System.out.println(lastName);
                   ObservableList<FetchStudent>studentData=FXCollections.observableArrayList(result.getStudentData());

                    Platform.runLater(()->{
                        tableStudents.setItems(studentData);
                    });
                });
    }


    public void ActionButtons(){
      Actions.setCellFactory(param->new TableCell<>(){
          private final Button editButton=new Button("Edit");


          private final Button deleteButton=new Button("Delete");

          private final Button viewButton=new Button("View");

          private final HBox hbox=new HBox();





          {
              FontIcon editIcon=new FontIcon("fas-edit");
              editIcon.setIconColor(WHITE);
              editButton.setGraphic(editIcon);

              FontIcon viewIcon=new FontIcon("fas-eye");
              viewIcon.setIconColor(WHITE);
              viewButton.setGraphic(viewIcon);

              FontIcon deleteIcon=new FontIcon("fas-trash-alt");
              deleteIcon.setIconColor(WHITE);
              deleteButton.setGraphic(deleteIcon);

              hbox.setSpacing(10);
              hbox.setAlignment(Pos.CENTER);
              viewButton.setStyle("-fx-background-color:#22C55E;"+"-fx-text-fill:white;");//green
              editButton.setStyle("-fx-background-color:#F59E0B;"+"-fx-text-fill:white;");//amber
              deleteButton.setStyle("-fx-background-color:#EF4444;"+"-fx-text-fill:white;");//red

              hbox.getChildren().addAll(editButton,deleteButton,viewButton);
              editButton.setOnAction(event->{
                  FetchStudent  student=getTableView().getItems().get(getIndex());

              });

              deleteButton.setOnAction(event->{
                 FetchStudent  student=getTableView().getItems().get(getIndex());
                  handleDelete(student);

              });

              editButton.setOnAction(event->{
                  FetchStudent  student=getTableView().getItems().get(getIndex());

                  try {
                      FXMLLoader loader= new FXMLLoader(getClass().getResource("/org/example/editStudent.fxml"));

                      Parent root=loader.load();
                      editStudent editStudent=loader.getController();
                      editStudent.setUpdateStudent(student);
                      editStudent.setOnloadStudentData(Student.this::loadStudents);
                      Stage stage =new Stage();

                      Scene scene=new Scene(root);

                      stage.setScene(scene);
                      stage.initOwner(editButton.getScene().getWindow());
                      stage.initModality(Modality.APPLICATION_MODAL);
                      stage.setTitle("Edit student");
                      stage.setResizable(false);
                      stage.show();





                  } catch (IOException e) {
                      throw new RuntimeException(e);
                  }


              });

              viewButton.setOnAction(event->{
                  FetchStudent  student=getTableView().getItems().get(getIndex());
                  handleView(student);


              });






          }

          @Override
          protected void updateItem(Void item, boolean empty){
              super.updateItem(item,empty);
                if(empty){
                    setGraphic(null);

                }else{
                    setAlignment(Pos.CENTER);
                    setGraphic(hbox);
                }

          }
      });



    }
    public void handleDelete(FetchStudent student){

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Student");
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText("Delete "+ student.getFirst_name()+"?");
        alert.setContentText("This student will be marked inactive and will no longer appear" +
                " in the active list");

        Optional <ButtonType> outcome=alert.showAndWait();

        if(outcome.isPresent()&& outcome.get()==ButtonType.OK){

            ApiServices apiServices=new ApiServices();
            apiServices.patchStudent(student)
                    .thenAccept(result->{
                        String message=result.getPatchResponse().getMessage();
                        int statusCode=result.getStatusCode();

                        Platform.runLater(()->{

                            if(statusCode>=400){
                                Alert alert2=new Alert(Alert.AlertType.ERROR);
                                alert2.setTitle("Delete Student");
                                alert2.setHeaderText(null);
                                alert2.initStyle(StageStyle.UTILITY);
                                alert2.setContentText(message);
                            }else{
                                Alert alert2=new Alert(Alert.AlertType.INFORMATION);
                                alert2.setTitle("Delete Student");
                                alert2.setHeaderText(null);
                                alert2.initStyle(StageStyle.UTILITY);
                                alert2.setContentText(message);




                            }


                        });
                    });

            loadStudents();

        }





}
    public void handleView(FetchStudent student){

        String formattedDOB=student.getDOB().split("T")[0];
        String dateAdmitted=student.getCreatedAt().split("T")[0];

       // System.out.print(dateAdmitted);
        //System.out.print(student.getStatus());


        if(student.getStatus().equals("active")){
            System.out.print(dateAdmitted);

            editableStatus.setStyle("-fx-background-color:#DCFCE7;"+"-fx-text-fill:#166534;" +
                    "-fx-padding:5 5 5 5;"+"-fx-background-radius:8px;");
            editableStatus.setText(student.getStatus());


        }else if(student.getStatus().equals("suspended")){
            System.out.print(student.getStatus());
            editableStatus.setStyle("-fx-background-color:#FEE2E2;"+"-fx-text-fill:#B91C1C; "+
                    "-fx-padding:5 5 5 5;"+"-fx-background-radius:8px;");
            editableStatus.setText(student.getStatus());

        }else if(student.getStatus().equals("graduated")){
            editableStatus.setStyle("-fx-background-color:#DBEAFE;"+"-fx-text-fill:#1D4ED8;"+
                    "-fx-padding:5 5 5 5;"+"-fx-background-radius:8px;");
            editableStatus.setText(student.getStatus());

        }else if(student.getStatus().equals("transfered")){
            editableStatus.setStyle("-fx-background-color:#FEF3C7;"+"-fx-text-fill:#B45309;"+
                    "-fx-padding:5 5 5 5;"+"-fx-background-radius:8px;");
            editableStatus.setText(student.getStatus());

        }else if(student.getStatus().equals("expelled")){
            System.out.print(student.getStatus());
            editableStatus.setStyle("-fx-background-color:#FECACA;"+"-fx-text-fill:#991B1B;"+
                    "-fx-padding:5 5 5 5;"+"-fx-background-radius:8px;");
            editableStatus.setText(student.getStatus());

        }else if(student.getStatus().equals("inactive")){
            editableStatus.setStyle("-fx-background-color:#F3F4F6;"+"-fx-text-fill:#4B5563;"+
                    "-fx-padding:5 5 5 5"+"-fx-background-radius:8px;");
            editableStatus.setText(student.getStatus());

        }


        studentName.setText(student.getFirst_name()+" "+student.getLast_name());
        admission_number.setText(student.getAdmission_number());
        grade2.setText(student.getGrade());
        studentGrade1.setText(student.getGrade());
        studentStatus.setText(student.getStatus());
        studentFullname.setText(student.getFirst_name()+" "+student.getLast_name());
        studentDOB.setText(formattedDOB);
        studentGender.setText(student.getGender());
        studentNationality.setText(student.getNationality());
        studentReligion.setText(student.getReligion());
        studentResidence.setText(student.getResidence());
        studentGrade2.setText(student.getGrade());
        admDate.setText(dateAdmitted);
        admissionNumber.setText(student.getAdmission_number());





    }


}
