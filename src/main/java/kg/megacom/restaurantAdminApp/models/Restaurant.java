package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

@Data
public class Restaurant {
    private Long id;
    private String name;
    private boolean active;
}
