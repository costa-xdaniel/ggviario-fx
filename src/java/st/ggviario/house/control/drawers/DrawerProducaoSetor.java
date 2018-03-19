package st.ggviario.house.control.drawers;

import com.jfoenix.controls.JFXDrawer;
import javafx.scene.layout.AnchorPane;
import st.ggviario.house.control.ControllerLoader;

public class DrawerProducaoSetor extends DrawerProducao {

    public static DrawerProducaoSetor newInstance(JFXDrawer drawer ) {
        ControllerLoader<AnchorPane, DrawerProducaoSetor > loader;
        loader = new ControllerLoader<>("/fxml/drawer/drawer_producao_setor.fxml");
        DrawerProducaoSetor controller = loader.getController();
        controller.setDrawer( drawer );
        return controller;
    }


}
