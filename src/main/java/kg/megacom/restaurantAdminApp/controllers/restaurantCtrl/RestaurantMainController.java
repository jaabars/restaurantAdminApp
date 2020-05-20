package kg.megacom.restaurantAdminApp.controllers.restaurantCtrl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import kg.megacom.restaurantAdminApp.models.Restaurant;
import kg.megacom.restaurantAdminApp.okHttp.restaurantHttpHelper.RestaurantOkHttp;

public class RestaurantMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem mnAdd;

    @FXML
    private MenuItem mnEdit;

    @FXML
    private MenuItem mnDeactivate;

    @FXML
    private MenuItem mnHelp;

    @FXML
    private TableView<Restaurant> tblViewRestaurants;

    @FXML
    private TableColumn<Restaurant, Integer> colmnNum;

    @FXML
    private TableColumn<Restaurant, String> colmName;

    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mnAdd)) {
            Stage stage = new Stage();
            stage.setTitle("Restaurant add window ");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/restaurantAddForm.fxml"));
            try {
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        try {
                            refreshTable();
                            tblViewRestaurants.refresh();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        } else if (event.getSource().equals(mnEdit)) {
            Stage stage = new Stage();
            stage.setTitle("Restaurant edit form");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/restaurantEditForm.fxml"));
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));

                RestaurantEditController controller = loader.getController();
                Restaurant restaurant = tblViewRestaurants.getSelectionModel().getSelectedItem();
                controller.initData(stage, restaurant);
                stage.setOnCloseRequest(eventClose -> {
                    try {
                        tblViewRestaurants.refresh();
                        refreshTable();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        } else if (event.getSource().equals(mnDeactivate)) {
            onDeactivateClicked();
        }
    }

    @FXML
    void initialize() throws IOException {

        tblViewRestaurants.setRowFactory(param -> new TableRow<Restaurant>(){
            @Override
            protected void updateItem(Restaurant item,boolean empty){
                super.updateItem(item, empty);
                if (empty|| item == null){
                    setStyle("-fx-control-inner-background: derive(-fx-base,80%);");
                }else{
                    if(!item.isActive()){
                        setStyle("-fx-control-inner-background: red");
                    }else {
                        setStyle("-fx-control-inner-background: derive(-fx-base,80%);");
                    }
                }
            }
        });
        refreshTable();
        initData();
    }


    private void initData() {
        colmnNum.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void refreshTable() throws IOException {
        List<Restaurant> restaurantList = RestaurantOkHttp.getInstance().getRestaurants();
        tblViewRestaurants.setItems(FXCollections.observableArrayList(restaurantList));
    }



    private void onDeactivateClicked() {
        Restaurant restaurant = tblViewRestaurants.getSelectionModel().getSelectedItem();
        try {
            restaurant.setActive(false);
            RestaurantOkHttp.getInstance().updateRestaurant(restaurant);
            tblViewRestaurants.refresh();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }