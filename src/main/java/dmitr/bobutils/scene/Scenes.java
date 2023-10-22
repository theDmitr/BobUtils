package dmitr.bobutils.scene;

import dmitr.bobutils.controller.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public enum Scenes {

    MENU("BobUtils | Menu", Scenes.class.getResource("/view/Menu.fxml")),
    TRANSLATOR("BobUtils | Translator", Scenes.class.getResource("/view/Translator.fxml")),
    FRAGMENTS("BobUtils | Fragments", Scenes.class.getResource("/view/Fragments.fxml"));

    private final String caption;
    private final Scene root;
    private final FXMLLoader loader;

    Scenes(String caption, URL path) {
        this.caption = caption;
        try {
            this.loader = new FXMLLoader(path);
            this.root = new Scene(loader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getCaption() {
        return caption;
    }

    public Scene getRoot() {
        return root;
    }

    public BaseController getController() {
        return loader.getController();
    }
}
