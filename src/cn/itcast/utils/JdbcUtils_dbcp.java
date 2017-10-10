package cn.itcast.utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by yvettee on 2017/10/9.
 */
public class JdbcUtils_dbcp {
    private static DataSource ds = null;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {
        try {
            InputStream in = JdbcUtils_dbcp.class.getClassLoader().getResourceAsStream("dbcpConfig.properties");
            Properties prop = new Properties();
            prop.load(in);
            BasicDataSourceFactory factory = new BasicDataSourceFactory();
            ds = factory.createDataSource(prop);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = threadLocal.get();
            if (conn == null) {
                conn = ds.getConnection();
                threadLocal.set(conn);
            }
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //提供一个开启事务的方法（由线程调用）
    public static void startTransaction() {
        //得到当前线程上绑定的连接，开启事务
        try {
            Connection conn = threadLocal.get();
            if (conn == null) {//代表线程上没有绑定连接
                conn = getConnection();//已经有连接了
                threadLocal.set(conn);//绑定到线程上去
            }
            conn.setAutoCommit(false);//开启事务
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commitTransaction() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            threadLocal.remove();//解除当前线程上绑定的连接
        }
    }
}
