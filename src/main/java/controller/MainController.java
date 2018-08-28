package controller;

import animation.Element;
import entity.UsersEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.CacheUtil;
import youtube.ListLiveChatMessages;
import youtube.URL;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainController {

    @FXML
    private ImageView btnClose;

    @FXML
    private Button btnVk;

    @FXML
    private Button btnChat;

    @FXML
    private  ImageView btnSetting;

    @FXML
    private Pane upBorder;

    @FXML
    private TextField textLink;

    @FXML
    private Button btnAuth;

    private double xOffset;
    private double yOffset;

    @FXML
    void initialize() {

        CacheUtil cacheUtil = new CacheUtil();
        cacheUtil.checkCacheUser();
        if (UsersEntity.user == null)
            btnSetting.setVisible(false);


        upBorder.setOnMousePressed(event -> {
            xOffset = upBorder.getScene().getWindow().getX() - event.getScreenX();
            yOffset = upBorder.getScene().getWindow().getY() - event.getScreenY();
        });


        upBorder.setOnMouseDragged(event -> {
            upBorder.getScene().getWindow().setX(event.getScreenX() + xOffset);
            upBorder.getScene().getWindow().setY(event.getScreenY() + yOffset);
        });

        btnClose.setOnMouseClicked(event -> System.exit(0));

        btnVk.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://vk.com/bobers_yt"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });


        btnChat.setOnAction(event -> {
            try {
                URL.parseVideoURL(textLink.getText());

                if (URL.videoId != null && URL.videoId != "1" && ListLiveChatMessages.checkURL()) {
                    Parent rootChat = FXMLLoader.load(getClass().getResource("/fxml/chats.fxml"));
                    Stage chatStage = new Stage();
                    chatStage.initStyle(StageStyle.UNDECORATED);
                    Scene chatScene = new Scene(rootChat, 500, 600);
                    chatStage.setScene(chatScene);
                    chatStage.setTitle("Чат - SSC");
                    chatStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/favicon.png")));
                    chatStage.show();
                    btnChat.getScene().getWindow().hide();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Element<ImageView> close = new Element<>(4,0,360,50);
        btnClose.setOnMouseEntered(event->close.rotate(btnClose));
        btnClose.setOnMouseExited(event ->close.pause());


        Element<ImageView> setting = new Element<>(2,0,360,20);
        btnSetting.setOnMouseEntered(event->setting.rotate(btnSetting));
        btnSetting.setOnMouseExited(event ->setting.pause());


        btnAuth.setOnAction(event -> {
            try {
                Parent rootAuth = FXMLLoader.load(getClass().getResource("/fxml/auths.fxml"));
                Stage authStage = new Stage();
                authStage.initStyle(StageStyle.UNDECORATED);
                Scene authScene = new Scene(rootAuth, 500, 600);
                authScene.getStylesheets().add(getClass().getResource("/stylesheets/font.css").toExternalForm());
                authStage.setScene(authScene);
                authStage.setTitle("Авторизация - SSC");
                authStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/favicon.png")));
                authStage.show();
                btnAuth.getScene().getWindow().hide();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        btnSetting.setOnMouseClicked(event -> {
            try {
                Parent rootSetting = FXMLLoader.load(getClass().getResource("/fxml/settings.fxml"));
                Stage authStage = new Stage();
                authStage.initStyle(StageStyle.UNDECORATED);
                Scene authScene = new Scene(rootSetting, 900, 550);
                authScene.getStylesheets().add(getClass().getResource("/stylesheets/font.css").toExternalForm());
                authStage.setScene(authScene);
                authStage.setTitle("Настройки - SSC");
                authStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/favicon.png")));
                authStage.show();
                btnSetting.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
