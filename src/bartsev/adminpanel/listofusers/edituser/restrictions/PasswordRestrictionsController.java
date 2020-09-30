package bartsev.adminpanel.listofusers.edituser.restrictions;

import bartsev.helpers.LoadScenes;
import bartsev.models.User;
import bartsev.helpers.UserActions;
import bartsev.models.UserRestrictions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;

public class PasswordRestrictionsController {
    private User user;

    @FXML
    private JFXButton backToUserCardButton;

    @FXML
    private JFXCheckBox firstRestriction;

    @FXML
    private JFXCheckBox secondRestriction;

    @FXML
    private JFXCheckBox thirdRestriction;

    @FXML
    private JFXButton saveButton;

    @FXML
    void initialize() {
        saveButton.setOnAction(event -> {
            UserRestrictions userRestrictions = new UserRestrictions(user.getLogin(), firstRestriction.isSelected(), secondRestriction.isSelected(), thirdRestriction.isSelected());
            UserActions.changeUserRestrictions(userRestrictions);
        });

        backToUserCardButton.setOnAction(event -> {
            backToUserCardButton.getScene().getWindow().hide();
            LoadScenes.loadEditUserWindow(user);
        });
    }

    public void transferMessage(User user) {
        this.user = user;
        UserActions.synhronizeTwoFiles();
        UserRestrictions userRestrictions = UserActions.getUserRestrictions(user.getLogin());
        firstRestriction.setSelected(userRestrictions.getFirstRestriction());
        secondRestriction.setSelected(userRestrictions.getSecondRestriction());
        thirdRestriction.setSelected(userRestrictions.getThirdRestriction());
    }
}
