package st.ggviario.house.control.drawers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.effects.JFXDepthManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerProducao implements Initializable {

    @FXML private AnchorPane root;
    @FXML private AnchorPane panelIconClose;


    private JFXDrawer drawer;

    public DrawerProducao setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
        return this;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.defineEvents();
    }



    private void defineEvents(){
        this.panelIconClose.setOnMouseClicked(event -> this.drawer.close());
    }

    public AnchorPane getRoot() {
        return root;
    }
}
