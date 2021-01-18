package bartsev.adminpanel.magicsquare;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.MagicSquare;
import bartsev.helpers.Tools;
import bartsev.helpers.UserActions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class AddNewMagicSquareController {

    @FXML
    private JFXTextField f10;

    @FXML
    private JFXButton backToEditMagicSquare;

    @FXML
    private JFXTextField f12;

    @FXML
    private JFXTextField f11;

    @FXML
    private JFXTextField f14;

    @FXML
    private JFXTextField f13;

    @FXML
    private JFXTextField f16;

    @FXML
    private JFXTextField f1;

    @FXML
    private JFXTextField f15;

    @FXML
    private JFXTextField f2;

    @FXML
    private JFXTextField f18;

    @FXML
    private JFXTextField f3;

    @FXML
    private JFXTextField f17;

    @FXML
    private JFXTextField f4;

    @FXML
    private JFXTextField f5;

    @FXML
    private JFXTextField f19;

    @FXML
    private JFXTextField f6;

    @FXML
    private JFXTextField f7;

    @FXML
    private JFXTextField f8;

    @FXML
    private JFXTextField f9;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXTextField f21;

    @FXML
    private JFXTextField f20;

    @FXML
    private JFXTextField f23;

    @FXML
    private JFXTextField f22;

    @FXML
    private JFXTextField f25;

    @FXML
    private JFXTextField f24;

    @FXML
    void initialize() {
        saveButton.setOnAction(event -> {
            List<Integer> valuesForMagicSquare = new ArrayList<>();
            valuesForMagicSquare.add(Integer.parseInt(f1.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f2.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f3.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f4.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f5.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f6.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f7.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f8.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f9.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f10.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f11.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f12.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f13.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f14.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f15.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f16.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f17.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f18.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f19.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f20.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f21.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f22.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f23.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f24.getText()));
            valuesForMagicSquare.add(Integer.parseInt(f25.getText()));
            if (!(MagicSquare.isMagicSquare(valuesForMagicSquare))) {
                Tools.showWarningAlert("Введенные вами значения не формируют магический квадрат.");
            } else {
                UserActions.addNewMagicSquare(MagicSquare.intListTo2DArray(valuesForMagicSquare));
            }
        });

        backToEditMagicSquare.setOnAction(event -> {
            backToEditMagicSquare.getScene().getWindow().hide();
            LoadScenes.loadMagicSquare();
        });
    }
}
