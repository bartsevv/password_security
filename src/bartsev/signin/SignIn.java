package bartsev.signin;

import bartsev.LoadScenes;
import bartsev.Tools;
import bartsev.users.User;
import bartsev.users.UserActions;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class SignIn {
    private String login;
    private String password;

    private final static String ADMIN_LOGIN = "ADMIN";
    private final static String ADMIN_PASSWORD = "ADMIN";

    private final static String INCORRECT_LOGIN_MESSAGE = "Пользователя с таким логином не существует!";
    private final static String INCORRECT_PASSWORD_MESSAGE = "Ваш пароль введен не верно!";
    private final static String EXPIRATION_DATE = "Срок действия пароля истек. Необходимо сменить пароль.";


    public SignIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Boolean isAdmin() {
        return ((login.equals(ADMIN_LOGIN)) && (password.equals(ADMIN_PASSWORD)));
    }

    public User findUser() {
        User user = null;
        try {
            user = UserActions.getUser(login);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (user == null) {
            Tools.showWarningAlert(INCORRECT_LOGIN_MESSAGE);
        }
        if (!(user.getPassword().equals(password))) {
            Tools.showWarningAlert(INCORRECT_PASSWORD_MESSAGE);
        } else {
            if (Duration.between(user.getDateOfCreation().atStartOfDay(), LocalDateTime.now()).toDays() > 25) {
                Tools.showWarningAlert(EXPIRATION_DATE);
                LoadScenes.loadChangeRottenPasswordWindow(user);
            } else {
                return user;
            }
        }

        return null;
    }
}
