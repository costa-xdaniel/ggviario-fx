package st.ggviario.house.controller;

import javafx.scene.Node;

public interface Page {

    default void setRootPage( Node rootPage ) {}

    default void setRoot( Node root ) {}

    default  void setRootController(HomeController rootController) {}

    default void onBeforeAppend() {}

    default void onAfterAppend() {}

    default void onBeforeRemove() {}

    default void onAfterRemove(){ }
}
