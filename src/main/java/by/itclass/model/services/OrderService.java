package by.itclass.model.services;

import by.itclass.model.dao.OrderDao;
import by.itclass.model.entities.Order;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Objects;

public class OrderService {
    private static OrderService service;
    private OrderDao dao;

    public OrderService() {
        dao = OrderDao.getInstance();
    }

    public static OrderService getInstance() {
        if (Objects.isNull(service)) {
            service = new OrderService();
        }
        return service;
    }

    public boolean saveOrder(HttpSession session, String address) {
        return dao.saveOrder(session, address);
    }

    public List<Order> getOrders(int userId) {
        return dao.getOrders(userId);
    }
}
