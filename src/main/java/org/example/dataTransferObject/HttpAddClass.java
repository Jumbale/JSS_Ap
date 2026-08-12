package org.example.dataTransferObject;

public class HttpAddClass <AddNewClass>{
    private AddNewClass newClass;
    private int statusCode;

    public HttpAddClass(AddNewClass newClass,int statusCode){
        this.newClass=newClass;
        this.statusCode=statusCode;

    }

    public AddNewClass getNewClass(){
        return newClass;
    }

    public int getStatusCode(){
        return statusCode;
    }

}
