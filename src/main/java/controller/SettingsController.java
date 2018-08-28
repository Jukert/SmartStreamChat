package controller;

import animation.Element;
import entity.UsersEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneSettings;

    @FXML
    private ImageView userAvatar;

    @FXML
    private Button btnSettingChroma;

    @FXML
    private Label userName;

    @FXML
    private Label caption;

    @FXML
    private Polygon triangle;

    @FXML
    private Button btnSettingText;

    @FXML
    private Button btnInternal;

    @FXML
    private ImageView toMain;

    @FXML
    private Button btnSettingPersistent;


    private Parent settingScene;

    private double coordinateY[] = {
            219,
            264,
            309,
            354
    };


    @FXML
    void initialize() {
        loadSettingScene(getClass().getResource("/fxml/parts/settingChroma.fxml"));

        userAvatar.setImage(new Image(UsersEntity.user.getAvatar()));
        userName.setText(UsersEntity.user.getName()+" "+UsersEntity.user.getSurname());

        toMain.setOnMouseClicked(event -> {
            Font.loadFont(getClass().getResource("/fonts/uni.ttf").toExternalForm(), 10);
            try {
                Parent main = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                Scene scene = new Scene(main,500,600);
                scene.getStylesheets().add(getClass().getResource("/stylesheets/font.css").toExternalForm());
                Stage st = new Stage();
                st.setScene(scene);
                st.setResizable(false);
                st.initStyle(StageStyle.UNDECORATED);
                st.setTitle("Smart Stream Chat");
                st.getIcons().add(new Image(getClass().getResourceAsStream("/images/favicon.png")));
                st.show();
                System.out.println("");
                toMain.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        btnSettingChroma.setOnAction(event -> {
            paneSettings.getChildren().removeAll(settingScene);
            triangle.setOpacity(0);
            triangle.setLayoutY(coordinateY[0]);
            Element<Polygon> polygon = new Element<>();
            polygon.fade(triangle);

            Element<Button> b = new Element<>();
            setDefaultColor();
            b.colorTransition(btnSettingChroma);
            caption.setText("меню настроек хромакея");
            loadSettingScene(getClass().getResource("/fxml/parts/settingChroma.fxml"));
        });

        btnSettingText.setOnAction(event -> {
            paneSettings.getChildren().removeAll(settingScene);
            triangle.setOpacity(0);
            triangle.setLayoutY(coordinateY[1]);
            Element<Polygon> polygon = new Element<>();
            polygon.fade(triangle);
            Element<Button> b = new Element<>();
            setDefaultColor();
            b.colorTransition(btnSettingText);
            caption.setText("меню настроек текста");
            loadSettingScene(getClass().getResource("/fxml/parts/settingText.fxml"));
        });

        btnInternal.setOnAction(event -> {
            paneSettings.getChildren().removeAll(settingScene);
            triangle.setOpacity(0);
            triangle.setLayoutY(coordinateY[2]);
            Element<Polygon> polygon = new Element<>();
            polygon.fade(triangle);
            Element<Button> b = new Element<>();
            setDefaultColor();
            b.colorTransition(btnInternal);
            caption.setText("внутренние настройки");
            loadSettingScene(getClass().getResource("/fxml/parts/settingInternal.fxml"));
        });

        btnSettingPersistent.setOnAction(event -> {
            paneSettings.getChildren().removeAll(settingScene);
            triangle.setOpacity(0);
            triangle.setLayoutY(coordinateY[3]);
            Element<Polygon> polygon = new Element<>();
            polygon.fade(triangle);
            Element<Button> b = new Element<>();
            setDefaultColor();
            b.colorTransition(btnSettingPersistent);
            caption.setText("найстройки привелегий");
            loadSettingScene(getClass().getResource("/fxml/parts/settingPersistent.fxml"));
        });


    }



    private void loadSettingScene(URL path){
        try {
            settingScene = FXMLLoader.load(path);
            paneSettings.getChildren().add(settingScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDefaultColor(){
        btnSettingText.setStyle("-fx-background-color: rgb(35,35,35)");
        btnSettingChroma.setStyle("-fx-background-color: rgb(35,35,35)");
        btnInternal.setStyle("-fx-background-color: rgb(35,35,35)");
        btnSettingPersistent.setStyle("-fx-background-color: rgb(35,35,35)");
    }
}
