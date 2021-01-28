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
        /*int[][] a = {
                {17, 24, 1, 8, 15},
                {23, 5, 7, 14, 16},
                {4, 6, 13, 20, 22},
                {10, 12, 19, 21, 3},
                {11, 18, 25, 2, 9},
        };
        int[][] a = {
                {1, 15, 24, 8, 17},
                {9, 18, 2, 11, 25},
                {12, 21, 10, 19, 3},
                {20, 4, 13, 22, 6},
                {23, 7, 16, 5, 14},
        };
        UserActions.addNewMagicSquare(a);*/
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
