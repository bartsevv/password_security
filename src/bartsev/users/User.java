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

public class User {
    private String login;
    private String password;
    private LocalDate dateOfCreation;
    private final static String FILE_PATH = "src/bartsev/users/listofusers.txt";

    public User(String login, String password, LocalDate dateOfCreation) {
        setLogin(login);
        setPassword(password);
        setDateOfCreation(dateOfCreation);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void addNewUser(User user) throws FileNotFoundException {
        File listOfUsers = new File(FILE_PATH);
        PrintWriter printWriter = new PrintWriter(listOfUsers);
        printWriter.println(user.login + " " + user.password + " " + user.dateOfCreation);
        printWriter.close();
    }

    public User getUser(String login) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
        String line = bufferedReader.readLine();
        while (line != null) {
            if (line.contains(login) && (line.contains(password))) {
                List<String> userAsString = Arrays.asList(line.split(" "));
                bufferedReader.close();
                return new User(userAsString.get(0), userAsString.get(1), LocalDate.parse(userAsString.get(2)));
            }
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return null;
    }

    //TODO
    public void changeUserPassword(User user, String newPassword) throws IOException {
        List<String> listOfUsers = new ArrayList<>(Files.readAllLines(Paths.get(FILE_PATH), StandardCharsets.UTF_8));
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (listOfUsers.get(i).contains(user.login)) {
                listOfUsers.set(i, newPassword);
                break;
            }
        }
        Files.write(Paths.get(FILE_PATH), listOfUsers, StandardCharsets.UTF_8);
    }

    public Boolean validatePasswordExpirationDate(User user){
        Period period = Period.between(user.dateOfCreation, LocalDate.now());
        return period.getDays() <= 30;
    }

    public static List<User> getUserList() throws IOException {
        List<String> listOfUsers = new ArrayList<>(Files.readAllLines(Paths.get(FILE_PATH), StandardCharsets.UTF_8));
        List<User> userList = new ArrayList<>();
        User tempUser;
        List<String> selectLine = new ArrayList<>();
        for (int i = 0; i < listOfUsers.size(); i++) {
            selectLine = Arrays.asList(listOfUsers.get(i).split(" ").clone());
            tempUser = new User(selectLine.get(0), selectLine.get(1), LocalDate.parse(selectLine.get(2)));
            userList.add(tempUser);
        }
        return userList;
    }
}
