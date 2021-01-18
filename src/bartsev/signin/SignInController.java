package bartsev.signin;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.SignIn;
import bartsev.models.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SignInController {
    public User user;
    private int count = 2;

    @FXML
    private JFXButton authSignInButton;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField loginField;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            if (count == 0) {
                Stage stage = (Stage) authSignInButton.getScene().getWindow();
                stage.close();
            } else {
                count--;
                String login = loginField.getText().trim();
                String password = passwordField.getText().trim();
                if (!(login.isEmpty() || (password.isEmpty()))) {
                    SignIn signIn = new SignIn(login, password);
                    if (signIn.isAdmin()) {
                        authSignInButton.getScene().getWindow().hide();
                        LoadScenes.loadAdminWindow();
                    } else {
                        user = null;
                        user = signIn.findUser();
                        if (user != null) {
                            authSignInButton.getScene().getWindow().hide();
                            LoadScenes.loadUserWindow(user);
                        }
                    }
                }
            }
        });
    }
}
