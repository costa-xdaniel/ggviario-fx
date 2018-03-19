package st.ggviario.house.control.tabs;

import javafx.scene.control.TabPane;
import st.ggviario.house.control.pages.Page;

public interface TabPage extends Page {

    default void acceptTab( javafx.scene.control.Tab tab ) {}

    default void acceptTabPane( TabPane tabPane ) {}

    default void onBeforeOpenRootPage() { }

    default void onAfterOpenRootPage() { }

    default void onBeforeCloseRootPage() { }

    default void onAfeterCloseRootPage() { }
}
