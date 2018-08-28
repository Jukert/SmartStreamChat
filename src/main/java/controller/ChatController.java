package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import parser.ConverterJSON;
import parser.pojo.Chat;
import youtube.ListLiveChatMessages;
import youtube.Message;

import java.io.IOException;


public class ChatController {

    @FXML
    private Pane upBorder;

    private double xOffset;
    private double yOffset;

    @FXML
    private VBox chatList;

    @FXML
    private ImageView btnClose;

    @FXML
    private ScrollPane chatScroll;

    @FXML
    private AnchorPane chatPane;

    private ConverterJSON<Chat> json = new ConverterJSON<>(Chat.class);

    @FXML
    void initialize() {
        settingsPreferens();
        chatList.setSpacing(10);
        upBorder.setOnMousePressed(event -> {
            xOffset = upBorder.getScene().getWindow().getX() - event.getScreenX();
            yOffset = upBorder.getScene().getWindow().getY() - event.getScreenY();
        });

        upBorder.setOnMouseDragged(event -> {
            upBorder.getScene().getWindow().setX(event.getScreenX() + xOffset);
            upBorder.getScene().getWindow().setY(event.getScreenY() + yOffset);
        });
        btnClose.setOnMouseClicked(event->System.exit(0));

        chatScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Message.chatScroll = chatScroll;
        Message.message = chatList;
        ListLiveChatMessages liveChat = new ListLiveChatMessages();
        liveChat.getMess();
    }

    private void settingsPreferens(){
        try {
            chatList.setStyle("-fx-background-color:#"+json.toObject().getBackground());
            chatScroll.setStyle("-fx-background-color:#"+json.toObject().getBackground());
            chatPane.setStyle("-fx-background-color:#"+json.toObject().getBackground());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
