package dmitr.bobutils.scene;

import dmitr.bobutils.controller.BaseController;
import dmitr.bobutils.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class SceneController {

    public static void setScene(Stage stage, Scenes scene) {
        stage.setTitle(scene.getCaption());
        stage.setScene(scene.getRoot());
        scene.getController().initialize(null, null);
        stage.show();
    }

    public static void setScene(ActionEvent actionEvent, Scenes scene) {
        Stage stage = Utils.getStageFromActionEvent(actionEvent);
        setScene(stage, scene);
    }

}
