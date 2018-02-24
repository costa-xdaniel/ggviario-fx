package st.ggviario.house.controller;

import javafx.scene.Node;

public interface Page {

    default void onSetRootPage(Node rootPage ) {}

    default void onSetRoot(Node root ) {}

    default  void setRootController(HomeController rootController) {}

    default void onBeforeAppend() {}

    default void onAfterAppend() {}

    default void onBeforeRemove() {}

    default void onAfterRemove(){ }
}
