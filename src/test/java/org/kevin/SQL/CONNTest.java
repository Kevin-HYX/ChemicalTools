package org.kevin.SQL;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class CONNTest {

    @Test
    void getConnection() {
        Connection connection = CONN.getNewConnection("Kevin", "Kevinui.");
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}