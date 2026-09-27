package org.example.utils;

import javafx.scene.control.Label;


public class PasswordStrengthChecker {

    public static  void passwordChecker(String password,Label textLabel){
        if(password==null|| password.isEmpty()) {
            return ;
        }
        int score=0;

        //checking password minimum length
        if(password.length()>=8){
            score++;

        }
        //checking uppercase letters
        if(password.matches(".*[A-Z].*")){
            score++;
        }
        //checking lowercase letters
        if(password.matches(".*[a-z].*")){
            score++;
        }
        //checking numbers
        if(password.matches(".*[0-9].*")){
            score++;
        }
        //checking special characters
        if(password.matches(".*[^a-zA-Z0-9].*")){
            score++;
        }

        if(score<=2){
            textLabel.setText("weak");
            textLabel.setStyle("-fx-text-fill:red");

        }else if(score==3){
            textLabel.setText("medium");
            textLabel.setStyle("-fx-text-fill:orange");
        }else if (score>=4){
            textLabel.setText("strong");
            textLabel.setStyle("-fx-text-fill:green");
        }else{
            textLabel.setText("");
        }
    }
}
