package st.ggviario.house.control.includs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.TableClontroller;
import st.ggviario.house.control.drawers.DrawerProduto;
import st.ggviario.house.model.LocalProducao;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.Setor;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class IncludProdutoLocalProducao extends TableClontroller<IncludProdutoLocalProducao.LocalProducaoModelView> implements Initializable {

    private StackPane stackPane;
    private TableClontroller.IconsActionsFactory< LocalProducao > iconsActionsFactory;

    public static IncludProdutoLocalProducao newInstance( ){
        ControllerLoader< AnchorPane, IncludProdutoLocalProducao> loader = new ControllerLoader<>("/fxml/includs/includ_produto_localproducao.fxml");
        return loader.getController();
    }

    @FXML private AnchorPane root;
    @FXML private StackPane fabArea;
    @FXML private JFXButton fabButton;
    @FXML private MaterialDesignIconView fabIcon;
    @FXML private JFXTreeTableView<LocalProducaoModelView> tableProdutoSector;

    private JFXTreeTableColumn <LocalProducaoModelView, String  > columnSetorCodigo = new JFXTreeTableColumn<>( "COD" );
    private JFXTreeTableColumn <LocalProducaoModelView, Setor  > columnSetor = new JFXTreeTableColumn<>( "NOME" );
    private JFXTreeTableColumn <LocalProducaoModelView, Setor  > columnSetorSuper = new JFXTreeTableColumn<>( "SUPER" );
    private JFXTreeTableColumn <LocalProducaoModelView, Number  > columnSetorQuantidade = new JFXTreeTableColumn<>("QT.");
    private JFXTreeTableColumn <LocalProducaoModelView, String  > columnSetorEstado = new JFXTreeTableColumn<>("ESTADO");
    private JFXTreeTableColumn <LocalProducaoModelView, IconsActionsObject< LocalProducao > > columnIconsAction = new JFXTreeTableColumn<>();


    private DrawerProduto.OnNovoLocalProducao onNovoLocalProducao;
    private DrawerProduto.OnChangeLocalProducaoDisable onChangeLocalProducaoDisable;

    private Produto produto;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
    }

    private void structure(){
        JFXDepthManager.setDepth( this.fabArea, 4 );
        this.columnSetorCodigo.setCellValueFactory( param -> param.getValue().getValue().setorCodigo );
        this.columnSetor.setCellValueFactory( param -> param.getValue().getValue().setorNome );
        this.columnSetor.getStyleClass().add( CLASS_COLUMN_LEFT );
        this.columnSetorSuper.setCellValueFactory( param -> param.getValue().getValue().setorSuper );
        this.columnSetorSuper.getStyleClass().add( CLASS_COLUMN_LEFT );
        this.columnSetorQuantidade.setCellValueFactory( param -> param.getValue().getValue().setorQuantidade );
        this.columnSetorQuantidade.getStyleClass().add( CLASS_COLUMN_NUMBER );
        this.columnSetorEstado.setCellValueFactory( param -> param.getValue().getValue().setorEstado );
        this.columnSetorEstado.getStyleClass().add( CLASS_COLUMN_LEFT );

        this.iconsActionsFactory = ( localProducao ) -> {
            if( localProducao.getLocalProducaoEstado() == LocalProducao.LocalProducaoEstado.ATIVO ){
                Node delete = this.newIconViewDestroy( MaterialDesignIcon.DELETE );
                delete.setOnMouseClicked( mouseEvent -> onDestroyLocalProducao() );
                return this.newIconCellContainer( delete );
            }
            return null;
        };


        this.columnIconsAction.setCellValueFactory( param -> param.getValue().getValue().iconsObject );
        this.columnIconsAction.setCellFactory( this.cellIconsView() );
        this.useAsIconsColumn( this.columnIconsAction, 2 );

        this.tableProdutoSector.getStyleClass().add( "produto-precos-ativo" );

        this.tableProdutoSector.getColumns().setAll(
                this.columnSetorCodigo,
                this.columnSetor,
                this.columnSetorSuper,
                this.columnSetorQuantidade,
                this.columnSetorEstado,
                this.columnIconsAction
        );
        this.push( new LinkedList<>(), this.tableProdutoSector);
    }

    private void defineEvents(){
        this.fabButton.setOnMouseClicked(mouseEvent -> onnNovoLocalProducao() );
        this.fabIcon.setOnMouseClicked( mouseEvent -> fabButton.getOnMouseClicked().handle( mouseEvent ) );
    }

    private void onDestroyLocalProducao() {
        ReadOnlyObjectProperty<TreeItem<LocalProducaoModelView>> select = this.tableProdutoSector.getSelectionModel().selectedItemProperty();
        if( select == null )return;
        if( select.getValue() == null )return;
        if( select.getValue().getValue() == null )return;
        if( this.onChangeLocalProducaoDisable != null ) this.onChangeLocalProducaoDisable.onChangeLocalProducaoDisable( select.getValue().getValue().localProducao );
    }


    private void onnNovoLocalProducao(){
        if( this.onNovoLocalProducao != null ) onNovoLocalProducao.onNovoLocalProducao( this.produto );
    }

    public IncludProdutoLocalProducao setOnNovoLocalProducao(DrawerProduto.OnNovoLocalProducao onNovoLocalProducao) {
        this.onNovoLocalProducao = onNovoLocalProducao;
        return this;
    }

    public IncludProdutoLocalProducao onChangeLocalProducaoDisable(DrawerProduto.OnChangeLocalProducaoDisable onChangeLocalProducaoDisable) {
        this.onChangeLocalProducaoDisable = onChangeLocalProducaoDisable;
        return this;
    }

    public void setProduto (Produto produto ){
        this.produto = produto;
        Thread thread = new Thread(() -> {
            Platform.runLater( ( ) -> this.tableProdutoSector.getRoot().getChildren().clear());
            LocalProducao.LocalProducaoBuilder localProducaoBuilder = new LocalProducao.LocalProducaoBuilder();
            Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
            Setor.SetorBuilder superSetorBuilder = new Setor.SetorBuilder();

            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            sql.query( "ggviario.funct_load_localproducao" )
                .withUUID( this.produto.getProdutoId() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            setorBuilder.load( row.asMapJsonn("setor" ) );
                            if( row.asMapJsonn( "setor_super" ) != null ){
                                superSetorBuilder.load( row.asMapJsonn( "setor_super" ) );
                                setorBuilder.setSetorSuper( superSetorBuilder.build() );
                            }
                            localProducaoBuilder.load( row );
                            localProducaoBuilder.setProduto( this.produto );
                            localProducaoBuilder.setSetor( setorBuilder.build() );
                            this.tableProdutoSector.getRoot().getChildren().add( new TreeItem<>( new LocalProducaoModelView( localProducaoBuilder.build(), this.iconsActionsFactory ) ) );
                        });
                    });
        });

        thread.start();
    }

    public AnchorPane getRoot() {
        return root;
    }

    class LocalProducaoModelView extends RecursiveTreeObject <LocalProducaoModelView> {

        private StringProperty setorCodigo;
        private ObjectProperty< Setor > setorNome;
        private ObjectProperty< Setor> setorSuper;
        private ObjectProperty< Number > setorQuantidade;
        private StringProperty setorEstado;
        private LocalProducao localProducao;
        public ObjectProperty<IconsActionsObject<LocalProducao>> iconsObject;

        private LocalProducaoModelView( LocalProducao localProducao, IconsActionsFactory<LocalProducao> fatory ){
            this.setorCodigo = new SimpleStringProperty( localProducao.getSetor().getSetorCodigo()  );
            this.setorNome = new SimpleObjectProperty<>( localProducao.getSetor() );
            this.setorSuper = new SimpleObjectProperty<>( localProducao.getSetor().getSetorSuper() );
            this.setorQuantidade = new SimpleObjectProperty<>( localProducao.getLocalProducaoQuantidade() );
            this.setorEstado = new SimpleStringProperty( localProducao.getLocalProducaoEstado().getNome() );
            this.iconsObject = new SimpleObjectProperty<>(new IconsActionsObject<LocalProducao>(localProducao, fatory));
            this.localProducao = localProducao;
        }

        @Override
        public String toString() {
            return "LocalProducaoModelView{" +
                    "setorCodigo=" + setorCodigo +
                    ", setorNome=" + setorNome +
                    ", setorSuper=" + setorSuper +
                    ", setorQuantidade=" + setorQuantidade +
                    '}';
        }
    }

}
