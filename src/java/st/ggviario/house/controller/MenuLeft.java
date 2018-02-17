package st.ggviario.house.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import st.ggviario.house.cell.MenuItemCell;
import st.ggviario.house.model.MenuHeader;
import st.ggviario.house.model.MenuItem;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuLeft implements Initializable {

    @FXML
    ListView<MenuItem> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<MenuItem> list = loadMenus();
        listView.setItems( list );
        listView.setCellFactory(param -> new MenuItemCell());
    }

    private ObservableList<MenuItem> loadMenus() {
        List<MenuItem> menuItems = new LinkedList<>();
        menuItems.add( new MenuHeader() );
        return FXCollections.observableList( menuItems );
    }
}
