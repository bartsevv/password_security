package bartsev.adminpanel.listofusers.edituser.accesses;

import bartsev.helpers.UserActions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LogsController {

    public void initialize() throws IOException {
        List<String> logList = UserActions.getLogList();

        ObservableList<String> userObservableList = FXCollections.observableArrayList(logList);
        TableView<String> tableView = new TableView<>();
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(300);
        TableColumn<String, String> nameColumn = new TableColumn<>("Attempts to gain access");
        nameColumn.setPrefWidth(600);
        tableView.getColumns().addAll(nameColumn);

        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableView.setItems(userObservableList);

        FlowPane root = new FlowPane(10, 10, tableView);
        Scene scene = new Scene(root, 600, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("List of users");
        stage.show();
    }
}
