package org.kevin.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 18145
 * @version 1.0
 */
public class ElementMemory {
    public PreparedStatement statement;

    public ElementMemory(String userName, String passwd) {
        this(ConnectionManager.getConnection(userName, passwd));
    }


    public ElementMemory(Connection connection) {
        try {
            statement = connection.prepareStatement("INSERT INTO chemicaltool.elements VALUE (?,?,?,?,?,?);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void writeElement(int id, double mass, double massForStudents, String ChineseName, String EnglishName, String Description) {
        try {
            statement.setInt(1, id);
            statement.setDouble(2, mass);
            statement.setDouble(3, massForStudents);
            statement.setString(4, ChineseName);
            statement.setString(5, EnglishName);
            if (Description == null) {
                Description = "无";
            }
            statement.setString(6, Description);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
