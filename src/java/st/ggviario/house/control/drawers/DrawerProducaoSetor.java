package st.ggviario.house.control.drawers;

import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.modals.ModalNovoSetor;
import st.ggviario.house.model.Setor;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class DrawerProducaoSetor extends DrawerProducao<DrawerProducaoSetor.SetorModelView> {

    private IconsActionsFactory<Setor> iconsFatory;

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

    private JFXTreeTableColumn< SetorModelView, String > columnSetorCodigo = new JFXTreeTableColumn<>( "COD" );
    private JFXTreeTableColumn< SetorModelView, String > columnSetorNome = new JFXTreeTableColumn<>( "NOME" );
    private JFXTreeTableColumn< SetorModelView, Setor > columnSetorSuper = new JFXTreeTableColumn<>( "SUPER" );
    private JFXTreeTableColumn< SetorModelView, Short > columnSetorNivel = new JFXTreeTableColumn<>( "N" );
    private JFXTreeTableColumn< SetorModelView, String > columnSetorEstado = new JFXTreeTableColumn<>( "ESTADO" );
    private JFXTreeTableColumn< SetorModelView, IconsActionsObject< Setor > > columnSetorIcons = new JFXTreeTableColumn<>( );

    private StackPane rootPageStackPane;
    private ModalNovoSetor modalNovoSetor;
    private List< SetorModelView > setorModelViewList = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.structure();
        this.defineEvents();

    }
    @Override
    public void onSelect(){
        if( this.setorModelViewList.isEmpty() ){
            this.loadSetorData();
        }
    }

    private void structure(){
        JFXDepthManager.setDepth( this.fabArea, 4 );
        this.columnSetorCodigo.setCellValueFactory( param -> param.getValue().getValue().setorCodigo );
        this.columnSetorNome.setCellValueFactory( param -> param.getValue().getValue().setorNome );
        this.columnSetorSuper.setCellValueFactory( param -> param.getValue().getValue().setorSuper );
        this.columnSetorNivel.setCellValueFactory( param -> param.getValue().getValue().setorNivel );
        this.columnSetorEstado.setCellValueFactory( param -> param.getValue().getValue().setorEstado );
        this.columnSetorIcons.setCellValueFactory( param -> param.getValue().getValue().icons );
        this.columnSetorIcons.setCellFactory( this.cellIconsView() );
        this.useAsIconsColumn( this.columnSetorIcons, 1 );
        this.iconsFatory = object ->{
            Node delete = this.newIconViewDestroy(MaterialDesignIcon.DELETE);
            delete.setOnMouseClicked(event -> this.onDeleteSetor( object ) );
            return this.newIconCellContainer(
                   delete
            );
        };


        this.tableSetores.getColumns().setAll(
            this.columnSetorCodigo,
            this.columnSetorNome,
            this.columnSetorSuper,
            this.columnSetorNivel,
            this.columnSetorEstado,
            this.columnSetorIcons
        );
        this.push( new LinkedList<>(), this.tableSetores );
    }

    private void defineEvents(){
        this.fabButton.setOnAction( event -> onOpenModalNovoSetor() );
    }


    private void onOpenModalNovoSetor( ) {
        this.loadModalNovoSeto();
        this.modalNovoSetor.openModal();
    }

    private void onDeleteSetor( Setor setor ){
    }

    private void loadSetorData(){
        Thread thread = new Thread(() -> {
            Platform.runLater(() -> {
                this.tableSetores.getRoot().getChildren().clear();
                this.setorModelViewList.clear();
                this.tableSetores.refresh();
            });
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
            Setor.SetorBuilder superSetorBuilder = new Setor.SetorBuilder();
            sql.query( "ggviario.funct_load_setor" )
                .withJsonb( new JsonObject() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> Platform.runLater( ( ) -> {
                        setorBuilder.load( row );
                        if( row.get( "setor_super") != null ){
                            superSetorBuilder.load( row.asMapJsonn( "setor_super" ) );
                            setorBuilder.setSetorSuper( superSetorBuilder.build() );
                        }
                        SetorModelView setorModelView = new SetorModelView(  setorBuilder.build(), this.iconsFatory);
                        this.setorModelViewList.add( setorModelView );
                        this.tableSetores.getRoot().getChildren().add( new TreeItem<>( setorModelView ) );
                        System.out.println("setorModelView = " +setorModelView.setor );
                    }));

        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }


    private void loadModalNovoSeto(){
        if( this.modalNovoSetor == null ){
            this.modalNovoSetor = ModalNovoSetor.newInstance( this.rootPageStackPane );
            this.modalNovoSetor.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){
                    this.loadSetorData();
                }
            });
        }
    }

    public class SetorModelView extends RecursiveTreeObject< SetorModelView >{
        private Setor setor;
        private StringProperty setorCodigo;
        private StringProperty setorNome;
        private ObjectProperty< Setor > setorSuper;
        private ObjectProperty< Short > setorNivel;
        private StringProperty setorEstado;
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
