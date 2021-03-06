package bartsev.adminpanel;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.UserActions;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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
    private JFXButton logsButton;

    @FXML
    private JFXButton magicSquare;

    @FXML
    private ImageView imageAdmin;

    @FXML
    private JFXButton selectImageButton;

    @FXML
    void initialize() {
        File f = new File("ADMIN.png");
        if (f.exists()) {
            imageAdmin.setImage(new Image(f.toURI().toString()));
        }

        selectImageButton.setOnAction(event -> {
            LoadScenes.loadFileChooser(UserActions.getUser("ADMIN"));
        });

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

        logsButton.setOnAction(event -> {
            try {
                LoadScenes.loadLogsWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

        magicSquare.setOnAction(event -> {
            aboutProgramButton.getScene().getWindow().hide();
            LoadScenes.loadMagicSquare();
        });
    }
}
