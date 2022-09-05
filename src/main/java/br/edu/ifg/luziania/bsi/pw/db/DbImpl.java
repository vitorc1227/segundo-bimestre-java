package br.edu.ifg.luziania.bsi.pw.db;

import javax.annotation.ManagedBean;
import java.sql.*;
import java.util.Properties;

@ManagedBean
public class DbImpl implements Db {


    static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static final String HOST = "migae5o25m2psr4q.cbetxkdyhwsb.us-east-1.rds.amazonaws.com";

    static final String DATABASE_URL = "jdbc:mysql://migae5o25m2psr4q.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/d4ihza7g3hxf225r";
    static final String USER = "kci2zoprta0xcet7";

    static final String PASS = "ponalcni78w55fi8";

    static final String PORT = "3306";

    static final String DATABASE = "d4ihza7g3hxf225r";

    private Properties properties;

    private static Connection conn = null;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASS);
            properties.setProperty("MaxPooledStatements", "250");
        }
        return properties;
    }

    public Connection getConnection() {

        if (conn != null) {
            try {
                if (!conn.isClosed())
                    conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        try {
            Class.forName(DRIVER);

            conn = DriverManager.getConnection(DATABASE_URL, getProperties());
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        closeConnection();
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        closeConnection();
    }

}
