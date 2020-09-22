package bartsev.adminpanel.addnewuser;

import bartsev.users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
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

            User user = new User(login, password, LocalDate.now(), User.ACTIVATED_USER);
            try {
                user.addNewUser(user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            addNewUserButton.getScene().getWindow().hide();
        });
    }
}
