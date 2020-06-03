package org.kevin.SQL;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 18145
 * @version 1.0
 */
public class addElement {
    public Statement statement;

    public addElement(String userName, String passwd) {
        Connection connection = CONN.getConnection(userName, passwd);
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public addElement(Connection connection) {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void add(int id, double mess, String ChineseName, String EnglishName) {
        try {
            System.out.println("insert into elements values (" + id + "," + mess + ",'" + ChineseName + "',''" + EnglishName + "')");
            statement.execute("insert into elements values (" + id + "," + mess + ",'" + ChineseName + "','" + EnglishName + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
