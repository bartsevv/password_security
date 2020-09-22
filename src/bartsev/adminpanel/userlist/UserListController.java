package bartsev.adminpanel.userlist;

import bartsev.LoadScenes;
import bartsev.users.User;
import bartsev.users.UserActions;
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
        ObservableList<User> userObservableList = FXCollections.observableList(UserActions.getUserList());
        TableView<User> table = new TableView<User>(userObservableList);
        table.setPrefWidth(600);
        table.setPrefHeight(300);

        TableColumn<User, String> loginColumn = new TableColumn<User, String>("Login");
        loginColumn.setPrefWidth(150);
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        table.getColumns().add(loginColumn);

        TableColumn<User, String> passwordColumn = new TableColumn<User, String>("Password");
        passwordColumn.setPrefWidth(150);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        table.getColumns().add(passwordColumn);

        TableColumn<User, LocalDate> expirationColumn = new TableColumn<User, LocalDate>("Expiration");
        expirationColumn.setPrefWidth(150);
        expirationColumn.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("dateOfCreation"));
        table.getColumns().add(expirationColumn);

        TableColumn<User, String> statusColumn = new TableColumn<User, String>("Status");
        statusColumn.setPrefWidth(150);
        statusColumn.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
        table.getColumns().add(statusColumn);

        table.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    LoadScenes.loadEditUserWindow(row.getItem());
                }
            });
            return row;
        });

        FlowPane root = new FlowPane(10, 10, table);
        Scene scene = new Scene(root, 600, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("List of users");
        stage.show();
    }
}
