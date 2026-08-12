package org.example.dataTransferObject;

public class HttpUpdateStudent <responseUpdateStudent>{
    private responseUpdateStudent updateStudent;
    private int statusCode;

    public HttpUpdateStudent(responseUpdateStudent updateStudent, int statusCode ){
        this.updateStudent=updateStudent;
        this.statusCode=statusCode;
    }
    public responseUpdateStudent getUpdateStudent(){
        return updateStudent;
    }

    public int getStatusCode(){
        return statusCode;
    }


}
