package by.itclass.controllers.order;

import by.itclass.controllers.abstraction.AbstractController;
import by.itclass.model.entities.FoodItem;
import by.itclass.model.entities.OrderItem;
import by.itclass.model.services.CartService;
import by.itclass.model.services.ServiceFactory;
import by.itclass.model.services.ServiceType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.ApplicationConstants.*;
import static by.itclass.constants.JspConstants.*;

@WebServlet(value = CART_CONTROLLER)
public class CartController extends AbstractController {
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = (CartService) ServiceFactory.getService(ServiceType.CART_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = Integer.parseInt(req.getParameter(FOOD_ID_PARAM));
        var foodType = Integer.parseInt(req.getParameter(FOOD_TYPE_PARAM));
        var foodName = req.getParameter(FOOD_NAME_PARAM);
        var foodPrice = Double.parseDouble(req.getParameter(FOOD_PRICE_PARAM));
        var foodQuant = Integer.parseInt(req.getParameter(FOOD_QUANTITY_PARAM));
        var cartAction = req.getParameter(CART_ACTION_PARAM);
        var foodItem = new FoodItem(id, foodType, foodName, foodPrice);
        var orderItem = new OrderItem(foodItem, foodQuant);
        var session = req.getSession();
        var items = cartService.processCart(session, cartAction, orderItem);
        session.setAttribute(ORDER_ITEMS_ATTR, items);
        if("addToCart".equals(cartAction)) {
            redirectToMenuPage(resp, foodType);
        } else {
            redirect(resp, CART_JSP);
        }
    }

    private void redirectToMenuPage(HttpServletResponse resp, int foodType) throws IOException {
        switch (foodType) {
            case 1 -> redirect(resp, PIZZAS_MENU);
            case 2 -> redirect(resp, DRINKS_MENU);
        }
    }
}
