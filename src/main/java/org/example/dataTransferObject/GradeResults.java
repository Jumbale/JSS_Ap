package org.example.dataTransferObject;

public class GradeResults {
    private String gender;
    private String admission_number;
    private String first_name;
    private String last_name;
    private String subject_name;
    private String score;
    private String remarks;

    public GradeResults(String gender,String admission_number,String first_name,
                        String last_name,String subject_name,String score,String remarks){
        this.gender=gender;
        this.admission_number=admission_number;
        this.first_name=first_name;
        this.last_name=last_name;
        this.subject_name=subject_name;
        this.score=score;
        this.remarks=remarks;

    }
    public String getGender(){
        return gender;
    }
    public String getAdmission_number(){
        return admission_number;
    }
    public String getFirst_name(){
        return first_name;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getSubject_name(){
        return subject_name;
    }
    public String getScore(){
        return score;
    }
    public String getRemarks(){
        return remarks;
    }
}
