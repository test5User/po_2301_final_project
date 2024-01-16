package by.itclass.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReceiptItem {
    private String foodName;
    private double foodPrice;
    private int quantity;
    private double itemAmount;
}
