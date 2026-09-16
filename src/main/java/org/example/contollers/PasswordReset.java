package org.example.contollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.TeacherPasswordResetter;
import org.example.services.ApiServices;
import org.example.utils.CheckEmptyFields;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Optional;

public class PasswordReset {

    @FXML
    public TextField currentPassword;
    @FXML
    public TextField newPassword;
    @FXML
    public TextField passwordRepeat;
    @FXML
    public Button ButtonCancel;
    @FXML
    public Button resetPasswordButton;
    @FXML
    public TextField passwordTextFieldCurrent;
    @FXML
    public FontIcon eyeIconCurrent;
    @FXML
    public TextField passwordTextFieldNew;
    @FXML
    public FontIcon eyeIconNew;
    @FXML
    public TextField passwordTextFieldRepeat;
    @FXML
    public FontIcon eyeIconRepeat;

    private String Email;

    @FXML
    public void initialize(){

        currentPassword.textProperty().bindBidirectional(passwordTextFieldCurrent.textProperty());

        newPassword.textProperty().bindBidirectional(passwordTextFieldNew.textProperty());

        passwordRepeat.textProperty().bindBidirectional(passwordTextFieldRepeat.textProperty());

    }

    public void setEmail(String teacherEmail){
        this.Email=teacherEmail;
    }


    public void resetPassword(MouseEvent mouseEvent) {
        String CurrentPassword=currentPassword.getText();
        String NewPassword=newPassword.getText();
        String PasswordRepeat=passwordRepeat.getText();

        System.out.println("This is email: "+Email);

        String [] fields={NewPassword,CurrentPassword,PasswordRepeat};
        boolean checkedFields= CheckEmptyFields.CheckFields(fields);

        if(checkedFields){
            TeacherPasswordResetter resetTeacherPassword=new TeacherPasswordResetter(CurrentPassword,NewPassword,PasswordRepeat,Email);
            ApiServices apiServices=new ApiServices();
            apiServices.ResetPassword(resetTeacherPassword)
                    .thenAccept(result->{
                        String message=result.getPasswordResponse().getMessage();
                        int statusCode= result.getStatusCode();

                        Platform.runLater(()->{
                            if(statusCode>=400){
                                Alert alert=new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Reset Password");
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);


                                Optional<ButtonType> closeModal=alert.showAndWait();

                                if(closeModal.isPresent()&& closeModal.get()==ButtonType.OK){
                                    Stage stage=(Stage)resetPasswordButton.getScene().getWindow();
                                    stage.close();

                                }

                            }else{
                                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Reset Password");
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText(message);
                                alert.showAndWait();

                            }
                        });

                    });

        }



    }
    public void CancelButton(MouseEvent mouseEvent) {
        Stage stage=(Stage) ButtonCancel.getScene().getWindow();
        stage.close();
    }

    public void togglePasswordVisibilityCurrent(MouseEvent mouseEvent) {
        if(currentPassword.isVisible()){
            eyeIconCurrent.setIconCode(FontAwesomeSolid.EYE_SLASH);

            currentPassword.setVisible(false);
            currentPassword.setManaged(false);

            passwordTextFieldCurrent.setVisible(true);
            passwordTextFieldCurrent.setManaged(true);

        }else{
            eyeIconCurrent.setIconCode(FontAwesomeSolid.EYE);

            currentPassword.setVisible(true);
            currentPassword.setManaged(true);

            passwordTextFieldCurrent.setVisible(false);
            passwordTextFieldCurrent.setManaged(false);


        }
        passwordTextFieldCurrent.requestFocus();
    }

    public void togglePasswordVisibilityNew(MouseEvent mouseEvent) {
        if(newPassword.isVisible()){
            eyeIconNew.setIconCode(FontAwesomeSolid.EYE_SLASH);

            newPassword.setVisible(false);
            newPassword.setManaged(false);

            passwordTextFieldNew.setVisible(true);
            passwordTextFieldNew.setManaged(true);

        }else{
            eyeIconNew.setIconCode(FontAwesomeSolid.EYE);

            newPassword.setVisible(true);
            newPassword.setManaged(true);

            passwordTextFieldNew.setVisible(false);
            passwordTextFieldNew.setManaged(false);


        }
        passwordTextFieldNew.requestFocus();
    }

    public void togglePasswordVisibilityRepeat(MouseEvent mouseEvent) {
        if(passwordRepeat.isVisible()){
            eyeIconRepeat.setIconCode(FontAwesomeSolid.EYE_SLASH);

            passwordRepeat.setVisible(false);
            passwordRepeat.setManaged(false);

            passwordTextFieldRepeat.setVisible(true);
            passwordTextFieldRepeat.setManaged(true);

        }else{
            eyeIconRepeat.setIconCode(FontAwesomeSolid.EYE);

            passwordRepeat.setVisible(true);
            passwordRepeat.setManaged(true);

            passwordTextFieldRepeat.setVisible(false);
            passwordTextFieldRepeat.setManaged(false);


        }
        passwordTextFieldRepeat.requestFocus();
    }

}
