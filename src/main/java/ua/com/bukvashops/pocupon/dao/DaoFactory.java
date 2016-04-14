package ua.com.bukvashops.pocupon.dao;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Igor on 3/12/2016.
 */
public interface DaoFactory {
    public Connection getConnection() throws SQLException, NamingException, ClassNotFoundException;
    //public <T> T getDao(GenericDao dao, Statement statement);
}
