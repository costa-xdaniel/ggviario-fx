package st.ggviario.house.control.drawers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.modals.Modal;
import st.ggviario.house.control.modals.ModalNovoSetor;
import st.ggviario.house.model.Setor;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerProducaoSetor extends DrawerProducao {

    public static DrawerProducaoSetor newInstance(JFXDrawer drawer, StackPane rootPageStackPane ) {
        ControllerLoader<AnchorPane, DrawerProducaoSetor > loader;
        loader = new ControllerLoader<>("/fxml/drawer/drawer_producao_setor.fxml");
        DrawerProducaoSetor controller = loader.getController();
        controller.setDrawer( drawer );
        controller.rootPageStackPane = rootPageStackPane;
        return controller;
    }

    @FXML private StackPane fabArea;
    @FXML private JFXButton fabButton;

    private StackPane rootPageStackPane;

    private ModalNovoSetor modalNovoSetor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.structure();
        this.defineEvents();
    }

    private void structure(){
        JFXDepthManager.setDepth( this.fabArea, 4 );
    }

    private void defineEvents(){
        this.fabButton.setOnAction( event -> onOpenModalNovoSetor() );
    }

    private void onOpenModalNovoSetor( ) {
        this.loadModalNovoSeto();
        this.modalNovoSetor.openModal();
    }

    private void loadSetorData(){
        Thread thread = new Thread(() -> {

        });
        thread.setPriority( Thread.MIN_PRIORITY );
    }


    private void loadModalNovoSeto(){
        if( this.modalNovoSetor == null ){
            this.modalNovoSetor = ModalNovoSetor.newInstance( this.rootPageStackPane );
            this.modalNovoSetor.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){

                }
            });
        }
    }




}
