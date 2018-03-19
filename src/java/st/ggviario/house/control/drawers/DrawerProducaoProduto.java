package st.ggviario.house.control.drawers;

import javafx.scene.layout.AnchorPane;
import st.ggviario.house.control.ControllerLoader;

public class DrawerProducaoProduto extends DrawerProducao {

    public static DrawerProducaoProduto newInstance() {
        ControllerLoader<AnchorPane, DrawerProducaoProduto > loader;
        loader = new ControllerLoader<>("/fxml/drawer/drawer_producao_produto.fxml");
        return loader.getController();
    }
}
