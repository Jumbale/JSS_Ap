package org.example.dataTransferObject;

public class FetchSubjects {
    private String SubjectCode;
    private String SubjectName;
    private String Department;

    public FetchSubjects(String SubjectCode, String SubjectName, String Department) {
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
        this.Department = Department;
    }

    public String getSubjectCode(){
        return SubjectCode;
    }

    public String getSubjectName(){
        return SubjectName;
    }

    public String getDepartment(){
        return Department;
    }
}
