package st.ggviario.house.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import st.ggviario.house.factory.MenuItemCell;
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
        menuItems.add( new MenuItem("Dasborad", getClass().getResource("/fxml/dasborad.fxml") ) );
        menuItems.add( new MenuItem("Clientes", getClass().getResource("/fxml/cliente.fxml") ) );
        menuItems.add( new MenuItem("Dividas", getClass().getResource("/fxml/venda_divida.fxml") ) );
        menuItems.add( new MenuItem("Vendas", getClass().getResource("/fxml/venda_venda.fxml") ) );
        menuItems.add( new MenuItem("Despesas", getClass().getResource("/fxml/venda_divida.fxml") ) );
        menuItems.add( new MenuItem("Produtos", getClass().getResource("/fxml/produto.fxml") ) );
        menuItems.add( new MenuItem("Produção", getClass().getResource("/fxml/producao.fxml") ) );
        menuItems.add( new MenuItem("Contas", getClass().getResource("/fxml/contas.fxml") ) );
        menuItems.add( new MenuItem("Movimetaçẽos", getClass().getResource("/fxml/movimentacoes.fxml") ) );
        menuItems.add( new MenuItem("Despesas", getClass().getResource("/fxml/despesas.fxml") ) );
        menuItems.add( new MenuItem("Colaboradores", getClass().getResource("/fxml/colaborador.fxml") ) );
        return FXCollections.observableList( menuItems );
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
