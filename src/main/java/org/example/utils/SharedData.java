package org.example.utils;

public class SharedData {

    //creating a single instance of SharedData in memory(RAM)- only a single instance exists in memory
    private static final SharedData instance=new SharedData();

    private  String teacherEmail;

    private String admissionNumber;

    private String term;
    private String school;
    private String exam;

    private String academicYear;

    private String url;

    //a private constructor to prevent creating a new object using 'new SharedData()'
    private SharedData(){}

// this method provide access to the object created in memory to modify the variable values...
    public static SharedData getInstance(){
        return instance;
    }

    public void setAdmissionNumber(String admissionNumber){
        this.admissionNumber=admissionNumber;
    }

    public String getTeacherEmail(){
        return teacherEmail;
    }

    public void setTeacherEmail(String email){
        this.teacherEmail=email;
    }

    public String getAdmissionNumber(){
        return admissionNumber;
    }

    public void setExam(String exam){
        this.exam=exam;
    }

    public String getExam(){
        return exam;
    }

    public void setSchool(String school){
        this.school=school;
    }

    public String getSchool(){
        return school;
    }

    public void setTerm(String term){
        this.term=term;
    }

    public String getTerm(){
        return term;
    }
    public void setAcademicYear(String academicYear){
        this.academicYear=academicYear;
    }

    public String getAcademicYear(){
        return academicYear;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public String getUrl(){
        return url;
    }
}
