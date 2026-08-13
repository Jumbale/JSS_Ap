package org.example.dataTransferObject;

public class FetchSubjects {
    private int subject_id;
    private String subject_name;
    private String subject_code;


    private String department;

    public FetchSubjects(int subject_id,String subject_name, String subject_code, String department) {
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.department = department;
        this.subject_id=subject_id;
    }

    public String getSubjectCode(){
        return subject_code;
    }

    public String getSubjectName(){
        return subject_name;
    }

    public String getDepartment(){
        return department;
    }
    public int getSubject_id(){
        return subject_id;
    }
}
