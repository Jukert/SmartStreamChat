package controller.parts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import parser.ConverterJSON;
import parser.pojo.Chat;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TextController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ColorPicker colorText;

    @FXML
    private Button btnSave;

    @FXML
    private TextField textSize;

    @FXML
    private ComboBox<String> fontBox;

    @FXML
    private Slider sliderSize;

    private ConverterJSON<Chat> json = new ConverterJSON<>(Chat.class);

    private GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();

    private String[] fonts = e.getAvailableFontFamilyNames();

    @FXML
    void initialize() {

        try {
            colorText.setValue(Color.valueOf(json.toObject().getSettingText().getColor()));

            ObservableList<String> list = FXCollections.observableList(Arrays.asList(fonts));
            fontBox.setItems(list);
            fontBox.setValue(json.toObject().getSettingText().getFont());

            textSize.setText(json.toObject().getSettingText().getSize()+"px");
            sliderSize.setMin(6);
            sliderSize.setMax(40);
            sliderSize.setValue(Double.parseDouble(json.toObject().getSettingText().getSize()));
            sliderSize.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    textSize.setText(newValue.intValue()+"px");
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }


        btnSave.setOnAction(event -> {
            try {
                Chat sText = json.toObject();
                sText.getSettingText().setColor(String.valueOf(colorText.getValue()).replace("0x",""));
                sText.getSettingText().setFont(fontBox.getValue());
                sText.getSettingText().setSize(textSize.getText().replace("px",""));
                json.toJSON(sText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
