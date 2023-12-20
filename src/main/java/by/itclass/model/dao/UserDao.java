package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.User;

import java.sql.SQLException;
import java.util.Objects;

import static by.itclass.constants.DbConstants.*;

public class UserDao {
    private static UserDao dao;

    private UserDao() {
        ConnectionManager.init();
    }

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
        return false;
    }
}
