package org.kevin.sql;

import org.kevin.objects.entity.Molecule;

import java.io.*;
import java.sql.*;

/**
 * @author 18145
 * @version 1.0
 */
public class MoleculeMemory {
    //<------------------------faction------------------------------->
    public static final byte CHINESE_NAME = 0;
    public static final byte ENGLISH_NAME = 1;
    public static final String LOCATION_CHINESE_NAME = "1";
    public static final String LOCATION_ENGLISH_NAME = "2";
    public static final Molecule LOCATION_OBJECT_DATA = new Molecule();
    public static final Integer LOCATION_DATA_VERSION = 4;
    public static final String LOCATION_DESCRIPTION = "5";
    public static final byte DO_REWRITE = 0;
    public static final byte DO_NOT_REWRITE = 1;
    //<--------------------------batch------------------------------->
    private static final boolean DO_BATCH = true;
    public PreparedStatement writer;
    public PreparedStatement updater;
    public PreparedStatement readerFromChineseName;
    public PreparedStatement readerFromEnglishName;
    public PreparedStatement checker1;
    public PreparedStatement checker2;
    private static final boolean DO_NOT_BATCH = false;
    //<--------------------------statement--------------------------->
    public Statement statement;


    public MoleculeMemory(Connection connection) throws SQLException {
        statement = connection.createStatement();
        writer = connection.prepareStatement("INSERT INTO chemicaltool.molecule(ChineseName, EnglishName, moleculeObjectData, dataVersion, Description) VALUE (?,?,?,?,?);");
        checker1 = connection.prepareStatement("SELECT ChineseName FROM chemicaltool.molecule m WHERE EnglishName = ? LIMIT 1;");
        checker2 = connection.prepareStatement("SELECT ChineseName FROM chemicaltool.molecule m WHERE ChineseName = ? LIMIT 1;");
        readerFromChineseName = connection.prepareStatement("SELECT * FROM chemicaltool.molecule m WHERE ChineseName = ?;");
        readerFromEnglishName = connection.prepareStatement("SELECT * FROM chemicaltool.molecule m WHERE EnglishName = ?;");
        updater = connection.prepareStatement("UPDATE chemicaltool.molecule SET moleculeObjectData = ?,dataVersion = ?,Description = ? WHERE EnglishName = ?;");


    }

    public MoleculeMemory(String userName, String passwd) throws SQLException {
        this(ConnectionManager.getConnection(userName, passwd));
    }

    public boolean checkAvailable(String ChineseName, String EnglishName) throws SQLException {
        long time = System.nanoTime();

        checker1.setString(1, EnglishName);
        checker2.setString(1, ChineseName);
        ResultSet resultSet = checker1.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            ResultSet resultSet1 = checker2.executeQuery();
            return resultSet1.next();
        }

    }

    private String checkDescription(String Description) {
        if (Description == null) {
            Description = "无";
        }
        return Description;
    }

    public void executeBatch() throws SQLException {
        writer.executeBatch();
        updater.executeBatch();
    }

    // TODO: 2020/6/6 错误:检查相同元素

    private boolean writeMolecule0(Molecule m, String ChineseName, String EnglishName, String Description, byte rewrite, boolean batch) throws SQLException, IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        // TODO: 2020/6/4 版本检查代码加入
        long time = System.nanoTime();
        if (checkAvailable(ChineseName, EnglishName)) {
            if (rewrite == DO_REWRITE) {
                if (batch) {
                    batchUpdateMolecule(ChineseName, EnglishName, m, Description);
                } else {
                    updateMolecule(ChineseName, EnglishName, m, Description);
                }
                return true;
            } else {

                return false;
            }
        }
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(m);
        objectOutputStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        writer.setString(1, ChineseName);
        writer.setString(2, EnglishName);
        writer.setBinaryStream(3, byteArrayInputStream);
        writer.setLong(4, Molecule.getVersion());
        Description = checkDescription(Description);
        writer.setString(5, Description);
        if (batch) {
            writer.addBatch();
        } else {
            writer.executeUpdate();
        }

        return true;

    }

    public boolean writeMolecule(Molecule m, String ChineseName, String EnglishName, String Description, byte rewrite) throws SQLException, IOException {
        return writeMolecule0(m, ChineseName, EnglishName, Description, rewrite, DO_NOT_BATCH);
    }


    public boolean batchWriteMolecule(Molecule m, String ChineseName, String EnglishName, String Description, byte rewrite) throws SQLException, IOException {
        return writeMolecule0(m, ChineseName, EnglishName, Description, rewrite, DO_BATCH);
    }

    public boolean writeMolecule(Molecule m, String ChineseName, String EnglishName, String Description) throws IOException, SQLException {
        return this.writeMolecule(m, ChineseName, EnglishName, Description, DO_REWRITE);
    }

    public boolean batchWriteMolecule(Molecule m, String ChineseName, String EnglishName, String Description) throws IOException, SQLException {
        return this.batchWriteMolecule(m, ChineseName, EnglishName, Description, DO_REWRITE);
    }

    private boolean updateMolecule0(String ChineseName, String EnglishName, Molecule molecule, String Description, boolean batch) throws SQLException, IOException {
        if (!checkAvailable(ChineseName, EnglishName)) {
            return false;
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(molecule);
            objectOutputStream.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            updater.setBinaryStream(1, byteArrayInputStream);
            updater.setLong(2, molecule.getSerialVersionUID());
            Description = checkDescription(Description);
            updater.setString(3, Description);
            updater.setString(4, EnglishName);
            if (batch) {
                updater.addBatch();
            } else {
                updater.executeUpdate();
            }
        }
        return true;
    }

    public boolean updateMolecule(String ChineseName, String EnglishName, Molecule molecule, String Description) throws SQLException, IOException {
        return updateMolecule0(ChineseName, EnglishName, molecule, Description, DO_NOT_BATCH);
    }

    public boolean batchUpdateMolecule(String ChineseName, String EnglishName, Molecule molecule, String Description) throws SQLException, IOException {
        return updateMolecule0(ChineseName, EnglishName, molecule, Description, DO_BATCH);
    }


    public ResultSet readByName(String Name, byte condition) throws SQLException, IOException, ClassNotFoundException {

        ResultSet resultSet;
        if (condition == CHINESE_NAME) {

            readerFromChineseName.setString(1, Name);
            resultSet = readerFromChineseName.executeQuery();
        } else if (condition == ENGLISH_NAME) {

            readerFromEnglishName.setString(1, Name);
            resultSet = readerFromEnglishName.executeQuery();
        } else {
            throw new UnsupportedOperationException("不支持此操作:" + condition);
        }

        return resultSet;

    }

    public <V> V readByName(String Name, byte condition, V location) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = readByName(Name, condition);
        resultSet.next();
        if (location instanceof Integer) {
            Integer integer = resultSet.getInt((Integer) location);
            return (V) integer;
        } else if (location instanceof String) {
            int Location = Integer.parseInt((String) location);
            String string = resultSet.getString(Location);
            return (V) string;
        } else if (location instanceof Molecule) {
            InputStream stream = resultSet.getBinaryStream(3);
            ObjectInputStream objectInputStream = new ObjectInputStream(stream);
            return (V) objectInputStream.readObject();
        } else {
            throw new UnsupportedOperationException("不支持该操作");
        }

    }


}
