package org.example.utils;

import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextInputControl;

public class validateFields {

    public static void checkFields(ComboBoxBase combo,TextInputControl... fields){

        for(TextInputControl field:fields){

            field.textProperty().addListener((obs,oldValue,newValue)->{
                if(newValue.isBlank()){
                    field.setStyle("-fx-border-color:red;"+
                            "-fx-border-width:2px;");
                }
                field.setStyle("");

            });

        }

        combo.valueProperty().addListener((obs,oldValue,newValue)->{
            if(combo==null){
                combo.setStyle("-fx-border-color:red;"+
                        "-fx-border-width:2px");
            }else{
                combo.setStyle("");
            }
        });
    }
}
