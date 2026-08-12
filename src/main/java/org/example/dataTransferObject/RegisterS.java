package org.example.dataTransferObject;

public class RegisterS {
    private String AdmNumber;
    private String firstName;
    private String lastName;
    private String Grade;
    private String Gender;
    private String DOB;
    private String Residence;
    private String Religion;
    private String Nationality;
    private String Status;
    private String Category;

    public  RegisterS( String admNumber,String firstName,String lastName,
                       String Grade,String Gender,String DOB,String Residence,
                       String Religion,String Nationality, String Status,
                       String Category){
        this.AdmNumber=admNumber;
        this.Gender=Gender;
        this.Category=Category;
        this.Nationality=Nationality;
        this.DOB=DOB;
        this.Grade=Grade;
        this.Religion=Religion;
        this.Status=Status;
        this.Residence=Residence;
        this.firstName=firstName;
        this.lastName=lastName;

    }
}

