package org.example.dataTransferObject;

public class AddNewClass {
    private String className;
    private String teachersTSC;
    private String Email;

    public AddNewClass(String className,String teachersTSC){
       this.className=className;
       this.teachersTSC=teachersTSC;


    }

    public String getClassName(){
        return className;
    }

    public String getTeachersTSC(){
        return teachersTSC;
    }


}
