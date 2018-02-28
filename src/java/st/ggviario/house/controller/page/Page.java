package st.ggviario.house.controller.page;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import st.ggviario.house.controller.HomeController;

import java.net.URL;

public interface Page {

    default void onSetRootPage(Node rootPage ) {}

    default void onSetRoot(Node root ) {}

    default  void setRootController(HomeController rootController) {}

    default void onBeforeAppend() {}

    default void onAfterAppend() {}

    default void onBeforeRemove() {}

    default void onAfterRemove(){ }

    default void accept(Stage primaryStage, Scene scene) { }

    default void onSearch(KeyEvent event, String textFilter ) { }

    static URL urlResource( String locatoion ){
        return URL.class.getResource( locatoion );
    }
}
