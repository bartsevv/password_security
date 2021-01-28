package bartsev;

import bartsev.helpers.UsbHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        for(String str: UsbHelper.getListOfUsb()) {
            System.out.println(str);
        }
        Parent root = FXMLLoader.load(getClass().getResource("signin/SignIn.fxml"));
        primaryStage.setTitle("Bulwark");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("sources/images/shield_logo_on_left_board.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
