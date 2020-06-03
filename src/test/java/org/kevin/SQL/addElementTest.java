package org.kevin.SQL;

import org.junit.jupiter.api.Test;
import org.kevin.objects.elements.element;

import java.sql.Connection;
import java.sql.PreparedStatement;

class addElementTest {

    @Test
    void add() {
        try {
            Connection connection = CONN.getConnection("Kevin", "Kevinui.");

            PreparedStatement statement = connection.prepareStatement("UPDATE elements SET messForStudents  = ? where ID = ?;");
            for (int i = 1; i <= 118; i++) {
                element e = new element(i);
                statement.setDouble(1, e.getMessForStudent());
                statement.setInt(2, i);
                statement.execute();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}