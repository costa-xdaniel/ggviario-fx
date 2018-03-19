package st.ggviario.house.control.drawers;

import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.modals.ModalNovoSetor;
import st.ggviario.house.model.Setor;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class DrawerProducaoSetor extends DrawerProducao<DrawerProducaoSetor.SetorModelView> {

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
    @FXML private JFXTreeTableView< SetorModelView > tableSetores;

    private StackPane rootPageStackPane;
    private ModalNovoSetor modalNovoSetor;
    private List< SetorModelView > setorModelViews = new LinkedList<>();

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
            Platform.runLater(() -> {
                this.tableSetores.getRoot().getChildren().clear();
                this.setorModelViews.clear();
            });
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            sql.query( "ggviario.funct_load_setor" )
                .withJsonb( new JsonObject() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {

                        });
                    });

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

    public class SetorModelView extends RecursiveTreeObject< SetorModelView >{
        private Setor setor;
        private StringProperty setorCodigo;
        private StringProperty setorNome;
        private ObjectProperty< Setor > setorSuper;
        private StringProperty setorEstado;
        private ObjectProperty< Short > setorNivel;
        private ObjectProperty< IconsActionsObject< Setor > > icons;

        public SetorModelView(Setor setor, IconsActionsFactory<Setor> fatory ){
            this.setor = setor;
            this.setorCodigo = new SimpleStringProperty( setor.getSetorCodigo() );
            this.setorNome = new SimpleStringProperty( setor.getSetorNome() );
            this.setorSuper = new SimpleObjectProperty<>( setor.getSetorSuper() );
            this.setorEstado = new SimpleStringProperty( setor.getSetorEstado().getNome() );
            this.setorNivel = new SimpleObjectProperty<>( setor.getSetorNivel() );
            this.icons = new SimpleObjectProperty<>( new IconsActionsObject< Setor >( setor, fatory ) );
        }
    }
}
