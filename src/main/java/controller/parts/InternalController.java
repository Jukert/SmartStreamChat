package controller.parts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import parser.ConverterJSON;
import parser.pojo.Chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InternalController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton btnPanelAnalytics;

    @FXML
    private Button btnSave;

    @FXML
    private ToggleButton btnFilterMessages;

    private ToggleGroup groupFilter = new ToggleGroup();

    private ToggleGroup groupAnalytics = new ToggleGroup();

    private ConverterJSON<Chat> json = new ConverterJSON<>(Chat.class);



    @FXML
    void initialize() {

        try {
            if(Boolean.parseBoolean(json.toObject().getCensor())) {
                btnFilterMessages.setSelected(true);
                turnOn(btnFilterMessages);
            }else{
                btnFilterMessages.setSelected(false);
                turnOff(btnFilterMessages);

            }

            if(Boolean.parseBoolean(json.toObject().getAnalytic())) {
                btnPanelAnalytics.setSelected(true);
                turnOn(btnPanelAnalytics);

            }else{
                btnPanelAnalytics.setSelected(false);
                turnOff(btnPanelAnalytics);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        btnFilterMessages.setToggleGroup(groupFilter);
        btnPanelAnalytics.setToggleGroup(groupAnalytics);
        groupFilter.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                System.out.println(oldValue);
                System.out.println(newValue);
                if (oldValue == null){
                    turnOn(btnFilterMessages);
                }
                else{
                    turnOff(btnFilterMessages);

                }

            }
        });

        groupAnalytics.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (oldValue == null){
                    turnOn(btnPanelAnalytics);

                }
                else{
                    turnOff(btnPanelAnalytics);

                }

            }
        });


        btnSave.setOnAction(event -> {

            try {
                Chat chat = json.toObject();
                chat.setCensor(String.valueOf(btnFilterMessages.isSelected()));
                chat.setAnalytic(String.valueOf(btnPanelAnalytics.isSelected()));
                json.toJSON(chat);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    private void turnOn(ToggleButton button){
        button.setStyle("-fx-text-fill:  rgb(58,193,13);-fx-background-color:  rgb(48,48,48);-fx-border-color: white");
        button.setText("включен");
    }
    private void turnOff(ToggleButton button){
        button.setStyle("-fx-text-fill: rgb(203,20,21);-fx-background-color:  rgb(48,48,48);-fx-border-color: white");
        button.setText("выключен");
    }
}
