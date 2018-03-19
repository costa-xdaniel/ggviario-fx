package st.ggviario.house.control.drawers;

import javafx.scene.layout.AnchorPane;
import st.ggviario.house.control.ControllerLoader;

public class DrawerProducaoSetor extends DrawerProducao {

    public static DrawerProducaoSetor newInstance() {
        ControllerLoader<AnchorPane, DrawerProducaoSetor > loader;
        loader = new ControllerLoader<>("/fxml/drawer/drawer_producao_setor.fxml");
        return loader.getController();
    }


}
