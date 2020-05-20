package kg.megacom.restaurantAdminApp.controllers.menuCtrl;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import javafx.stage.Window;
import kg.megacom.restaurantAdminApp.models.Menu;
import kg.megacom.restaurantAdminApp.models.Restaurant;
import kg.megacom.restaurantAdminApp.okHttp.menuHttpHelper.MenuOkHttp;
import kg.megacom.restaurantAdminApp.okHttp.restaurantHttpHelper.RestaurantOkHttp;

public class MenuEditCtrl {
    Stage stage;
    Menu menu;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Restaurant> chkBoxRestaurant;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            onSaveButtonClicked();
        }else{
            Window scene = btnCancel.getScene().getWindow();
            scene.hide();
        }

    }

    private void onSaveButtonClicked() {
        menu.setId(menu.getId());
        menu.setRestaurant(chkBoxRestaurant.getValue());
        menu.setCreateDate(new Date());
        try {
            MenuOkHttp.getInstance().updateMenu(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() throws IOException {
      initData();

    }
    private void initData() throws IOException {
        chkBoxRestaurant.setCellFactory(param -> new ListCell<Restaurant>(){
            @Override
            protected void updateItem(Restaurant item, boolean empty){
                super.updateItem(item,empty);
                if(item!=null&&!empty){
                    setText(item.getName());
                }else{
                    setText(null);
                }
            }
        });
        chkBoxRestaurant.setItems(FXCollections.observableArrayList(RestaurantOkHttp.getInstance().getRestaurants()));

    }
    public void initDataToEdit(Stage stage, Menu menu) {
        this.stage=stage;

        if(menu!=null){
            this.menu=menu;
           menu.setId(menu.getId());
           menu.setRestaurant(menu.getRestaurant());
           chkBoxRestaurant.setValue(menu.getRestaurant());


        }else {
            this.menu = new Menu();
        }
    }
}
