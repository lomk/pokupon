package ua.com.bukvashops.pocupon.dao;

import ua.com.bukvashops.pocupon.Entities.BaseEntity;
import ua.com.bukvashops.pocupon.Entities.Certificate;
import ua.com.bukvashops.pocupon.Entities.Shop;
import ua.com.bukvashops.pocupon.Entities.User;

import javax.naming.NamingException;
import java.sql.*;

/**
 * Created by Igor on 3/12/2016.
 */
public class MySqlDaoFactory<T extends BaseEntity> implements DaoFactory {

    private T object=null;

    public MySqlDaoFactory() {}

    public MySqlDaoFactory(T object){
        this.object=(T) object;
    }

    public void setObject(T object) {
        this.object = (T) object;
    }

    public Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = connectionManager.getConnection();
        return connection;
    }

    public MySqlUserDao getMySqlUserDao() throws SQLException, NamingException, ClassNotFoundException {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = connectionManager.getConnection();

        if (object != null){
            return new MySqlUserDao((User) object, connection);
        } else {
            return new MySqlUserDao(connection);
        }
    }

    public MySqlCertificateDao getMySqlCertificateDao() throws SQLException, NamingException, ClassNotFoundException {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = connectionManager.getConnection();
        if (object != null){
            return new MySqlCertificateDao((Certificate) object, connection);
        } else {
            return new MySqlCertificateDao(connection);
        }
    }

    public MySqlShopDao getMySqlShopDao() throws SQLException, NamingException, ClassNotFoundException {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = connectionManager.getConnection();
        if (object != null){
            return new MySqlShopDao((Shop) object, connection);
        } else {
            return new MySqlShopDao(connection);
        }
    }


}
