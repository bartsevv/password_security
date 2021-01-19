package bartsev.adminpanel.listofusers.edituser;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.MagicSquare;
import bartsev.models.User;
import bartsev.helpers.UserActions;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EditUserController {
    private User user;

    @FXML
    private JFXButton passwordRestrictions;

    @FXML
    private JFXButton changeUserPasswordButton;

    @FXML
    private JFXButton backToUserListButton;

    @FXML
    private Label loginText;

    @FXML
    private Label passwordText;

    @FXML
    private Label expirationDateText;

    @FXML
    private JFXButton deactivateUserButton;

    @FXML
    private Label statusText;

    @FXML
    private JFXButton deleteUserButton;

    @FXML
    private JFXButton updateUserCardButton;

    @FXML
    private JFXButton accessToUsbButton;

    @FXML
    void initialize() {
        updateUserCardButton.setOnAction(event -> {
            user = UserActions.getUser(user.getLogin());
            initFields();
        });

        changeUserPasswordButton.setOnAction(event -> {
            LoadScenes.loadChangeUserPasswordByAdminWindow(user);
            changeUserPasswordButton.getScene().getWindow().hide();
        });

        deleteUserButton.setOnAction(event -> {
            UserActions.deleteUser(user.getLogin());
            deleteUserButton.getScene().getWindow().hide();
            LoadScenes.loadAdminWindow();
        });

        deactivateUserButton.setOnAction(event -> {
            if (user.getStatus().equals(UserActions.ACTIVATED_USER)) {
                UserActions.changeUserStatus(user.getLogin(), false);
                deactivateUserButton.setText("ACTIVATE USER");
                user.setStatus(UserActions.DEACTIVATED_USER);
            } else {
                UserActions.changeUserStatus(user.getLogin(), true);
                deactivateUserButton.setText("DEACTIVATE USER");
                user.setStatus(UserActions.ACTIVATED_USER);
            }
        });

        passwordRestrictions.setOnAction(event -> {
            LoadScenes.loadPasswordRestrictionsWindow(user);
            passwordRestrictions.getScene().getWindow().hide();
        });

        accessToUsbButton.setOnAction(event -> {
            LoadScenes.loadUserAccessWindow(user);
            accessToUsbButton.getScene().getWindow().hide();
        });

        backToUserListButton.setOnAction(event -> {
            backToUserListButton.getScene().getWindow().hide();
            LoadScenes.loadAdminWindow();
        });
    }

    public void transferMessage(User user) {
        this.user = user;
        initFields();
    }

    private void initFields() {
        loginText.setText(user.getLogin());
        passwordText.setText(MagicSquare.decryptMagicSquare(user.getPassword()));
        expirationDateText.setText(user.getDateOfCreation().toString());
        statusText.setText(user.getStatus());
        deactivateUserButton.setText(user.getStatus().equals("Activated") ? "DEACTIVATE USER" : "ACTIVATE USER");
    }
}
