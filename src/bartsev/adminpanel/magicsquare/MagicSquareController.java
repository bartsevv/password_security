package bartsev.adminpanel.magicsquare;

import bartsev.helpers.LoadScenes;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class MagicSquareController {

    @FXML
    private JFXButton backToAdminPanel;

    @FXML
    private JFXButton addNewMagicSquare;

    @FXML
    private JFXButton viewMagicSquare;

    @FXML
    void initialize() {
        addNewMagicSquare.setOnAction(event -> {

        });

        viewMagicSquare.setOnAction(event -> {

        });

        backToAdminPanel.setOnAction(event -> {
            backToAdminPanel.getScene().getWindow().hide();
            LoadScenes.loadAdminWindow();
        });
    }
}
