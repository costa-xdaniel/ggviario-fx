package st.ggviario.house.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import st.ggviario.house.controller.page.Page;
import st.ggviario.house.factory.MenuItemCell;
import st.ggviario.house.model.DrawerHeader;
import st.ggviario.house.model.Menu;
import st.ggviario.house.model.MenuItem;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class DrawerController implements Page,  Initializable {

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
        menuItems.add( new MenuItem("Dividas", getClass().getResource("/fxml/venda_divida.fxml") ) );
        menuItems.add( new MenuItem("Venda", getClass().getResource("/fxml/venda_venda.fxml") ) );
        menuItems.add( new MenuItem("Clientes", getClass().getResource("/fxml/cliente.fxml") ) );
        menuItems.add( new MenuItem("Produtos", getClass().getResource("/fxml/produto.fxml") ) );
        menuItems.add( new MenuItem("Fornecedor", getClass().getResource("/fxml/fornecedor.fxml") ) );
        return FXCollections.observableList( menuItems );
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

}
