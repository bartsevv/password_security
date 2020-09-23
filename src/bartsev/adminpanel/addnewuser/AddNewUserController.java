package bartsev.adminpanel.addnewuser;

import bartsev.users.User;
import bartsev.users.UserActions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
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

            User user = new User(login, password, LocalDate.now(), UserActions.ACTIVATED_USER);
            try {
                UserActions.addNewUser(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addNewUserButton.getScene().getWindow().hide();
        });
    }


}
