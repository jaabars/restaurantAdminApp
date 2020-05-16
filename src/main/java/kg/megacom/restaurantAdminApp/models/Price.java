package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

import java.util.Date;

@Data
public class Price {
    private Long id;
    private double price;
    private Date startDate;
    private Date endDate;

    private Dish dish;
}
