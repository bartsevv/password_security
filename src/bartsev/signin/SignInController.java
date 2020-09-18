package bartsev.signin;

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
import java.util.ResourceBundle;

public class SignInController {


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
             if (validateData(login, password)) {
                 authSignInButton.getScene().getWindow().hide();

                 FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("/bartsev/adminpanel/AdminPanel.fxml"));
                 try {
                     loader.load();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

                 Parent root = loader.getRoot();
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root));
                 stage.showAndWait();
             }
        });
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
}
