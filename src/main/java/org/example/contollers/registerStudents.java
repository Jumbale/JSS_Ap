package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.RegisterS;
import org.example.services.ApiServices;

import java.time.LocalDate;
import java.util.Optional;

public class registerStudents {
    @FXML
    public TextField AdmissionNumber;
    @FXML
    public ComboBox<String> S_religion;
    @FXML
    public ComboBox <String>S_status;
    @FXML
    public Button ButtonCancel;
    @FXML
    public Button ButtonRegister;
    @FXML


    private DatePicker S_DOB;



    @FXML
    public TextField SfirstName;
    @FXML
    public TextField S_secondName;

    @FXML
    public ComboBox<String> Sgrade;
    @FXML
    public ComboBox<String> Sgender;
    @FXML
    public TextField Sresidence;
    @FXML
    public TextField Snationality;
    @FXML
    public ComboBox<String> Scategory;

    public void initialize() {
        Sgender.getItems().addAll("male", "female");

        Scategory.getItems().addAll("day_scholar", "boarder");

        S_religion.getItems().addAll("Christianity", "Islamic", "Hindu");

        S_status.getItems().addAll("active", "suspended","graduated","transferred","dropped_out",
                                    "expelled","Inactive");

        Sgrade.getItems().addAll("Grade 7", "Grade 8", "Grade 9");
    }





    public void RegisterStudents(MouseEvent mouseEvent) {

        if( !SfirstName.getText().trim().isEmpty()|| !AdmissionNumber.getText().trim().isEmpty()||!S_secondName.getText().trim().isEmpty()
            || !Snationality.getText().trim().isEmpty()||!Sresidence.getText().trim().isEmpty()||((Sgrade.getValue() != null) || (Scategory.getValue() != null )||
                (Sgender.getValue() != null) || (S_religion.getValue() != null) ||
                ( S_DOB.getValue()!= null))){

            String firstName = SfirstName.getText();

            String AdmNumber = AdmissionNumber.getText();

            String lastName = S_secondName.getText();

            String Nationality = Snationality.getText();

            String Residence = Sresidence.getText();

            LocalDate date=S_DOB.getValue();


            System.out.println(AdmNumber);

            //System.out.println(date);



            String Grade = Sgrade.getValue();

            String Category = Scategory.getValue();

            String Religion = S_religion.getValue();

            String Status=S_status.getValue();

            String Gender = Sgender.getValue();


            String DOB = date.toString();
            System.out.println(Gender);
            System.out.println(DOB);
            System.out.println(Category);


            RegisterS registerS = new RegisterS(AdmNumber, firstName, lastName, Grade,
                    Gender, DOB, Residence,Religion,Nationality, Status,Category);

            ApiServices apiServices=new ApiServices();
            apiServices.createStudent(registerS)
                    .thenAccept(result->{
                        int statusCode=result.getStatusCode();
                        String message=result.getStudent().getMessage();

                        Platform.runLater(()->{
                            System.out.println(statusCode);
                            if(statusCode>=400){
                                Alert alert=new Alert(Alert.AlertType.ERROR );
                                alert.setTitle("Register Student");
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);
                                alert.show();

                                return;
                            }else{
                                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Register Student");
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);


                                Optional<ButtonType> closeModal=alert.showAndWait();

                                if(closeModal.isPresent()&& closeModal.get()==ButtonType.OK){
                                    Stage stage=(Stage)ButtonRegister.getScene().getWindow();
                                    stage.close();

                                }


                            }


                        });
                    });



        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR );
            alert.setTitle("Register Student");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("Provide all student details");
            alert.show();

            return;
        }




    }

    public void closedRegister(MouseEvent mouseEvent) {
        Stage stage=(Stage)ButtonCancel.getScene().getWindow();
        stage.close();

    }
}
