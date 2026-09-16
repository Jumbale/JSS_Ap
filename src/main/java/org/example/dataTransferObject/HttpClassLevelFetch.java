package org.example.dataTransferObject;

public class HttpClassLevelFetch<ClassLevelStudents>{
   private ClassLevelStudents classLevelStudents;

   public HttpClassLevelFetch(ClassLevelStudents classLevelStudents){
       this.classLevelStudents=classLevelStudents;
    }

    public ClassLevelStudents getClassLevelStudents(){
       return classLevelStudents;
    }
}
