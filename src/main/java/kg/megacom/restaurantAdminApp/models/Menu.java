package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

import java.util.Date;

@Data
public class Menu {
    private Long id;

    private Restaurant restaurant;
    private Date createDate;
}
