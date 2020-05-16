package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

import java.util.Date;

@Data
public class RestaurantOrders {
    private Long id;

    private Restaurant restaurant;


    private Order order;
    private boolean isReady;
    private Date startDate;
    private Date endDate;
}
