package bartsev.adminpanel.userlist;

import bartsev.users.User;
import bartsev.userwindow.UserWindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class UserListController {

    public void initialize() throws IOException {
        ObservableList<User> userObservableList = FXCollections.observableList(User.getUserList());
        TableView<User> table = new TableView<User>(userObservableList);
        table.setPrefWidth(510);
        table.setPrefHeight(300);

        TableColumn<User, String> loginColumn = new TableColumn<User, String>("Login");
        loginColumn.setPrefWidth(170);
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        table.getColumns().add(loginColumn);

        TableColumn<User, String> passwordColumn = new TableColumn<User, String>("Password");
        passwordColumn.setPrefWidth(170);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        table.getColumns().add(passwordColumn);

        TableColumn<User, LocalDate> expirationColumn = new TableColumn<User, LocalDate>("Expiration");
        expirationColumn.setPrefWidth(170);
        expirationColumn.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("dateOfCreation"));
        table.getColumns().add(expirationColumn);

        table.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    System.out.println(rowData);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/bartsev/adminpanel/userlist/EditUser.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    EditUserController sceneController = loader.getController();
                    sceneController.transferMessage(rowData);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit user");
                    stage.show();

                }
            });
            return row;
        });

        FlowPane root = new FlowPane(10, 10, table);
        Scene scene = new Scene(root, 510, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("List of users");
        stage.show();
    }
}
