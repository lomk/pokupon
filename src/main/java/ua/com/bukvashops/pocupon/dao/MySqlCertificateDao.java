package ua.com.bukvashops.pocupon.dao;

import ua.com.bukvashops.pocupon.Entities.BaseEntity;
import ua.com.bukvashops.pocupon.Entities.Certificate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mater on 18-Mar-16.
 */
public class MySqlCertificateDao extends AbstractMySqlDao {
    private Certificate certificate;

    public MySqlCertificateDao(Connection connection) {
        super.connection=connection;
    }

    public MySqlCertificateDao(Certificate object, Connection connection) {
        this.certificate=object;
        super.connection=connection;
    }

    @Override
    public String getCreateQuery() {
        String sql ="INSERT INTO certificate (code, certificate.is_active, date_of_application, comment) " +
                "VALUES (?, ?, ?, ?)";
        return sql;
    }

    @Override
    public String getDeleteQuery() {
        String sql = "DELETE FROM certificate WHERE id=?";
        return sql;
    }

    @Override
    public String getSelectQuery() {
        String sql = "SELECT certificate_id, code, certificate.is_active, date_of_application, comment, login " +
                "FROM certificate LEFT JOIN user ON used_by_user=user_id";
        return sql;
    }

    @Override
    public String getUpdateQuery() {
        String sql = "UPDATE certificate SET code=?, is_active=?, date_of_application=current_date(), comment=?, " +
                "used_by_user=(SELECT user_id FROM user WHERE login=?) WHERE certificate_id=?";
        return sql;
    }

    @Override
    protected List<Certificate> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Certificate> result = new LinkedList<Certificate>();

            while (rs.next()) {
                Certificate certificate = new Certificate();
                certificate.setId(rs.getInt("certificate_id"));
                certificate.setCode(rs.getString("code"));
                certificate.setActive(rs.getBoolean("is_active"));
                certificate.setDateOfApplication(rs.getDate("date_of_application"));
                certificate.setComment(rs.getString("comment"));
                certificate.setUsedBy(rs.getString("login"));
                result.add(certificate);
            }
        return result;
    }

    @Override
    protected  void prepareStatement(PreparedStatement statement, BaseEntity object, String action) throws SQLException {
        Certificate certificate = (Certificate) object;
        statement.setString(1, certificate.getCode());
        statement.setBoolean(2, certificate.isActive());
        statement.setString(3, certificate.getComment());

        if (action.equals("update")){
            statement.setString(4, certificate.getUsedBy());
            statement.setObject(5, certificate.getId());
        }
    }
}
