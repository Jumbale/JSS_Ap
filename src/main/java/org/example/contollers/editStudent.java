package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.FetchStudent;
import org.example.dataTransferObject.UpdateStudent;
import org.example.services.ApiServices;

import java.time.LocalDate;
import java.util.Optional;

public class editStudent {


@FXML
    public Button BuutonUpdateStudent;
    private FetchStudent student;
    public   DatePicker S_DOB;
    public Button ButtonCancel;

    public TextField SfirstName;
    public TextField S_secondName;
    public TextField AdmissionNumber;
    public ComboBox <String>Sgrade;
    public ComboBox<String> Sgender;
    public TextField Sresidence;
    public ComboBox <String>S_religion;
    public TextField Snationality;
    public ComboBox<String> S_status;
    public ComboBox<String> Scategory;

    private Runnable studentData;


    public void initialize(){
        Sgrade.getItems().addAll("Grade 7", "Grade 8", "Grade 9");

        Sgender.getItems().addAll("male", "female");

        S_religion.getItems().addAll("Christianity", "Islamic", "Hindu");

        S_status.getItems().addAll("active", "suspended","graduated","transferred","dropped_out",
                "expelled","Inactive");

        Scategory.getItems().addAll("day_scholar", "boarder");
    }

    public void setUpdateStudent(FetchStudent student){

        String date=student.getDOB().split("T")[0];

        LocalDate localdate= LocalDate.parse(date);

        SfirstName.setText(student.getFirst_name());

        S_secondName.setText(student.getLast_name());

        AdmissionNumber.setText(student.getAdmission_number());

        Sgender.setValue(student.getGender());

        Sgrade.setValue(student.getGrade());
        
        S_religion.setValue(student.getReligion());

        S_status.setValue(student.getStatus());

        Sresidence.setText(student.getResidence());

        Snationality.setText(student.getNationality());

        Scategory.setValue(student.getCategory());

        S_DOB.setValue(localdate);


        this.student=student;

    }

    public void updateStudent(MouseEvent mouseEvent) {

        System.out.println("This field is "+Sgrade.getValue());
        if (((Sgrade.getValue() !=null ||
                (Sgender.getValue()) !=null||S_DOB.getValue()!=null))||(!(SfirstName.getText().trim().isEmpty())||
                !(S_secondName.getText().trim().isEmpty())|| !(AdmissionNumber.getText().trim().isEmpty())||
                !(Snationality.getText().trim().isEmpty())||!(Sresidence.getText().trim().isEmpty())
                ) ){

            LocalDate localDate=S_DOB.getValue();

            String firstName = SfirstName.getText();

            String lastName = S_secondName.getText();

            String AdmNumber = AdmissionNumber.getText();

            String Nationality = Snationality.getText();

            String Residence = Sresidence.getText();

            String Gender = Sgender.getValue();

            String Grade = Sgrade.getValue();

            String Status=S_status.getValue();

            String Category=Scategory.getValue();

            String Religion=S_religion.getValue();

            String  Date= String.valueOf(localDate);
            System.out.println(Religion);
            System.out.println(firstName);


            UpdateStudent updateStudentData=new UpdateStudent(AdmNumber,firstName,lastName,Gender,
                    Grade,Residence,Nationality,Religion,Status,Category,Date);

            ApiServices apiServices=new ApiServices();
            apiServices.UpdateStudentDetails(updateStudentData)
                    .thenAccept(result->{
                        String message=result.getUpdateStudent().getMessage();
                        int statusCode=result.getStatusCode();

                        Platform.runLater(()->{
                           System.out.println(message);
                            if(statusCode>=400){
                                Alert alert2=new Alert(Alert.AlertType.ERROR);
                                alert2.setTitle("Update Student");
                                alert2.setHeaderText(null);
                                alert2.initStyle(StageStyle.UTILITY);
                                alert2.setContentText(message);
                            }else{
                                Alert alert2=new Alert(Alert.AlertType.INFORMATION);
                                alert2.setTitle("Update Student");
                                alert2.setHeaderText(null);
                                alert2.initStyle(StageStyle.UTILITY);
                                alert2.setContentText(message);

                              //  System.out.println(message);

                                Optional<ButtonType> update= alert2.showAndWait();
                                if(update.isPresent()&& update.get()==ButtonType.OK){
                                    Stage stage=(Stage)BuutonUpdateStudent.getScene().getWindow();
                                    if (studentData!=null) {
                                            studentData.run();
                                    }

                                    stage.close();







                                }






                            }
                        });
                    });

        }


    }







       // {


           // String Grade = Sgrade.getValue();

           // String Category = Scategory.getValue();

           // String Religion = S_religion.getValue();

           // String Status = S_status.getValue();

           // String Gender = Sgender.getValue();
       // }

    //}

    public void closedRegister(MouseEvent mouseEvent) {
        Stage stage=(Stage)ButtonCancel.getScene().getWindow();
        stage.close();
    }

    public void setOnloadStudentData(Runnable studentData){
        this.studentData=studentData;


    }
}
