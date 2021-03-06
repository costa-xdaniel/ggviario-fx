package st.ggviario.house.control.tabs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableRow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.SnackbarBuilder;
import st.ggviario.house.control.TableClontroller;
import st.ggviario.house.control.component.ChoseControl;
import st.ggviario.house.control.drawers.DrawerObjectItem;
import st.ggviario.house.control.drawers.DrawerProduto;
import st.ggviario.house.control.modals.*;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TabPageProducaoProduto extends TableClontroller<TabPageProducaoProduto.ProdutoModelView > implements TabPage, Initializable{

    private static final String ITEM_UNIDADE = "unidade";
    private static final String ITEM_CATEGORIA = "categoria";

    @FXML private AnchorPane root;
    @FXML private HBox topArea;
    @FXML private JFXTreeTableView< ProdutoModelView > treeTableViewUnidade;
    @FXML private StackPane fabArea;
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
    private JFXTreeTableColumn< ProdutoModelView, String > columnEstado = new JFXTreeTableColumn<>();

    private ModalNovoProduto modalNovoProduto;
    private ModalNovoPreco modalNovoPreco;
    private DrawerProduto drawerProduto;
    private DrawerObjectItem drawerObjectItems;
    private ModalNovaCategoria modalNovaCategoria;
    private ModalNovaUnidade modalNovaUnidade;
    private ModalNovoLocalProducao modalNovoLocalProducao;
    private ModalDestroy< Preco > modalPrecoDestry;
    private ModalDestroy< Unidade > unidadeModalDestroy;
    private ModalDestroy< Categoria > categoriaModalDestroy;
    private ProdutoModelView lastProduto;

    private StackPane rootPage;
    private List< ProdutoModelView > originalProductList = new LinkedList<>();
    private ChoseControl itemChoseControl = new ChoseControl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
        this.push( new LinkedList<>(), this.treeTableViewUnidade );
        this.drawerObjectItems.loadUnidade();
        this.drawerObjectItems.loadCategoria();
    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    @Override
    public void onAfterOpen() {
        this.loadDatProduto();
    }

    private void structure(){
        JFXDepthManager.setDepth( this.fabArea, 4 );
        JFXDepthManager.pop( this.treeTableViewUnidade );
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

        this.columnStock.setCellFactory( this.cellNumber( NUMBER_FORMAT ) );
        this.columnVendas.setCellFactory( this.cellMoney( MONEY_FORMAT, "" ) );
        this.columnVendaVendas.setCellFactory( this.cellMoney( MONEY_FORMAT, "" ) );
        this.columnVendaDividas.setCellFactory( this.cellMoney( MONEY_FORMAT, "" ) );
        this.columnVendaPagas.setCellFactory( this.cellMoney( MONEY_FORMAT, "" ) );
        this.columnVendaPendentes.setCellFactory( this.cellMoney( MONEY_FORMAT, "" ) );
        this.columnCompras.setCellFactory( this.cellMoney( MONEY_FORMAT, "STN" ) );


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

        this.treeTableViewUnidade.getStyleClass().add( "produto" );
        this.treeTableViewUnidade.setRowFactory(produtoModelViewTreeTableView -> new TreeTableRow<TabPageProducaoProduto.ProdutoModelView>(){
            @Override
            protected void updateItem(ProdutoModelView item, boolean empty) {
                super.updateItem(item, empty);
                if( item != null && !empty ){
                    item.produto.getProdutoEstado().others().forEach( ( next ) -> this.getStyleClass().remove( next.name() ));
                    this.getStyleClass().add( item.produto.getProdutoEstado().name().toLowerCase() );
                } else setGraphic( null );
                setItem( item );
            }
        });

        this.columnNome.getStyleClass().add( CLASS_COLUMN_LEFT);
        this.columnStock.getStyleClass().add( CLASS_COLUMN_NUMBER );
        this.columnPreco.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnVendas.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnVendaVendas.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnVendaDividas.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnVendaPagas.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnVendaPendentes.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnCompras.getStyleClass().add( CLASS_COLUMN_MONEY );

        this.itemChoseControl
                .setIcon( new MaterialDesignIconView(MaterialDesignIcon.VIEW_LIST) )
                .newItem()
                .setText( "CATEGIORIA" )
                .setKey( ITEM_CATEGORIA )
                .setOnChose( item -> {
                    this.onOpenDrawerObjectType( DrawerObjectItem.ObjectType.CATEGORIA );
                })
                .append()
                .newItem()
                .setText( "UNIDADE" )
                .setKey( ITEM_UNIDADE )
                .setOnChose( item ->{
                    this.onOpenDrawerObjectType( DrawerObjectItem.ObjectType.UNIDADE );
                })
                .append();

        Pane pane = itemChoseControl.getRoot();
        this.topArea.getChildren().add( pane );
        this.jfxDrawerItems.close();
        this.jfxDrawerProdutoDetails.close();
        this.root.getChildren().remove( this.jfxDrawerItems );
        this.root.getChildren().remove( this.jfxDrawerProdutoDetails);

    }

    private void defineEvents() {
        this.fabArea.setOnMouseClicked(mouseEvent -> this.onOpenModalNovoProduto()  );
        this.fabButton.setOnMouseClicked( fabArea.getOnMouseClicked() );
        this.fabIcon.setOnMouseClicked( fabArea.getOnMouseClicked() );
        this.treeTableViewUnidade.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> onOpenDrawerProduto( newValue == null? null : newValue.getValue() ));
        this.jfxDrawerItems.setOnDrawerClosed(jfxDrawerEvent -> this.root.getChildren().remove( this.jfxDrawerItems ));
        this.jfxDrawerProdutoDetails.setOnDrawerClosed(jfxDrawerEvent -> this.root.getChildren().remove( this.jfxDrawerProdutoDetails));
        this.jfxDrawerItems.heightProperty().addListener((observableValue, oldValue, newValue) -> {
            if( this.drawerObjectItems != null ) drawerObjectItems.getRoot().setPrefHeight( newValue.doubleValue() );
        });
    }

    private void loadDatProduto() {
        Thread thread = new Thread(() -> {
            this.originalProductList.clear();
            Platform.runLater(() ->{
                this.treeTableViewUnidade.getRoot().getChildren().clear();
                this.treeTableViewUnidade.refresh();
            });

            Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
            Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();
            Preco.PrecoBuilder precoBuilder = new Preco.PrecoBuilder();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            sql.query( "ggviario.funct_load_produto" )
                    .withJsonb((String) null )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row ->{
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


                            if( this.lastProduto != null &&  this.lastProduto.equals( item ) ) {
                                this.treeTableViewUnidade.getSelectionModel().select( this.originalProductList.size() -1 );
                            }
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
                int index = this.root.getChildren().indexOf( this.fabArea );
                this.root.getChildren().add(index+1, this.jfxDrawerProdutoDetails);
            }

            this.jfxDrawerProdutoDetails.open();
            this.closeDrawerItem();
            this.lastProduto = newProduto;
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
            int index = this.root.getChildren().indexOf(  this.fabArea );
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
        this.modalNovoPreco.setTitle( "Unidade de "+produto.getProdutoNome() );
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

    private void onOpenEditarPreco(Preco preco ){
        this.loadModalNovoPreco( );
        this.modalNovoPreco.setTitle( "Editar "+preco.getUnidade().getUnidadeNome() );
        this.modalNovoPreco.openModalEditarPreco( preco );
    }

    private void onOpenModalNovoLocalProducao(Produto produto ){
        this.loadModalNovoLocalProducao();
        this.modalNovoLocalProducao.openModal( produto );
    }

    private void onChangeProdutoEstado( Produto produto ){
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();
            Produto.ProdutoBuilder builder = new Produto.ProdutoBuilder();
            sql.query( "funct_change_produto_estado" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( produto.getProdutoId() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        if( result.isSuccess() ){
                            Platform.runLater(() -> this.loadDatProduto());
                        } else {
                            SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.rootPage );
                            snackbarBuilder.showError( result.getMessage() );
                        }
                    });
            ;

        });
        thread.start();
    }

    private void onChangeLocalProducaoDisable( LocalProducao localProducao ) {
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();
            sql.query( "ggviario.funct_change_locaoproducao_disable" )
                    .withUUID( colaborador.getColaboradorId() )
                    .withUUID( localProducao.getLocalProducaoId() )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            SQLResult result = new SQLResult( row );
                            SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.rootPage );
                            if( result.isSuccess() ){
                                snackbarBuilder.show( "Local de produção fechado com sucesso!", SnackbarBuilder.MessageLevel.SUCCESS );
                                this.drawerProduto.notifyLocalProducao();
                            } else {
                                snackbarBuilder.show( result.getMessage(), SnackbarBuilder.MessageLevel.ERROR );
                            }
                        });
                    })
            ;
        });
        thread.start();
    }

    private void loadDrawerProduto() {
        if( this.drawerProduto == null ){
            this.drawerProduto = DrawerProduto.newInstance( this.jfxDrawerProdutoDetails);
            if( drawerProduto == null ) throw  new RuntimeException( "Para Qui" );
            this.drawerProduto.setOnNovoPreco(this::onOpenModalNovoPreco );
            this.drawerProduto.setOnProdutoEdit( this::onOpenModalNovoProdutoEditMode );
            this.drawerProduto.setOnPrecoDestroy(  this::onOpenModalDestroyPreco );
            this.drawerProduto.setOnEditarPreco( this::onOpenEditarPreco );
            this.drawerProduto.setOnChangeProdutoEstado( this::onChangeProdutoEstado );
            this.drawerProduto.setOnNovoLocalProducao( this::onOpenModalNovoLocalProducao );
            this.drawerProduto.onChangeLocalProducaoDisable( this::onChangeLocalProducaoDisable );
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

    private void loadModalNovoLocalProducao(){
        if( this.modalNovoLocalProducao == null ){
            this.modalNovoLocalProducao = ModalNovoLocalProducao.newInstance( this.rootPage );
            this.modalNovoLocalProducao.setOnModalResult( modalResult -> {
                if( modalResult.isSuccess() ){
                    this.drawerProduto.notifyLocalProducao();
                }
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ProdutoModelView)) return false;

            ProdutoModelView that = (ProdutoModelView) o;

            return produto.equals(that.produto);
        }

        @Override
        public int hashCode() {
            return produto.hashCode();
        }
    }
}
