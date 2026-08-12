package org.example.dataTransferObject;

public class ApiResponse {
    private String message;

    private DataModel users;



    public String getMessage(){
        return message;
    }

    public DataModel getModel(){
        return  users;
    }
}
