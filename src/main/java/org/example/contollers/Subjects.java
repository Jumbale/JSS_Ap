package org.example.contollers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.FetchSubjects;
import org.example.services.ApiServices;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

import static javafx.scene.paint.Color.WHITE;

public class Subjects {
    @FXML
    public TableView <FetchSubjects>subjectsTable;
    @FXML
    public TableColumn<FetchSubjects,String> subjectCode;
    @FXML
    public TableColumn <FetchSubjects,String> subjectName;
    @FXML
    public TableColumn <FetchSubjects,String>department;
    @FXML
    public TableColumn<FetchSubjects,Void> actions;
    @FXML
    public Button ButtonCancel;
    @FXML
    public Button addClassButton;


    @FXML
    public void initialize(){
        subjectCode.setCellValueFactory(new PropertyValueFactory<>("SubjectCode"));
        subjectName.setCellValueFactory(new PropertyValueFactory<>("SubjectName"));
        department.setCellValueFactory(new PropertyValueFactory<>("Department"));


        loadSubjects();
        action();
    }



    public void loadSubjects(){
        ApiServices apiServices=new ApiServices();
        apiServices.FetchSubject()
                .thenAccept(result->{
                    ObservableList<FetchSubjects> subjectsList= FXCollections.observableArrayList(result.getFetchSubjects());

                    Platform.runLater(()->{
                        subjectsTable.setItems(subjectsList);
                        System.out.println("subjects...subjectsList");

                    });
                });
    }

    public void action(){
        actions.setCellFactory(param->new TableCell<>(){
            private final Button editButton=new Button("Edit");
            private final Button Delete=new Button("Delete");

            private final HBox hbox =new HBox();

            {
                editButton.setStyle("-fx-background-color:#F59E0B;-fx-text-fill:white;");
                Delete.setStyle("-fx-background-color:#EF4444;-fx-text-fill:white;");

                FontIcon editIcon=new FontIcon("fas-edit");
                editIcon.setIconColor(WHITE);
                editButton.setGraphic(editIcon);

                FontIcon deleteIcon=new FontIcon("fas-trash-alt");
                deleteIcon.setIconColor(WHITE);
                Delete.setGraphic(deleteIcon);

                hbox.setSpacing(10);
                hbox.setAlignment(Pos.CENTER);
                hbox.getChildren().addAll(editButton,Delete);

                editButton.setOnAction(event->{
                    FetchSubjects subjects=getTableView().getItems().get(getIndex());
                });

                Delete.setOnAction(event->{
                    FetchSubjects subjects=getTableView().getItems().get(getIndex());
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item,empty);

                if(empty){
                    setGraphic(null);
                }else{
                    setGraphic(hbox);
                }
            }
        });
    }

    public void createNewSubject(MouseEvent mouseEvent) throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/addNewSubject.fxml"));
            Parent root=loader.load();

            SubjectRegister addSubject=loader.getController();
            addSubject.setOnLoadSubjects(this::loadSubjects);
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(addClassButton.getScene().getWindow());
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("New subject");
            stage.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public void CancelButton(MouseEvent mouseEvent) {
        Stage stage=(Stage)ButtonCancel.getScene().getWindow();
        stage.close();
    }


}
