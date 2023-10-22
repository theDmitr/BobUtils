package dmitr.bobutils.controller;

import dmitr.bobutils.converter.FragmentToStringConverter;
import dmitr.bobutils.converter.FragmentsGroupToStringConverter;
import dmitr.bobutils.database.FragmentsManager;
import dmitr.bobutils.model.Fragment;
import dmitr.bobutils.model.FragmentsGroup;
import dmitr.bobutils.scene.SceneController;
import dmitr.bobutils.scene.Scenes;
import dmitr.bobutils.util.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

import java.net.URL;
import java.util.ResourceBundle;

public class FragmentsController extends BaseController {

    @FXML
    private ComboBox<FragmentsGroup> fragmentsGroupsComboBox;

    @FXML
    private ListView<Fragment> fragmentsListView;

    @FXML
    private Button insertFragmentButton;

    @FXML
    private Button removeFragmentButton;

    @FXML
    private TextField insertFragmentField;

    @FXML
    private TextField insertFragmentsGroupField;

    @FXML
    private Button insertFragmentsGroupButton;

    @FXML
    private Button removeFragmentsGroupButton;

    @FXML
    private Button menuButton;

    private void updateFragmentsList(FragmentsGroup fragmentsGroup) {
        ObservableList<Fragment> fragments =
                Utils.listToObservableList(FragmentsManager.getFragmentsByFragmentsGroup(fragmentsGroup));
        fragmentsListView.setItems(fragments);
    }

    private void updateFragmentsGroupsList() {
        ObservableList<FragmentsGroup> fragments = Utils.listToObservableList(FragmentsManager.getAllFragmentsGroups());
        fragmentsGroupsComboBox.setItems(fragments);
    }

    private void applyActions() {
        fragmentsGroupsComboBox.setOnAction((actionEvent -> {
            FragmentsGroup fragmentsGroup = fragmentsGroupsComboBox.getSelectionModel().getSelectedItem();
            if (fragmentsGroup == null)
                return;
            updateFragmentsList(fragmentsGroup);
        }));

        insertFragmentButton.setOnAction(actionEvent -> {
            FragmentsGroup fragmentsGroup = fragmentsGroupsComboBox.getSelectionModel().getSelectedItem();
            String content = insertFragmentField.getText();
            if (content == null || fragmentsGroup == null || content.isEmpty())
                return;
            FragmentsManager.insertFragmentToFragmentsGroup(fragmentsGroup, content);
            updateFragmentsList(fragmentsGroup);
            insertFragmentField.setText(null);
        });

        removeFragmentButton.setOnAction(actionEvent -> {
            FragmentsGroup fragmentsGroup = fragmentsGroupsComboBox.getSelectionModel().getSelectedItem();
            Fragment fragment = fragmentsListView.getSelectionModel().getSelectedItem();
            if (fragmentsGroup == null || fragment == null)
                return;
            FragmentsManager.removeFragmentFragmentsGroup(fragmentsGroup, fragment);
            updateFragmentsList(fragmentsGroup);
        });

        insertFragmentsGroupButton.setOnAction(actionEvent -> {
            String content = insertFragmentsGroupField.getText();
            if (content == null || content.isEmpty())
                return;
            FragmentsManager.insertFragmentsGroup(insertFragmentsGroupField.getText());
            updateFragmentsGroupsList();
            insertFragmentsGroupField.setText(null);
        });

        removeFragmentsGroupButton.setOnAction(actionEvent -> {
            FragmentsGroup fragmentsGroup = fragmentsGroupsComboBox.getSelectionModel().getSelectedItem();
            if (fragmentsGroup == null)
                return;
            FragmentsManager.removeFragmentsGroup(fragmentsGroup);
            updateFragmentsGroupsList();
            updateFragmentsList(fragmentsGroup);
        });

        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneController.setScene(actionEvent, Scenes.MENU);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateFragmentsGroupsList();

        fragmentsGroupsComboBox.setConverter(new FragmentsGroupToStringConverter());

        fragmentsListView.setCellFactory(l -> {
            TextFieldListCell<Fragment> cell = new TextFieldListCell<>();
            cell.setConverter(new FragmentToStringConverter());
            return cell;
        });

        applyActions();
    }

}
