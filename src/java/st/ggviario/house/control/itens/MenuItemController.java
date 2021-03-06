package st.ggviario.house.control.itens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.HomeController;
import st.ggviario.house.control.pages.Page;
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
            onClickMenu();
        });
    }

    private void onClickMenu(){
        this.load();
        this.rootController.setDocumentRoot( this.nodeContent, this.page );
    }

    private void load() {
        if( this.page == null ){
            ControllerLoader< Node, Page > loader = new ControllerLoader<>(this.menuItem.getContentPageLink() );
            this.page = loader.getController();
            this.nodeContent = loader.getNodeView();
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
        if( menuItem.isClickMe() ){
            this.onClickMenu();
        }
    }

    @Override
    public void setRootController(HomeController rootController) {
        this.rootController = rootController;
    }
}
