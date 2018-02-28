package st.ggviario.house.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import st.ggviario.house.controller.page.Page;
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
    private MenuItem menuItem;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

        item.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
            this.load();
            this.rootController.setDocumentRoot( this.nodeContent, this.page );
        });
    }

    private void load() {
        if( this.page == null ){
            ControllerLoader< Node, Page > loader = new ControllerLoader<>(this.menuItem.getContentPageLink() );
            this.page = loader.getViewController().getController();
            this.nodeContent = loader.getViewController().getNodeView();
            this.page.onSetRoot( this.rootController.getRoot() );
            this.page.onSetRootPage( this.rootController.getRootPage() );
            this.page.accept( this.rootController.getPrimaryStage(), this.rootController.getScene() );
            this.page.setRootController( this.rootController );
        }
    }

    @Override
    public void setMenu( Menu menu) {
        this.menuItem = (MenuItem) menu;
        this.itemAction.setText( menuItem.getName() );
    }

    @Override
    public void setRootController(HomeController rootController) {
        this.rootController = rootController;
    }
}
