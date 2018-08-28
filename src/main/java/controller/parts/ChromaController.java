package controller.parts;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import parser.ConverterJSON;
import parser.pojo.Chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChromaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ColorPicker colorChromakey;

    @FXML
    private Button btnSave;

    private ConverterJSON<Chat> json = new ConverterJSON<>(Chat.class);

    @FXML
    void initialize() {

        try {
            colorChromakey.setValue(Color.valueOf(json.toObject().getBackground()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnSave.setOnAction(event -> {
            try {
                Chat chat = json.toObject();
                chat.setBackground(String.valueOf(colorChromakey.getValue()).replace("0x",""));
                json.toJSON(chat);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
