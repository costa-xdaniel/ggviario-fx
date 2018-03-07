package st.ggviario.house.controller.tabs;

import javafx.scene.control.TabPane;
import st.ggviario.house.controller.pages.Page;

public interface TabPage extends Page {

    default void acceptTab( javafx.scene.control.Tab tab ) {}

    default void acceptTabPane( TabPane tabPane ) {}

    default void onBeforeOpenRootPage() { }

    default void onAfterOpenRootPage() { }

    default void onBeforeCloseRootPage() { }

    default void onAfeterCloseRootPage() { }
}
