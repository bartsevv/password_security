package bartsev.adminpanel.magicsquare;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.UserActions;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewMagicSquareController {
    @FXML
    private Label l1;

    @FXML
    private JFXButton backToEditMagicSquare;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l5;

    @FXML
    private Label l6;

    @FXML
    private Label l10;

    @FXML
    private Label l7;

    @FXML
    private Label l8;

    @FXML
    private Label l12;

    @FXML
    private Label l9;

    @FXML
    private Label l11;

    @FXML
    private Label l14;

    @FXML
    private Label l13;

    @FXML
    private Label l16;

    @FXML
    private Label l15;

    @FXML
    private Label l18;

    @FXML
    private Label l17;

    @FXML
    private Label l19;

    @FXML
    private Label l21;

    @FXML
    private Label l20;

    @FXML
    private Label l23;

    @FXML
    private Label l22;

    @FXML
    private Label l25;

    @FXML
    private Label l24;

    @FXML
    void initialize() {
        int[][] matrix = UserActions.getMagicSquareFromFile();
        l1.setText(new Integer(matrix[0][0]).toString());
        l2.setText(new Integer(matrix[0][1]).toString());
        l3.setText(new Integer(matrix[0][2]).toString());
        l4.setText(new Integer(matrix[0][3]).toString());
        l5.setText(new Integer(matrix[0][4]).toString());
        l6.setText(new Integer(matrix[1][0]).toString());
        l7.setText(new Integer(matrix[1][1]).toString());
        l8.setText(new Integer(matrix[1][2]).toString());
        l9.setText(new Integer(matrix[1][3]).toString());
        l10.setText(new Integer(matrix[1][4]).toString());
        l11.setText(new Integer(matrix[2][01]).toString());
        l12.setText(new Integer(matrix[2][1]).toString());
        l13.setText(new Integer(matrix[2][2]).toString());
        l14.setText(new Integer(matrix[2][3]).toString());
        l15.setText(new Integer(matrix[2][4]).toString());
        l16.setText(new Integer(matrix[3][0]).toString());
        l17.setText(new Integer(matrix[3][1]).toString());
        l18.setText(new Integer(matrix[3][2]).toString());
        l19.setText(new Integer(matrix[3][3]).toString());
        l20.setText(new Integer(matrix[3][4]).toString());
        l21.setText(new Integer(matrix[4][0]).toString());
        l22.setText(new Integer(matrix[4][1]).toString());
        l23.setText(new Integer(matrix[4][2]).toString());
        l24.setText(new Integer(matrix[4][3]).toString());
        l25.setText(new Integer(matrix[4][4]).toString());

        backToEditMagicSquare.setOnAction(event -> {
            backToEditMagicSquare.getScene().getWindow().hide();
            LoadScenes.loadMagicSquare();
        });
    }
}
