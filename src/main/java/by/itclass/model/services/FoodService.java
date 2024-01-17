package by.itclass.model.services;

import by.itclass.model.dao.FoodDao;
import by.itclass.model.entities.FoodItem;

import java.util.List;
import java.util.Objects;

public class FoodService implements Service  {
    private FoodDao dao;

    public FoodService() {
        dao = new FoodDao();
    }

    public List<FoodItem> getFoodItemsByType(int foodType) {
        return dao.getFoodItemsByType(foodType);
    }
}
