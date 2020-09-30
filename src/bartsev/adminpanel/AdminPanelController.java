package bartsev.adminpanel;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.UserActions;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AdminPanelController {
    @FXML
    private JFXButton changeAdminPasswordButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton aboutProgramButton;

    @FXML
    private JFXButton listOfUsersButton;

    @FXML
    private JFXButton addNewUserButton;

    @FXML
    void initialize() {
        addNewUserButton.setOnAction(event -> {
            LoadScenes.loadAddNewUserWindow();
            addNewUserButton.getScene().getWindow().hide();
        });

        listOfUsersButton.setOnAction(event -> {
            LoadScenes.loadUserListWindow();
            listOfUsersButton.getScene().getWindow().hide();
        });

        changeAdminPasswordButton.setOnAction(event -> {
            changeAdminPasswordButton.getScene().getWindow().hide();
            LoadScenes.loadChangePasswordByAdminWindow(UserActions.getUser("ADMIN"));
        });

        aboutProgramButton.setOnAction(event -> {
            aboutProgramButton.getScene().getWindow().hide();
            LoadScenes.loadAboutProgramFromAdminPanel();
        });

        exitButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
    }
}
