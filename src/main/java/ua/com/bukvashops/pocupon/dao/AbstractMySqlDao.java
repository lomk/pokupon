package ua.com.bukvashops.pocupon.dao;



import ua.com.bukvashops.pocupon.Entities.BaseEntity;
import ua.com.bukvashops.pocupon.Exceptions.NoDataDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mater on 13-Mar-16.
 */
public abstract class AbstractMySqlDao<T extends BaseEntity> implements GenericDao {
    public Connection connection;

    public abstract String getCreateQuery();
    public abstract String getDeleteQuery();
    public abstract String getSelectQuery();
    public abstract String getUpdateQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract <T extends BaseEntity> void prepareStatement(PreparedStatement statement, T object, String action) throws SQLException;

    public <T> T getByPK(int key) throws SQLException, NoDataDaoException {
        List<T> list = null;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, key);
        ResultSet rs = statement.executeQuery();
        list = (List<T>) parseResultSet(rs);

        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new NoDataDaoException("Received more than one record.");
        }
        return (T) list.iterator().next();
    }

    public <T> T getByUniqParameter(String param, String paramValue) throws NoDataDaoException, SQLException {
        List<T> list=null;
        String sql = getSelectQuery();
        sql += " WHERE " + param + " = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, paramValue);
        ResultSet rs = statement.executeQuery();
        list = (List<T>) parseResultSet(rs);

        if (list == null || list.size() == 0) {
            return null;
        } if (list.size() > 1) {
            throw new NoDataDaoException("Received more than one record.");
        }
        return (T) list.iterator().next();
    }

    public List<T> getAll() throws SQLException {
        List<T> list = null;
        String sql = getSelectQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        list = (List<T>) parseResultSet(rs);
        return list;
    }

    public void delete(T object) throws NoDataDaoException, SQLException {
        String sql = getDeleteQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, object.getId());
        int count = statement.executeUpdate();
        if (count!=1){
            throw new NoDataDaoException("On delete modify more then 1 record: " + count);
        }
    }
    public void update(T object) throws NoDataDaoException, SQLException {
        String sql = getUpdateQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        prepareStatement(statement, object, "update");
        int count = statement.executeUpdate();
        if (count!=1){
            throw new NoDataDaoException("On update modify more then 1 record: " + count);
        }
    }

    public void create(T object) throws NoDataDaoException, SQLException {
        String sql = getUpdateQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        prepareStatement(statement, object, "create");
        int count = statement.executeUpdate();
        if (count!=1){
            throw new NoDataDaoException("On persist modify more then 1 record: " + count);
        }
    }
    public void closeConnection() {
        if (this.connection != null){
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
