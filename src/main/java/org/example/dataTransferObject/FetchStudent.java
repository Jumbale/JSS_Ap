package org.example.dataTransferObject;

public class FetchStudent {
    private int id;
    private String admission_number;
    private String first_name;
    private String last_name;
    private String created_at;
    private String grade;
    private String gender;
    private String residence;
    private String date_of_birth;
    private String religion;
    private String nationality;
    private String status;
    private String category;
    private String class_id;

    //private String message;



    public FetchStudent(int id,String admission_number,String first_name, String last_name,
                        String created_at,String grade, String gender,String residence,
                        String date_of_birth, String religion,String nationality,
                        String status,String category,
                        String class_id){
        this.id=id;
        this.admission_number=admission_number;
        this.category=category;
        this.date_of_birth=date_of_birth;
        this.status=status;
        this.first_name=first_name;
        this.last_name=last_name;
        this.gender=gender;
        this.grade=grade;
        this.nationality=nationality;
        this.residence=residence;
        this.created_at=created_at;
        this.religion=religion;
        this.class_id=class_id;
        //this.message=message;

    }

    public int getId(){
        return id;
    }

    public String getAdmission_number(){
        return admission_number;
    }

    public String getFirst_name(){
        return first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public String getGrade(){
        return grade;
    }

    public String getGender(){
        return gender;
    }

    public String getResidence(){
        return residence;
    }

    public String getCreatedAt(){
        return created_at;
    }

    public String getDOB(){
        return date_of_birth;
    }

    public String getReligion(){
        return religion;
    }

    public String getNationality(){
        return nationality;
    }

    public String getStatus(){
        return status;
    }

    public String getCategory(){
        return category;
    }

    public String getClass_id (){
        return class_id;
    }





}
