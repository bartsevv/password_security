package bartsev.userwindow;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.Tools;
import bartsev.helpers.UsbHelper;
import bartsev.helpers.UserActions;
import bartsev.models.User;
import bartsev.models.UserAccess;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserWindowController implements Initializable {
    private User user;

    @FXML
    private JFXButton aboutProgramButton;

    @FXML
    private JFXButton changePasswordButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton checkUsbButton;

    @FXML
    private JFXButton selectImageButton;

    @FXML
    private ImageView imageUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectImageButton.setOnAction(event -> {
            LoadScenes.loadFileChooser(user);
        });

        changePasswordButton.setOnAction(event -> {
            changePasswordButton.getScene().getWindow().hide();
            LoadScenes.loadChangePasswordByUserWindow(user, UserActions.getUserRestrictions(user.getLogin()));
        });

        exitButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

        aboutProgramButton.setOnAction(event -> {
            aboutProgramButton.getScene().getWindow().hide();
            LoadScenes.loadAboutProgramFromUserPanel(user);
        });

        checkUsbButton.setOnAction(event -> {
            aboutProgramButton.getScene().getWindow().hide();
            UserAccess userAccess = UserActions.getUserAccess(user.getLogin());
            List<String> removableUsbNames = UsbHelper.getListOfUsb();
            String accessToRemovableUsbNames = "У вас есть доступ к устройствам: ";
            Boolean haveAccess = false;
            for (String usbName : removableUsbNames) {
                if (userAccess.getFirstDevice().equals(usbName) || userAccess.getSecondDevice().equals(usbName)) {
                    haveAccess = true;
                    accessToRemovableUsbNames = accessToRemovableUsbNames + usbName + "  ";
                }
            }
            if (haveAccess) {
                Tools.showSuccessAlert(accessToRemovableUsbNames);
            } else {
                UserActions.addNewLogAboutAccess(userAccess);
                Tools.showWarningAlert("У вас нет доступа к USB, которые используются на этом компьютере");
            }
            LoadScenes.loadUserWindow(user);
        });
    }

    public void transferMessage(User user) {
        this.user = user;
        File f = new File(user.getLogin() + ".png");
        if (f.exists()) {
            imageUser.setImage(new Image(f.toURI().toString()));
        }
    }
}
