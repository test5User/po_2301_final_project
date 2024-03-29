package by.itclass.model.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static Map<ServiceType, Service> services;

    public static void init() {
        services = new HashMap<>();
        services.put(ServiceType.CART_SERVICE, new CartService());
        services.put(ServiceType.FOOD_SERVICE, new FoodService());
        services.put(ServiceType.ORDER_SERVICE, new OrderService());
        services.put(ServiceType.USER_SERVICE, new UserService());
    }

    public static Service getService(ServiceType serviceType) {
        return services.get(serviceType);
    }
}
