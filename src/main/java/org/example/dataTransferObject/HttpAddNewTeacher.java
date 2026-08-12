package org.example.dataTransferObject;

public class HttpAddNewTeacher <AddNewTeacherResponse>{
    private AddNewTeacherResponse addedTeacher;

    private int statusCode;

    public  HttpAddNewTeacher(AddNewTeacherResponse addedTeacher,int statusCode){
        this.statusCode=statusCode;
        this.addedTeacher=addedTeacher;


    }

    public AddNewTeacherResponse getAddedTeacher(){
        return addedTeacher;
    }

    public int  getStatusCode(){
        return statusCode;
    }
}
