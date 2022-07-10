package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.saveUser("Neil","Alishev",(byte) 30);
        userDao.saveUser("Zaur","Tregulov",(byte) 41);
        userDao.saveUser("Joe","Biden",(byte) 75);
        userDao.saveUser("Bruce","Banner",(byte) 34);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        Util.closeConnection();


    }
}
