package bartsev.userwindow;

import bartsev.helpers.LoadScenes;
import bartsev.helpers.Tools;
import bartsev.helpers.UsbHelper;
import bartsev.models.User;
import bartsev.helpers.UserActions;
import bartsev.models.UserAccess;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import net.samuelcampos.usbdrivedetector.USBStorageDevice;

import javax.tools.Tool;
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
    private Text loginText;

    @FXML
    private Text passwordExpirationDateText;

    @FXML
    private JFXButton checkUsbButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            for(String usbName: removableUsbNames) {
                if (userAccess.getFirstDevice().equals(usbName) || userAccess.getSecondDevice().equals(usbName)) {
                    haveAccess = true;
                    accessToRemovableUsbNames = accessToRemovableUsbNames + usbName + "  ";
                }
            }
            if (haveAccess) {
                Tools.showSuccessAlert(accessToRemovableUsbNames);
            } else {
                Tools.showWarningAlert("У вас нет доступа к USB, которые используются на этом компьютере");
            }
            LoadScenes.loadUserWindow(user);
        });
    }

    public void transferMessage(User user) {
        this.user = user;
        loginText.setText(("HELLO, " + user.getLogin()).toUpperCase());
        passwordExpirationDateText.setText("PASSWORD EXPIRATION DATE - " + user.getDateOfCreation());
        //UserActions.synhronizeUsersWithAccessToUsb();
    }
}
