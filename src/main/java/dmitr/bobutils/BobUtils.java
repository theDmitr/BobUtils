package dmitr.bobutils;

import dmitr.bobutils.scene.SceneController;
import dmitr.bobutils.scene.Scenes;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BobUtils extends Application {

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Image logo = new Image(Objects.requireNonNull(BobUtils.class.getResourceAsStream("/icon/logo.png")));
        stage.getIcons().add(logo);
        SceneController.setScene(stage, Scenes.MENU);
    }

}