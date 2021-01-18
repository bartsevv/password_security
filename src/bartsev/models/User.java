package bartsev.models;

import bartsev.helpers.MagicSquare;

import java.time.LocalDate;

public class User {
    private String login;
    private String password;
    private LocalDate dateOfCreation;
    private String status;

    public User(String login, String password, LocalDate dateOfCreation, String status) {
        setLogin(login);
        setPassword(password);
        setDateOfCreation(dateOfCreation);
        setStatus(status);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = MagicSquare.encryptMagicSquare(password);
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return MagicSquare.decryptMagicSquare(password);
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
