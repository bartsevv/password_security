package bartsev.adminpanel.userlist;

import bartsev.users.User;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EditUserController {
    private User user;

    @FXML
    private JFXButton deactivateUserButton;

    @FXML
    private JFXButton changeUserPasswordButton;

    @FXML
    private JFXButton backToUserListButton;

    @FXML
    private Label loginText;

    @FXML
    private Label passwordText;

    @FXML
    private Label expirationDateText;

    @FXML
    private JFXButton deleteUserButton;

    @FXML
    private Label statusText;

    @FXML
    void initialize() {
        changeUserPasswordButton.setOnAction(event -> {

        });

        deleteUserButton.setOnAction(event -> {

        });

        deactivateUserButton.setOnAction(event -> {

        });

        backToUserListButton.setOnAction(event -> {

        });
    }

    public void transferMessage(User user) {
        this.user = user;
        loginText.setText(user.getLogin());
        passwordText.setText(user.getPassword());
        expirationDateText.setText(user.getDateOfCreation().toString());
        statusText.setText(user.getStatus());
    }
}
