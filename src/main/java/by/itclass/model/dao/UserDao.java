package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static by.itclass.constants.DbConstants.*;

public class UserDao {
    private static UserDao dao;

    public static UserDao getInstance() {
        if (Objects.isNull(dao)) {
            dao = new UserDao();
        }
        return dao;
    }

    public User getUser(String login, String password) {
        try (var cn = ConnectionManager.getConnection();
            var ps = cn.prepareStatement(SELECT_USER)){
            ps.setString(1, login);
            ps.setString(2, password);

            var rs = ps.executeQuery();
            if(rs.next()) {
                var id = rs.getInt(ID_COL);
                var name = rs.getString(NAME_COL);
                var email = rs.getString(EMAIL_COL);
                return new User(id, name,email, login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user, String password) {
        try (var cn = ConnectionManager.getConnection();
            var psInsert = cn.prepareStatement(INSERT_USER);
            var psCheck = cn.prepareStatement(SELECT_USER_BY_LOGIN)){
            psCheck.setString(1, user.getLogin());
            if(isAccessible(psCheck)) {
                psInsert.setString(1, user.getName());
                psInsert.setString(2, user.getEmail());
                psInsert.setString(3, user.getLogin());
                psInsert.setString(4, password);
                return psInsert.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isAccessible(PreparedStatement ps) throws SQLException {
        return !ps.executeQuery().next();
    }

}
