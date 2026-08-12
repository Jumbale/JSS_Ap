package org.example.dataTransferObject;

public class LoginResult <LoginResponse> {
    private int statusCode;

    private LoginResponse response;

    public LoginResult(int statusCode, LoginResponse response) {
        this.statusCode = statusCode;
        this.response = response;


    }

    public int  getStatusCode(){
        return statusCode;
    }


    public  LoginResponse getResponse(){
        return response;
    }
}