package kg.megacom.restaurantAdminApp.models;

import kg.megacom.restaurantAdminApp.enums.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderHistory {
    private Long id;
    private Date startDate;
    private Date endDate;

    private Order order;

    private User user;

    private OrderStatus orderStatus;
}
