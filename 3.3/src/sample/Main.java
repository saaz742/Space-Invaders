package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    static Stage primStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
       String path = "src/sounds/spaceInvader.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);


        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("space invaders");
        primaryStage.setScene(new Scene(root, 1324, 828));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
