package org.example.dataTransferObject;

public class HttpAddNewSubject<AddNewSubjectResponse>{
    private AddNewSubjectResponse addNewSubjectResponse;
    private int statuscode;

    public HttpAddNewSubject(AddNewSubjectResponse addNewSubjectResponse,int statuscode){
        this.addNewSubjectResponse=addNewSubjectResponse;
        this.statuscode=statuscode;
    }
    public AddNewSubjectResponse getAddNewSubjectResponse(){
        return addNewSubjectResponse;
    }

    public int getStatuscode(){
        return statuscode;
    }
}
