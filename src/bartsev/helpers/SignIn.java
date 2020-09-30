package bartsev.helpers;

import bartsev.models.User;
import bartsev.models.UserRestrictions;

import java.time.Duration;
import java.time.LocalDateTime;

public class SignIn {
    private String login;
    private String password;

    private final static String ADMIN_LOGIN = "ADMIN";

    private final static String INCORRECT_LOGIN_MESSAGE = "Пользователя с таким логином не существует!";
    private final static String INCORRECT_PASSWORD_MESSAGE = "Ваш пароль введен не верно!";
    private final static String EXPIRATION_DATE = "Срок действия пароля истек. Необходимо сменить пароль.";


    public SignIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Boolean isAdmin() {
        if (login.equals(ADMIN_LOGIN)) {
            User admin = UserActions.getUser(ADMIN_LOGIN);
            return password.equals(admin.getPassword());
        }
        return false;
    }

    public User findUser() {
        User user = UserActions.getUser(login);

        if (user == null) {
            Tools.showWarningAlert(INCORRECT_LOGIN_MESSAGE);
        }
        if (!(user.getPassword().equals(password))) {
            Tools.showWarningAlert(INCORRECT_PASSWORD_MESSAGE);
        } else {
            if (Duration.between(user.getDateOfCreation().atStartOfDay(), LocalDateTime.now()).toDays() > 25) {
                Tools.showWarningAlert(EXPIRATION_DATE);
                UserRestrictions userRestrictions = UserActions.getUserRestrictions(login);
                LoadScenes.loadChangeRottenPasswordWindow(user, userRestrictions);
            } else {
                return user;
            }
        }

        return null;
    }
}
