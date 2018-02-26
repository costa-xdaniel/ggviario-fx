package st.ggviario.house.controller.page;

import javafx.scene.Node;

import java.net.URL;

public interface Page {

    default void onSetRootPage(Node rootPage ) {}

    default void onSetRoot(Node root ) {}

    default  void setRootController(HomeController rootController) {}

    default void onBeforeAppend() {}

    default void onAfterAppend() {}

    default void onBeforeRemove() {}

    default void onAfterRemove(){ }

    static URL urlResource( String locatoion ){
        return URL.class.getResource( locatoion );
    }
}
