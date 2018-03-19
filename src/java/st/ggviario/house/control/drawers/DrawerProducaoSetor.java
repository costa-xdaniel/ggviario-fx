package st.ggviario.house.control.drawers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerProducaoSetor extends DrawerProducao {

    public static DrawerProducaoSetor newInstance(JFXDrawer drawer ) {
        ControllerLoader<AnchorPane, DrawerProducaoSetor > loader;
        loader = new ControllerLoader<>("/fxml/drawer/drawer_producao_setor.fxml");
        DrawerProducaoSetor controller = loader.getController();
        controller.setDrawer( drawer );
        return controller;
    }

    @FXML
    private StackPane fabArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.structure();
    }

    private void structure(){
        JFXDepthManager.setDepth( this.fabArea, 4 );
    }


}
