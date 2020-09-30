package bartsev.models;

public class UserRestrictions {
    private String login;
    private Boolean firstRestriction;
    private Boolean secondRestriction;
    private Boolean thirdRestriction;

    public UserRestrictions(String login, Boolean firstRestriction, Boolean secondRestriction, Boolean thirdRestriction) {
        setLogin(login);
        setFirstRestriction(firstRestriction);
        setSecondRestriction(secondRestriction);
        setThirdRestriction(thirdRestriction);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getFirstRestriction() {
        return firstRestriction;
    }

    public void setFirstRestriction(Boolean firstRestriction) {
        this.firstRestriction = firstRestriction;
    }

    public Boolean getSecondRestriction() {
        return secondRestriction;
    }

    public void setSecondRestriction(Boolean secondRestriction) {
        this.secondRestriction = secondRestriction;
    }

    public Boolean getThirdRestriction() {
        return thirdRestriction;
    }

    public void setThirdRestriction(Boolean thirdRestriction) {
        this.thirdRestriction = thirdRestriction;
    }
}
