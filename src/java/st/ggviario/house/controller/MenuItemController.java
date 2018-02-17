package st.ggviario.house.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import st.ggviario.house.model.ContentPage;
import st.ggviario.house.model.DrawerItem;
import st.ggviario.house.model.Menu;
import st.ggviario.house.model.MenuItem;


import java.net.URL;
import java.util.ResourceBundle;

public class MenuItemController implements DrawerItem, Initializable {

    @FXML
    private AnchorPane item;

    @FXML
    private Label itemAction;
    private HomeController homeController;
    private Node nodeContent;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

        item.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.homeController.setDocumentRoot( this.nodeContent );
        });
    }

    @Override
    public void setMenu(Menu menu) {
        MenuItem menuItem = (MenuItem) menu;
        this.itemAction.setText( menuItem.getName() );

        try{
            FXMLLoader loader = new FXMLLoader( menuItem.getContentPageLink() );
            this.nodeContent = loader.load();

        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    @Override
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
