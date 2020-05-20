package kg.megacom.restaurantAdminApp.controllers.priceCtrl;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import kg.megacom.restaurantAdminApp.models.Price;
import kg.megacom.restaurantAdminApp.okHttp.priceHttpHelper.PriceOkHttp;
import lombok.SneakyThrows;

public class PriceMainFormCtrl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<Price> tblViewPrice;

    @FXML
    private TableColumn<Price, Double> colmPrice;

    @FXML
    private TableColumn<Price, Date> colmStartDate;

    @FXML
    private TableColumn<Price, Date> colmEndDate;

    @FXML
    private TableColumn<Price, String> colmDish;

    @FXML
    private MenuItem mnAdd;

    @FXML
    private MenuItem mnEdit;

    @FXML
    void onMenuItmClicked(ActionEvent event) {
        if (event.getSource().equals(mnAdd)){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/priceAddForm.fxml"));
            try {
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @SneakyThrows
                    @Override
                    public void handle(WindowEvent event) {
                        tblViewPrice.refresh();
                        refreshTableView();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        }

    }

    @FXML
    void initialize() throws IOException {
        initTableColumns();
        refreshTableView();

    }

    private void refreshTableView() throws IOException {
        List<Price> priceList = PriceOkHttp.getInstance().getPriceList();
        colmDish.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDish().getName()));
        tblViewPrice.setItems(FXCollections.observableArrayList(priceList));
    }

    private void initTableColumns() {
        colmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colmEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colmDish.setCellValueFactory(new PropertyValueFactory<>("dish"));

    }
}
