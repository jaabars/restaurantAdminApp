package kg.megacom.restaurantAdminApp.controllers.restaurantCtrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import kg.megacom.restaurantAdminApp.models.Restaurant;
import kg.megacom.restaurantAdminApp.okHttp.restaurantHttpHelper.RestaurantOkHttp;

import java.io.IOException;

public class RestaurantAddController {

    @FXML
    private TextField txtName;

    @FXML
    private CheckBox chkBoxActive;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnClose;

    @FXML
    void onBtnClicked(ActionEvent event) {
        if(event.getSource().equals(btnSave)){
            onSaveButtonClicked();
        }else {
            txtName.clear();
            chkBoxActive.setSelected(false);
        }
    }

    private void onSaveButtonClicked() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(txtName.getText());
        restaurant.setActive(chkBoxActive.isSelected());
        try {
            RestaurantOkHttp.getInstance().postRestaurant(restaurant);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
