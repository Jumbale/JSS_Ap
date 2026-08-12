package org.example.dataTransferObject;

public class HttpAcademicYear <AcademicYearResponse>{
    private AcademicYearResponse academicYearResponse;
    private int statuscode;

    public HttpAcademicYear(AcademicYearResponse academicYearResponse,int statuscode){
        this.academicYearResponse=academicYearResponse;
        this.statuscode=statuscode;

    }

    public AcademicYearResponse getAcademicYearResponse() {
        return academicYearResponse;
    }
    public int getStatuscode(){
        return statuscode;
    }
}
