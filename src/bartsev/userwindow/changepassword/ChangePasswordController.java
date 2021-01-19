package bartsev.userwindow.changepassword;

import bartsev.helpers.*;
import bartsev.models.User;
import bartsev.models.UserRestrictions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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
                        if (oldPassword.equals(newPassword) && userRestrictions.getThirdRestriction()) {
                            Tools.showWarningAlert("Ваш новый пароль не должен совпадать со старым.");
                        } else {
                            UserActions.changeUserPassword(user, newPassword);
                            user.setPassword(MagicSquare.encryptMagicSquare(newPassword));
                            user.setDateOfCreation(LocalDate.now());
                            okButton.getScene().getWindow().hide();
                            LoadScenes.loadUserWindow(user);
                        }
                    }
                }
            }
        });

        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
            LoadScenes.loadUserWindow(user);
        });
    }

    public void transferMessage(User user, UserRestrictions userRestrictions) {
        this.user = user;
        this.userRestrictions = userRestrictions;
    }

    private Boolean compareOldPassword(String oldPassword) {
        if (!(oldPassword.equals(MagicSquare.decryptMagicSquare(user.getPassword())))) {
            Tools.showWarningAlert("Вы ввели не верно старый пароль.");
            return false;
        }
        return true;
    }
}
