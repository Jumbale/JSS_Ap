package org.example.dataTransferObject;

public class AddNewTeacher {
    private String Username;
    private String firstName;
    private String lastName;
    private String tscNumber;
    private String Email;
    private String Gender;
    private String phoneNumber;
    private String Department;
    private String Subject;
    private String Password;
    private String Role;

    public AddNewTeacher(String teacherUsername,String teacherFirstname,String teacherLastname,
                                 String role,String gender,String department,String teacherPhoneNumber,
                                 String subject,String email,String TSC,String password ){

        this.Username=teacherUsername;
        this.firstName=teacherFirstname;
        this.lastName=teacherLastname;
        this.Email=email;
        this.Gender=gender;
        this.Department=department;
        this.Role=role;
        this.Subject=subject;
        this.Password=password;
        this.tscNumber=TSC;
        this.phoneNumber=teacherPhoneNumber;

    }
}

