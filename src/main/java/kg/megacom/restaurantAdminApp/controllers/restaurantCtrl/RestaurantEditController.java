package kg.megacom.restaurantAdminApp.controllers.restaurantCtrl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import kg.megacom.restaurantAdminApp.models.Restaurant;
import kg.megacom.restaurantAdminApp.okHttp.restaurantHttpHelper.RestaurantOkHttp;

public class RestaurantEditController {
    Stage stage;
    Restaurant restaurant;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        if (event.getSource().equals(btnSave)){
            onSaveButtonClicked();
        }else {
            Window stage = btnClose.getScene().getWindow();
            stage.hide();
        }
    }

    private void onSaveButtonClicked() {
        restaurant.setName(txtName.getText());
        restaurant.setActive(chkBoxActive.isSelected());
        try {
            RestaurantOkHttp.getInstance().updateRestaurant(restaurant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
    public void initData(Stage stage, Restaurant restaurant) {
        this.stage=stage;

        if(restaurant!=null){
            this.restaurant=restaurant;
            txtName.setText(restaurant.getName());
            chkBoxActive.setSelected(restaurant.isActive());


        }else {
            this.restaurant = new Restaurant();
        }
    }
}
