package org.example.dataTransferObject;

public class HttpPatch <PatchResponse>{
    private PatchResponse patchResponse;

    private int statusCode;

    public HttpPatch(PatchResponse patchResponse, int statusCode){
        this.patchResponse=patchResponse;
        this.statusCode=statusCode;
    }

    public PatchResponse getPatchResponse(){
        return patchResponse;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
