package org.example.dataTransferObject;

public class HttpRegisterStudent <RegisterStudent>{
    private RegisterStudent studentData;

    private int statusCode;

    public HttpRegisterStudent(RegisterStudent studentData, int statusCode){
        this.statusCode=statusCode;
        this.studentData=studentData;

    }

    public RegisterStudent getStudent(){
        return studentData;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
