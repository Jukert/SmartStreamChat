package base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import util.HibernateUtil;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass().getResource("/fonts/uni.ttf").toExternalForm(), 10);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root,500,600);
        scene.getStylesheets().add(getClass().getResource("/stylesheets/font.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Smart Stream Chat");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/favicon.png")));
        primaryStage.show();

        Session session = HibernateUtil.getSession();
        session.close();
        /*NetworkInterface inter = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        System.out.println(inter.getHardwareAddress());*/

    }

    public static void main(String[] args) {
        launch(args);
    }
}
