package org.example.dataTransferObject;

public class HttpResult<res> {
    private int statusCode;

    private res body;

    public HttpResult(int statusCode, res body ){
        this.statusCode=statusCode;
        this.body=body;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public res getBody(){
        return  body;
    }
}
