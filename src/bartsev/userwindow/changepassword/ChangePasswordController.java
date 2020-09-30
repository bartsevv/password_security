package bartsev.userwindow.changepassword;

import bartsev.helpers.Tools;
import bartsev.helpers.ValidateData;
import bartsev.models.User;
import bartsev.helpers.UserActions;
import bartsev.models.UserRestrictions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangePasswordController {
    private User user;
    private UserRestrictions userRestrictions;

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
            if (!(oldPassword.trim().isEmpty() || newPassword.trim().isEmpty())) {
                if (compareOldPassword(oldPassword)) {
                    if (new ValidateData(user.getLogin(), newPassword).validatePasswordWithRestrictions(userRestrictions)) {
                        UserActions.changeUserPassword(user, newPassword);
                        okButton.getScene().getWindow().hide();
                    }
                }
            }
        });

        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
        });
    }

    public void transferMessage(User user, UserRestrictions userRestrictions) {
        this.user = user;
        this.userRestrictions = userRestrictions;
    }

    private Boolean compareOldPassword(String oldPassword) {
        if (!(oldPassword.equals(user.getPassword()))) {
            Tools.showWarningAlert("Вы ввели не верно старый пароль.");
            return false;
        }
        return true;
    }
}
