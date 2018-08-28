package youtube;

import javafx.scene.control.Alert;

import java.net.MalformedURLException;

public class URL {

    public static String videoId;

    public static void parseVideoURL(String url){
        videoId = null;
        try {
            java.net.URL aURL = new java.net.URL(url);
            String query = aURL.getQuery();
            String queryArray[] = query.split("&");
            if (queryArray.length > 1)
                return;

            int index = query.indexOf("=");

            videoId = query.substring(index+1);
        }
        catch (MalformedURLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("YouTube Chat");
            alert.setContentText("Введена некоректная ссылка!!!");
            alert.showAndWait();
            videoId  = "1";
        }

    }

}
