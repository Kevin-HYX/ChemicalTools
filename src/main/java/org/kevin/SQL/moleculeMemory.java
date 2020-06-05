package org.kevin.SQL;

import org.kevin.objects.elements.molecule;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 18145
 * @version 1.0
 */
public class moleculeMemory {
    public PreparedStatement statement;
    public static final String CHINESE_NAME = "ChineseName";
    public static final String ENGLISH_NAME = "EnglishName";
    public static final byte DO_REWRITE = 0;
    public static final byte DO_NOT_REWRITE = 1;
    private static final byte WEITE = 1;
    private static final byte READ = 2;
    private static int lastOperation;
    public Connection connection;

    public moleculeMemory(Connection connection) {
        this.connection = connection;
    }

    public moleculeMemory(String userName, String passwd) {
        this.connection = connectionManager.getConnection(userName, passwd);
    }

    public boolean add(molecule m, String ChineseName, String EnglishName) {
        return this.add(m, ChineseName, EnglishName, DO_NOT_REWRITE);
    }

    public boolean add(molecule m, String ChineseName, String EnglishName, byte rewrite) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            //性能改进
            if (lastOperation != WEITE) {
                statement = connection.prepareStatement("insert into molecule " +
                        "values (" +
                        "?," +
                        "?," +
                        "?," +
                        "?" +
                        ");");
                statement.execute("use chemicaltool");
                lastOperation = WEITE;
            }
            // TODO: 2020/6/4 版本检查代码加入
            statement.execute("use chemicaltool");
            ResultSet resultSet = statement.executeQuery("select ChineseName from molecule WHERE ChineseName = '" + ChineseName + "'AND EnglishName = '" + EnglishName + "';");
            if (resultSet.next() && rewrite == DO_NOT_REWRITE) {
                return false;
            }
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(m);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            statement.setString(1, ChineseName);
            statement.setString(2, EnglishName);
            statement.setBinaryStream(3, byteArrayInputStream);
            statement.setLong(4, molecule.getVersion());
            statement.execute();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    public molecule readByName(String Name, String condition) {
        molecule molecule = null;
        try {

            statement = connection.prepareStatement("select moleculeObjectData from molecule where " + condition + " = ?");
            statement.execute("use chemicaltool");
            statement.setString(1, Name);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            InputStream stream = resultSet.getBinaryStream(1);
            ObjectInputStream objectInputStream = new ObjectInputStream(stream);
            molecule = (molecule) objectInputStream.readObject();
            return molecule;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return molecule;
    }
}
