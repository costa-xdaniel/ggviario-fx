package st.ggviario.house.controller.page;

import com.google.gson.JsonElement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modal.ModalNovoProduto;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.model.Preco;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.Unidade;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class PageTabProduto extends RowsController<PageTabProduto.ProdutoModelView > implements PageTab, Initializable{

    @FXML private JFXTreeTableView< ProdutoModelView > treeTableViewUnidade;
    @FXML private AnchorPane fabArea;
    @FXML private JFXButton fabButton;
    @FXML private MaterialDesignIconView fabIcon;

    private JFXTreeTableColumn< ProdutoModelView, String > columnCodigo = new JFXTreeTableColumn<>("CODIGO");
    private JFXTreeTableColumn< ProdutoModelView, String > columnNome = new JFXTreeTableColumn<>( "NOME" );
    private JFXTreeTableColumn< ProdutoModelView, Number > columnStock = new JFXTreeTableColumn<>( "Qt." );
    private JFXTreeTableColumn< ProdutoModelView, String > columnPreco = new JFXTreeTableColumn<>( "PRECO" );
    private JFXTreeTableColumn< ProdutoModelView, Number > columnVendas = new JFXTreeTableColumn<>( "T. VENDAS" );
    private JFXTreeTableColumn< ProdutoModelView, Number > columnVendaVendas = new JFXTreeTableColumn<>( "V. DIRECTA" );
    private JFXTreeTableColumn< ProdutoModelView, Number > columnVendaDividas = new JFXTreeTableColumn<>( "V. CREDITO" );
    private JFXTreeTableColumn< ProdutoModelView, Number > columnVendaPagas = new JFXTreeTableColumn<>( "PAGAS" );
    private JFXTreeTableColumn< ProdutoModelView, Number > columnVendaPendentes = new JFXTreeTableColumn<>( "PENDENTES" );
    private JFXTreeTableColumn< ProdutoModelView, Number > columnCompras = new JFXTreeTableColumn<>( "DESPESA" );

    private ModalNovoProduto modalNovoProduto;
    private StackPane rootPage;
    private List< ProdutoModelView > produtoModelViewList = new LinkedList<>();
    private List< Categoria > categoriaList = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
        this.loadDataCategoria();
        this.loadDatProduto();
        this.pushProduto();
    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    private void structure(){
        this.columnCodigo.setCellValueFactory( param -> param.getValue().getValue().codigo );
        this.columnNome.setCellValueFactory( param -> param.getValue().getValue().nome );
        this.columnStock.setCellValueFactory( param -> param.getValue().getValue().stock );
        this.columnPreco.setCellValueFactory( param -> param.getValue().getValue().preco );
        this.columnVendas.setCellValueFactory( param -> param.getValue().getValue().vendas );
        this.columnVendaVendas.setCellValueFactory( param -> param.getValue().getValue().vendaVendas );
        this.columnVendaDividas.setCellValueFactory( param -> param.getValue().getValue().vendaDividas );
        this.columnVendaPagas.setCellValueFactory( param -> param.getValue().getValue().vendaPagas );
        this.columnVendaPendentes.setCellValueFactory( param -> param.getValue().getValue().vendaPendentes );
        this.columnCompras.setCellValueFactory( param -> param.getValue().getValue().compras );

        this.treeTableViewUnidade.getColumns().setAll(
                this.columnCodigo,
                this.columnNome,
                this.columnStock,
                this.columnPreco,
                this.columnVendas,
                this.columnVendaVendas,
                this.columnVendaDividas,
                this.columnVendaPagas,
                this.columnVendaPendentes,
                this.columnCompras
        );

        this.columnNome.getStyleClass().add( "table-column-left" );
        this.columnStock.getStyleClass().add( "table-column-number" );
        this.columnPreco.getStyleClass().add( "table-column-money" );
        this.columnVendas.getStyleClass().add( "table-column-money" );
        this.columnVendaVendas.getStyleClass().add( "table-column-money" );
        this.columnVendaDividas.getStyleClass().add( "table-column-money" );
        this.columnVendaPagas.getStyleClass().add( "table-column-money" );
        this.columnVendaPendentes.getStyleClass().add( "table-column-money" );
        this.columnCompras.getStyleClass().add( "table-column-money" );
//        JFXDepthManager.setDepth( , 2 );
        JFXDepthManager.pop( this.treeTableViewUnidade );
    }

    private void defineEvents() {
        this.fabArea.setOnMouseClicked(mouseEvent -> this.onOpenModalNOvoProduto()  );
        this.fabButton.setOnMouseClicked( fabArea.getOnMouseClicked() );
        this.fabIcon.setOnMouseClicked( fabArea.getOnMouseClicked() );
    }

    private void loadDatProduto() {
        this.produtoModelViewList.clear();
        Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
        Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();
        Preco.PrecoBuilder precoBuilder = new Preco.PrecoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();

        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        sql.query( "ggviario.funct_load_produto" )
            .withJsonb((String) null )
            .callFunctionTable()
                .onResultQuery( row -> {

                    produtoBuilder.load( row );

                    if( row.get("preco") != null ){
                        precoBuilder.load( row.asMapJsonn( "preco" ) );
                        unidadeBuilder.load( row.asMapJsonn("unidade" ) );
                        precoBuilder.setUnidade( unidadeBuilder.build() );
                        produtoBuilder.setPreco( precoBuilder.build() );
                    } else produtoBuilder.setPreco( null );

                    categoriaBuilder.load( row.asMapJsonn( "categoria" ) );
                    produtoBuilder.setCategoria( categoriaBuilder.build() )
                    ;
                    Produto produto = produtoBuilder.build();
                    this.produtoModelViewList.add( new ProdutoModelView( produto ) );
                });
        ;
    }

    private void pushProduto(){
        this.push( this.produtoModelViewList, this.treeTableViewUnidade );
    }

    private void loadDataCategoria(){
        this.categoriaList.clear();
        Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        sql.query( "ggviario.funct_load_categoria_simple" )
                .withJsonb( (JsonElement) null )
                .callFunctionTable()
                .onResultQuery( row -> {
                    categoriaBuilder.load( row );
                    this.categoriaList.add( categoriaBuilder.build() );
                })
        ;
    }

    private void onOpenModalNOvoProduto(){
        this.loadModalNovoProduto();
        this.modalNovoProduto.setCategoriaList( this.categoriaList );
        this.modalNovoProduto.openModal();
    }

    private void loadModalNovoProduto(){
        if (this.modalNovoProduto == null) {
            this.modalNovoProduto = ModalNovoProduto.load( this.rootPage );
            this.modalNovoProduto.setOnModalResult(modalResult -> {
                if (modalResult.isSuccess()) {
                    this.loadDatProduto();
                    this.pushProduto();
                }
            });
        }
    }

    class ProdutoModelView extends RecursiveTreeObject< ProdutoModelView >{
        private Produto produto;
        private StringProperty codigo;
        private StringProperty nome;
        private ObjectProperty< Number > stock;
        private ObjectProperty< Number > vendas;
        private ObjectProperty< Number > vendaDividas;
        private ObjectProperty< Number > vendaVendas;
        private ObjectProperty< Number > vendaPagas;
        private ObjectProperty< Number > vendaPendentes;
        private ObjectProperty< Number > compras;
        private StringProperty preco;

        public ProdutoModelView( Produto produto) {
            this.produto = produto;
            this.codigo = new SimpleStringProperty( produto.getProdutoCodigo() );
            this.nome = new SimpleStringProperty( produto.getProdutoNome() );
            this.stock = new SimpleObjectProperty<>( produto.getProdutoStock() );
            this.vendas = new SimpleObjectProperty<>( produto.getProdutoMontanteVendas() );
            this.vendaDividas = new SimpleObjectProperty<>( produto.getProdutoMontanteVendaDivida() );
            this.vendaVendas = new SimpleObjectProperty<>( produto.getProdutoMontanteVendaVendas()  );
            this.vendaPagas = new SimpleObjectProperty<>( produto.getProdutoMontanteVendaPagas() );
            this.vendaPendentes = new SimpleObjectProperty<>( produto.getProdutoMontanteVendaPendentes() );
            this.compras = new SimpleObjectProperty<>( produto.getProdutoMontanteCompras() );
            this.preco = new SimpleStringProperty(
                    produto.getPreco() == null ? null
                            : produto.getPreco().getPrecoCustoUnidade() + "/" + produto.getPreco().getUnidade().getUnidadeCodigo()
            );
        }
    }
}
