package bartsev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Launch extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        int[][] a = {
//                {17, 24, 1, 8, 15},
//                {23, 5, 7, 14, 16},
//                {4, 6, 13, 20, 22},
//                {10, 12, 19, 21, 3},
//                {11, 18, 25, 2, 9},
//        };
        //UserActions.addNewMagicSquare(a);
        Parent root = FXMLLoader.load(getClass().getResource("signin/SignIn.fxml"));
        primaryStage.setTitle("Bulwark");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getIcons().add(new Image(Launch.class.getResourceAsStream("sources/images/shield_logo_on_left_board.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
