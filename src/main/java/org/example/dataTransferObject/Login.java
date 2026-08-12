package org.example.dataTransferObject;

public class Login {

    private String Email;
    private String Role;
    private String Password;




    public Login( String email, String role, String password ){

        this.Email=email;
        this.Role=role;
        this.Password=password;
    }



    public String getEmail(){
        return Email;
    }

    public String getRole(){
        return Role;
    }

    public String getPassword(){
        return Password;
    }

    public void setEmail(String email){
        this.Email=email;
    }

    public void setRole(String role){
        this.Role=role;
    }

    public void setPassword(String password){
        this.Password=password;
    }


}
