package kg.megacom.restaurantAdminApp.models;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private String login;
    private String password;
}
