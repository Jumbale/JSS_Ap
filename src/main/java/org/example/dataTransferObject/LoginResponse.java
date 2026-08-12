package org.example.dataTransferObject;

public class LoginResponse {
    private String message;

    private boolean loggedIn;

    private String role;

    private String email;

    private boolean mustChangePassword;

    private String token;

    private Login login;

    public String getMessage(){

        return message;
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }
    public boolean isMustChangePassword(){
        return mustChangePassword;
    }

    public String getRole(){
        return role;
    }

    public String getToken(){
        return token;
    }

    public Login getLogin(){
        return login;
    }


    public String getEmail(){
        return email;
    }
}
