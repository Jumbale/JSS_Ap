package org.example.dataTransferObject;

public class HttpNewExamRegistration<NewExamRegistrationResponse>{
    private int statusCode;
    private NewExamRegistrationResponse newExamRegistrationResponse;

    public HttpNewExamRegistration(int statusCode,NewExamRegistrationResponse newExamRegistrationResponse ){
        this.statusCode=statusCode;
        this.newExamRegistrationResponse=newExamRegistrationResponse;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public NewExamRegistrationResponse getNewExamRegistrationResponse(){
        return newExamRegistrationResponse;
    }

}
