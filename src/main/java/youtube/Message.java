package youtube;

import com.google.api.services.youtube.model.LiveChatMessage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Message {

    public static VBox message;
    public static ScrollPane chatScroll;
    private static int index = 0;
    private static LiveChatMessage chatMessage;


    public static LiveChatMessage getChatMessage() {
        return chatMessage;
    }

    public static void setMessage(LiveChatMessage message) {
        if (message == null)
            return;
        chatMessage = message;
        try {
            Message.message.getChildren().add(FXMLLoader.load(Message.class.getResource("/fxml/parts/message.fxml")));
            if (index == 0) {
                Message.chatScroll.vvalueProperty().bind(Message.message.heightProperty());
                index++;
            }
            if (Message.chatScroll.getVvalue() == Message.chatScroll.getVmax()) {
                Message.chatScroll.vvalueProperty().bind(Message.message.heightProperty());
            }
        }
        catch (NullPointerException e){

        }
        catch (IllegalStateException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
