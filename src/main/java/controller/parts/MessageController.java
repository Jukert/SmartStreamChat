package controller.parts;

import animation.Element;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import parser.ConverterJSON;
import parser.pojo.Chat;
import youtube.Message;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtNick;

    @FXML
    private ImageView imgAvatar;

    @FXML
    private Label txtComment;

    @FXML
    private AnchorPane paneMessage;

    private ConverterJSON<Chat> json = new ConverterJSON<>(Chat.class);


    @FXML
    void initialize() throws IOException {
        settingsPreferens();
        txtNick.setText(Message.getChatMessage().getAuthorDetails().getDisplayName());
        txtComment.setText(Message.getChatMessage().getSnippet().getDisplayMessage());
        imgAvatar.setImage(new Image(Message.getChatMessage().getAuthorDetails().getProfileImageUrl()));
            if (Message.getChatMessage().getAuthorDetails().getIsChatOwner())
                txtNick.setStyle("-fx-text-fill:"+json.toObject().getPermission().getOwner());
            else
                if (Message.getChatMessage().getAuthorDetails().getIsChatModerator())
                    txtNick.setStyle("-fx-text-fill:"+json.toObject().getPermission().getModerator());
            else
                if (Message.getChatMessage().getAuthorDetails().getIsChatSponsor())
                    txtNick.setStyle("-fx-text-fill:"+json.toObject().getPermission().getSponsor());
            else
                    txtNick.setStyle("-fx-text-fill:"+json.toObject().getPermission().getGuest());

        paneMessage.setOpacity(0);
        Element<AnchorPane> messageFade = new Element<>();
        messageFade.fade(paneMessage);
    }

    private void settingsPreferens(){
        try {
            txtComment.setStyle("-fx-text-fill:#"+json.toObject().getSettingText().getColor()+";-fx-font-family:"+json.toObject().getSettingText().getFont()+";-fx-font-size:"+json.toObject().getSettingText().getSize());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
