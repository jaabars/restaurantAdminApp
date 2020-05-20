package kg.megacom.restaurantAdminApp.controllers.adminMainCtrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMainFormCtrl {

    @FXML
    private Button btnRestaurant;
    @FXML
    private Button btnPrice;

    @FXML
    private Button btnMenu;
    @FXML
    private Button btnDish;

    @FXML
    void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(btnRestaurant)){
            Stage stage = new Stage();
            stage.setTitle("Restaurants view window");
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/layouts/restaurantMainForm.fxml"));
            try {
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        }else if (event.getSource().equals(btnMenu)){
            Stage stage = new Stage();
            stage.setTitle("Menus view window");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/menuMainForm.fxml"));
            try {
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        }else if(event.getSource().equals(btnDish)){
            Stage stage = new Stage();
            stage.setTitle("Dishes view window");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/dishMainForm.fxml"));
            try {
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        }else if(event.getSource().equals(btnPrice)){
            Stage stage = new Stage();
            stage.setTitle("Prices view window");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/priceMainForm.fxml"));
            try {
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        }

    }

}
