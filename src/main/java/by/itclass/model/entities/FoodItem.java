package by.itclass.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FoodItem {
    private int id;
    private int type;
    private String name;
    private double price;
}
