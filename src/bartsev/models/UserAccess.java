package bartsev.models;

public class UserAccess {
    private String login;
    private String firstDevice;
    private String secondDevice;

    public UserAccess(String login, String firstDevice, String secondDevice) {
        setLogin(login);
        setFirstDevice(firstDevice);
        setSecondDevice(secondDevice);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstDevice() {
        return firstDevice;
    }

    public void setFirstDevice(String firstDevice) {
        this.firstDevice = firstDevice.replaceAll("\\s+","");
    }

    public String getSecondDevice() {
        return secondDevice;
    }

    public void setSecondDevice(String secondDevice) {
        this.secondDevice = secondDevice.replaceAll("\\s+","");
    }
}
