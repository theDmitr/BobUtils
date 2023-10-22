package dmitr.bobutils.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Utils {

    public static String getHomeFolder() {
        String homePath = System.getProperty("user.home");
        String bobHome = "/BobUtils";

        File home = new File(homePath, bobHome);

        if (!home.exists())
            home.mkdir();

        return home.getAbsolutePath();
    }

    public static Stage getStageFromActionEvent(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }

    public static <T> ObservableList<T> listToObservableList(List<T> list) {
        return FXCollections.observableList(list);
    }

}
