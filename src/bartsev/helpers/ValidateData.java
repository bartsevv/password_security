package bartsev.helpers;

import bartsev.models.User;
import bartsev.models.UserRestrictions;
import javafx.scene.control.Alert;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidateData {
    private final static Integer minLoginLength = 4;
    private final static Integer minPasswordLength = 6;

    private Boolean requiredLoginLength = false;

    private Boolean requiredPasswordLength = false;
    private Boolean requiredPasswordUpperCase = false;
    private Boolean requiredPasswordLowerCase = false;
    private Boolean requiredPasswordNumbers = false;

    private final static String ADMIN_LOGIN = "ADMIN";
    private final static String ADMIN_PASSWORD = "ADMIN";

    private String login;
    private String password;

    public ValidateData(String login, String password) {
        this.login = login;
        this.password = password;
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

    public Boolean getRequiredPasswordNumbers() {
        return requiredPasswordNumbers;
    }

    public Boolean validateUser() {
        if (UserActions.getUser(login) == null) {
            return true;
        } else {
            Tools.showWarningAlert("Пользователь с таким логином уже существует.");
            return false;
        }
    }

    public Boolean validateLoginAndPassword() {
        return ((validateLogin()) && (validatePassword()));
    }

    private Boolean validateLogin() {
        if (login.length() < minLoginLength) {
            requiredLoginLength = false;
        } else {
            requiredLoginLength = true;
        }

        if (!getRequiredLoginLength()) {
            showAlertWithError("логин", "логин должен содержать минимум 4 символа");
            return false;
        }
        return true;
    }

    public Boolean validatePassword() {
        if (password.length() < minPasswordLength) {
            requiredPasswordLength = false;
        } else {
            requiredPasswordLength = true;
        }

        requiredPasswordUpperCase = !(password.equals(password.toLowerCase()));
        requiredPasswordLowerCase = !(password.equals(password.toUpperCase()));

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                requiredPasswordNumbers = true;
                break;
            }
        }

        Boolean validate = true;
        String passwordErorr = "";

        if (!getRequiredPasswordLength()) {
            passwordErorr = passwordErorr + "пароль должен содержать минимум 6 символов";
        }

        if (!getRequiredPasswordLowerCase()) {
            if (passwordErorr.isEmpty()) {
                passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
            } else {
                passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
            }
        }

        if (!getRequiredPasswordUpperCase()) {
            if (!passwordErorr.contains("пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру")) {
                if (passwordErorr.isEmpty()) {
                    passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                } else {
                    passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                }
            }
        }

        if (!getRequiredPasswordNumbers()) {
            if (!passwordErorr.contains("пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру")) {
                if (passwordErorr.isEmpty()) {
                    passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                } else {
                    passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                }
            }
        }
        if (!passwordErorr.isEmpty()) {
            showAlertWithError("пароль", passwordErorr);
            validate = false;
        }
        return validate;
    }

    public Boolean validatePasswordWithRestrictions(UserRestrictions userRestrictions) {
        if (password.length() < minPasswordLength) {
            requiredPasswordLength = false;
        } else {
            requiredPasswordLength = true;
        }

        Boolean validate = true;
        String passwordErorr = "";

        if (!getRequiredPasswordLength()) {
            passwordErorr = passwordErorr + "пароль должен содержать минимум 6 символов";
        }

        if (userRestrictions.getFirstRestriction()) {
            requiredPasswordUpperCase = !(password.equals(password.toLowerCase()));
            requiredPasswordLowerCase = !(password.equals(password.toUpperCase()));

            for (char c : password.toCharArray()) {
                if (Character.isDigit(c)) {
                    requiredPasswordNumbers = true;
                    break;
                }
            }

            if (!getRequiredPasswordLowerCase()) {
                if (passwordErorr.isEmpty()) {
                    passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                } else {
                    passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                }
            }

            if (!getRequiredPasswordUpperCase()) {
                if (!passwordErorr.contains("пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру")) {
                    if (passwordErorr.isEmpty()) {
                        passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                    } else {
                        passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                    }
                }
            }

            if (!getRequiredPasswordNumbers()) {
                if (!passwordErorr.contains("пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру")) {
                    if (passwordErorr.isEmpty()) {
                        passwordErorr = passwordErorr + "пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                    } else {
                        passwordErorr = passwordErorr + "; пароль должен содержать символ нижнего регистра, верхнего регистра и одну цифру";
                    }
                }
            }
        }
        if (!passwordErorr.isEmpty()) {
            showAlertWithError("пароль", passwordErorr);
            validate = false;
        }
        return validate;
    }

    private void showAlertWithError(String type, String warningText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");

        alert.setHeaderText(null);
        alert.setContentText("Ваш " + type + " не соответствует требованиям: " + warningText);

        alert.showAndWait();
    }
}
