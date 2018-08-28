package controller;

import entity.UsersEntity;
import interfaces.impls.Authentication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;
import parser.ConverterJSON;
import parser.pojo.UserCache;
import util.CacheUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

public class AuthController {

    @FXML
    private ImageView btnClose;

    @FXML
    private Pane upBorder;

    @FXML
    private TextField textPassword;

    @FXML
    private TextField textLogin;

    @FXML
    private Button signIn;

    @FXML
    private Button getAccount;

    @FXML
    private Label returnTo;

    private double xOffset;
    private double yOffset;


    @FXML
    void initialize() {
        upBorder.setOnMousePressed(event -> {
            xOffset = upBorder.getScene().getWindow().getX() - event.getScreenX();
            yOffset = upBorder.getScene().getWindow().getY() - event.getScreenY();
        });


        upBorder.setOnMouseDragged(event -> {
            upBorder.getScene().getWindow().setX(event.getScreenX() + xOffset);
            upBorder.getScene().getWindow().setY(event.getScreenY() + yOffset);
        });

        btnClose.setOnMouseClicked(event -> System.exit(0));

        getAccount.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("http://transcom69.ru/ssc/"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        signIn.setOnAction(event -> {

            if (textLogin.getText() == "" || textPassword.getText() == ""){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка ввода");
                alert.setHeaderText("Авторизация");
                alert.setContentText("Заполните, пожалуйста все поля.");
                alert.showAndWait();
                return;
            }

            Authentication auth = new Authentication();
            for (UsersEntity u :
                    auth.findByLogin(textLogin.getText())) {
                if (u.getLogin().equals(textLogin.getText()) && u.getPassword().equals(textPassword.getText())){
                    UsersEntity.user = auth.getUserById(u.getId());
                    CacheUtil cache = new CacheUtil(new UserCache(u.getLogin(),DigestUtils.md5Hex(u.getPassword()),String.valueOf(new Date().getTime()),u.getId()));
                    cache.save();
                    ConverterJSON<UserCache> z = new ConverterJSON<>(UserCache.class);
                    URL url = getClass().getResource("/json/data.json");
                    z.setSetting(new File(url.getPath()));
                    try {
                        UserCache a =  z.toObject();
                        System.out.println(a.getPassword());
                        System.out.println(a.getLogin());
                        System.out.println(a.getDateFrom());
                        System.out.println(a.getUserID());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        });

        returnTo.setOnMouseClicked(event ->{
            Font.loadFont(getClass().getResource("/fonts/uni.ttf").toExternalForm(), 10);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                Scene scene = new Scene(root,500,600);
                scene.getStylesheets().add(getClass().getResource("/stylesheets/font.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Smart Stream Chat");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/favicon.png")));
                stage.show();
                returnTo.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}