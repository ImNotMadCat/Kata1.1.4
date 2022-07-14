package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Neil", "Alishev", (byte) 30);
        userService.saveUser("Zaur", "Tregulov", (byte) 41);
        userService.saveUser("Joe", "Biden", (byte) 75);
        userService.saveUser("Bruce", "Banner", (byte) 34);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
