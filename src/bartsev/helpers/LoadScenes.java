package bartsev.helpers;

import bartsev.Launch;
import bartsev.adminpanel.aboutprogram.AboutProgramController;
import bartsev.adminpanel.changepassword.ChangeAdminPasswordController;
import bartsev.adminpanel.listofusers.UserListController;
import bartsev.adminpanel.listofusers.edituser.EditUserController;
import bartsev.adminpanel.listofusers.edituser.accesses.AccessToUsbController;
import bartsev.adminpanel.listofusers.edituser.accesses.LogsController;
import bartsev.adminpanel.listofusers.edituser.restrictions.PasswordRestrictionsController;
import bartsev.adminpanel.magicsquare.MagicSquareController;
import bartsev.models.User;
import bartsev.models.UserRestrictions;
import bartsev.signin.rottenpassword.RottenPasswordController;
import bartsev.userwindow.UserWindowController;
import bartsev.userwindow.changepassword.ChangePasswordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
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
    private final static String PATH_TO_USER_ACCESS_WINDOW = "/bartsev/adminpanel/listofusers/edituser/accesses/AccessToUsb2.fxml";
    private final static String PATH_TO_ABOUT_PROGRAM_FROM_ADMIN_PANEL = "/bartsev/adminpanel/aboutprogram/AboutProgram.fxml";
    private final static String PATH_TO_ABOUT_PROGRAM_FROM_USER_PANEL = "/bartsev/userwindow/aboutprogram/AboutProgram.fxml";
    private final static String PATH_TO_MAGIC_SQUARE = "/bartsev/adminpanel/magicsquare/MagicSquare.fxml";
    private final static String PATH_TO_VIEW_MAGIC_SQUARE = "/bartsev/adminpanel/magicsquare/ViewMagicSquare.fxml";
    private final static String PATH_TO_EDIT_MAGIC_SQUARE = "/bartsev/adminpanel/magicsquare/AddNewMagicSquare.fxml";

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

    public static void loadAboutProgramFromUserPanel(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_ABOUT_PROGRAM_FROM_USER_PANEL));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bartsev.userwindow.aboutprogram.AboutProgramController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "About program");
    }

    public static void loadAboutProgramFromAdminPanel() {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_ABOUT_PROGRAM_FROM_ADMIN_PANEL));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AboutProgramController sceneController = loader.getController();

        loadStage(root, "About program");
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

    public static void loadUserAccessWindow(User user) {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_USER_ACCESS_WINDOW));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AccessToUsbController sceneController = loader.getController();
        sceneController.transferMessage(user);

        loadStage(root, "Change access");
    }

    public static void loadLogsWindow() throws IOException {
        new LogsController().initialize();
    }

    public static void loadMagicSquare() {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_MAGIC_SQUARE));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MagicSquareController sceneController = loader.getController();

        loadStage(root, "Edit magic square");
    }

    public static void loadViewMagicSquare() {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_VIEW_MAGIC_SQUARE));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadStage(root, "View magic square");
    }

    public static void loadEditMagicSquare() {
        FXMLLoader loader = new FXMLLoader(LoadScenes.class.getResource(PATH_TO_EDIT_MAGIC_SQUARE));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadStage(root, "Edit magic square");
    }

    public static void loadFileChooser(User user) {
        new FileChooserController().initialize(user);
    }

    private static void loadStage(Parent root, String title) {
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(Launch.class.getResourceAsStream("sources/images/shield_logo_on_left_board.png")));
        stage.setTitle(title);
        stage.show();
    }
}
