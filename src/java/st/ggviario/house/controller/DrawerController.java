package st.ggviario.house.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import st.ggviario.house.cell.MenuItemCell;
import st.ggviario.house.model.DrawerHeader;
import st.ggviario.house.model.Menu;
import st.ggviario.house.model.MenuItem;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class DrawerController implements Initializable {

    @FXML
    ListView<Menu> listView;
    private HomeController homeController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Menu> list = loadMenus();
        listView.setItems( list );
        listView.setCellFactory(param -> new MenuItemCell( homeController ) );


    }

    private ObservableList<Menu> loadMenus() {
        List<Menu> menuItems = new LinkedList<>();
        menuItems.add( new DrawerHeader() );
        menuItems.add( new MenuItem("Dasborad", getClass().getResource("/fxml/produto.fxml") ) );
        menuItems.add( new MenuItem("Compra", getClass().getResource("/fxml/produto.fxml") ) );
        menuItems.add( new MenuItem("Divida", getClass().getResource("/fxml/produto.fxml") ) );
        menuItems.add( new MenuItem("Produtos", getClass().getResource("/fxml/produto.fxml") ) );
        menuItems.add( new MenuItem("Colaboradores", getClass().getResource("/fxml/produto.fxml") ) );
        return FXCollections.observableList( menuItems );
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
