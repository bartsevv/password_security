package bartsev.adminpanel.aboutprogram;

import bartsev.helpers.LoadScenes;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class AboutProgramController {

    @FXML
    private JFXButton backButtonToAdminPanel;

    @FXML
    void initialize() {
        backButtonToAdminPanel.setOnAction(event -> {
            backButtonToAdminPanel.getScene().getWindow().hide();
            LoadScenes.loadAdminWindow();
        });
    }
}
