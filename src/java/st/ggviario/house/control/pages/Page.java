package st.ggviario.house.control.pages;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import st.ggviario.house.control.HomeController;

import java.net.URL;

public interface Page {

    default void onSetRootPage( StackPane rootPage ) {}

    default void onSetRoot(Node root ) {}

    default  void setRootController(HomeController rootController) {}

    default void onBeforeOpen() {}

    default void onAfterOpen() {}

    default void onBeforeClose() {}

    default void onAfterClose(){ }

    default void accept(Stage primaryStage, Scene scene) { }

    default void onSearch(KeyEvent event, String textFilter ) { }

    static URL urlResource( String locatoion ){
        return URL.class.getResource( locatoion );
    }
}
