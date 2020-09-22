package bartsev.adminpanel;

import bartsev.LoadScenes;
import bartsev.adminpanel.addnewuser.AddNewUserController;
import bartsev.adminpanel.userlist.UserListController;
import bartsev.users.User;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminPanelController {
    private User user;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        });

        listOfUsersButton.setOnAction(event -> {
            LoadScenes.loadUserListWindow();
        });
    }

    public void transferMessage(User user) {
        this.user = user;
    }
}
