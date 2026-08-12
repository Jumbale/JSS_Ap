package org.example.dataTransferObject;

public class HttpStudent <FetchStudent>{
    private FetchStudent studentData;

    private int statusCode;

    public HttpStudent(FetchStudent studentData, int statusCode){
        this.statusCode=statusCode;
        this.studentData=studentData;

    }

    public FetchStudent getStudentData(){
        return studentData;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
