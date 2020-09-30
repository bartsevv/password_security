package bartsev.helpers;

import bartsev.adminpanel.changepassword.ChangeAdminPasswordController;
import bartsev.adminpanel.listofusers.UserListController;
import bartsev.adminpanel.listofusers.edituser.EditUserController;
import bartsev.adminpanel.listofusers.edituser.restrictions.PasswordRestrictionsController;
import bartsev.models.User;
import bartsev.models.UserRestrictions;
import bartsev.signin.rottenpassword.RottenPasswordController;
import bartsev.userwindow.UserWindowController;
import bartsev.userwindow.changepassword.ChangePasswordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadScenes {
    private final static String PATH_TO_USER_WINDOW = "/bartsev/userwindow/UserWindow.fxml";
    private final static String PATH_TO_ADMIN_WINDOW = "/bartsev/adminpanel/AdminPanel.fxml";
    private final static String PATH_TO_ADD_USER_WINDOW = "/bartsev/adminpanel/addnewuser/AddNewUser.fxml";
    private final static String PATH_TO_EDIT_USER_WINDOW = "/bartsev/adminpanel/listofusers/edituser/EditUser.fxml";
    private final static String PATH_TO_UPDATE_ROTTEN_PASSWORD_WINDOW = "/bartsev/signin/rottenpassword/RottenPassword.fxml";
    private final static String PATH_TO_CHANGE_USER_PASSWORD_WINDOW = "/bartsev/userwindow/changepassword/ChangePassword.fxml";
    private final static String PATH_TO_CHANGE_ADMIN_PASSWORD_BY_ADMIN_WINDOW = "/bartsev/adminpanel/changepassword/ChangeAdminPassword.fxml";
    private final static String PATH_TO_CHANGE_USER_PASSWORD_BY_ADMIN_WINDOW = "/bartsev/adminpanel/listofusers/edituser/changeuserpassword/ChangePassword.fxml";
    private final static String PATH_TO_PASSWORD_RESTRICTIONS_WINDOW = "/bartsev/adminpanel/listofusers/edituser/restrictions/PasswordRestrictions.fxml";

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

    public static void loadAdminWindow() {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_ADMIN_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void loadChangeRottenPasswordWindow(User user, UserRestrictions userRestrictions) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_UPDATE_ROTTEN_PASSWORD_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RottenPasswordController sceneController = loader.getController();
        sceneController.transferMessage(user, userRestrictions);

        loadStage(root, "Update password");
    }

    public static void loadChangePasswordByUserWindow(User user, UserRestrictions userRestrictions) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_CHANGE_USER_PASSWORD_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChangePasswordController sceneController = loader.getController();
        sceneController.transferMessage(user, userRestrictions);

        loadStage(root, "Change password");
    }

    public static void loadChangePasswordByAdminWindow(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_CHANGE_ADMIN_PASSWORD_BY_ADMIN_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChangeAdminPasswordController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "Change password");
    }

    public static void loadChangeUserPasswordByAdminWindow(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_CHANGE_USER_PASSWORD_BY_ADMIN_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bartsev.adminpanel.listofusers.edituser.changeuserpassword.ChangePasswordController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "Change password");
    }

    public static void loadPasswordRestrictionsWindow(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_PASSWORD_RESTRICTIONS_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PasswordRestrictionsController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "Change password");
    }

    private static void loadStage(Parent root, String title) {
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }
}
