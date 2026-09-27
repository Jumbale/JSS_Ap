package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.Main;
import org.example.utils.CheckEmptyFields;
import org.example.utils.SharedData;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ResultsSummary {
    @FXML
    public TextField AdmissionNumber;
    @FXML
    public TextField Term;
    @FXML
    public TextField School;
    @FXML
    public TextField Exam;
    @FXML
    public Label TotalMarks;
    @FXML
    public Label AverageMarks;
    @FXML
    public Label OverallLevel;
    @FXML
    public Label TotalSubjects;
    @FXML
    public Button saveExamButton;
    @FXML
    public TextField AcademicYear;

    private String admissionNumber;
    private String term;
    private String academicYear;
    private String school;
    private String exam;


    private static final String BASE_URL="http://localhost:5000/api/jss";
    String  studentReportUrl=BASE_URL+"/fetchReportCard/";

    public void initialize(){
        setStudentDetails();
    }

    public void GenerateReport(MouseEvent mouseEvent) throws IOException, InterruptedException {
        String admissionNumber=AdmissionNumber.getText();
        String term=Term.getText();
        String school=School.getText();
        String exam=Exam.getText();
        String academicYear=AcademicYear.getText();

        String[]fields={admissionNumber,term,school,exam,academicYear};

        boolean checkedFields= CheckEmptyFields.CheckFields(fields);

        if(checkedFields){
           // HttpClient client= HttpClient.newHttpClient();

            String academic_year= URLEncoder.encode(academicYear, StandardCharsets.UTF_8);
            String terms=URLEncoder.encode(term,StandardCharsets.UTF_8).replace("+","%20");
            String examName=URLEncoder.encode(exam,StandardCharsets.UTF_8).replace("+","%20");
            String admission_number=URLEncoder.encode(admissionNumber,StandardCharsets.UTF_8).replace("+","%20");
            String schoolName=URLEncoder.encode(school,StandardCharsets.UTF_8).replace("+","%20");
            String gradeMarksUrl=studentReportUrl+admission_number+"/"+terms+"/"+academic_year+"/"+schoolName+"/"+examName;


            SharedData.getInstance().setUrl(gradeMarksUrl);
            //System.out.println(gradeMarksUrl);
        try {
            Main.openWebPage(gradeMarksUrl);
            System.out.println(gradeMarksUrl);
        }catch(IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        }else{
            return;
        }




    }

    public void setStudentDetails()  {
        this.academicYear= SharedData.getInstance().getAcademicYear();
        this.admissionNumber=SharedData.getInstance().getAdmissionNumber();
        this.exam=SharedData.getInstance().getExam();
        this.term=SharedData.getInstance().getTerm();
        this.school=SharedData.getInstance().getSchool();

        AdmissionNumber.setText(admissionNumber);
        Term.setText(term);
        Exam.setText(exam);
        AcademicYear.setText(academicYear);
        School.setText(school);

    }
}
