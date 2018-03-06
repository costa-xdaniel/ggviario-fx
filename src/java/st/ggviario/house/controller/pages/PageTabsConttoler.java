package st.ggviario.house.controller.pages;

import com.jfoenix.controls.JFXTabPane;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;

import java.util.LinkedList;
import java.util.List;

public abstract class PageTabsConttoler implements Page {

    private StackPane rootPage;
    private List< ControllerLoader < Node, PageTab > > loaders = new LinkedList<>();

    void addTab(Tab tab, String urlContent ){
        ControllerLoader < Node, PageTab > loader = new ControllerLoader<>(urlContent);
        tab.setContent( loader.getNodeView() );
        this.getTabPane().getTabs().add( tab );
        if( this.rootPage != null ) loader.getController().onSetRootPage( this.rootPage );
        this.loaders.add( loader );
    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
        this.loaders.forEach( item -> item.getController().onSetRootPage( rootPage ));
    }

    abstract JFXTabPane getTabPane();

}
