package bartsev.signin;

public class SignIn {
    private String login;
    private String password;

    private final static Integer minLoginLength = 4;
    private final static Integer minPasswordLength = 6;

    private Boolean requiredLoginLength = false;

    private Boolean requiredPasswordLength = false;
    private Boolean requiredPasswordUpperCase = false;
    private Boolean requiredPasswordLowerCase = false;

    public SignIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void validateLogin() {
        if (login.length() < minLoginLength) {
            requiredLoginLength = false;
        } else {
            requiredLoginLength = true;
        }
    }

    public void validatePassword() {
        if (password.length() < minPasswordLength) {
            requiredPasswordLength = false;
        } else {
            requiredPasswordLength = true;
        }

        requiredPasswordUpperCase = !(password.equals(password.toLowerCase()));
        requiredPasswordLowerCase = !(password.equals(password.toUpperCase()));
    }


    public Boolean getRequiredLoginLength() {
        return requiredLoginLength;
    }

    public Boolean getRequiredPasswordLength() {
        return requiredPasswordLength;
    }

    public Boolean getRequiredPasswordUpperCase() {
        return requiredPasswordUpperCase;
    }

    public Boolean getRequiredPasswordLowerCase() {
        return requiredPasswordLowerCase;
    }
}
