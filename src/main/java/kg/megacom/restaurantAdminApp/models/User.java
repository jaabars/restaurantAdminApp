package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long id;
    private String name;
    private boolean active;

    private Position position;

    private Account account;

    private List<Phone> phones;
}
