package sample;

import game.Data;
import game.Player;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    @FXML
    private TextField SignUpUsername;
    @FXML
    private TextField username;
    @FXML
    private TextField newUsername;
    @FXML
    private Label readyMs;
    @FXML
    private Label userMs;
    @FXML
    private Label newUserMs;
    @FXML
    private Label signUpUserMs;
    @FXML
    private Label scoreMs;
    Stage window = new Stage();
    Scene pageTwoScene;
    public static ObservableList data = FXCollections.observableArrayList();
    ArrayList<ImageView> allAliens = new ArrayList<>();
    ArrayList<ImageView> allShots = new ArrayList<>();


    public ImageView spaceShip;
    @FXML
    private GridPane aliens;
    @FXML
    private Label score;
    @FXML
    public static AnchorPane pane;
    private Double time = -30.0;
    private Double moveTime = 1.0;

    private int scores = 0;


    public void move() throws IOException {
        createAliens();
        createSpaceShip();
        moveAlien();
        alienShot();
        pageTwoScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case D:
                    spaceShip.setLayoutX(spaceShip.getLayoutX() + 100);
                    break;
                case A:
                    spaceShip.setLayoutX(spaceShip.getLayoutX() - 100);
                    break;

                case SPACE:
                    try {
                        sho();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    break;
            }
        });
    }

    private void createSpaceShip() {
        spaceShip = new ImageView(new Image("sample/images/spaceShip.png"));
        spaceShip.setLayoutX(607);
        spaceShip.setLayoutY(776);
        spaceShip.setFitHeight(100);
        spaceShip.setFitWidth(100);
        pane.getChildren().add(spaceShip);
    }


    private void createAliens() {
        ArrayList<ImageView> aliens1 = new ArrayList<>();
        ArrayList<ImageView> aliens2 = new ArrayList<>();
        ArrayList<ImageView> aliens3 = new ArrayList<>();
        ArrayList<ImageView> aliens4 = new ArrayList<>();
        aliens = new GridPane();
        aliens.setPrefSize(100, 100);
        aliens.setLayoutY(75);
        aliens.setLayoutX(10);

        for (int i = 0; i < 6; i++) {
            ImageView alien1 = new ImageView(new Image("sample/images/pAlien.png"));
            alien1.setFitHeight(75);
            alien1.setFitWidth(75);
            aliens1.add(alien1);
            allAliens.add(alien1);

        }
        for (int i = 0; i < 6; i++) {
            ImageView alien2 = new ImageView(new Image("sample/images/blAlien.png"));
            alien2.setFitHeight(75);
            alien2.setFitWidth(75);
            aliens2.add(alien2);
            allAliens.add(alien2);
        }
        for (int i = 0; i < 6; i++) {
            ImageView alien3 = new ImageView(new Image("sample/images/gAlien.png"));
            alien3.setFitHeight(75);
            alien3.setFitWidth(75);
            aliens3.add(alien3);
            allAliens.add(alien3);
        }
        for (int i = 0; i < 6; i++) {
            ImageView alien4 = new ImageView(new Image("sample/images/bgAlien.png"));
            alien4.setFitHeight(75);
            alien4.setFitWidth(75);
            aliens4.add(alien4);
            allAliens.add(alien4);
        }

        aliens.add(aliens1.get(0), 0, 0);
        aliens.add(aliens1.get(1), 1, 0);
        aliens.add(aliens1.get(2), 2, 0);
        aliens.add(aliens1.get(3), 3, 0);
        aliens.add(aliens1.get(4), 4, 0);
        aliens.add(aliens1.get(5), 5, 0);

        aliens.add(aliens2.get(0), 0, 1);
        aliens.add(aliens2.get(1), 1, 1);
        aliens.add(aliens2.get(2), 2, 1);
        aliens.add(aliens2.get(3), 3, 1);
        aliens.add(aliens2.get(4), 4, 1);
        aliens.add(aliens2.get(5), 5, 1);

        aliens.add(aliens3.get(0), 0, 2);
        aliens.add(aliens3.get(1), 1, 2);
        aliens.add(aliens3.get(2), 2, 2);
        aliens.add(aliens3.get(3), 3, 2);
        aliens.add(aliens3.get(4), 4, 2);
        aliens.add(aliens3.get(5), 5, 2);

        aliens.add(aliens4.get(0), 0, 3);
        aliens.add(aliens4.get(1), 1, 3);
        aliens.add(aliens4.get(2), 2, 3);
        aliens.add(aliens4.get(3), 3, 3);
        aliens.add(aliens4.get(4), 4, 3);
        aliens.add(aliens4.get(5), 5, 3);
        pane.getChildren().add(aliens);
        alienMove();

    }

    private void alienMove() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(aliens);
        transition.setDuration(Duration.seconds(1));
        transition.setToX(850);
        transition.setAutoReverse(true);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.play();
    }

    private void moveAlien() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveTime += 0.1;
                if (moveTime > 20.0) {
                    aliens.setLayoutY(aliens.getLayoutY() + 30);
                    moveTime = 0.0;
                }
            }
        }.start();
    }

    private void shotSound() {
        String path = "src/sounds/shot.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    private void expSound() {
/*        String path = "src/sounds/explosion.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

 */
    }

    public void alienShot() throws IOException {
        Random random = new Random();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                start();
                time += 0.1;
                if (time > 2) {
                    if (allAliens.size() > 0) {
                        int n = random.nextInt(allAliens.size() - 1);
                        shotSound();
                        TranslateTransition transition = new TranslateTransition();
                        ImageView shot = new ImageView(new Image("sample/images/shot.png"));
                        pane.getChildren().add(shot);
                        allShots.add(shot);
                        transition.setNode(shot);
                        transition.setCycleCount(1);
                        transition.setFromY(allAliens.get(n).getLayoutY() + 220);
                        transition.setFromX(allAliens.get(n).getTranslateX() + 500);
                        transition.setDuration(Duration.seconds(1));
                        transition.setToY(1000);
                        transition.play();
                        try {
                            if (hit(shot)) {
                                stop();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        time = 0.0;
                    }
                }

            }
        }.start();

    }

    private boolean hit(ImageView shot) throws IOException {
        if (shot.getBoundsInLocal().intersects(spaceShip.getBoundsInLocal())) {
            expSound();
            pane.getChildren().remove(spaceShip);
            gameOver();
            return true;
        }
        return false;
    }


    private void destroy(ImageView shot) {
        ImageView alien = null;
        for (ImageView al : allAliens) {
            if (shot.getBoundsInLocal().intersects(al.getBoundsInLocal())) {
                expSound();
                alien = al;
                //shot.setVisible(false);
                scores++;
                score = new Label();
                pane.getChildren().add(score);
                score.setPrefSize(100, 100);
                score.setLayoutY(700);
                score.setLayoutX(700);
                score.setStyle("-fx-font-size: 32px");
                score.setStyle("-fx-color: white");
                score.setText(String.valueOf(scores));
                Data.getLoginPlayer().setScore(1);
                if (allAliens.size() <= 0) {
                    show("you won");
                }
            } else alien = null;
        }
        if (alien != null) {
            alien.setImage(null);
            allAliens.remove(alien);
            pane.getChildren().remove(alien);
        }
    }


    private void gameOver() throws IOException {
        show("game over!");
    }

    private void finish() throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene pageTwoScene = new Scene(page1, 1324, 828);
        Stage window = Main.primStage;
        window.setScene(pageTwoScene);
        window.show();

    }

    private void show(String text) {
        Label label = new Label();
        StackPane root = new StackPane();
        root.getChildren().add(label);
        label.setText(text);
        Stage massage = new Stage();
        massage.setScene(new Scene(root, 500, 500));
        massage.show();
    }

    public void sho() throws IOException {
        shotSound();
        TranslateTransition transition = new TranslateTransition();
        ImageView shot = new ImageView(new Image("sample/images/shot.png"));
        pane.getChildren().add(shot);
        transition.setNode(shot);
        transition.setFromY(spaceShip.getLayoutY() + 5);
        transition.setFromX(spaceShip.getLayoutX() + 50);
        transition.setDuration(Duration.seconds(1));
        transition.setToY(-600);
        transition.setCycleCount(1);
        transition.play();
        destroy(shot);

    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void scoreBoard(MouseEvent mouseEvent) {
        if (Data.getLoginPlayer().score().size() != 0) {
            data.clear();
            data.addAll(Player.score());
            showList();
        } else scoreMs.setText("there is no player yet");
    }

    public void signUp(MouseEvent mouseEvent) {
        String newUser = SignUpUsername.getText();
        if (!Player.isTherePlayerWithName(newUser)) {
            Player player = new Player(newUser);
            signUpUserMs.setText("sign up successfully");
        } else signUpUserMs.setText("there is player with this name");
    }

    public void login(MouseEvent mouseEvent) {
        String user = username.getText();
        if (Player.isTherePlayerWithName(user)) {
            Data.setLoginPlayer(Player.getPlayerByName(user));
            userMs.setText("login successfully");
        } else userMs.setText("there is no player with this name");
    }

    public void newUsername(MouseEvent mouseEvent) {
        Player login = Data.getLoginPlayer();
        String newUser = newUsername.getText();
        if (login != null) {
            if (!Player.isTherePlayerWithName(newUser)) {
                Data.getLoginPlayer().setName(newUser);
                newUserMs.setText("username changed");
            } else newUserMs.setText("there is player with this name");
        } else newUserMs.setText("you have to login first");
    }

    public void ready(MouseEvent mouseEvent) throws IOException {
        if (Data.getLoginPlayer() != null) {
            goToGame(mouseEvent);
        } else readyMs.setText("you have to set username first");
    }

    private void goToGame(MouseEvent mouseEvent) throws IOException {
        Game.pane = FXMLLoader.load(getClass().getResource("game.fxml"));
        pageTwoScene = new Scene(Game.pane, 1328, 922);
        window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        move();
        window.setScene(pageTwoScene);
        window.show();

    }

    private void showList() {
        ListView listView = new ListView(data);
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        Stage list = new Stage();
        list.setScene(new Scene(root, 200, 250));
        list.show();
    }


/*    private void alienMove(ImageView alienPic) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(alienPic);
        transition.setDuration(Duration.seconds(1));
        transition.setToX(800);
        transition.setAutoReverse(true);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.play();
    }

    private void createAliens() {
        for (int i=1;i<=8;i++){
            for (int j=1 ; j<=4 ;j++){
                ImageView alienPic = new ImageView();
                Image img = new Image("sample/images/pAlien.png");
                if (j % 4 == 0){
                    alienPic.setImage(img);
                }
                else if (j % 4 == 1){
                    img = new Image("sample/images/blAlien.png");
                    alienPic.setImage(img);
                }
                else if (j % 4 == 2){
                    img = new Image("sample/images/gAlien.png");
                    alienPic.setImage(img);
                }
                else {
                    img = new Image("sample/images/bgAlien.png");
                    alienPic.setImage(img);
                }
                pane.getChildren().add(alienPic);
                alienPic.setLayoutX(60*i);
                alienPic.setLayoutY(60*j);
                alienPic.setFitHeight(50);
                alienPic.setFitWidth(50);
                allAliens.add(alienPic);
             alienMove(alienPic);
            }
        }

    }

    */
}
