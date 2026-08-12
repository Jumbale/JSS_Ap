package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.AcademicYearDetails;
import org.example.services.ApiServices;
import org.example.utils.CheckEmptyFields;

import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.DateCell;

public class AcademicYears {
    @FXML
    public ComboBox<String> yearName;
    @FXML
    public ComboBox<String> yearStatus;

    @FXML
    public ComboBox <String>academicYear;
    @FXML
    public ComboBox <String>termName;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    public Button ButtonCancel1;
    @FXML
    public Button addTeacherButton1;

    @FXML
    public void initialize(){
        yearName.getItems().addAll("2027/2028","2028/2029","2029/2030","2030/2031","2031/2032","2032/2033","2033/2034");
        yearStatus.getItems().addAll("active","upcoming","closed");

        academicYear.getItems().addAll("2027/2028","2028/2029","2029/2030","2030/2031","2031/2032","2032/2033","2033/2034");
        termName.getItems().addAll("Term One","Term Two","Term Three");



        yearName.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue==null) return;

            String []years=newValue.split("/");

            int startYear=Integer.parseInt(years[0]);
            int endYear=Integer.parseInt(years[1]);
            System.out.println("Start Year "+startYear);
            System.out.println("Start Year "+startYear);

            startDate.setDayCellFactory(dp->new DateCell(){
                @Override
                public void updateItem(LocalDate date,boolean empty){
                    super.updateItem(date, empty);

                    setDisable(empty || date.getYear()!=startYear);
                }
            });

            endDate.setDayCellFactory(dp->new DateCell(){
                @Override
                public void updateItem(LocalDate date,boolean empty){
                    super.updateItem(date, empty);

                    setDisable(empty || date.getYear()!=endYear);
                }
            });


        });


        academicYear.valueProperty().addListener((obs,oldValue,newValue)->{
            if(yearName.getValue()==null|| academicYear.getValue()==null) return;

            if(newValue==null) return;

            if(!newValue.equals(yearName.getValue())){
                academicYear.setStyle("-fx-border-color:#DC2626; -fx-border-width:2px;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-background-color:white;");


            }else{
                academicYear.setStyle("-fx-border-color:#c0c0c0;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-border-width:1px;-fx-background-color:white;");
            }
        });


        yearName.valueProperty().addListener((obs,oldValue,newValue)->{
            if(yearName.getValue()==null|| academicYear.getValue()==null) return;
            if(newValue==null) return;

            if(!newValue.equals(academicYear.getValue())){
                academicYear.setStyle("-fx-border-color:#DC2626; -fx-border-width:2px;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-background-color:white;");


            }else{
                academicYear.setStyle("-fx-border-color:#c0c0c0;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-border-width:1px;-fx-background-color:white;");
            }
        });

        startDate.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null){
                startDate.setStyle("-fx-border-color:#c0c0c0;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-border-width:1px;-fx-background-color:white;");
            }
        });

        endDate.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null){
                endDate.setStyle("-fx-border-color:#c0c0c0;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-border-width:1px;-fx-background-color:white;");
            }
        });

        yearName.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null){
                yearName.setStyle("-fx-border-color:#c0c0c0;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-border-width:1px;-fx-background-color:white;");
            }
        });

        academicYear.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null){
                academicYear.setStyle("-fx-border-color:#c0c0c0;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-border-width:1px;-fx-background-color:white;");
            }
        });
    }



    public void Save(MouseEvent mouseEvent) {


        //String[] fields={startDate,endDate,termName,};

        if(startDate.getValue()!=null&& endDate.getValue()!=null && yearName.getValue()!=null && academicYear.getValue()!=null){





          //  System.out.println("No error");
           if(!yearName.getValue().equals(academicYear.getValue())) {
               return;
           }





            LocalDate localStartDate=startDate.getValue();
            LocalDate localEndDate=endDate.getValue();

            String TermName= termName.getValue();
            String YearName= yearName.getValue();
            String AcademicYear=academicYear.getValue();
            String YearStatus=yearStatus.getValue();
            String startDate=localStartDate.toString();
            String endDate=localEndDate.toString();

            AcademicYearDetails academicYearDetails=new AcademicYearDetails(TermName,YearName,AcademicYear,YearStatus,
                    startDate,endDate);

            ApiServices apiServices=new ApiServices();
            apiServices.createAcademicYear(academicYearDetails)
                    .thenAccept(result->{
                        String message=result.getAcademicYearResponse().getMessage();
                        int statuscode=result.getStatuscode();

                        Platform.runLater(()->{
                            if(statuscode>=400) {
                                Alert alert=new Alert(Alert.AlertType.ERROR );
                                alert.setTitle("Academic Details Error");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);
                                alert.show();

                            }else{
                                Alert alert=new Alert(Alert.AlertType.INFORMATION );
                                alert.setTitle("Academic Details");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);

                                Optional<ButtonType> modal=alert.showAndWait();
                                if(modal.isPresent()&& modal.get()==ButtonType.OK){
                                    Stage stage=(Stage)addTeacherButton1.getScene().getWindow();
                                    stage.close();
                                }
                            }
                        });
                    });

        }else{

            if(startDate.getValue()==null && endDate.getValue()==null){
                startDate.setStyle("-fx-border-color:#DC2626; -fx-border-width:2px;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-background-color:white;");

                endDate.setStyle("-fx-border-color:#DC2626; -fx-border-width:2px;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-background-color:white;");

            } else if (endDate.getValue()==null) {
                endDate.setStyle("-fx-border-color:#DC2626; -fx-border-width:2px;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-background-color:white;");
            }else if (yearName.getValue()==null) {
                yearName.setStyle("-fx-border-color:#DC2626; -fx-border-width:2px;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-background-color:white;");

            }else if (academicYear.getValue()==null) {
                academicYear.setStyle("-fx-border-color:#DC2626; -fx-border-width:2px;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-background-color:white;");
            }else{
                startDate.setStyle("-fx-border-color:#DC2626; -fx-border-width:2px;" +
                        "-fx-border-radius:10px;-fx-background-radius:10px;-fx-background-color:white;");

            }


        }

          //  System.out.println("Error");
           // Alert alert=new Alert(Alert.AlertType.ERROR );
           // alert.setTitle("Academic Details Error");
            //alert.setHeaderText(null);
           // alert.initStyle(StageStyle.UTILITY);
           // alert.setContentText("Specify term dates");
           // alert.show();
        //}


    }
    public void Cancel(MouseEvent mouseEvent) {
        Stage stage=(Stage)ButtonCancel1.getScene().getWindow();
        stage.close();
    }
}
