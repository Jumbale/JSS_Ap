package org.example.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import okhttp3.*;
import org.example.utils.CheckEmptyFields;


import java.io.File;
import java.io.IOException;


public class Settings {
    @FXML
    public TextField schoolName;
    @FXML
    public TextField boxNumber;
    @FXML
    public TextField town;
    @FXML
    public TextField county;
    @FXML
    public TextField country;
    @FXML
    public TextField telephone;
    @FXML
    public TextField email;
    @FXML
    public TextField motto;
    @FXML
    public TextField principal;
    @FXML
    public ImageView schoolLogo;
    @FXML
    public Label logoName;
    @FXML
    public Button chooseFile;
    @FXML
    public Button saveData;
    @FXML
    public TextField boxCode;

    private File photo;





    public void chooseFile(MouseEvent mouseEvent) {
        FileChooser fileChooser=new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.jpg","*.jpeg","*.png")
        );

         photo=fileChooser.showOpenDialog(chooseFile.getScene().getWindow());

         if(photo!=null){
             Image image=new Image(photo.toURI().toString());

             schoolLogo.setImage(image);

             logoName.setText(photo.getName());
         }






    }
    public void saveData(MouseEvent mouseEvent) throws IOException {
        String SchoolName=schoolName.getText();
        String BoxNumber=boxNumber.getText();
        String Town=town.getText();
        String County=county.getText();
        String Country=country.getText();
        String Telephone=telephone.getText();
        String Email=email.getText();
        String Motto=motto.getText();
        String BoxCode=boxCode.getText();
        String Principal=principal.getText();

        String [] fields={SchoolName,BoxNumber,Town,County,Country,Telephone,Email,Motto,Principal};

        boolean CheckedFields= CheckEmptyFields.CheckFields(fields);
         if(CheckedFields){
             if(photo!=null){

                 String fileName=photo.getName().toLowerCase();

                 MediaType mediaType;

                 if(fileName.endsWith(".jpeg")|| fileName.endsWith(".jpg")){
                     mediaType=MediaType.get("image/jpeg");
                 }else if(fileName.endsWith(".png")){
                     mediaType=MediaType.get("image/png");

                 }else{
                     throw new IllegalArgumentException("Unsupported file format");
                 }

                 OkHttpClient client=new OkHttpClient();

                 RequestBody fileBody=RequestBody.create(
                         photo,
                         mediaType
                 );

                 MultipartBody requestBody=new MultipartBody.Builder()
                         .setType(MultipartBody.FORM)
                         .addFormDataPart("SchoolName",SchoolName)
                         .addFormDataPart("BoxNumber",BoxNumber)
                         .addFormDataPart("Town",Town)
                         .addFormDataPart("County",County)
                         .addFormDataPart("Country",Country)
                         .addFormDataPart("Telephone",Telephone)
                         .addFormDataPart("Email",Email)
                         .addFormDataPart("Motto",Motto)
                         .addFormDataPart("BoxCode",BoxCode)
                         .addFormDataPart("Principal",Principal)
                         .addFormDataPart(
                                 "schoolLogo",
                                 photo.getName(),
                                 fileBody
                         )
                         .build();

                 Request request= new Request.Builder()
                         .url("http://localhost:5000/api/jss/schoolData")
                         .post(requestBody)
                         .build();

                 Response response=client
                         .newCall(request)
                         .execute();
             }

         }else{
             return;
         }







    }
}
