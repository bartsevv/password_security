package bartsev.adminpanel.addnewuser;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.MagicSquare;
import bartsev.helpers.UserActions;
import bartsev.helpers.ValidateData;
import bartsev.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AddNewUserController {

    @FXML
    private TextField userPassword;

    @FXML
    private TextField userLogin;

    @FXML
    private Button addNewUserButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {
        addNewUserButton.setOnAction(event -> {
            String login = userLogin.getText();
            String password = userPassword.getText();
            if (!(login.trim().isEmpty() || password.trim().isEmpty())) {
                User user = new User(login, MagicSquare.encryptMagicSquare(password), LocalDate.now(), UserActions.ACTIVATED_USER);
                ValidateData validateData = new ValidateData(user.getLogin(), MagicSquare.decryptMagicSquare(user.getPassword()));
                if (validateData.validateLoginAndPassword() && validateData.validateUser()) {
                    UserActions.addNewUsers(user);
                    addNewUserButton.getScene().getWindow().hide();
                    LoadScenes.loadAdminWindow();
                }
            }
        });

        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
            LoadScenes.loadAdminWindow();
        });
    }


}
