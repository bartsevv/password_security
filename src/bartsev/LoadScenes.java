package bartsev;

import bartsev.adminpanel.AdminPanelController;
import bartsev.adminpanel.userlist.EditUserController;
import bartsev.adminpanel.userlist.UserListController;
import bartsev.signin.RottenPasswordController;
import bartsev.users.User;
import bartsev.userwindow.UserWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadScenes {
    private final static String PATH_TO_USER_WINDOW = "/bartsev/userwindow/UserWindow.fxml";
    private final static String PATH_TO_ADMIN_WINDOW = "/bartsev/adminpanel/AdminPanel.fxml";
    private final static String PATH_TO_ADD_USER_WINDOW = "/bartsev/adminpanel/addnewuser/AddNewUser.fxml";
    private final static String PATH_TO_EDIT_USER_WINDOW = "/bartsev/adminpanel/userlist/EditUser.fxml";
    private final static String PATH_TO_UPDATE_ROTTEN_PASSWORD_WINDOW = "/bartsev/signin/RottenPassword.fxml";

    public static void loadUserWindow(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_USER_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserWindowController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "User panel");
    }

    public static void loadAdminWindow(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_ADMIN_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminPanelController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "Admin panel");
    }

    public static void loadAddNewUserWindow() {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_ADD_USER_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadStage(root, "Add new user");
    }

    public static void loadUserListWindow() {
        try {
            new UserListController().initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadEditUserWindow(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_EDIT_USER_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EditUserController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "Edit user");
    }

    public static void loadChangeRottenPasswordWindow(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_UPDATE_ROTTEN_PASSWORD_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RottenPasswordController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "Update password");
    }

    private static void loadStage(Parent root, String title) {
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }
}
