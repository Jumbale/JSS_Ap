package org.example.dataTransferObject;

public class NewExamRegistration {
    private String examName;
    private String termName;
    private String examStatus;
    private String examDate;

    public NewExamRegistration(String examName,String termName,String examStatus,String examDate){
        this.examName=examName;
        this.termName=termName;
        this.examStatus=examStatus;
        this.examDate=examDate;

    }
    public String getExamName(){
        return examName;
    }
    public String getTermName(){
        return termName;
    }

    public String getExamStatus(){
        return examStatus;
    }

    public String getExamDate(){
        return examDate;
    }
}
