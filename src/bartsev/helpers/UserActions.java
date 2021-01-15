package bartsev.helpers;

import bartsev.models.User;
import bartsev.models.UserAccess;
import bartsev.models.UserRestrictions;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActions {
    private final static String FILE_PATH_TO_USERLIST = "src/bartsev/sources/users/listofusers.txt";
    private final static String FILE_PATH_TO_RESTRICTIONS = "src/bartsev/sources/users/userrestrictions.txt";
    private final static String FILE_PATH_TO_ACCESS = "src/bartsev/sources/users/usersusbaccess.txt";
    public final static String DEACTIVATED_USER = "Deactivated";
    public final static String ACTIVATED_USER = "Activated";

    public static void addNewUser(User user) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_PATH_TO_USERLIST, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        try {
            bufferWriter.write(user.getLogin() + " " + user.getPassword() + " " + user.getDateOfCreation() + " " + user.getStatus());
            bufferWriter.newLine();
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addNewUserRestrictions(new UserRestrictions(user.getLogin(), true, true, true));
        addNewUserAccess(new UserAccess(user.getLogin(), "n/a", "n/a"));
    }

    public static User getUser(String login)  {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_PATH_TO_USERLIST));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            if (line.contains(login)) {
                List<String> userAsString = Arrays.asList(line.split(" "));
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new User(userAsString.get(0), userAsString.get(1), LocalDate.parse(userAsString.get(2)), userAsString.get(3));
            }
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void changeUserPassword(User user, String newPassword) {
        List<User> userList = new ArrayList<>();
        try {
            userList = getUserList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (User selectUser : userList) {
            if (selectUser.getLogin().equals(user.getLogin())) {
                selectUser.setPassword(newPassword);
                selectUser.setDateOfCreation(LocalDate.now());
                break;
            }
        }
        clearTheFile(FILE_PATH_TO_USERLIST);
        for (User selectUser : userList) {
            addNewUser(selectUser);
        }
    }

    public static Boolean validatePasswordExpirationDate(User user) {
        Period period = Period.between(user.getDateOfCreation(), LocalDate.now());
        return period.getDays() <= 30;
    }

    public static List<User> getUserList() throws IOException {
        List<String> listOfUsers = new ArrayList<>(Files.readAllLines(Paths.get(FILE_PATH_TO_USERLIST), StandardCharsets.UTF_8));
        List<User> userList = new ArrayList<>();
        User tempUser;
        List<String> selectLine = new ArrayList<>();
        for (int i = 0; i < listOfUsers.size(); i++) {
            selectLine = Arrays.asList(listOfUsers.get(i).split(" ").clone());
            tempUser = new User(selectLine.get(0), selectLine.get(1), LocalDate.parse(selectLine.get(2)), selectLine.get(3));
            userList.add(tempUser);
        }
        return userList;
    }

    public static void deleteUser(String login) {
        List<User> userList = new ArrayList<>();
        try {
            userList = getUserList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User selectUser : userList) {
            if (selectUser.getLogin().equals(login)) {
                userList.remove(selectUser);
                break;
            }
        }
        clearTheFile(FILE_PATH_TO_USERLIST);
        for (User selectUser : userList) {
            addNewUser(selectUser);
        }
    }

    public static void changeUserStatus(String login, Boolean status) {
        List<User> userList = new ArrayList<>();
        try {
            userList = getUserList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (User selectUser : userList) {
            if (selectUser.getLogin().equals(login)) {
                selectUser.setStatus(status ? ACTIVATED_USER : DEACTIVATED_USER);
                break;
            }
        }
        clearTheFile(FILE_PATH_TO_USERLIST);
        for (User selectUser : userList) {
            addNewUser(selectUser);
        }
    }

    private static void clearTheFile(String path) {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter(path, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try {
            fwOb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void synhronizeUsersWithRestrictions() {
        List<User> userList = new ArrayList<>();
        List<UserRestrictions> userRestrictionsList = new ArrayList<>();
        try {
            userList = getUserList();
            userRestrictionsList = getListUserRestrictions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (User user: userList) {
            if (getUserRestrictions(user.getLogin()) == null) {
                addNewUserRestrictions(new UserRestrictions(user.getLogin(), true, true, true));
            }
        }

        for (UserRestrictions userRestrictions: userRestrictionsList) {
            if (getUser(userRestrictions.getLogin()) == null) {
                deleteUser(userRestrictions.getLogin());
            }
        }
    }

    public static void synhronizeUsersWithAccessToUsb() {
        List<User> userList = new ArrayList<>();
        List<UserAccess> userAccesses = new ArrayList<>();
        try {
            userList = getUserList();
            userAccesses = getListUserAccess();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (User user: userList) {
            if (getUserAccess(user.getLogin()) == null) {
                addNewUserAccess(new UserAccess(user.getLogin(), "n/a", "n/a"));
            }
        }

        for (UserAccess userAccess: userAccesses) {
            if (getUser(userAccess.getLogin()) == null) {
                deleteUser(userAccess.getLogin());
            }
        }
    }

    private static List<UserRestrictions> getListUserRestrictions() throws IOException {
        List<String> listOfUsers = new ArrayList<>(Files.readAllLines(Paths.get(FILE_PATH_TO_RESTRICTIONS), StandardCharsets.UTF_8));
        List<UserRestrictions> userRestrictions = new ArrayList<>();
        UserRestrictions tempUserRestrictions;
        List<String> selectLine = new ArrayList<>();
        for (int i = 0; i < listOfUsers.size(); i++) {
            selectLine = Arrays.asList(listOfUsers.get(i).split(" ").clone());
            tempUserRestrictions = new UserRestrictions(selectLine.get(0), Boolean.parseBoolean(selectLine.get(1)), Boolean.parseBoolean(selectLine.get(2)), Boolean.parseBoolean(selectLine.get(3)));
            userRestrictions.add(tempUserRestrictions);
        }
        return userRestrictions;
    }

    private static List<UserAccess> getListUserAccess() throws IOException {
        List<String> listOfUsers = new ArrayList<>(Files.readAllLines(Paths.get(FILE_PATH_TO_ACCESS), StandardCharsets.UTF_8));
        List<UserAccess> userAccesses = new ArrayList<>();
        UserAccess tempUserAccess;
        List<String> selectLine = new ArrayList<>();
        for (int i = 0; i < listOfUsers.size(); i++) {
            selectLine = Arrays.asList(listOfUsers.get(i).split(" ").clone());
            tempUserAccess = new UserAccess(selectLine.get(0), selectLine.get(1), selectLine.get(2));
            userAccesses.add(tempUserAccess);
        }
        return userAccesses;
    }

    private static void addNewUserRestrictions(UserRestrictions userRestrictions) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_PATH_TO_RESTRICTIONS, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        try {
            bufferWriter.write(userRestrictions.getLogin() + " " + userRestrictions.getFirstRestriction() + " " + userRestrictions.getSecondRestriction() + " " + userRestrictions.getThirdRestriction());
            bufferWriter.newLine();
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addNewUserAccess(UserAccess userAccess) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_PATH_TO_ACCESS, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        try {
            bufferWriter.write(userAccess.getLogin() + " " + userAccess.getFirstDevice() + " " + userAccess.getSecondDevice());
            bufferWriter.newLine();
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserAccess getUserAccess(String login) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_PATH_TO_ACCESS));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            if (line.contains(login)) {
                List<String> userAsString = Arrays.asList(line.split(" "));
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new UserAccess(userAsString.get(0), userAsString.get(1), userAsString.get(2));
            }
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UserRestrictions getUserRestrictions(String login)  {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_PATH_TO_RESTRICTIONS));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            if (line.contains(login)) {
                List<String> userAsString = Arrays.asList(line.split(" "));
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new UserRestrictions(userAsString.get(0), Boolean.parseBoolean(userAsString.get(1)), Boolean.parseBoolean(userAsString.get(2)), Boolean.parseBoolean(userAsString.get(3)));
            }
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void changeUserRestrictions(UserRestrictions userRestrictions) {
        List<UserRestrictions> userRestrictionsList = new ArrayList<>();
        try {
            userRestrictionsList = getListUserRestrictions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (UserRestrictions selectUser : userRestrictionsList) {
            if (selectUser.getLogin().equals(userRestrictions.getLogin())) {
                selectUser.setFirstRestriction(userRestrictions.getFirstRestriction());
                selectUser.setSecondRestriction(userRestrictions.getSecondRestriction());
                selectUser.setThirdRestriction(userRestrictions.getThirdRestriction());
                break;
            }
        }
        clearTheFile(FILE_PATH_TO_RESTRICTIONS);
        for (UserRestrictions selectUser : userRestrictionsList) {
            addNewUserRestrictions(selectUser);
        }
    }

    public static void changeUserAccess(UserAccess userAccess) {
        List<UserAccess> userAccessList = new ArrayList<>();
        try {
            userAccessList = getListUserAccess();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (UserAccess selectUser : userAccessList) {
            if (selectUser.getLogin().equals(userAccess.getLogin())) {
                selectUser.setFirstDevice(userAccess.getFirstDevice());
                selectUser.setSecondDevice(userAccess.getSecondDevice());
                break;
            }
        }
        clearTheFile(FILE_PATH_TO_ACCESS);
        for (UserAccess selectUser : userAccessList) {
            addNewUserAccess(selectUser);
        }
    }
}
