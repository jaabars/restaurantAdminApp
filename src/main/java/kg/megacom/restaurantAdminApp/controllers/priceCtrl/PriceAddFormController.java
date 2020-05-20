package kg.megacom.restaurantAdminApp.controllers.priceCtrl;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import kg.megacom.restaurantAdminApp.models.Dish;
import kg.megacom.restaurantAdminApp.models.Price;
import kg.megacom.restaurantAdminApp.okHttp.dishHttpHelper.DishOkHttp;
import kg.megacom.restaurantAdminApp.okHttp.priceHttpHelper.PriceOkHttp;

public class PriceAddFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPrice;

    @FXML
    private DatePicker dtPickerStart;

    @FXML
    private DatePicker dtPickerEnd;

    @FXML
    private ComboBox<Dish> comboBoxDish;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;


    @FXML
    void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            onSaveButtonClicked();
        }
    }

    private void onSaveButtonClicked() {
        Price price = new Price();
        price.setPrice(Double.parseDouble(txtPrice.getText()));
        Date startDate = Date.from(dtPickerStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        price.setStartDate(startDate);
        Date endDate = Date.from(dtPickerEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        price.setEndDate(endDate);
        price.setDish(comboBoxDish.getSelectionModel().getSelectedItem());
        try {
            PriceOkHttp.getInstance().postPrice(price);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() throws IOException {
       initComboBox();

    }

    private void initComboBox() throws IOException {
        List<Dish> dishList = DishOkHttp.getInstance().getDishes();
        comboBoxDish.setCellFactory(p->new ListCell<Dish>(){
            @Override
            protected void updateItem(Dish item,boolean empty){
                super.updateItem(item,empty);
                if (item!=null&&!empty){
                    setText(item.getName());
                }else{
                    setText(null);
                }
            }
        });
        comboBoxDish.setItems(FXCollections.observableArrayList(dishList));
    }
}
