package bartsev.userwindow.aboutprogram;

import bartsev.helpers.LoadScenes;
import bartsev.models.User;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class AboutProgramController {

    private User user;

    @FXML
    private JFXButton backButtonToUserPanel;

    @FXML
    void initialize() {
        backButtonToUserPanel.setOnAction(event -> {
            backButtonToUserPanel.getScene().getWindow().hide();
            LoadScenes.loadUserWindow(user);
        });
    }

    public void transferMessage(User user) {
        this.user = user;
    }
}
