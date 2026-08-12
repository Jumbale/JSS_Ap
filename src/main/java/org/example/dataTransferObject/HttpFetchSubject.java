package org.example.dataTransferObject;

public class HttpFetchSubject <FetchSubjects>{
    private FetchSubjects fetchSubject;
    private int statusCode;

    public HttpFetchSubject(FetchSubjects fetchSubject,int statusCode){
        this.fetchSubject=fetchSubject;
        this.statusCode=statusCode;


    }

    public  FetchSubjects getFetchSubjects(){
        return fetchSubject;
    }

    public  int getStatusCode(){
        return statusCode;
    }

}
