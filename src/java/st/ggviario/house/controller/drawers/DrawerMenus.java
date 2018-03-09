package st.ggviario.house.controller.drawers;

import com.jfoenix.controls.JFXRippler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import st.ggviario.house.controller.HomeController;
import st.ggviario.house.controller.pages.Page;
import st.ggviario.house.factory.MenuItemCell;
import st.ggviario.house.model.DrawerHeader;
import st.ggviario.house.model.Menu;
import st.ggviario.house.model.MenuItem;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class DrawerMenus implements Page,  Initializable {


    @FXML
    ListView<Menu> listView;

    @FXML
    private AnchorPane iconMenu;

    @FXML
    private HBox iconMenuArea;

    private HomeController homeController;
    private OnClickMenuIcon onClickMenuIcon;
    private JFXRippler rippler;
    private List<Menu> menuList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.menuList = new LinkedList<>();
        this.structure();
        this.defineEvents();
        this.loadDataMenus();
        if( this.menuList.size() > 1 ){
            this.menuList.get( 1 ).setClickMe( true );
        }
        this.pushAll();
    }

    private void structure( ){
        this.rippler = new JFXRippler( this.iconMenu );
        this.iconMenuArea.getChildren().add( 0, rippler );
        this.listView.setCellFactory(param -> new MenuItemCell( homeController ) );

    }

    private void defineEvents(){
        this.rippler.setOnMouseClicked(event -> {
            if( this.onClickMenuIcon != null ) this.onClickMenuIcon.onClickMenuIcon();
        });

    }

    private void pushAll(){
        this.pushMenu( this.menuList );
    }

    private void pushMenu(List< Menu > menuList ){
        this.listView.setItems( FXCollections.observableList( menuList ));
    }

    public void setOnClickMenuIcon( OnClickMenuIcon onClickMenuIcon  ){
        this.onClickMenuIcon = onClickMenuIcon;
    }

    private void loadDataMenus() {
        this.menuList.add( new DrawerHeader() );
        this.menuList.add( new MenuItem("Produto", getClass().getResource("/fxml/page/page_producao.fxml") ) );
        this.menuList.add( new MenuItem("Venda de despesa", getClass().getResource("/fxml/page/page_vendadespesa.fxml") ) );
        this.menuList.add( new MenuItem("Pessoal", getClass().getResource("/fxml/page/page_pessoal.fxml") ) );
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public interface OnClickMenuIcon {
        void onClickMenuIcon ( );
    }

}
