package bartsev.helpers;
import bartsev.models.User;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileChooserController {

    public void initialize(User user) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("JavaFX App");

        FileChooser fileChooser = new FileChooser();

        Button button = new Button("Select File");
        button.setOnAction(e -> {
            if (!user.getLogin().equals("ADMIN")) {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                try {
                    new File(user.getLogin() + ".png").delete();
                    Files.move(selectedFile.toPath(), selectedFile.toPath().resolveSibling(user.getLogin() + ".png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("lol");
            }
        });

        VBox vBox = new VBox(button);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}