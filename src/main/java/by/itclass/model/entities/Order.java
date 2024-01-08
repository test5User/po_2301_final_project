package by.itclass.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {
    private String id;
    private String date;
    int userId;
    private String address;
}
