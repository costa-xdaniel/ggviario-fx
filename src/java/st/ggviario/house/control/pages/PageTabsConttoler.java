package st.ggviario.house.control.pages;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.tabs.TabPage;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class PageTabsConttoler implements Page, Initializable {

    private StackPane rootPage;
    private List< ItemTab > loaders = new LinkedList<>();

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        this.getTabPane().getSelectionModel().selectedIndexProperty().addListener((observableValue, oldIndex, newIndex) -> {
            ItemTab oldTabe = null;
            ItemTab newTabe = null ;

            if( oldIndex != null && oldIndex.intValue() >= 0 )
                oldTabe = this.loaders.get( oldIndex.intValue() );

            if( newIndex != null && newIndex.intValue() >= 0 )
                newTabe = this.loaders.get( newIndex.intValue() );

            this.onChangeTab( oldTabe, newTabe );
        });
    }

    private void onChangeTab( ItemTab oldTab, ItemTab newTab ){


        if( oldTab != null ){
            oldTab.tabPage.onBeforeClose();
            oldTab.tabPage.onAfterClose();
        }
        if( newTab != null ){
            loadPage(newTab);
            newTab.tabPage.onBeforeOpen();
            newTab.tabPage.onAfterOpen();
        }
    }

    private void loadPage(ItemTab newTab) {
        if( newTab.tabPage == null ){
            ControllerLoader< Node, TabPage > loader = new ControllerLoader<>( newTab.controller );
            newTab.node = loader.getNodeView();
            newTab.tabPage = loader.getController();
            newTab.tab.setContent( loader.getNodeView() );
            if( this.rootPage != null  )
                loader.getController().onSetRootPage( this.rootPage );
        }
    }

    @Override
    public void onAfterClose() {
        this.loaders.forEach( item -> item.tabPage.onAfeterCloseRootPage() );
    }

    @Override
    public void onAfterOpen() {
        this.loaders.forEach( item -> item.tabPage.onAfterOpenRootPage() );
    }

    @Override
    public void onBeforeClose() {
        this.loaders.forEach( item -> item.tabPage.onBeforeCloseRootPage() );
    }

    @Override
    public void onBeforeOpen() {
        this.loaders.forEach( item -> item.tabPage.onBeforeOpenRootPage() );
    }

    void addTab( String tabName, String urlContent ){
        Tab tab = new Tab( tabName );
        this.loaders.add(new ItemTab( tab, urlContent ));
        this.getTabPane().getTabs().add( tab );
    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
        this.loaders.forEach( item -> {
            this.loadPage( item );
            item.tabPage.onSetRootPage( rootPage );
        });
    }

    abstract JFXTabPane getTabPane();

    private class ItemTab{
        private Tab tab;
        private TabPage tabPage;
        private Node node;
        private String controller;

        ItemTab( Tab tab, String controller ) {
            this.tab = tab;
            this.controller = controller;
        }
    }

}
