package org.example.dataTransferObject;

public class ScoresAndComments {

    private String termName;
    private String Exam;
    private String yearName;
    private String AdmissionNumber;
    private String SubjectCode;
    private String Score;
    private String schoolName;
    private String subjectName;
    private String Remarks;

    public ScoresAndComments(String AdmissionNumber, String SubjectCode,String subjectName,
                             String termName,String yearName,String Score,String Remarks,String schoolName,String Exam){

        this.Exam=Exam;
        this.termName=termName;
        this.AdmissionNumber=AdmissionNumber;
        this.SubjectCode=SubjectCode;
        this.Score=Score;
        this.schoolName=schoolName;
        this.Remarks=Remarks;
        this.subjectName=subjectName;
        this.yearName=yearName;

    }
    public String getTermName(){
        return termName;
    }

    public String getExam(){
        return Exam;
    }

    public String getYearName(){
        return yearName;
    }
    public String getAdmissionNumber(){
        return AdmissionNumber;
    }
    public String getSubjectCode(){
        return SubjectCode;
    }
    public String getScore(){
        return Score;
    }
    public String getSchoolName(){
        return schoolName;
    }

    public String getRemarks(){
        return Remarks;
    }
    public String getSubjectName(){
        return subjectName;
    }

}
