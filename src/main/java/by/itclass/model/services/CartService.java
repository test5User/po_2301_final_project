package by.itclass.model.services;

import by.itclass.model.entities.OrderItem;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.itclass.constants.JspConstants.ORDER_ITEMS_ATTR;

public class CartService implements Service {
    public List<OrderItem> processCart(HttpSession session, String cartAction,
                                       OrderItem item) {
        var orderItems = session.getAttribute(ORDER_ITEMS_ATTR);
        var items = Objects.nonNull(orderItems)
                ? (List<OrderItem>) orderItems
                : new ArrayList<OrderItem>();
        switch (cartAction) {
            case "addToCart" -> items.add(item);
            case "removeFromCart" -> items.remove(item);
        }
        return items;
    }
}
