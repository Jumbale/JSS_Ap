package org.example.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TextFieldEmptyErrorMessage {

    public static void ErrorMessage(Label label1,Label label2,Label label3){

        label1.setText("Fill this field!");
        label1.setStyle("-fx-text-fill:red");

        label2.setText("Fill this field!");
        label2.setStyle("-fx-text-fill:red");

        label3.setText("Fill this field!");
        label3.setStyle("-fx-text-fill:red");

    }

    public static void CheckIfEmpty(Label label1,Label label2,Label label3,ComboBox box,TextField... fields){





            box.valueProperty().addListener((observable,oldValue,newValue)->{
                if(newValue==null){
                    label2.setText("Fill this field!");

                }else{
                    label2.setText("");
                }
            });
        }
    }


