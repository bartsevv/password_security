package bartsev.signin.rottenpassword;

import bartsev.helpers.MagicSquare;
import bartsev.helpers.Tools;
import bartsev.helpers.ValidateData;
import bartsev.models.User;
import bartsev.helpers.UserActions;
import bartsev.models.UserRestrictions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RottenPasswordController {
    private final static String INCORRECT_OLD_PASSWORD = "Старый пароль введен не верно!";

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
            if (!(oldPassword.isEmpty() || (newPassword.isEmpty()))) {
                if (!(MagicSquare.decryptMagicSquare(user.getPassword()).equals(oldPassword))) {
                    Tools.showWarningAlert(INCORRECT_OLD_PASSWORD);
                } else {
                    if (new ValidateData(user.getLogin(), newPassword).validatePasswordWithRestrictions(userRestrictions)) {
                        if (userRestrictions.getThirdRestriction()) {
                            UserActions.changeUserPassword(user, newPassword);
                            okButton.getScene().getWindow().hide();
                        } else {
                            if (oldPassword.equals(newPassword)) {
                                Tools.showWarningAlert("Новый пароль не может совпадать со старым паролем.");
                            } else {
                                UserActions.changeUserPassword(user, newPassword);
                                okButton.getScene().getWindow().hide();
                            }
                        }
                    }
                }
            }
        });

        cancelButton.setOnAction(event -> {
            okButton.getScene().getWindow().hide();
        });
    }

    public void transferMessage(User user, UserRestrictions userRestrictions) {
        this.user = user;
        this.userRestrictions = userRestrictions;
    }
}
