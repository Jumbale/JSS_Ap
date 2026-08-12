package org.example.services;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import org.example.dataTransferObject.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ApiServices {

    private static final String BASE_URL="http://localhost:5000/api/jss";

    private final HttpClient client= HttpClient.newHttpClient();

    String usersSignupUrl=BASE_URL+"/users_signup";

    String LoginSignupUrl=BASE_URL+"/desktop_login";

    String  studentRecord=BASE_URL+"/fetch_students";
    String  studentDetails=BASE_URL+"/patch_student";

    String  RegisterStudent=BASE_URL+"/signup";

    String AddTeacher=BASE_URL+"/teachers_signup";
    String addClass=BASE_URL+"/allocateClass";
    String resetTeacherPassword=BASE_URL+"/desktop_login";
    String academicYear=BASE_URL+"/academicYear";

    String AddSubject=BASE_URL+"/newSubject";

    String FetchSubjects=BASE_URL+"/fetch_subject";



    private Gson gson=new Gson();



    //POST requests-sending data to the jss_backend



    public CompletableFuture<HttpResetTeacherPassword<ResetTeacherPasswordResponse>> ResetPassword(ResetTeacherPassword teacherData){
        String bodyPayload=gson.toJson(teacherData);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(resetTeacherPassword))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(bodyPayload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    ResetTeacherPasswordResponse body=gson.fromJson(response.body(),ResetTeacherPasswordResponse.class);

                   String message=body.getMessage();
                   System.out.print(message);

                    return new HttpResetTeacherPassword<>(body,response.statusCode() );
                });




    }
    public CompletableFuture<HttpResult<ApiResponse>> createUser(DataModel user){
        String bodyPayload=gson.toJson(user);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(usersSignupUrl))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(bodyPayload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    ApiResponse body=gson.fromJson(response.body(),ApiResponse.class);

                    String message=body.getMessage();
                    System.out.print(message);

                    return new HttpResult<>(response.statusCode(),body );
                });




    }

    public CompletableFuture<HttpAcademicYear<AcademicYearResponse>> createAcademicYear(AcademicYearDetails year){
        String Payload=gson.toJson(year);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(academicYear))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(Payload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    AcademicYearResponse body=gson.fromJson(response.body(),AcademicYearResponse.class);

                    String message=body.getMessage();
                    System.out.print(message);

                    return new HttpAcademicYear<>(body,response.statusCode() );
                });




    }

    public CompletableFuture<HttpAddNewSubject<AddNewSubjectResponse>> createNewSubject(AddNewSubject subject){
        String Payload=gson.toJson(subject);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(AddSubject))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(Payload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    AddNewSubjectResponse body=gson.fromJson(response.body(),AddNewSubjectResponse.class);


                    return new HttpAddNewSubject<>(body,response.statusCode() );
                });




    }


    public CompletableFuture<LoginResult<LoginResponse>> login(Login users){
        String bodyPayload=gson.toJson(users);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(LoginSignupUrl))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(bodyPayload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    LoginResponse body=gson.fromJson(response.body(),LoginResponse.class);

                    String message=body.getMessage();
                    System.out.print(message);

                    System.out.print(response.statusCode());

                    String token=body.getToken();
                    System.out.print(token);


                    return new LoginResult<>(response.statusCode(),body );
                });




    }

    public CompletableFuture<HttpRegisterStudent<RegisterStudent>> createStudent(RegisterS students){
        String bodyPayload=gson.toJson(students);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(RegisterStudent))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(bodyPayload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    RegisterStudent body=gson.fromJson(response.body(),RegisterStudent.class);

                    String message=body.getMessage();
                    System.out.print(message);

                    return new HttpRegisterStudent<>(body,response.statusCode() );
                });




    }

    public CompletableFuture<HttpAddClass<AddClassResponse>>createClass(AddNewClass newGrade){
        String payload=gson.toJson(newGrade);
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(addClass))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    AddClassResponse body=gson.fromJson(response.body(),AddClassResponse.class);

                    return new HttpAddClass<>(body, response.statusCode());
                });
    }

    public CompletableFuture<HttpAddNewTeacher<AddNewTeacherResponse>>addNewTeacher(AddNewTeacher addTeacherData){
        String payload=gson.toJson(addTeacherData);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(AddTeacher))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    AddNewTeacherResponse body=gson.fromJson(response.body(),AddNewTeacherResponse.class);

                  return   new HttpAddNewTeacher<>(body, response.statusCode());

                });

    }





    //GET requests-getting data from the jss_backend
    public CompletableFuture<HttpStudent<FetchStudent[]>> getStudentData(){

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(studentRecord))
                .GET()
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    FetchStudent[]body=gson.fromJson(response.body(),FetchStudent[].class);
                    ObservableList<FetchStudent> students=FXCollections.observableArrayList(body);

                    System.out.println("The Dataset: "+students);

                    return new HttpStudent<>(body,response.statusCode());

                });




    }

    public CompletableFuture<HttpFetchSubject<FetchSubjects[]>>FetchSubject(){


        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(FetchSubjects))
                .header("Content-type","application/json")
                .GET()
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    FetchSubjects []body=gson.fromJson(response.body(),FetchSubjects[].class);

                    return   new HttpFetchSubject<>(body, response.statusCode());

                });

    }





    //PATCH Requests-sending specific data to the jss_backend

    public CompletableFuture<HttpPatch<PatchResponse>> patchStudent(FetchStudent myStudent){
        String payload=gson.toJson(myStudent);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(studentDetails))
                .header("Content-Type","application/json")
                .method("PATCH",HttpRequest.BodyPublishers.ofString(payload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    PatchResponse body=gson.fromJson(response.body(),PatchResponse.class);

                    String message=body.getMessage();
                    System.out.print(message);

                    System.out.println(response);
                    System.out.println(response.statusCode());

                    return new HttpPatch<>(body, response.statusCode());
                });




    }

    public CompletableFuture<HttpUpdateStudent<ResponseUpdateStudent>> UpdateStudentDetails(UpdateStudent updateSt){
        String  updateStudent=BASE_URL+"/Updatestudent/"+updateSt.getAdmNumber();
        String payload=gson.toJson(updateSt);

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(updateStudent))
                .header("Content-Type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{
                    ResponseUpdateStudent body=gson.fromJson(response.body(),ResponseUpdateStudent.class);

                    String message=body.getMessage();
                    System.out.print(message);

                    System.out.println(response);
                    System.out.println(response.statusCode());

                    return new HttpUpdateStudent<>(body, response.statusCode());
                });

    }

}
