package kg.megacom.restaurantAdminApp.controllers.dishCtrl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import kg.megacom.restaurantAdminApp.models.Dish;
import kg.megacom.restaurantAdminApp.models.Menu;
import kg.megacom.restaurantAdminApp.okHttp.dishHttpHelper.DishOkHttp;
import lombok.SneakyThrows;

public class DishMainFormCtrl {

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
    private TableView<Dish> tblViewDish;

    @FXML
    private TableColumn<Dish, Integer> colmNum;

    @FXML
    private TableColumn<Dish, String> colmName;

    @FXML
    private TableColumn<Dish, Double> colmSize;

    @FXML
    private TableColumn<Dish, String> colmMenu;

    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mnAdd)){
            Stage stage = new Stage();
            stage.setTitle("Dish add window");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/dishAddForm.fxml"));
            try {
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        try {
                            tblViewDish.refresh();
                            refreshTableDish();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        }else if(event.getSource().equals(mnEdit)){
            Stage stage = new Stage();
            stage.setTitle("Dish edit window");
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/dishEditForm.fxml"));
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
                DishEditCtrl dishEditCtrl = loader.getController();
                Dish dish = tblViewDish.getSelectionModel().getSelectedItem();
                dishEditCtrl.initDataToEdit(stage,dish);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @SneakyThrows
                    @Override
                    public void handle(WindowEvent event) {
                        tblViewDish.refresh();
                        refreshTableDish();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        }else if(event.getSource().equals(mnDeactivate)) {
            onMnDeactivatedClicked();
        }
    }

    private void onMnDeactivatedClicked() {
        Dish dish = tblViewDish.getSelectionModel().getSelectedItem();
        dish.setActive(false);
        try {
            DishOkHttp.getInstance().updateDish(dish);
            tblViewDish.refresh();
            refreshTableDish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() throws IOException {
        initData();
        refreshTableDish();
    }

    private void refreshTableDish() throws IOException {
        List<Dish> dishList = DishOkHttp.getInstance().getDishes();
        colmMenu.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getMenu().getRestaurant().getName()));
        tblViewDish.setItems(FXCollections.observableArrayList(dishList));


    }

    private void initData() {
        colmNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colmMenu.setCellValueFactory(new PropertyValueFactory<>("menu"));
    }
}
