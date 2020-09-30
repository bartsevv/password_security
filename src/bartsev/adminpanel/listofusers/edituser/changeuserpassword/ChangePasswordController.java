package bartsev.adminpanel.listofusers.edituser.changeuserpassword;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.ValidateData;
import bartsev.models.User;
import bartsev.helpers.UserActions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangePasswordController {
    private User user;
    @FXML
    private TextField newPasswordField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {
        saveButton.setOnAction(event -> {
            String newPassword = newPasswordField.getText();
            if (!(newPassword.trim().isEmpty())) {
                if (new ValidateData(user.getLogin(), newPassword).validatePassword()) {
                    UserActions.changeUserPassword(user, newPassword);
                    saveButton.getScene().getWindow().hide();
                    LoadScenes.loadEditUserWindow(user);
                }
            }
        });
        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
            LoadScenes.loadEditUserWindow(user);
        });
    }

    public void transferMessage(User user) {
        this.user = user;
    }
}
