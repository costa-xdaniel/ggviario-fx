package st.ggviario.house.controller.page;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private HomeController rootController;
    private Node nodeContent;
    private Page page;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        item.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> this.rootController.setDocumentRoot( this.nodeContent, this.page ));
    }

    @Override
    public void setMenu( Menu menu) {
        MenuItem menuItem = (MenuItem) menu;
        this.itemAction.setText( menuItem.getName() );

        try{
            FXMLLoader loader = new FXMLLoader( menuItem.getContentPageLink() );
            this.nodeContent = loader.load();
            this.page = loader.getController();
            this.page.onSetRoot( this.rootController.getRoot() );
            this.page.onSetRootPage( this.rootController.getRootPage() );
            this.page.setRootController( this.rootController );
        } catch ( Exception ex ){
            System.out.println( menu );
            ex.printStackTrace();

        }
    }

    @Override
    public void setRootController(HomeController rootController) {
        this.rootController = rootController;
    }
}
