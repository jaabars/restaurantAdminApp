package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetails {
    private Long id;

    private Order order;

    private Dish dish;
    private int amount;
}
