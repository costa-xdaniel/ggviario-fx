package st.ggviario.house.control.drawers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import st.ggviario.house.control.TableClontroller;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class DrawerProducao < Type extends RecursiveTreeObject< Type> > extends TableClontroller < Type > implements Initializable {

    @FXML private AnchorPane root;
    @FXML
    protected HBox headerPageIcon;
    @FXML private AnchorPane panelIconClose;


    protected JFXDrawer drawer;

    public DrawerProducao setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
        return this;
    }



    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        this.defineEvents();
        this.structure();
    }

    private void structure() {
        JFXRippler rippler = new JFXRippler( this.panelIconClose );
        rippler.getStyleClass().add( "rippler" );
        rippler.getStyleClass().add( "close" );
        this.headerPageIcon.getChildren().add( rippler );
    }


    private void defineEvents(){
        this.panelIconClose.setOnMouseClicked(event -> this.drawer.close());
    }

    public AnchorPane getRoot() {
        return root;
    }

    public abstract void onSelect();
}
