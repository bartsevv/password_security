package bartsev.users;

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
    private final static String FILE_PATH = "src/bartsev/users/listofusers.txt";
    public final static String DEACTIVATED_USER = "Deactivated";
    public final static String ACTIVATED_USER = "Activated";

    public static void addNewUser(User user) throws IOException {
//        File listOfUsers = new File(FILE_PATH);
//        PrintWriter printWriter = new PrintWriter(listOfUsers);
//        printWriter.println(user.getLogin() + " " + user.getPassword() + " " + user.getDateOfCreation());
//        printWriter.close();
        FileWriter writer = new FileWriter(FILE_PATH, true);
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        bufferWriter.write(user.getLogin() + " " + user.getPassword() + " " + user.getDateOfCreation() + " " + user.getStatus());
        bufferWriter.newLine();
        bufferWriter.close();
    }

    public static User getUser(String login) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
        String line = bufferedReader.readLine();
        while (line != null) {
            if (line.contains(login)) {
                List<String> userAsString = Arrays.asList(line.split(" "));
                bufferedReader.close();
                return new User(userAsString.get(0), userAsString.get(1), LocalDate.parse(userAsString.get(2)), userAsString.get(3));
            }
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return null;
    }

    //TODO
    public static void changeUserPassword(User user, String newPassword) {
        List<User> userList = new ArrayList<>();
        try {
            userList = getUserList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(User selectUser: userList) {
            if (selectUser.getLogin().equals(user.getLogin())) {
                selectUser.setPassword(newPassword);
                selectUser.setDateOfCreation(LocalDate.now());
                break;
            }
        }
        clearTheFile();
        for(User selectUser: userList) {
            try {
                addNewUser(selectUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean validatePasswordExpirationDate(User user) {
        Period period = Period.between(user.getDateOfCreation(), LocalDate.now());
        return period.getDays() <= 30;
    }

    public static List<User> getUserList() throws IOException {
        List<String> listOfUsers = new ArrayList<>(Files.readAllLines(Paths.get(FILE_PATH), StandardCharsets.UTF_8));
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

    private static void clearTheFile() {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter(FILE_PATH, false);
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
}
