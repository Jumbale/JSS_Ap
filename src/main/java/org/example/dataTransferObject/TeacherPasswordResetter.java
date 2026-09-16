package org.example.dataTransferObject;

public class TeacherPasswordResetter {

    private String currentPassword;
    private String newPassword;
    private String passwordRepeat;
    private String Email;

    public TeacherPasswordResetter(String currentPassword, String newPassword,
                                   String passwordRepeat, String Email){


        this.currentPassword=currentPassword;
        this.newPassword=newPassword;
        this.passwordRepeat=passwordRepeat;
        this.Email=Email;

    }
}
