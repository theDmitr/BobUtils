package dmitr.bobutils.controller;

import dmitr.bobutils.converter.FragmentsGroupToStringConverter;
import dmitr.bobutils.data.Translator;
import dmitr.bobutils.database.FragmentsManager;
import dmitr.bobutils.model.Fragment;
import dmitr.bobutils.model.FragmentsGroup;
import dmitr.bobutils.scene.SceneController;
import dmitr.bobutils.scene.Scenes;
import dmitr.bobutils.util.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TranslatorController extends BaseController {

    @FXML
    private TextArea userArea;
    @FXML
    private TextArea translateArea;
    @FXML
    private Button upperButton;
    @FXML
    private Button lowerButton;
    @FXML
    private Button menuButton;
    @FXML
    private ChoiceBox<FragmentsGroup> fragmentsGroupsChoiceBox;
    @FXML
    private Button applyFragmentsGroupsButton;

    private void applyFragmentsGroups(FragmentsGroup fragmentsGroup) {
        List<Fragment> fragments = FragmentsManager.getFragmentsByFragmentsGroup(fragmentsGroup);

        String result = userArea.getText();

        for (Fragment fragment : fragments) {
            String content = fragment.getContent();
            result = result.replaceAll("(?i)" + content, content.toUpperCase());
        }

        userArea.setText(result);
    }

    public void updateFragmentsGroups() {
        fragmentsGroupsChoiceBox.setItems(Utils.listToObservableList(FragmentsManager.getAllFragmentsGroups()));
    }

    private void applyActions() {
        userArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String appendsText, String allText) {
                new Thread(() -> {
                    String translatedText = Translator.translate("en", "ru", allText);
                    try {
                        translateArea.setText(translatedText);
                    } catch (RuntimeException ignored) {
                    }
                }).start();
            }
        });

        upperButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                markSelectedText(true);
            }
        });

        lowerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                markSelectedText(false);
            }
        });

        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneController.setScene(actionEvent, Scenes.MENU);
            }
        });

        applyFragmentsGroupsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FragmentsGroup fragmentsGroup = fragmentsGroupsChoiceBox.getSelectionModel().getSelectedItem();
                if (fragmentsGroup == null)
                    return;
                applyFragmentsGroups(fragmentsGroup);
            }
        });
    }

    private void markSelectedText(boolean upper) {
        IndexRange selection = userArea.getSelection();

        if (selection.getLength() == 0)
            return;

        int indexStart = selection.getStart();
        int indexEnd = selection.getEnd();

        String currentText = userArea.getText();

        String start = currentText.substring(0, indexStart);
        String selected = currentText.substring(indexStart, indexEnd);
        selected = upper ? selected.toUpperCase() : selected.toLowerCase();
        String end = currentText.substring(indexEnd);

        userArea.setText(start + selected + end);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateFragmentsGroups();
        fragmentsGroupsChoiceBox.setConverter(new FragmentsGroupToStringConverter());
        applyActions();
    }

}
