package org.example.contollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.dataTransferObject.DataModel;
import org.example.dataTransferObject.Login;
import org.example.services.ApiServices;
import org.example.utils.sceneManager;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class Users {
    //private final Preferences prefs = Preferences.userNodeForPackage(Users.class);


    @FXML
    public Button LoginButton;
    @FXML
    public Button signup;
    @FXML
    public TextField name;

    @FXML
    public  ComboBox <String> role;
    @FXML
    public TextField school;
    @FXML
    public TextField phoneNumber;
    @FXML
    public CheckBox rememberMe;
    @FXML
    public Button CreateAccount;
    @FXML
    public Button submit;
    @FXML
    public StackPane SignUpPage;
    @FXML
    public StackPane LoginPage;
    @FXML
    public  ComboBox <String> LoginRole;
    @FXML
    public ComboBox <String>SignupRole;

    @FXML
    public Button signInButton;
    public Button signupButton;
    @FXML
    public PasswordField signupPassword;
    @FXML
    public PasswordField loginPassword;
    @FXML
    public TextField loginEmail;
    @FXML
    public TextField registerEmail;
    @FXML
    private static GridPane nameGrid;
    @FXML
    private static GridPane emailGrid;
    @FXML
    private static GridPane positionGrid;
    @FXML
    private static GridPane passwordGrid;
    @FXML
    public TextField passwordTextField1;
    @FXML
    public FontIcon eyeIcon1;
    @FXML
    public TextField passwordTextField;
    @FXML
    public FontIcon eyeIcon;


    @FXML
    public void initialize(){
        loginPassword.textProperty().bindBidirectional(passwordTextField.textProperty());

        signupPassword.textProperty().bindBidirectional(passwordTextField1.textProperty());

        passwordTextField.setVisible(false);
        passwordTextField.setManaged(false);





        FontIcon icon=new FontIcon(FontAwesomeSolid.USER);
        System.out.println(icon.getIconLiteral());
       initial();

        if(SignupRole!=null){
            SignupRole.getItems().addAll("admin");
        }

       if(LoginRole!=null){
           LoginRole.getItems().addAll("admin","teacher");
       }


   }

    public void SignUpView(ActionEvent actionEvent) {
        SignUpPage.setVisible(true);
        SignUpPage.setManaged(true);

        LoginPage.setVisible(false);
        LoginPage.setManaged(false);
    }

    public void LoginView(ActionEvent actionEvent) {
        LoginPage.setVisible(true);
        LoginPage.setManaged(true);

        SignUpPage.setVisible(false);
        SignUpPage.setManaged(false);

    }
    public void initial(){
        LoginPage.setVisible(true);
        LoginPage.setManaged(true);

        SignUpPage.setVisible(false);



    }

    public void ToLogin(ActionEvent actionEvent) {
        initial();
    }

    public void submitSignup(ActionEvent actionEvent) {
    }

    @FXML
    public void loginUser(ActionEvent actionEvent) {
       String  Password=loginPassword.getText();
        String  Email=loginEmail.getText();
        String  Role=LoginRole.getValue();

        System.out.println(Password);
        System.out.println(Email);
        System.out.println(Role);

        if ((Password.trim().isEmpty() )|| (Role==null) || (Email.trim().isEmpty() )) {

            //validateFields.checkFields(LoginRole,loginEmail,loginPassword);


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("Fill all fields!");
            alert.show();

        }else{

            Login login=new Login(Email,Role,Password);
            System.out.println("Hello World");

            ApiServices apiServices=new ApiServices();
            apiServices.login(login)
                    .thenAccept(result->{
                        int statusCode=result.getStatusCode();
                        String message=result.getResponse().getMessage();
                        String token=result.getResponse().getToken();
                        String admin=result.getResponse().getEmail();

                        boolean mustChangePassword=result.getResponse().isMustChangePassword();

                        boolean isLoggedIn=result.getResponse().isLoggedIn();

                        String role=result.getResponse().getRole();

                       // String admin=result.getResponse().




                        Platform.runLater(()->{

                           // System.out.println("Inside main thread");
                           // System.out.println(Role+"=="+role);
                            //System.out.println(mustChangePassword);

                            if(statusCode>=400){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Login Error!");
                                alert.setHeaderText(null);
                                alert.setContentText(message);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.showAndWait();





                            }else{

                               System.out.print("This "+isLoggedIn);
                                //apiServices.getStudentData();

                               System.out.println(Role+"=="+role);
                                System.out.println(mustChangePassword);
                                System.out.print(Role+"=="+role);




                                if(Role.equals(role)){
                                    if(!isLoggedIn){
                                        try {
                                            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/PasswordReset.fxml"));
                                            Parent root=loader.load();
                                            Scene scene=new Scene(root);
                                            Stage stage=new Stage();
                                            stage.setScene(scene);
                                            stage.setTitle("Reset password");
                                            stage.setResizable(false);
                                            stage.showAndWait();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }else{
                                        Stage stage=sceneManager.getStage();
                                        stage.setResizable(true);
                                        stage.setTitle("Teacher Dashboard");
                                        try {
                                            sceneManager.navigateDashboard("/org/example/TeacherDashboard.fxml");
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    return;

                                }




                                try{

                                  //  sceneManager.navigateDashboard("/org/example/Dashboard.fxml");

                                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/Dashboard.fxml"));

                                    Parent root=loader.load();

                                    Dashboard dashboard=loader.getController();

                                    dashboard.setAdminEmail(result.getResponse().getEmail());
                                    Scene scene=new Scene(root);
                                    Stage stage=sceneManager.getStage();
                                    stage.setScene(scene);
                                    stage.setTitle("Admin Dashboard");
                                    stage.setResizable(true);
                                    stage.show();


                                } catch (IOException e) {

                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Navigation Error");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Navigation failed");
                                    alert.initStyle(StageStyle.UTILITY);
                                    alert.showAndWait();

                                    e.printStackTrace();
                                }
                            }
                            loginPassword.setText("");
                            loginEmail.setText("");

                            name.requestFocus();
                        });
                    });



        }


    }


    @FXML
    public void registerUser(ActionEvent actionEvent) {

        String Email = registerEmail.getText();
        String Role = SignupRole.getValue();
        String Username = name.getText();
        String Password = signupPassword.getText();


        if ((Email.trim().isEmpty() )|| (Role==null) || (Username.trim().isEmpty() )|| (Password.trim().isEmpty())) {

            //validateFields.checkFields(SignupRole,registerEmail,name,signupPassword);

          Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account creation error!");
                 alert.setHeaderText(null);
                 alert.initStyle(StageStyle.UTILITY);
                 alert.setContentText("Fill all fields!");
                 alert.show();
        } else {



            System.out.println(Email);
            System.out.println(Role);
            System.out.println(Username);
            System.out.println(Password);

            DataModel model = new DataModel(Username, Email, Role, Password);

            ApiServices apiServices=new ApiServices();
            apiServices.createUser(model)
                    .thenAccept(result->{
                        int statusCode= result.getStatusCode();
                        String message=result.getBody().getMessage();

                        Platform.runLater(()->{
                            if(statusCode==200 || statusCode==201) {
                                System.out.print(message);

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Account creation");
                                alert.setHeaderText(null);
                                alert.setContentText(message);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.showAndWait();


                            }

                            else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Account creation error!");
                                alert.setHeaderText(null);
                                alert.setContentText(message);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.showAndWait();

                                System.out.println("This is the message: "+message);




                            }

                            registerEmail.setText("");
                            name.setText("");
                            signupPassword.setText("");

                            name.requestFocus();

                        });



                    });




        }
    }


    public void togglePasswordVisibility(MouseEvent mouseEvent) {
        if(loginPassword.isVisible()){

            eyeIcon.setIconCode(FontAwesomeSolid.EYE_SLASH);

            loginPassword.setVisible(false);
            loginPassword.setManaged(false);

            passwordTextField.setVisible(true);
            passwordTextField.setManaged(true);




        }else{
            eyeIcon.setIconCode(FontAwesomeSolid.EYE);

            loginPassword.setVisible(true);
            loginPassword.setManaged(true);

            passwordTextField.setVisible(false);
            passwordTextField.setManaged(false);
        }
    }

    public void togglePasswordShow(MouseEvent mouseEvent) {

        if(signupPassword.isVisible()){

            eyeIcon1.setIconCode(FontAwesomeSolid.EYE_SLASH);

            signupPassword.setVisible(false);
            signupPassword.setManaged(false);

            passwordTextField1.setVisible(true);
            passwordTextField1.setManaged(true);




        }else{
            eyeIcon1.setIconCode(FontAwesomeSolid.EYE);

            signupPassword.setVisible(true);
            signupPassword.setManaged(true);

            passwordTextField1.setVisible(false);
            passwordTextField1.setManaged(false);
        }
    }
}

