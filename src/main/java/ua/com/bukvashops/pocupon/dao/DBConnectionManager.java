package ua.com.bukvashops.pocupon.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.rmi.registry.Registry;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Igor on 2/16/2016.
 */
public class DBConnectionManager {
    private Connection connection = null;

        public DBConnectionManager() throws ClassNotFoundException, NamingException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            Context initialContext = null;
            initialContext = new InitialContext();
            Context environmentContext = null;
            environmentContext = (Context) initialContext.lookup("java:comp/env");
            String dataResourceName = "jdbc/pocupon";
            DataSource dataSource = null;
            dataSource = (DataSource) environmentContext.lookup(dataResourceName);
            this.connection = (Connection) dataSource.getConnection();
        }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
