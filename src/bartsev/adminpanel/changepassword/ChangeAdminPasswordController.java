package bartsev.adminpanel.changepassword;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.UserActions;
import bartsev.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangeAdminPasswordController {
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
                UserActions.changeUserPassword(user, newPassword);
                saveButton.getScene().getWindow().hide();
                LoadScenes.loadAdminWindow();
            }
        });
        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
            LoadScenes.loadAdminWindow();
        });
    }

    public void transferMessage(User user) {
        this.user = user;
    }
}
