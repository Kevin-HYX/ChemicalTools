package org.kevin.SQL;

import org.kevin.objects.elements.molecule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 18145
 * @version 1.0
 */
public class addMolecule {
    public PreparedStatement statement;

    public addMolecule(Connection connection, molecule m, String ChineseName, String EnglishName) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            statement = connection.prepareStatement("insert into molecule " +
                    "values (" +
                    "?," +
                    "?," +
                    "?" +
                    ");");
            statement.execute("use chemicaltools");
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(m);
            byteArrayOutputStream = null;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            statement.setString(1, ChineseName);
            statement.setString(2, EnglishName);
            statement.setBinaryStream(3, byteArrayInputStream);
            statement.execute();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    public addMolecule(String userName, String passwd, molecule m, String ChineseName, String EnglishName) {
        this(CONN.getNewConnection(userName, passwd), m, ChineseName, EnglishName);
    }


    public void add(byte[] objData, String description) {

    }


}
