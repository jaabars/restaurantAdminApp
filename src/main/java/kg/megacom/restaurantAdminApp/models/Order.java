package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;
    private Date createDate;
    private double cost;

    private Customer customer;
}
