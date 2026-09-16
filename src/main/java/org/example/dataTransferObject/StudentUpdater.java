package org.example.dataTransferObject;

public class StudentUpdater {
    private String admNumber;
    private String firstName;
    private String lastName;
    private String Gender;
    private String Grade;
    private String Residence;
    private String Nationality;



    private String Religion;

    private String Status;

    private String Category;

    private String DOB;

    public StudentUpdater(String admNumber, String firstName, String lastName,
                          String Gender, String Grade, String Residence, String Nationality,
                          String Religion, String Status, String Category, String DOB){
        this.admNumber=admNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        this.Gender=Gender;
        this.Grade=Grade;
        this.Residence=Residence;
        this.Nationality=Nationality;
        this.Category=Category;
        this.Status=Status;
        this.DOB=DOB;
        this.Religion=Religion;
    }
    public String getAdmNumber(){
        return admNumber;
    }

}
