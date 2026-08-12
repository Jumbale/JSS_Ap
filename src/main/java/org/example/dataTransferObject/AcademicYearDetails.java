package org.example.dataTransferObject;

public class AcademicYearDetails {
    private String TermName;
    private String YearName;
    private String AcademicYear;
    private String YearStatus;
    private String StartDate;
    private String EndDate;

    public AcademicYearDetails(String TermName,String YearName,String AcademicYear,
                               String YearStatus, String startDate,String endDate){
        this.TermName=TermName;
        this.YearName=YearName;
        this.AcademicYear=AcademicYear;
        this.YearStatus=YearStatus;
        this.StartDate=startDate;
        this.EndDate=endDate;

    }


}
