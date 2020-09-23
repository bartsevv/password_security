package bartsev.signin;

import bartsev.Tools;
import bartsev.ValidateData;
import bartsev.users.User;
import bartsev.users.UserActions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RottenPasswordController {
    private final static String INCORRECT_OLD_PASSWORD = "Старый пароль введен не верно!";

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

            if (!(user.getPassword().equals(oldPassword))) {
                Tools.showWarningAlert(INCORRECT_OLD_PASSWORD);
            } else {
                if (new ValidateData(user.getLogin(), newPassword).validatePassword()) {
                    UserActions.changeUserPassword(user, newPassword);
                }
                okButton.getScene().getWindow().hide();
            }
        });

        cancelButton.setOnAction(event -> {
            okButton.getScene().getWindow().hide();
        });
    }

    public void transferMessage(User user) {
        this.user = user;
    }
}
