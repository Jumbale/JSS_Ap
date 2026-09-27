package org.example.dataTransferObject;

public class HttpGradeResults <GradeResults>{
    private GradeResults gradeResults;
    private int statusCode;

    public HttpGradeResults(int statusCode, GradeResults gradeResults){
        this.statusCode=statusCode;
        this.gradeResults=gradeResults;

    }

    public int getStatusCode(){
        return statusCode;
    }
    public GradeResults getGradeResults(){
        return  gradeResults;
    }
}
