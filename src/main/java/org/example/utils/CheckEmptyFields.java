package org.example.utils;

public class CheckEmptyFields {
    public static boolean CheckFields(String... fields){

        for(String field:fields){
            if(field==null||field.isBlank()){
                return false;
            }

        }
        return true;
    }
}
