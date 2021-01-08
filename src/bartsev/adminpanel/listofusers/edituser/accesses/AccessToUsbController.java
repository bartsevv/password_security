package bartsev.adminpanel.listofusers.edituser.accesses;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.UserActions;
import bartsev.models.User;
import bartsev.models.UserAccess;
import bartsev.models.UserRestrictions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class AccessToUsbController {
    private final static String FIRST_DEVICE = "ANG";
    private final static String SECOND_DEVICE = "Bartseva";
    private User user;

    @FXML
    private Button saveButton;

    @FXML
    private CheckBox firstDevice;

    @FXML
    private CheckBox secondDevice;

    @FXML
    private Button backToUserCardButton;

    @FXML
    void initialize() {
        saveButton.setOnAction(event -> {
            UserAccess userAccess = new UserAccess(user.getLogin(), firstDevice.isSelected() ? FIRST_DEVICE : "n/a", secondDevice.isSelected() ? SECOND_DEVICE : "n/a");
            UserActions.changeUserAccess(userAccess);
        });

        backToUserCardButton.setOnAction(event -> {
            backToUserCardButton.getScene().getWindow().hide();
            LoadScenes.loadEditUserWindow(user);
        });
    }

    public void transferMessage(User user) {
        this.user = user;
        //UserActions.synhronizeUsersWithAccessToUsb();
        UserAccess userAccess = UserActions.getUserAccess(user.getLogin());
        firstDevice.setSelected(userAccess.getFirstDevice().equals(FIRST_DEVICE));
        secondDevice.setSelected(userAccess.getSecondDevice().equals(SECOND_DEVICE));
    }
}
