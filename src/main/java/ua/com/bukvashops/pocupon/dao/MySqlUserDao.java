package ua.com.bukvashops.pocupon.dao;

import ua.com.bukvashops.pocupon.Entities.BaseEntity;
import ua.com.bukvashops.pocupon.Entities.User;

import java.sql.*;
import java.util.*;

/**
 * Created by Igor on 3/12/2016.
 */

public class MySqlUserDao extends AbstractMySqlDao {

    //Connection connection;
    User user;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setUser(User user) {
        this.user = user;
    }

    MySqlUserDao(){
    };

    MySqlUserDao(Connection connection){
        super.connection=connection;
    }

    MySqlUserDao(User user, Connection connection){
        this.user=user;
        super.connection=connection;
    }

    @Override
    public String getSelectQuery(){
        String sql = "SELECT user_id, name_first, name_last, login, password, is_active, is_admin, shop_name " +
                "FROM user LEFT JOIN shop USING(shop_id)";
        return sql;
    }

    @Override
    public String getCreateQuery(){
        String sql = "INSERT INTO user (name_first, name_last, login, password, is_active, is_admin, shop_id) " +
                " VALUES (?, ?, ?, ?, ?, ?, (SELECT shop_id FROM shop WHERE shop_name=? LIMIT 1))";
        return sql;
    }

    @Override
    public String getUpdateQuery(){
        String sql = "UPDATE user SET name_first=?, name_last=?, login=?, password=?, is_active=?, is_admin=?, " +
                "shop_id=(SELECT shop_id FROM shop WHERE shop_name=? LIMIT 1) WHERE user_id=?";
        return sql;
    }

    @Override
    public String getDeleteQuery(){
        String sql = "DELETE FROM user WHERE user_id=?";
        return sql;
    }

    public List<User> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<User> result = new LinkedList<User>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("name_first"));
                user.setLastName(rs.getString("name_first"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("is_admin"));
                user.setActive(rs.getBoolean("is_active"));
                user.setShop(rs.getString("shop_name"));
                result.add(user);
            }

        return result;
    }

    protected void prepareStatement(PreparedStatement statement, BaseEntity object, String action) throws SQLException {
        User user = (User) object;

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isActive());
            statement.setBoolean(6, user.isAdmin());
            statement.setString(7, user.getShop());

            if (action.equals("update")){
                statement.setObject(8, user.getId());
            }

    }
}
