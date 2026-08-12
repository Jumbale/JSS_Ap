package org.example.dataTransferObject;

public class HttpResetTeacherPassword <ResetTeacherPasswordResponse>{
    private ResetTeacherPasswordResponse passwordResponse;
    int statusCode;

    public HttpResetTeacherPassword(ResetTeacherPasswordResponse passwordResponse,int statusCode){
        this.passwordResponse=passwordResponse;
        this.statusCode=statusCode;

    }

    public ResetTeacherPasswordResponse getPasswordResponse(){
        return passwordResponse;
    }
    public int  getStatusCode(){
        return statusCode;
    }


}
