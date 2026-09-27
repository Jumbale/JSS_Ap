package org.example.dataTransferObject;

public class HttpScoresAndComments <ScoresAndCommentsResponse>{
    private int statuscode;
    private ScoresAndCommentsResponse scoresAndCommentsResponse;

    public HttpScoresAndComments(int statuscode,ScoresAndCommentsResponse scoresAndCommentsResponse){
        this.statuscode=statuscode;
        this.scoresAndCommentsResponse=scoresAndCommentsResponse;

    }

    public int getStatuscode(){
        return statuscode;
    }
    public ScoresAndCommentsResponse getScoresAndCommentsResponse(){
        return scoresAndCommentsResponse;
    }

}
