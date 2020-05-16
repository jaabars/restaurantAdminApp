package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String address;
    private String houseNo;
    private int flatNo;
    private String phone;
}
