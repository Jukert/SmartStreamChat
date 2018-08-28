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

public class PersistentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSave;

    @FXML
    private ColorPicker clPSponsor;

    @FXML
    private ColorPicker clPOwner;

    @FXML
    private ColorPicker clPModerator;

    @FXML
    private ColorPicker clPGuest;

    private ConverterJSON<Chat> json = new ConverterJSON<>(Chat.class);

    @FXML
    void initialize() {

        try {
            clPOwner.setValue(Color.valueOf(json.toObject().getPermission().getOwner()));
            clPSponsor.setValue(Color.valueOf(json.toObject().getPermission().getSponsor()));
            clPModerator.setValue(Color.valueOf(json.toObject().getPermission().getModerator()));
            clPGuest.setValue(Color.valueOf(json.toObject().getPermission().getGuest()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        btnSave.setOnAction(event -> {



        });

    }
}
