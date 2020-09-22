package bartsev.adminpanel;

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
            loadAddNewUserScene();
        });

        listOfUsersButton.setOnAction(event -> {
            try {
                loadUserListScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void transferMessage(User user) {
        this.user = user;
    }

    private void loadAddNewUserScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bartsev/adminpanel/addnewuser/AddNewUser.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add new user");
        stage.show();
    }

    private void loadUserListScene() throws IOException {
        new UserListController().initialize();
    }
}
