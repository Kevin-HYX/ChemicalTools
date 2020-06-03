package org.kevin.SQL;

import org.kevin.objects.elements.element;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 18145
 * @version 1.0
 */
public class changeElement {
    public static void change(element oldElement, element newElement, Statement statement) {
        try {
            statement.execute("");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
