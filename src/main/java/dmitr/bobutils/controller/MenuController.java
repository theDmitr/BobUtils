package dmitr.bobutils.controller;

import dmitr.bobutils.scene.SceneController;
import dmitr.bobutils.scene.Scenes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends BaseController {

    @FXML
    private Button translatorButton;

    @FXML
    private Button fragmentsButton;

    private void applyActions() {
        translatorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneController.setScene(actionEvent, Scenes.TRANSLATOR);
            }
        });

        fragmentsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneController.setScene(actionEvent, Scenes.FRAGMENTS);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        applyActions();
    }

}
