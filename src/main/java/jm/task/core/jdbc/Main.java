package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        dao.createUsersTable();
        dao.saveUser("00_Name", "00_lastname", (byte) 10);
        dao.saveUser("01_Name", "01_lastname", (byte) 20);
        dao.saveUser("02_Name", "02_lastname", (byte) 30);
        dao.saveUser("03_Name", "03_lastname", (byte) 40);
        for (User user : dao.getAllUsers()) {
            System.out.println(user);
        }
        dao.cleanUsersTable();
        dao.dropUsersTable();

    }
}
