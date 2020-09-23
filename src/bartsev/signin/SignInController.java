package bartsev.signin;

import bartsev.LoadScenes;
import bartsev.users.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class SignInController {
    public User user;

    @FXML
    private JFXButton authSignInButton;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField loginField;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();
            if (!(login.isEmpty() || (password.isEmpty()))) {
                SignIn signIn = new SignIn(login, password);

                if (signIn.isAdmin()) {
                    authSignInButton.getScene().getWindow().hide();
                    LoadScenes.loadAdminWindow(user);
                } else {
                    user = null;
                    user = signIn.findUser();
                    if (user != null) {
                        authSignInButton.getScene().getWindow().hide();
                        LoadScenes.loadUserWindow(user);
                    }
                }
            }
        });
    }
}
