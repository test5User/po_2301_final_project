package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.Order;
import by.itclass.model.entities.User;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static by.itclass.constants.JspConstants.*;

public class OrderDao {
    private static OrderDao dao;

    private OrderDao() {
        ConnectionManager.init();
    }

    public static OrderDao getInstance() {
        if (Objects.isNull(dao)) {
            dao = new OrderDao();
        }
        return dao;
    }

    public boolean saveOrder(HttpSession session, String address) {
        var user = (User) session.getAttribute(USER_ATTR);
        var now = LocalDateTime.now();
        var date = now.format(DateTimeFormatter.ofPattern("y-MM-dd"));
        var time = now.format(DateTimeFormatter.ofPattern("HH-mm"));
        var orderId = String.join("-", user.getName(), date, time);
        return saveOrderToDb(new Order(orderId, date, user.getId(), address), session);
    }

    private boolean saveOrderToDb(Order order, HttpSession session) {

    }
}
