package org.kevin.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 18145
 * @version 1.0
 */
public class ConnectionManager {
    /**
     * 一个<code>Connection</code>,充当缓冲池
     */
    private static Connection connection;

    /**
     * 使用默认的地址创建新的<code>Connection</code>
     * 返回一个新的<code>Connection</code>,该<code>Connection</code>与池中的<code>Connection</code>没有关联
     * 不推荐使用与缓冲池没有关联的<code>Connection</code>,这会增加数据库的负担
     *
     * @param userName 一个<code>String</code>,连接数据库的用户名
     * @param passwd   一个<code>String</code>,连接数据库的密码
     * @return <code>Connection</code>,和缓冲池没有任何联系
     * @see Connection
     */
    public static Connection getNewConnection(String userName, String passwd) {
        return getNewConnection("jdbc:mysql://192.168.1.103:3306/chemicaltool ? serverTimezone = UTC", userName, passwd);
    }

    /**
     * 返回一个新的<code>Connection</code>,该<code>Connection</code>与池中的<code>Connection</code>没有关联
     * 不推荐使用与缓冲池没有关联的<code>Connection</code>,这会增加数据库的负担
     *
     * @param host     一个<code>String</code>,指向SQL服务器的地址名例如
     *                 <code>String host = "jdbc:mysql://127.0.0.1/abc ? serverTimezone = UTC" </code>
     * @param userName 一个<code>String</code>,连接数据库的用户名
     * @param passwd   一个<code>String</code>,连接数据库的密码
     * @return <code>Connection</code>,和缓冲池没有任何联系
     * @see Connection
     */
    public static Connection getNewConnection(String host, String userName, String passwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(host, userName, passwd);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return connection;
    }

    /**
     * 使用此方法应确保缓冲池中有已经创建的<code>Connection</code>
     * 返回缓冲池中的<code>Connection</code>
     *
     * @return 缓冲池中的<code>Connection</code>
     * @throws NullPointerException 如果缓冲池中不存在<code>Connection</code>
     * @see Connection
     */
    public static Connection getConnection() {
        if (connection == null) {
            throw new NullPointerException("不存在connection,尝试使用getConnection(userName,passwd)");
        }
        return connection;
    }

    /**
     * 使用默认的地址创建<code>Connection</code>
     * 若不明确缓冲池中是否有存在的<code>Conenction</code>,请使用此方法
     * 用于返回一个<code>Connection</code>如果缓冲池中的<code>Connection</code>存在,则不再另外创建一个<code>Connection</code>,
     * 如果池中的<code>Connection</code>不存在,则不再创建一个新的<code>Connection</code>,即便使用了一个新的地址或者用户名
     * 如果想要强制更改缓冲池中的<code>Connection</code>,请使用<code>changeDefaultConnection()方法</code>
     *
     * @param userName 一个<code>String</code>,连接数据库的用户名
     * @param passwd   一个<code>String</code>,连接数据库的密码
     * @return 一个<code>Connection</code>,与调用时缓冲池中的Connection一致
     * @see Connection
     */
    public static Connection getConnection(String userName, String passwd) {
        return getConnection("jdbc:mysql://192.168.1.103:3306/chemicaltool ? serverTimezone = UTC", userName, passwd);
    }

    /**
     * 若不明确缓冲池中是否有存在的<code>Conenction</code>,请使用此方法
     * 用于返回一个<code>Connection</code>如果缓冲池中的<code>Connection</code>存在,则不再另外创建一个<code>Connection</code>,
     * 如果池中的<code>Connection</code>不存在,则不再创建一个新的<code>Connection</code>,即便使用了一个新的地址或者用户名
     * 如果想要强制更改缓冲池中的<code>Connection</code>,请使用<code>changeDefaultConnection()方法</code>
     *
     * @param host     一个<code>String</code>,指向SQL服务器的地址名例如
     *                 <code>String host = "jdbc:mysql://127.0.0.1/abc ? serverTimezone = UTC" </code>
     * @param userName 一个<code>String</code>,连接数据库的用户名
     * @param passwd   一个<code>String</code>,连接数据库的密码
     * @return 一个<code>Connection</code>,与调用时缓冲池中的Connection一致
     * @see Connection
     */
    public static Connection getConnection(String host, String userName, String passwd) {
        if (connection == null) {
            connection = getNewConnection(host, userName, passwd);
        }
        return connection;
    }

}

