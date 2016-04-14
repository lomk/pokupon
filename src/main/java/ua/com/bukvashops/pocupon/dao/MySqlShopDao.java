package ua.com.bukvashops.pocupon.dao;

import ua.com.bukvashops.pocupon.Entities.BaseEntity;
import ua.com.bukvashops.pocupon.Entities.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mater on 18-Mar-16.
 */
public class MySqlShopDao extends AbstractMySqlDao {
    Shop shop;
    public MySqlShopDao(Shop object, Connection connection) {
        this.shop=object;
        super.connection=connection;
    }

    public MySqlShopDao(Connection connection) {
        super.connection=connection;
    }

    @Override
    public String getCreateQuery() {

        String sql= "INSERT INTO shop (shop_name) VALUE(?)";
        return sql;
    }

    @Override
    public String getDeleteQuery() {
        String sql = "DELETE FROM shop WHERE id=?";
        return sql;
    }

    @Override
    public String getSelectQuery() {
        String sql="SELECT shop_id, shop_name FROM shop";
        return sql;
    }

    @Override
    public String getUpdateQuery() {
        String sql = "UPDATE shop SET shop_name=? where shop_id=?";
        return null;
    }

    @Override
    public List<Shop> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Shop> result = new LinkedList<Shop>();
            while (rs.next()) {
                Shop shop = new Shop();
                shop.setId(rs.getInt("shop_id"));
                shop.setName(rs.getString("shop_name"));
                result.add(shop);
            }

        return result;
    }

    @Override
    protected void prepareStatement(PreparedStatement statement, BaseEntity object, String action) {
        Shop shop = (Shop) object;
        try {
            statement.setString(1, shop.getName());

            if (action.equals("update")){
                statement.setObject(2, shop.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
