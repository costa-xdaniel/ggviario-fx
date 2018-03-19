package st.ggviario.house.control.drawers;

import com.jfoenix.controls.JFXDrawer;
import javafx.scene.layout.AnchorPane;
import st.ggviario.house.control.ControllerLoader;

public class DrawerProducaoProduto extends DrawerProducao {

    public static DrawerProducaoProduto newInstance(JFXDrawer drawer ) {
        ControllerLoader<AnchorPane, DrawerProducaoProduto > loader;
        loader = new ControllerLoader<>("/fxml/drawer/drawer_producao_produto.fxml");
        DrawerProducaoProduto controller = loader.getController();
        controller.setDrawer( drawer );
        return controller;
    }
}
