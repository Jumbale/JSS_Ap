package org.example.dataTransferObject;

public class DataModel {
    private String Username;
    private String Email;
    private String Role;
    private String Password;





   public DataModel(String username, String email, String role, String password ){
         this.Username=username;
         this.Email=email;
         this.Role=role;
         this.Password=password;
   }

           public String getUsername(){
               return Username;
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


            public void setUsername(String username){
                    this.Username=username;
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
