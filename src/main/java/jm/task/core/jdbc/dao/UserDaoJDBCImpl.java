package jm.task.core.jdbc.dao;

import java.sql.Statement;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (id mediumint not null auto_increment, name VARCHAR (20) NOT NULL, lastname VARCHAR (20) NOT NULL, age INT NOT NULL, PRIMARY KEY (ID));";
    private static final String GET_ALL_USERS = "SELECT * FROM users;";
    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users";
    private static final String CLEAN_USERS_TABLE = "TRUNCATE TABLE users";
    private static final String SAVE_USER = "INSERT users(name, lastName, age) VALUES(?, ?, ?)";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        PreparedStatement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            connection = Util.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(CREATE_USERS_TABLE, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.err.println("failed to create table");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String SQL = DROP_USERS_TABLE;
            statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Failed to drop table");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(CLEAN_USERS_TABLE);
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate( REMOVE_USER_BY_ID + id);
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        PreparedStatement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String SQL = SAVE_USER;
            statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            connection.commit();
            System.out.printf(" User с именем – %s добавлен в базу данных" + "\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("failed to save user");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_ALL_USERS);
            List<User> result = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                byte age = rs.getByte("age");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setAge(age);

                result.add(user);
            }
            connection.commit();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

}


















//    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
//    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mytestdb";
//    private static final String LOGIN = "root";
//    private static final String PASSWORD = "root2";

//    private static final String SELECT_ALL_SQL = "SELECT id, login, email FROM User";
//    private static final String DELETE_BY_ID_SQL = "DELETE FROM User WHERE id = ?";
//    private static final String INSERT_SQL = "INSERT INTO User (login, email) VALUES (?, ?)";
//    private static final String SELECT_BY_LOGIN_SQL = "SELECT id FROM User WHERE login = ?";
//    private static final String SELECT_BY_EMAIL_SQL = "SELECT id FROM User WHERE email = ?";


//    private static Connection getConnection() {
//        try {
//            return DriverManager.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException("Can,t create connection", e);
//        }
//    }
