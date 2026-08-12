package org.example.dataTransferObject;

public class ResetTeacherPassword {

    private String currentPassword;
    private String newPassword;
    private String passwordRepeat;

    public ResetTeacherPassword( String currentPassword,String newPassword,
                                   String passwordRepeat  ){


        this.currentPassword=currentPassword;
        this.newPassword=newPassword;
        this.passwordRepeat=passwordRepeat;

    }
}
