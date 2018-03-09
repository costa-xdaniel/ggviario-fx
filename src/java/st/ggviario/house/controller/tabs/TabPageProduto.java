package st.ggviario.house.controller.tabs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.SnackbarBuilder;
import st.ggviario.house.controller.drawers.DrawerObjectItem;
import st.ggviario.house.controller.drawers.DrawerProduto;
import st.ggviario.house.controller.modals.*;
import st.ggviario.house.controller.pages.TableClontroller;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TabPageProduto extends TableClontroller<TabPageProduto.ProdutoModelView > implements TabPage, Initializable{

    @FXML private AnchorPane root;
    @FXML private JFXTreeTableView< ProdutoModelView > treeTableViewUnidade;
    @FXML private AnchorPane fabArea;
    @FXML private JFXButton fabButton;
    @FXML private MaterialDesignIconView fabIcon;
    @FXML private JFXDrawer jfxDrawerProdutoDetails;
    @FXML private JFXDrawer jfxDrawerItems;
    @FXML private JFXButton buttomCategoria;
    @FXML private JFXButton buttomUnidade;

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
    private ModalNovoPreco modalNovoPreco;
    private DrawerProduto drawerProduto;
    private DrawerObjectItem drawerObjectItems;
    private ModalNovaCategoria modalNovaCategoria;
    private ModalNovaUnidade modalNovaUnidade;
    private ModalDestroy< Preco > modalPrecoDestry;
    private ModalDestroy< Unidade > unidadeModalDestroy;
    private ModalDestroy< Categoria > categoriaModalDestroy;

    private StackPane rootPage;
    private List< ProdutoModelView > originalProductList = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
        this.push( new LinkedList<>(), this.treeTableViewUnidade );
        this.drawerObjectItems.loadUnidade();
        this.drawerObjectItems.loadCategoria();
        this.loadDatProduto();
    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    private void structure(){
        this.loadDrawerObjectType();
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
        JFXDepthManager.pop( this.treeTableViewUnidade );
        this.root.getChildren().remove( this.jfxDrawerItems );
        this.root.getChildren().remove( this.jfxDrawerProdutoDetails);

    }

    private void defineEvents() {
        this.fabArea.setOnMouseClicked(mouseEvent -> this.onOpenModalNovoProduto()  );
        this.fabButton.setOnMouseClicked( fabArea.getOnMouseClicked() );
        this.fabIcon.setOnMouseClicked( fabArea.getOnMouseClicked() );
        this.treeTableViewUnidade.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> onOpenDrawerProduto( newValue == null? null : newValue.getValue() ));
        this.buttomCategoria.setOnAction(actionEvent -> this.onOpenDrawerObjectType( DrawerObjectItem.ObjectType.CATEGORIA ));
        this.buttomUnidade.setOnAction(actionEvent -> this.onOpenDrawerObjectType( DrawerObjectItem.ObjectType.UNIDADE ));
        this.jfxDrawerItems.setOnDrawerClosed(jfxDrawerEvent -> this.root.getChildren().remove( this.jfxDrawerItems ));
        this.jfxDrawerProdutoDetails.setOnDrawerClosed(jfxDrawerEvent -> this.root.getChildren().remove( this.jfxDrawerProdutoDetails));
        this.jfxDrawerItems.heightProperty().addListener((observableValue, oldValue, newValue) -> {
            if( this.drawerObjectItems != null ) drawerObjectItems.getRoot().setPrefHeight( newValue.doubleValue() );
        });
    }

    private void loadDatProduto() {
        Thread thread = new Thread(() -> {
            this.originalProductList.clear();
            Platform.runLater(() -> this.treeTableViewUnidade.getRoot().getChildren().clear());

            Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
            Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();
            Preco.PrecoBuilder precoBuilder = new Preco.PrecoBuilder();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
            PostgresSQL sql = PostgresSQLSingleton.getInstance();

            sql.query( "ggviario.funct_load_produto" )
                    .withJsonb((String) null )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
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
                            ProdutoModelView item = new ProdutoModelView(produto);
                            this.treeTableViewUnidade.getRoot().getChildren().add( new TreeItem<>( item ) );
                            this.originalProductList.add( item );
                        });
                    });
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    void onOpenDrawerProduto(ProdutoModelView  newProduto ) {
        if( newProduto != null ){
            this.loadDrawerProduto();
            this.jfxDrawerProdutoDetails.setSidePane( this.drawerProduto.getRoot() );
            this.drawerProduto.setProduto( newProduto.produto  );

            if( !this.root.getChildren().contains( this.jfxDrawerProdutoDetails) ){
                int index = this.root.getChildren().indexOf( this.treeTableViewUnidade );
                this.root.getChildren().add(index+1, this.jfxDrawerProdutoDetails);
            }

            this.jfxDrawerProdutoDetails.open();
            this.closeDrawerItem();
        } else {
            this.closeDrawerDetails();
        }
    }

    private void closeDrawerItem(){
        this.jfxDrawerItems.close();
    }

    void onOpenDrawerObjectType(DrawerObjectItem.ObjectType objectType ){
        this.loadDrawerObjectType();
        this.jfxDrawerItems.setSidePane( this.drawerObjectItems.getRoot() );
        this.drawerObjectItems.setObjectType( objectType  );

        if( !this.root.getChildren().contains( this.jfxDrawerItems) ){
            int index = this.root.getChildren().indexOf( this.treeTableViewUnidade );
            this.root.getChildren().add(index+1, this.jfxDrawerItems);
        }
        this.jfxDrawerItems.open();
        this.closeDrawerDetails();

    }

    private void onOpenModalNovoProduto(){
        this.loadModalNovoProduto();
        this.modalNovoProduto.setCategoriaList( this.drawerObjectItems.getCategoriaList() );
        this.modalNovoProduto.setTitle( "Novo produto" );
        this.modalNovoProduto.openModal();
    }
    private void onOpenModalNovoProdutoEditMode( Produto produto ){
        this.loadModalNovoProduto();
        this.modalNovoProduto.setCategoriaList( this.drawerObjectItems.getCategoriaList() );
        this.modalNovoProduto.setTitle( "Editar " + produto.getProdutoNome() );
        this.modalNovoProduto.openModal( produto );
    }

    private void onOpenModalNovoPreco(Produto produto){
        if( produto == null ){
            this.closeDrawerDetails();
            return;
        }
        this.loadModalNovoPreco();
        this.modalNovoPreco.openModal( produto );
    }

    private void onOpenModalNovaCategoriaEditar( Categoria categoria ) {
        this.loadModalNovaCategoria();
        this.modalNovaCategoria.setTitle( "Editar categoria "+categoria.getCategoriaNome() );
        this.modalNovaCategoria.pushCategoriaSupers( this.drawerObjectItems.getCategoriaList() );
        this.modalNovaCategoria.openModal( categoria );
    }

    private void onOpenModalNovaCategoria() {
        this.loadModalNovaCategoria();
        this.modalNovaCategoria.setTitle( "Nova categoria" );
        this.modalNovaCategoria.pushCategoriaSupers( this.drawerObjectItems.getCategoriaList() );
        this.modalNovaCategoria.openModal();
    }

    private void onOpenModalNovaUnidade(){
        this.loadModalNovaUnidade();
        this.modalNovaUnidade.setTitle( "Nova unidade" );
        this.modalNovaUnidade.openModal();
    }

    private void onOpenModalNovaUnidadeEditar( Unidade unidade ){
        this.loadModalNovaUnidade();
        this.modalNovaUnidade.setTitle( "Editar unidade " + unidade.getUnidadeNome() );
        this.modalNovaUnidade.openModal( unidade );
    }

    private void onOpenModalDestroyPreco( Preco preco ){
        this.loadModalDestroyPreco( );
        ModalDestroy.Destroy<Preco> destroy = new ModalDestroy.Destroy<>( preco,  "Digite o "+preco.getUnidade().getUnidadeCodigo()+" para desativar o preco", preco.getUnidade().getUnidadeCodigo() );
        destroy.setRequireText( false );
        destroy.setIgnoreCase( true );
        this.modalPrecoDestry.setTitle( "Fechar "+preco.getUnidade().getUnidadeNome() );
        this.modalPrecoDestry.opemModal( destroy );
    }

    private void loadDrawerProduto() {
        if( this.drawerProduto == null ){
            this.drawerProduto = DrawerProduto.newInstance( this.jfxDrawerProdutoDetails);
            if( drawerProduto == null ) throw  new RuntimeException( "Para Qui" );
            this.drawerProduto.setOnNovoPreco(this::onOpenModalNovoPreco );
            this.drawerProduto.setOnProdutoEdit( this::onOpenModalNovoProdutoEditMode );
            this.drawerProduto.setOnPrecoDestroy(  this::onOpenModalDestroyPreco );
        }
    }

    private void loadDrawerObjectType( ){
        if( this.drawerObjectItems == null ){
            this.drawerObjectItems = DrawerObjectItem.newInstance( this.jfxDrawerItems);
            this.drawerObjectItems.setOnNovaUnidade( this::onOpenModalNovaUnidade );
            this.drawerObjectItems.setOnEditarUnidade( this::onOpenModalNovaUnidadeEditar );
            this.drawerObjectItems.setOnNovaCategoria( this::onOpenModalNovaCategoria );
            this.drawerObjectItems.setOnEditarCategoria( this::onOpenModalNovaCategoriaEditar );
        }
    }

    private void loadModalNovoProduto(){
        if (this.modalNovoProduto == null) {
            this.modalNovoProduto = ModalNovoProduto.newInstance( this.rootPage );
            this.modalNovoProduto.setOnModalResult(modalResult -> {
                if (modalResult.isSuccess()) {
                    this.loadDatProduto();
                }
            });
        }
    }

    private void loadModalNovaCategoria(){
        if( this.modalNovaCategoria == null ){
            this.modalNovaCategoria = ModalNovaCategoria.newInstance( this.rootPage );
            this.modalNovaCategoria.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){
                    this.drawerObjectItems.setObjectType( DrawerObjectItem.ObjectType.CATEGORIA );
                }
            });
        }
    }

    private void loadModalNovaUnidade( ){
        if( this.modalNovaUnidade == null ){
            this.modalNovaUnidade = ModalNovaUnidade.newInstance( this.rootPage );
            this.modalNovaUnidade.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){
                    this.drawerObjectItems.setObjectType( DrawerObjectItem.ObjectType.UNIDADE );
                }
            });
        }
    }

    private void loadModalDestroyPreco( ){
        if( this.modalPrecoDestry == null ){
            this.modalPrecoDestry = ModalDestroy.newInstance( this.rootPage );
            this.modalPrecoDestry.setMessageInvalidIdentifier( "Codigo de unidade invalido!" );
            this.modalPrecoDestry.setMessageMissingIdentifier( "Insira o identificador!" );
            this.modalPrecoDestry.setMessageMissingText( "Expecifique o texto de confirmação!" );
            this.modalPrecoDestry.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){
                    Thread thread = new Thread(() -> {
                        PostgresSQL sql = PostgresSQLSingleton.getInstance();
                        Colaborador colaborador = AuthSingleton.getInstance();
                        Preco preco = modalResult.getValue().getObject();
                        sql.query( "ggviario.funct_change_preco_close" )
                            .withUUID( colaborador.getColaboradorId() )
                            .withUUID( preco.getPrecoId() )
                            .withVarchar( modalResult.getValue().getCapturedText() )
                            .callFunctionTable()
                                .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                                    Platform.runLater( ( )-> {
                                        SQLResult result = new SQLResult( row );
                                        String message;
                                        SnackbarBuilder.MessageLevel level;
                                        if( result.isSuccess() ){
                                            message = "O preco para " + preco.getUnidade().getUnidadeNome()+" foi desativado com sucesso";
                                            level = SnackbarBuilder.MessageLevel.SUCCESS;
                                        } else {
                                            message = result.getMessage();
                                            level = SnackbarBuilder.MessageLevel.ERROR;
                                        }

                                        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.rootPage );
                                        snackbarBuilder.show( message, level );
                                        if( level == SnackbarBuilder.MessageLevel.SUCCESS ){
                                            this.modalPrecoDestry.clear();
                                            this.modalPrecoDestry.closeModal();
                                            this.drawerProduto.notifyPrecoDestroy();
                                        }
                                    });
                                });
                    });
                    thread.start();
                } else  {
                    SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.rootPage );
                    snackbarBuilder.show( modalResult.getMessage(), SnackbarBuilder.MessageLevel.WARNING );
                }
            });
        }
    }

    private void loadModalNovoPreco(){
        if( this.modalNovoPreco == null ){
            this.modalNovoPreco = ModalNovoPreco.newInstance( this.rootPage );
            this.modalNovoPreco.setOnModalResult( modalResult -> {
                this.loadDatProduto();
            });
        }
    }

    private void closeDrawerDetails() {
        if( this.jfxDrawerProdutoDetails.isShown() )
            this.jfxDrawerProdutoDetails.close();
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

        public ProdutoModelView( Produto produto ) {
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
