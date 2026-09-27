package org.example.contollers;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.example.dataTransferObject.AiDataSubmission;
import org.example.dataTransferObject.AiResponse;
import org.example.dataTransferObject.HttpAiResponse;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ChatBot {
    @FXML
    public FontIcon fileAttachementButton;
    @FXML
    public TextArea promptArea;
    @FXML
    public AnchorPane SubmitButton;
    @FXML
    public VBox mainContentArea;
    @FXML
    public ScrollPane scrollPane;

    int statusCode;

    @FXML
    public void initialize(){

        String refined="I’m here to help you navigate" +
                " and understand your school management system." +
                " You can ask me about students, teachers, classes, " +
                "exams, results, fees, attendance, reports, and more." +
                "How can I help you today?";
        Label welcomeMessage=new Label(refined);
        welcomeMessage.setWrapText(true);
        welcomeMessage.setMaxWidth(450);
        welcomeMessage.getStyleClass().add("bot-message");

        HBox hBox=new HBox(welcomeMessage);
        hBox.setMaxWidth(Double.MAX_VALUE);
        hBox.setPadding(new Insets(5,10,5,10));
        hBox.setMinHeight(Region.USE_COMPUTED_SIZE);
        hBox.setPrefHeight(Region.USE_PREF_SIZE);
        hBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
        hBox.setAlignment(Pos.CENTER_LEFT);
        mainContentArea.getChildren().addAll(hBox);

        //listen to the VBox height property whenever a new message bubble ia appended
        mainContentArea.heightProperty().addListener((observable,oldValue,newValue)->{
            scrollPane.setVvalue(1.0);// scrolls to the bottom of the scrollBar(scale 1.0->bottom)
        });
    }

    private static final String BASE_URL="http://localhost:5000/api/jss";

    String aiUrl=BASE_URL+"/tusomeAI";

    Gson gson=new Gson();



    public CompletableFuture<Void> submit(MouseEvent mouseEvent) throws IOException, InterruptedException {
        String question=promptArea.getText().trim();
        addMessage(question,true);
        promptArea.clear();

        HttpClient client= HttpClient.newHttpClient();



        AiDataSubmission aiDataSubmission =new AiDataSubmission(question);

        String userMessage=gson.toJson(aiDataSubmission);

        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(aiUrl))
                .header("Content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userMessage))
                .build();

        return client.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(response->{


                        AiResponse body = gson.fromJson(response.body(), AiResponse.class);
                        int statusCode = response.statusCode();


                    return new HttpAiResponse<>(body,statusCode);
                })

                .thenAccept(results->{


                    String aiReply = results.getAiResponse().getMessage();
                    int statusCode=results.getStatusCode();
                    Platform.runLater(()->{



                        if(statusCode>=400){

                            Label errorMessageLabel=new Label(aiReply);
                            errorMessageLabel.getStyleClass().add("ai-error-message");

                            HBox box=new HBox(errorMessageLabel);
                            box.setMaxWidth(Double.MAX_VALUE);
                            mainContentArea.getChildren().addAll(box);


                        }else{

                            addMessage(aiReply,false);
                        }



                    });



                });




            }



    public void addMessage(String message,boolean isUser){
        Label messageLabel=new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(
                isUser? 450:8000000
        );

        messageLabel.getStyleClass().add(
                isUser? "user-message":""
        );
        HBox hBox=new HBox(messageLabel);
        hBox.setMaxWidth(Double.MAX_VALUE);
        hBox.setPadding(new Insets(5,10,5,10));

        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        hBox.setMaxHeight(Region.USE_PREF_SIZE);
        hBox.setAlignment(
                isUser? Pos.CENTER_RIGHT:Pos.CENTER_LEFT
        );
        mainContentArea.getChildren().addAll(hBox);

    }

    public void fileAttachment(MouseEvent mouseEvent) {
    }


}
