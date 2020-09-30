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
    private final static String DEACTIVATED_ACCOUNT = "Ваш аккаунт деактивирован.";


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
        UserRestrictions userRestrictions = UserActions.getUserRestrictions(login);

        if (user == null) {
            Tools.showWarningAlert(INCORRECT_LOGIN_MESSAGE);
        }
        if (!(user.getPassword().equals(password))) {
            Tools.showWarningAlert(INCORRECT_PASSWORD_MESSAGE);
        } else {
            if ((Duration.between(user.getDateOfCreation().atStartOfDay(), LocalDateTime.now()).toDays() > 25) && (userRestrictions.getSecondRestriction())) {
                Tools.showWarningAlert(EXPIRATION_DATE);
                LoadScenes.loadChangeRottenPasswordWindow(user, userRestrictions);
            } else {
                if (user.getStatus().equals(UserActions.DEACTIVATED_USER)) {
                    Tools.showWarningAlert(DEACTIVATED_ACCOUNT);
                } else {
                    return user;
                }
            }
        }

        return null;
    }
}
