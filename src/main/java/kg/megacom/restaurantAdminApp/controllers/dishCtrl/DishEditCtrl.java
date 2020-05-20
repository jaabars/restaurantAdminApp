package kg.megacom.restaurantAdminApp.controllers.dishCtrl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import kg.megacom.restaurantAdminApp.models.Dish;
import kg.megacom.restaurantAdminApp.models.Menu;
import kg.megacom.restaurantAdminApp.okHttp.dishHttpHelper.DishOkHttp;
import kg.megacom.restaurantAdminApp.okHttp.menuHttpHelper.MenuOkHttp;

public class DishEditCtrl {
    Stage stage;
    Dish dish;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSize;

    @FXML
    private ComboBox<Menu> combBoxMenu;

    @FXML
    private Button btnFileChooser;

    @FXML
    private ImageView imgView;

    @FXML
    private CheckBox checkBoxActive;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            onSaveButtonClicked();
        }else  if(event.getSource().equals(btnFileChooser)){
            Window window = btnFileChooser.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(window);
            Image image = new Image(file.toURI().toString());
            imgView.setImage(image);
            dish.setImgPath(file.getAbsolutePath());
        }

    }

    private void onSaveButtonClicked() {
        dish.setName(txtName.getText());
        dish.setMenu(combBoxMenu.getSelectionModel().getSelectedItem());
        dish.setSize(Double.parseDouble(txtSize.getText()));
        dish.setActive(checkBoxActive.isSelected());
        try {
            DishOkHttp.getInstance().updateDish(dish);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() throws IOException {
       initComboBox();

    }
    public void initDataToEdit(Stage stage, Dish dish){
        this.stage = stage;
        if(dish!=null){
            this.dish = dish;
            txtName.setText(dish.getName());
            txtSize.setText(String.valueOf(dish.getSize()));
            combBoxMenu.setValue(dish.getMenu());
            FileChooser fileChooser =new FileChooser();
            fileChooser.setInitialDirectory(new File(dish.getImgPath()));
            imgView.setImage(new Image(fileChooser.getInitialDirectory().toURI().toString()));
            checkBoxActive.setSelected(dish.isActive());

        }
    }
    private void initComboBox() throws IOException {
        combBoxMenu.setCellFactory(param -> new ListCell<Menu>(){
            @Override
            protected void updateItem(Menu item, boolean empty){
                super.updateItem(item,empty);
                if(item!=null&&!empty){
                    setText(item.getRestaurant().getName());
                }else{
                    setText(null);
                }
            }
        });
        combBoxMenu.setItems(FXCollections.observableArrayList(MenuOkHttp.getInstance().getMenus()));
    }
}
