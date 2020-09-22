package bartsev.signin;

import bartsev.adminpanel.AdminPanelController;
import bartsev.users.User;
import bartsev.userwindow.UserWindowController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignInController {
    private final static String ADMIN_LOGIN = "ADMIN";
    private final static String ADMIN_PASSWORD = "ADMIN";

    public User user;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

            user = new User(login, password, LocalDate.now(), User.ACTIVATED_USER);
            if (isAdmin()) {
                try {
                    loadAdminScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    loadUserScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//             if (validateData(login, password)) {
//                 authSignInButton.getScene().getWindow().hide();
//                 FXMLLoader loader = new FXMLLoader();
//                 loader.setLocation(getClass().getResource("/bartsev/userwindow/UserWindow.fxml"));
//                 try {
//                     loader.load();
//
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//                 Parent root = loader.getRoot();
//
//                 Stage stage = new Stage();
//                 stage.setScene(new Scene(root));
//                 stage.showAndWait();
//             }
        });
    }

    private void loadUserScene() throws IOException {
        authSignInButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bartsev/userwindow/UserWindow.fxml"));
        Parent root = loader.load();

        UserWindowController sceneController = loader.getController();
        sceneController.transferMessage(user);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("User panel");
        stage.show();
    }

    private void loadAdminScene() throws IOException {
        authSignInButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bartsev/adminpanel/AdminPanel.fxml"));
        Parent root = loader.load();

        AdminPanelController sceneController = loader.getController();
        sceneController.transferMessage(user);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin panel");
        stage.show();
    }

    private Boolean verifyData(String login, String password) {
        Boolean verify = true;

        return verify;
    }

    private Boolean validateData(String login, String password) {
        Boolean validate = true;
        String loginErorr = "";
        String passwordErorr = "";

        SignIn signIn = new SignIn(login, password);
        if (!signIn.getRequiredLoginLength()) {
            loginErorr = loginErorr + "логин должен содержать минимум 4 символа";
        }

        if (!signIn.getRequiredPasswordLength()) {
            passwordErorr = passwordErorr + "пароль должен содержать минимум 6 символов";
        }

        if (!signIn.getRequiredPasswordLowerCase()) {
            if (passwordErorr.isEmpty()) {
                passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
            } else {
                passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
            }
        }

        if (!signIn.getRequiredPasswordUpperCase()) {
            if (!passwordErorr.contains("пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру")) {
                if (passwordErorr.isEmpty()) {
                    passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                } else {
                    passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                }
            }
        }

        if (!signIn.getRequiredPasswordNumbers()) {
            if (!passwordErorr.contains("пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру")) {
                if (passwordErorr.isEmpty()) {
                    passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                } else {
                    passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                }
            }
        }

        if (!loginErorr.isEmpty()) {
            showAlertWithError("логин", loginErorr);
            validate = false;
        }
        if (!passwordErorr.isEmpty()) {
            showAlertWithError("пароль", passwordErorr);
            validate = false;
        }
        return validate;
    }

    private void showAlertWithError(String type, String warningText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");

        alert.setHeaderText(null);
        alert.setContentText("Ваш " + type + " не соответствует требованиям: " + warningText);

        alert.showAndWait();
    }

    private Boolean isAdmin() {
        return ((user.getLogin().equals(ADMIN_LOGIN)) && (user.getPassword().equals(ADMIN_PASSWORD)));
    }
}
