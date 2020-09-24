package bartsev.userwindow;

import bartsev.LoadScenes;
import bartsev.users.User;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UserWindowController implements Initializable {
    private User user;

    @FXML
    private JFXButton aboutProgramButton;

    @FXML
    private JFXButton changePasswordButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private Text loginText;

    @FXML
    private Text passwordExpirationDateText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changePasswordButton.setOnAction(event -> {
            LoadScenes.loadChangePasswordByUserWindow(user);
        });
    }

    public void transferMessage(User user) {
        this.user = user;
        loginText.setText(("HELLO, " + user.getLogin()).toUpperCase());
        passwordExpirationDateText.setText("PASSWORD EXPIRATION DATE - " + user.getDateOfCreation());
    }
}
