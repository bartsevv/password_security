package bartsev.helpers;

import javafx.scene.control.Alert;

public class Tools {
    public static void showWarningAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");

        alert.setHeaderText(null);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public static void showSuccessAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех");

        alert.setHeaderText(null);
        alert.setContentText(contentText);

        alert.showAndWait();
    }
}
