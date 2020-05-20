package kg.megacom.restaurantAdminApp.controllers.menuCtrl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import kg.megacom.restaurantAdminApp.models.Menu;

import kg.megacom.restaurantAdminApp.okHttp.menuHttpHelper.MenuOkHttp;

public class MenuMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem mnAdd;

    @FXML
    private MenuItem mnEdit;



    @FXML
    private TableView<Menu> tblViewMenu;

    @FXML
    private TableColumn<Menu, Long> colmMenuId;

    @FXML
    private TableColumn<Menu, String> colmResName;
    @FXML
    void onMenuItmClicked(ActionEvent event) {
        if(event.getSource().equals(mnAdd)){
            Stage stage = new Stage();
            stage.setTitle("Menu add window");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/menuAddForm.fxml"));
            try {
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        try {
                            tblViewMenu.refresh();
                            refreshTableView();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        }else if(event.getSource().equals(mnEdit)){
            Stage stage = new Stage();
            stage.setTitle("Menu edit window");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/menuEditForm.fxml"));
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));

                MenuEditCtrl controller = loader.getController();
                Menu menu = tblViewMenu.getSelectionModel().getSelectedItem();
                controller.initDataToEdit(stage, menu);
                stage.setOnCloseRequest(eventClose -> {
                    try {
                        tblViewMenu.refresh();
                        refreshTableView();
                    } catch (IOException e) {
                        e.printStackTrace();
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
      initData();
      refreshTableView();

    }

    private void refreshTableView() throws IOException {
        List<Menu> menuList = MenuOkHttp.getInstance().getMenus();
        colmResName.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getRestaurant().getName()));
        tblViewMenu.setItems(FXCollections.observableArrayList(menuList));
    }

    private void initData() {
        colmMenuId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmResName.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
    }
}
