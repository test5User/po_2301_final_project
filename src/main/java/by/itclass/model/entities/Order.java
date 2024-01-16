package by.itclass.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Order {
    private final String id;
    private final String date;
    private int userId;
    private final String address;
}
