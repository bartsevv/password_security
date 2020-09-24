package bartsev.userwindow;

import bartsev.Tools;
import bartsev.ValidateData;
import bartsev.users.User;
import bartsev.users.UserActions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangePasswordController {
    private User user;

    @FXML
    private TextField newPasswordField;

    @FXML
    private TextField oldPasswordField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {
        okButton.setOnAction(event -> {
            String oldPassword = oldPasswordField.getText();
            String newPassword = newPasswordField.getText();
            if (compareOldPassword(oldPassword)) {
                if (new ValidateData(user.getLogin(), newPassword).validatePassword()) {
                    UserActions.changeUserPassword(user, newPassword);
                    okButton.getScene().getWindow().hide();
                }
            }
        });

        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
        });
    }

    public void transferMessage(User user) {
        this.user = user;
    }

    private Boolean compareOldPassword(String oldPassword) {
        if (!(oldPassword.equals(user.getPassword()))) {
            Tools.showWarningAlert("Вы ввели не верно старый пароль.");
            return false;
        }
        return true;
    }
}
