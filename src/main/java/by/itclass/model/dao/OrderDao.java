package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.*;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.itclass.constants.DbConstants.*;
import static by.itclass.constants.JspConstants.*;

public class OrderDao {
    private static OrderDao dao;

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
        try (var cn = ConnectionManager.getConnection();
            var psSaveOrder = cn.prepareStatement(INSERT_ORDER);
            var psSaveItem = cn.prepareStatement(INSERT_ORDER_ITEM)){

            cn.setAutoCommit(false);
            firstAction(order, psSaveOrder);
            var items = (List<OrderItem>)session.getAttribute(ORDER_ITEMS_ATTR);
            for (OrderItem item : items) {
                secondAction(order, item, psSaveItem);
            }
            cn.commit();
            session.setAttribute(ORDER_ID_ATTR, order.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void firstAction(Order order, PreparedStatement ps) throws SQLException {
        ps.setString(1, order.getId());
        ps.setString(2, order.getDate());
        ps.setInt(3, order.getUserId());
        ps.setString(4, order.getAddress());
        ps.executeUpdate();
    }

    private void secondAction(Order order, OrderItem item, PreparedStatement ps) throws SQLException {
        ps.setString(1, order.getId());
        ps.setInt(2, item.getFoodItem().getId());
        ps.setInt(3, item.getQuantity());
        ps.executeUpdate();
    }

    public List<Order> getOrders(int userId) {
        var orders = new ArrayList<Order>();
        try (var cn = ConnectionManager.getConnection();
            var ps = cn.prepareStatement(SELECT_ORDERS_BY_USER)){
            ps.setInt(1, userId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var id = rs.getString(ID_COL);
                var date = rs.getString(DATE_COL);
                var address = rs.getString(ADDRESS_COL);
                orders.add(new Order(id, date, userId, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Receipt buildReceipt(String orderId) {
        var receipt = new Receipt();
        try (var cn = ConnectionManager.getConnection();
            var ps = cn.prepareStatement(SELECT_HEAD_FOR_ORDER);
            var ps1 = cn.prepareStatement(SELECT_ITEMS_FOR_ORDER)){
            ps.setString(1, orderId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                var date = rs.getString(DATE_COL);
                var address = rs.getString(ADDRESS_COL);
                receipt.setOrder(new Order(orderId, date, address));
                var receiptItems = getItemsForReceipt(orderId, ps1);
                receipt.setReceiptItems(receiptItems);
                receipt.setTotal(Math.round(getTotalAmount(receiptItems)*100)/100d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receipt;
    }

    private List<ReceiptItem> getItemsForReceipt(String orderId, PreparedStatement ps) throws SQLException {
        var items = new ArrayList<ReceiptItem>();
        ps.setString(1, orderId);
        var rs = ps.executeQuery();
        while (rs.next()) {
            var foodName = rs.getString(NAME_COL);
            var foodPrice = rs.getDouble(PRICE_COL);
            var quantity = rs.getInt(QUANTITY_COL);
            var amount = foodPrice * quantity;
            items.add(new ReceiptItem(foodName, foodPrice, quantity, amount));
        }
        return items;
    }

    private double getTotalAmount(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::getItemAmount)
                .reduce(0d, Double::sum);
    }
}
