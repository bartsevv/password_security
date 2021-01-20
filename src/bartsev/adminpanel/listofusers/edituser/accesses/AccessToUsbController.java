package bartsev.adminpanel.listofusers.edituser.accesses;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.UserActions;
import bartsev.models.User;
import bartsev.models.UserAccess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;

public class AccessToUsbController {
    private final static String FIRST_DEVICE = "ANG";
    private final static String SECOND_DEVICE = "Bartseva";
    private User user;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXCheckBox firstDevice;

    @FXML
    private JFXCheckBox secondDevice;

    @FXML
    private JFXButton backToUserCardButton;

    @FXML
    void initialize() {
        saveButton.setOnAction(event -> {
            UserAccess userAccess = new UserAccess
                    (user.getLogin(), firstDevice.isSelected() ? FIRST_DEVICE : "n/a", secondDevice.isSelected() ? SECOND_DEVICE : "n/a");
            UserActions.changeUserAccess(userAccess);
        });

        backToUserCardButton.setOnAction(event -> {
            backToUserCardButton.getScene().getWindow().hide();
            LoadScenes.loadEditUserWindow(user);
        });
    }

    public void transferMessage(User user) {
        this.user = user;
        UserAccess userAccess = UserActions.getUserAccess(user.getLogin());
        firstDevice.setSelected(userAccess.getFirstDevice().equals(FIRST_DEVICE));
        secondDevice.setSelected(userAccess.getSecondDevice().equals(SECOND_DEVICE));
    }
}
