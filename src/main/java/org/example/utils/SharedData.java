package org.example.utils;

public class SharedData {

    //creating a single instance of SharedData in memory(RAM)- only a single instance exists in memory
    private static final SharedData instance=new SharedData();

    private  String teacherEmail;

    //a private constructor to prevent creating a new object using 'new SharedData()'
    private SharedData(){}

// this method provide access to the object created in memory to modify the variable values...
    public static SharedData getInstance(){
        return instance;
    }

    public void setTeacherEmail(String email){
        this.teacherEmail=email;
    }

    public String getTeacherEmail(){
        return teacherEmail;
    }
}
