package org.kevin.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 18145
 * @version 1.0
 */
public class connectionManager {
    private static Connection connection;

    public static Connection getNewConnection(String userName, String passwd) {
        return getNewConnection("jdbc:mysql://192.168.1.103:3306/chemicaltool ? serverTimezone = UTC", userName, passwd);
    }

    public static Connection getNewConnection(String host, String userName, String passwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(host, userName, passwd);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            throw new NullPointerException("不存在connection,尝试使用getConnection(userName,passwd)");
        }
        return connection;
    }

    public static Connection getConnection(String userName, String passwd) {
        if (connection == null) {
            connection = getNewConnection("jdbc:mysql://192.168.1.103:3306/chemicaltool ? serverTimezone = UTC", userName, passwd);
        }
        return connection;
    }


}
