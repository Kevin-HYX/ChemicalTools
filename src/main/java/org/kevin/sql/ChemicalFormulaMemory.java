package org.kevin.sql;

import org.kevin.objects.entity.ChemicalFormula;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 18145
 * @version 1.0
 */
public class ChemicalFormulaMemory {
    public static final byte DO_REWRITE = 0;
    public static final byte DO_NOT_REWRITE = 1;
    /**
     * 向数据库写入文件
     */
    private PreparedStatement writer;
    /**
     * 检查同样的是否存在
     */
    private PreparedStatement checker;
    /**
    向数据库读取
     */
    private PreparedStatement reader;
    /**
    更改指定项
     */
    private PreparedStatement changer;


    public ChemicalFormulaMemory(Connection connection) {
        try {
            writer = connection.prepareStatement("INSERT INTO chemicaltool.chemicalformula VALUE (?,?,?,?,?,?);");
            checker = connection.prepareStatement("SELECT ChineseName FROM chemicaltool.chemicalformula c WHERE ChineseName = ? OR EnglishName = ?;");
            reader = connection.prepareStatement("SELECT chemicalFormulaObjectsData FROM chemicaltool.chemicalformula c WHERE EnglishName = ?;");
            changer = connection.prepareStatement("UPDATE chemicaltool.chemicalformula SET `Condition` = ?,Description = ?,chemicalFormulaObjectsData = ? WHERE EnglishName = ?;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ChemicalFormulaMemory(String userName, String password) {
        this(ConnectionManager.getConnection(userName, password));
    }

    public boolean checkSame(ChemicalFormula chemicalFormula, String ChineseName, String EnglishName) {
        ResultSet resultset = null;
        try {
            checker.setLong(1, chemicalFormula.hashCode());
            checker.setString(2, ChineseName);
            checker.setString(3, EnglishName);
            resultset = checker.executeQuery();

            if (resultset == null || resultset.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    /**
     * 往数据库中加入一个化学方程式
     *
     * @param chemicalFormula 一个<code>ChemicalFormula</code>,将被写入数据库
     * @param ChineseName     该化学方程式的中文名称
     * @param EnglishName     该化学方程式的英文名称,即化学方程式</br>
     *                        如:2H2O == 2H2 + O2</br>
     *                        所有的空格都会被自动去除</br>
     * @param Condition       该化学方程式的反应条件,如无,可写无
     * @param Description     该化学方程式的反应现象,如无,可写无
     * @return 如果写入成功, 则返回true, 发现重复的元素或者其他状况的, 返回false
     */
    public boolean writeChemicalFormula(ChemicalFormula chemicalFormula, String ChineseName, String EnglishName, String Condition, String
            Description, byte rewrite) {

        byte[] data = new byte[0];
        final boolean checkSame = checkSame(chemicalFormula, ChineseName, EnglishName);
        if (!checkSame) {

            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(chemicalFormula);
                objectOutputStream.close();
                data = byteArrayOutputStream.toByteArray();

                writer.setString(1, ChineseName);
                writer.setString(2, EnglishName.replaceAll(" ", ""));
                writer.setString(3, Condition);
                writer.setString(4, Description);
                writer.setBlob(5, new ByteArrayInputStream(data));
                writer.setLong(6, chemicalFormula.hashCode());
                writer.execute();
                return true;
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        } else if (rewrite == DO_REWRITE) {
// TODO: 2020/6/5 未完成
        }
        return false;
    }


}
