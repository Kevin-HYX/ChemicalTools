package org.kevin.SQL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 18145
 * @version 1.0
 */
public class elementMemory {
    // TODO: 2020/6/5 使用preparedStatement
    public PreparedStatement statement;

    public elementMemory(String userName, String passwd) {
        this(ConnectionManager.getConnection(userName, passwd));
    }

    public elementMemory(Connection connection) {
        try {
            statement = connection.prepareStatement("INSERT INTO chemicaltool.elements VALUE (?,?,?,?,?,?);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void writeElement(int id, double mass, double massForStudents, String ChineseName, String EnglishName, String Description) {
        public void
    }


}
