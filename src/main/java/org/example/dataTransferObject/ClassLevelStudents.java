package org.example.dataTransferObject;

public class ClassLevelStudents {
    private int id;
    private String admission_number;
    private String first_name;
    private String last_name;
    private String created_at;
    private String gender;
    private String grade;
    private String date_of_birth;
    private String residence;
    private String religion;
    private String nationality;
    private String status;
    private String category;
    private String class_id;


    public  ClassLevelStudents(int id,String admission_number,String first_name,
                               String last_name,String created_at,String gender,
                               String grade,String date_of_birth,String residence,
                               String religion,String nationality,String status,
                               String category,String class_id){
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.created_at=created_at;
        this.category=category;
        this.date_of_birth=date_of_birth;
        this.gender=gender;
        this.nationality=nationality;
        this.residence=residence;
        this.class_id=class_id;
        this.grade=grade;
        this.religion=religion;
        this.status=status;
        this.admission_number=admission_number;

    }



    public int getId(){
        return id;
    }

    public String getAdmission_number(){
        return admission_number;
    }

    public String getFirst_name() {
        return first_name;

    }

    public String getLast_name() {
        return last_name;

    }

    public String getGender() {
        return gender;

    }
    public String getCreated_at() {
        return created_at;

    }

    public String getGrade() {
        return grade;

    }

    public String getDate_of_birth() {
        return date_of_birth;

    }

    public String getResidence() {
        return residence;

    }

    public String getReligion() {
        return religion;

    }

    public String getNationality() {
        return nationality;

    }

    public String getStatus() {
        return status;

    }

    public String getCategory() {
        return category;

    }

    public String getClass_id() {
        return class_id;

    }
}
