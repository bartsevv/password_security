package bartsev.userwindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangePasswordController {

    @FXML
    private TextField newPasswordField;

    @FXML
    private TextField oldPasswordField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {
        okButton.setOnAction(event -> {

        });
    }
}
