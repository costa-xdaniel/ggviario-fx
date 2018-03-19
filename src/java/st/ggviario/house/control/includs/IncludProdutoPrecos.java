package st.ggviario.house.control.includs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableRow;
import javafx.scene.layout.AnchorPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.TableClontroller;
import st.ggviario.house.control.drawers.DrawerProduto;
import st.ggviario.house.model.Preco;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.Unidade;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class IncludProdutoPrecos extends TableClontroller<IncludProdutoPrecos.ProdutoUnidadeModelView > implements Initializable {

    public static IncludProdutoPrecos newInstance( ){
        ControllerLoader< AnchorPane, IncludProdutoPrecos> loader = new ControllerLoader<>("/fxml/includs/includ_produto_precos.fxml");
        return loader.getController();
    }


    @FXML private AnchorPane root;
    @FXML private JFXButton fabButton;
    @FXML private MaterialDesignIconView fabIcon;

    @FXML private JFXTreeTableView< ProdutoUnidadeModelView > tableProdutoUnidades;

    private JFXTreeTableColumn < ProdutoUnidadeModelView, String  > columnUnidadeNome = new JFXTreeTableColumn<>( "UNIDADE" );
    private JFXTreeTableColumn < ProdutoUnidadeModelView, String  > columnUnidadeCodigo = new JFXTreeTableColumn<>( "COD" );
    private JFXTreeTableColumn < ProdutoUnidadeModelView, Number  > columnPrecoQuantidade = new JFXTreeTableColumn<>( "QT." );
    private JFXTreeTableColumn < ProdutoUnidadeModelView, Number  > columnPrecoCustoUnidade = new JFXTreeTableColumn<>("PREÇO");
    private JFXTreeTableColumn < ProdutoUnidadeModelView, IconsActionsObject< Preco > > columnIconsAction = new JFXTreeTableColumn<>();


    private DrawerProduto.OnPrecoDestroy onPrecoDestroy;
    private DrawerProduto.OnEditarPreco onEditarPreco;
    private DrawerProduto.OnNovoPreco onNovoPreco;
    private IconsActionsFactory< Preco > iconsActionsFactoryFatory;

    private Produto produto;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
    }

    private void structure(){
        this.columnUnidadeNome.setCellValueFactory( param -> param.getValue().getValue().unidadeNome );
        this.columnUnidadeCodigo.setCellValueFactory( param -> param.getValue().getValue().unidadeCodigo );
        this.columnPrecoQuantidade.setCellValueFactory( param -> param.getValue().getValue().quantidadeProduto );
        this.columnPrecoCustoUnidade.setCellValueFactory( param -> param.getValue().getValue().custoUnidade );

        this.iconsActionsFactoryFatory = ( preco ) -> {
            Node delete = this.newIconViewDestroy( MaterialDesignIcon.DELETE );
            Node edite = this.newIconViewPrimary( MaterialIcon.EDIT );
            edite.setOnMouseClicked(mouseEvent -> onEditarPreco() );
            delete.setOnMouseClicked( mouseEvent -> onDelectePreco() );
            return this.newIconCellContainer( edite, delete );
        };

        this.columnIconsAction.setCellValueFactory( param -> param.getValue().getValue().iconsObject );
        this.columnIconsAction.setCellFactory( this.cellIconsView() );
        this.useAsIconsColumn( this.columnIconsAction, 2 );

        this.tableProdutoUnidades.getStyleClass().add( "produto-precos-ativo" );

        this.tableProdutoUnidades.getColumns().setAll(
                this.columnUnidadeNome,
                this.columnUnidadeCodigo,
                this.columnPrecoCustoUnidade,
                this.columnPrecoQuantidade,
                this.columnIconsAction
        );

        this.tableProdutoUnidades.setRowFactory(produtoUnidadeModelViewTreeTableView -> new TreeTableRow<ProdutoUnidadeModelView>() {
            @Override
            protected void updateItem(ProdutoUnidadeModelView item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    if (item.preco.isPrecoBase() && !this.getStyleClass().contains( "base" ) ) {
                        this.getStyleClass().add("base");
                    } else if( !item.preco.isPrecoBase() ){
                        this.getStyleClass().remove( "base" );
                    }
                } else {
                    this.setGraphic( null );
                }
                this.setItem(item);
            }
        });
        this.push( new LinkedList<>(), this.tableProdutoUnidades );
    }

    private void defineEvents(){
        this.fabButton.setOnMouseClicked(actionEvent -> {
           if( this.onNovoPreco != null ) this.onNovoPreco.onNovoPreco( this.produto );
        });
        this.fabIcon.setOnMouseClicked(mouseEvent -> fabButton.getOnMouseClicked().handle( mouseEvent));
    }



    private void onDelectePreco() {
        ReadOnlyObjectProperty<TreeItem<ProdutoUnidadeModelView>> select = this.tableProdutoUnidades.getSelectionModel().selectedItemProperty();
        if( select == null )return;
        if( select.getValue() == null )return;
        if( select.getValue().getValue() == null )return;
        if(  this.onPrecoDestroy != null ) this.onPrecoDestroy.onPrecoDestroy( select.getValue().getValue().preco );
    }

   private void onEditarPreco() {
        ReadOnlyObjectProperty<TreeItem<ProdutoUnidadeModelView>> select = this.tableProdutoUnidades.getSelectionModel().selectedItemProperty();
        if( select == null )return;
        if( select.getValue() == null )return;
        if( select.getValue().getValue() == null )return;
        if(  this.onEditarPreco != null ) this.onEditarPreco.onEditarPreco( select.getValue().getValue().preco );
    }

    public IncludProdutoPrecos setOnNovoPreco(DrawerProduto.OnNovoPreco onNovoPreco) {
        this.onNovoPreco = onNovoPreco;
        return this;
    }

    public IncludProdutoPrecos setOnPrecoDestroy(DrawerProduto.OnPrecoDestroy onPrecoDestroy) {
        this.onPrecoDestroy = onPrecoDestroy;
        return this;
    }

    public IncludProdutoPrecos setOnEditarPreco(DrawerProduto.OnEditarPreco onEditarPreco) {
        this.onEditarPreco = onEditarPreco;
        return this;
    }



    public void setProduto (Produto produto ){
        this.produto = produto;
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Platform.runLater(() -> {
                this.tableProdutoUnidades.getRoot().getChildren().clear();
                this.tableProdutoUnidades.refresh();
            });
            Preco.PrecoBuilder precoBuilder = new Preco.PrecoBuilder();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
            sql.query( "ggviario.funct_load_produto_unidades" )
                .with( produto.getProdutoId() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater( ( ) -> {
                            unidadeBuilder.load( row );
                            precoBuilder.load( row );
                            precoBuilder.setUnidade( unidadeBuilder.build() );
                            precoBuilder.setProduto( this.produto );
                            this.tableProdutoUnidades.getRoot().getChildren().add( new TreeItem<>( new ProdutoUnidadeModelView( precoBuilder.build(), this.iconsActionsFactoryFatory) ));
                        });
                    })
            ;

        });
        thread.start();
    }

    public AnchorPane getRoot() {
        return root;
    }

    class ProdutoUnidadeModelView extends RecursiveTreeObject < ProdutoUnidadeModelView> {

        private StringProperty unidadeNome;
        private StringProperty unidadeCodigo;
        private ObjectProperty< Number > quantidadeProduto;
        private ObjectProperty< Number > custoUnidade;
        public Preco preco;
        public ObservableValue<IconsActionsObject<Preco>> iconsObject;

        private ProdutoUnidadeModelView( Preco preco, IconsActionsFactory<Preco> fatory ){
            this.unidadeNome = new SimpleStringProperty( preco.getUnidade().getUnidadeNome()  );
            this.unidadeCodigo = new SimpleStringProperty( preco.getUnidade().getUnidadeCodigo() );
            this.quantidadeProduto = new SimpleObjectProperty<>( preco.getPrecoQuantidadeProduto() );
            this.custoUnidade = new SimpleObjectProperty<>( preco.getPrecoCustoUnidade() );

            this.iconsObject = new SimpleObjectProperty<>( new IconsActionsObject< Preco >( preco, fatory ));
            this.preco = preco;
        }

        @Override
        public String toString() {
            return "LocalProducaoModelView{" +
                    "unidadeNome=" + unidadeNome +
                    ", unidadeCodigo=" + unidadeCodigo +
                    ", quantidadeProduto=" + quantidadeProduto +
                    ", custoUnidade=" + custoUnidade +
                    '}';
        }
    }

}
