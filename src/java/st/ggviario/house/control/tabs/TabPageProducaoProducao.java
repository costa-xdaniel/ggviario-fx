package st.ggviario.house.control.tabs;

import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.TableClontroller;
import st.ggviario.house.control.component.ChoseControl;
import st.ggviario.house.control.drawers.DrawerProducaoProduto;
import st.ggviario.house.control.drawers.DrawerProducaoSetor;
import st.ggviario.house.control.modals.ModalNovaProducao;
import st.ggviario.house.control.modals.ModalNovoSetor;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.model.Producao;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.Setor;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


public class TabPageProducaoProducao extends TableClontroller< TabPageProducaoProducao.ProducaoModelView > implements TabPage, Initializable {

    @FXML private AnchorPane root;
    @FXML private JFXDrawer jfxDrawerItems;
    @FXML private HBox topArea;

    @FXML private JFXTreeTableView< ProducaoModelView > treeTableProducao;
    @FXML private AnchorPane fabNewProducaoArea;
    @FXML  private JFXButton fabNewProducaoButtom;
    @FXML  private MaterialDesignIconView fabNewProducaoIcon;
    @FXML  private JFXListView< Setor > listViewSetor;

    private String ITEM_PRODUTO = "produto";
    private String ITEM_SETOR = "setor";
    private String ITEM_DAY = "day";
    private String ITEM_MONTH = "month";
    private String ITEM_YEAR = "year";


    private TreeTableColumn< ProducaoModelView, Date > columnPoducaoData = new TreeTableColumn<>( "DATA" );
    private TreeTableColumn< ProducaoModelView, Produto > columnProducaoProduto = new TreeTableColumn<>("PRODUTO" );
    private TreeTableColumn< ProducaoModelView, Setor > columnProducaoSetor = new TreeTableColumn<>("SETOR");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoQuantidadeTotal = new TreeTableColumn<>("QT.");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoQuantidadeComerciavel = new TreeTableColumn<>("COM.");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoQuantidadeDefeituosa = new TreeTableColumn<>("DEF.");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoMontantePrevisto = new TreeTableColumn<>("PREVISTO");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoLancamentos  = new TreeTableColumn<>("LANCH.");

    private List< ProducaoModelView > originalModelViewList;
    private List< Setor> setorProducaoList;
    private List< Setor> sectorAtivosList;
    private List< Produto > produtoList;
    private DrawerProducaoSetor drawerProducaoSetor;
    private DrawerProducaoProduto drawerProducaoProduto;

    private ModalNovoSetor modalNovoSetor;
    private ModalNovaProducao modalNovaProducao;
    private StackPane rootPage;

    private ChoseControl itemChoseControl = new ChoseControl();
    private ChoseControl dateFormatChoseControl = new ChoseControl();
    private String localFormat;
    private String dataBaseKey;


    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        this.init();
        this.structure();
        this.defineEvents();
        this.loadData();
    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    private void init(){
        this.sectorAtivosList = new LinkedList<>();
        this.setorProducaoList = new LinkedList<>();
        this.originalModelViewList = new LinkedList<>();
        this.produtoList = new LinkedList<>();
    }

    private void structure(){
        this.columnPoducaoData.setCellValueFactory( param -> param.getValue().getValue().producaoData );
        this.columnProducaoProduto.setCellValueFactory( param -> param.getValue().getValue().producaoProduto );
        this.columnProducaoSetor.setCellValueFactory( param -> param.getValue().getValue().producaoSector );
        this.columnProducaoQuantidadeTotal.setCellValueFactory(param -> param.getValue().getValue().producaoQuantidadeTotal);
        this.columnProducaoQuantidadeComerciavel.setCellValueFactory(param -> param.getValue().getValue().producaoQuantidadeComerciavel);
        this.columnProducaoQuantidadeDefeituosa.setCellValueFactory(param -> param.getValue().getValue().producaoQuantidadeDefeituosa);
        this.columnProducaoMontantePrevisto.setCellValueFactory(param -> param.getValue().getValue().producaoMontantePrevisto);
        this.columnProducaoLancamentos.setCellValueFactory( param -> param.getValue().getValue().producaoLancamentos);
        this.push( new LinkedList<>(), this.treeTableProducao );

        this.itemChoseControl
        .setIcon(new MaterialDesignIconView(MaterialDesignIcon.VIEW_LIST), this::onClickIconItems)
        .newItem()
            .setText( "PRODUTO" )
            .setKey( ITEM_PRODUTO )
            .setOnChose(item -> this.loadProducaoVistaProduto())
            .append()
        .newItem()
            .setText( "SECTOR" )
            .setKey( ITEM_SETOR )
            .setOnChose( item -> this.loadProducaoVistaSector() )
            .append();

        Pane pane = itemChoseControl.getRoot();
        this.topArea.getChildren().add( pane );


        dateFormatChoseControl.setIcon( new MaterialDesignIconView( MaterialDesignIcon.CALENDAR_TODAY ) )
        .newItem() .setText( "DIARIO" )
            .setOnChose( item -> onChoseFormat( "dd-MM-yyyy" , ITEM_DAY ) )
            .append()
        .newItem()
            .setText( "MENSAL" )
            .setOnChose( item ->onChoseFormat( "MMMM 'de' yyyy", ITEM_MONTH ) )
            .append()
        .newItem()
            .setText( "ANUAL" )
            .setOnChose( item -> onChoseFormat( "yyyy", ITEM_YEAR ) )
            .append()
        ;


        pane = dateFormatChoseControl.getRoot();
        this.topArea.getChildren().add( pane );
        HBox.setMargin( pane, new Insets( 0, 0, 0, 16));
        this.root.getChildren().remove( this.jfxDrawerItems );
    }

    private void defineEvents(){
        this.fabNewProducaoArea.setOnMouseClicked(event -> onOpemModalNovaProducao() );
        this.fabNewProducaoButtom.setOnMouseClicked( this.fabNewProducaoArea.getOnMouseClicked() );
        this.fabNewProducaoIcon.setOnMouseClicked( this.fabNewProducaoArea.getOnMouseClicked() );
    }

    private void loadData(){
        this.loadDataProduto();
    }

    private void onChoseFormat(String localFormat, String dataBaseKey ){
        this.localFormat = localFormat;
        this.dataBaseKey = dataBaseKey;
        this.columnPoducaoData.setCellFactory( cellDateFormat( new SimpleDateFormat( localFormat, Locale.forLanguageTag("pt") ) ));
        if( this.itemChoseControl.getChosenItem() != null && this.itemChoseControl.getChosenItem().getOnChose() != null ){
            this.itemChoseControl.getChosenItem().getOnChose().onChoseMe( this.itemChoseControl.getChosenItem() );
        }
    }

    private void onClickIconItems(){
        if( this.itemChoseControl.getChosenItem() == null ) return;


        if( this.itemChoseControl.getChosenItem().getKey().equals( ITEM_SETOR ) ){
            this.loadDrawerProducaoSetor();
            this.jfxDrawerItems.setSidePane( this.drawerProducaoSetor.getRoot() );

        } else if( this.itemChoseControl.getChosenItem().getKey().equals( ITEM_PRODUTO ) ){
            this.loadDrawerProcucaoProduto();
            this.jfxDrawerItems.setSidePane( this.drawerProducaoProduto.getRoot() );
        }

        if( !this.root.getChildren().contains( this.jfxDrawerItems) ){
            int index = this.root.getChildren().indexOf(  this.fabNewProducaoArea );
            this.root.getChildren().add(index+1, this.jfxDrawerItems);
        }
        this.jfxDrawerItems.open();
    }

    private void loadDrawerProducaoSetor(){
        if( this.drawerProducaoSetor == null ){
            this.drawerProducaoSetor = DrawerProducaoSetor.newInstance();
        }
    }

    private void loadDrawerProcucaoProduto(){
        if( this.drawerProducaoProduto == null ){
            this.drawerProducaoProduto = DrawerProducaoProduto.newInstance();
        }
    }

    private void loadProducaoVistaProduto( ){
        if( this.dateFormatChoseControl.getChosenItem() == null ) return;
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Producao.ProducaoBuilder producaoBuilder = new Producao.ProducaoBuilder();
            Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
            Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();

            this.originalModelViewList.clear();
            Platform.runLater(() -> {
                this.treeTableProducao.getRoot().getChildren().clear();
                this.treeTableProducao.getColumns().setAll(
                        this.columnPoducaoData,
                        this.columnProducaoProduto,
                        this.columnProducaoQuantidadeTotal,
                        this.columnProducaoQuantidadeComerciavel,
                        this.columnProducaoQuantidadeDefeituosa,
                        this.columnProducaoMontantePrevisto,
                        this.columnProducaoLancamentos
                );
                this.treeTableProducao.refresh();
            });

            JsonObject jsonb = new JsonObject();
            jsonb.addProperty("format_type", this.dataBaseKey );

            sql.query( "funct_load_producao_vista_produto" )
                    .withJsonb( jsonb )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            producaoBuilder.load( row );
                            produtoBuilder.load( row.asMapJsonn("produto" ) );
                            categoriaBuilder.load( row.asMapJsonn( "categoria" ) );
                            produtoBuilder.setCategoria( categoriaBuilder.build() );
                            producaoBuilder.setProduto( produtoBuilder.build() );
                            ProducaoModelView producao = new ProducaoModelView( producaoBuilder.build() );
                            this.treeTableProducao.getRoot().getChildren().add( new TreeItem<>( producao ) );
                            this.originalModelViewList.add( producao  );
                        });
                    })
            ;
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }


    private void loadProducaoVistaSector( ){
        if( this.dateFormatChoseControl.getChosenItem() == null ) return;
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Producao.ProducaoBuilder producaoBuilder = new Producao.ProducaoBuilder();
            Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
            Setor.SetorBuilder setor_super = new Setor.SetorBuilder();
            this.originalModelViewList.clear();
            Platform.runLater(() -> {
                this.treeTableProducao.getRoot().getChildren().clear();
                this.treeTableProducao.getColumns().setAll(
                        this.columnPoducaoData,
                        this.columnProducaoSetor,
                        this.columnProducaoQuantidadeTotal,
                        this.columnProducaoQuantidadeComerciavel,
                        this.columnProducaoQuantidadeDefeituosa,
                        this.columnProducaoMontantePrevisto,
                        this.columnProducaoLancamentos
                );
                this.treeTableProducao.refresh();
            });
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("format_type", this.dataBaseKey );

            sql.query( "funct_load_producao_vista_setor" )
                    .withJsonb( jsonObject )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            producaoBuilder.load( row );
                            setorBuilder.load( row.asMapJsonn("setor" ) );

                            if(  row.asMapJsonn("setor_super" ) != null ) {
                                setor_super.load( row.asMapJsonn("setor_super" ) );
                                setorBuilder.setSetorSuper( setor_super.build() );
                            }
                            setorBuilder.setSetorSuper( setor_super.build() );
                            producaoBuilder.setSetor( setorBuilder.build() );
                            ProducaoModelView producao = new ProducaoModelView( producaoBuilder.build() );
                            this.treeTableProducao.getRoot().getChildren().add( new TreeItem<>( producao ) );
                            this.originalModelViewList.add( producao  );
                        });
                    })
            ;
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    private void loadDataProduto(){
        Thread thread = new Thread(() -> {
            this.produtoList.clear();
            Produto.ProdutoBuilder builder = new Produto.ProdutoBuilder();
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            JsonObject jsonObject = new JsonObject();


            sql.query( "funct_load_produto_producao" )
                    .withJsonb( jsonObject )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        produtoList.add( builder.load( row ).build() );
                    });
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    private void onOpenModalNovoSetor( ){
        loadModalNovoSetor();
        this.modalNovoSetor.pushSetorSupers( this.setorProducaoList);
        this.modalNovoSetor.openModal();
    }

    private void onOpemModalNovaProducao(){
        this.loadModalNovaProducao();
        this.modalNovaProducao.pushSetor( this.sectorAtivosList );
        this.modalNovaProducao.pushProduto( this.produtoList );
        this.modalNovaProducao.openModal();
    }


    private void loadModalNovoSetor(){
        if( this.modalNovoSetor == null ){
            this.modalNovoSetor = ModalNovoSetor.load( this.rootPage );
            this.modalNovoSetor.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){
                }
            });
        }
    }

    private void loadModalNovaProducao(){
        if( this.modalNovaProducao == null ){
            this.modalNovaProducao = ModalNovaProducao.load( this.rootPage );
            this.modalNovaProducao.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){
                    this.loadProducaoVistaProduto();
                }
            });
        }
    }


    class ProducaoModelView extends RecursiveTreeObject< ProducaoModelView >{

        private final Producao producao;
        private ObjectProperty< Date > producaoData;
        private ObjectProperty< Produto > producaoProduto;
        private ObjectProperty< Setor > producaoSector;
        private ObjectProperty< Number > producaoQuantidadeTotal;
        private ObjectProperty< Number > producaoQuantidadeComerciavel;
        private ObjectProperty< Number > producaoQuantidadeDefeituosa;
        private ObjectProperty< Number > producaoMontantePrevisto;
        private ObjectProperty< Number > producaoLancamentos;

        public ProducaoModelView(Producao producao ) {
            this.producaoData = new SimpleObjectProperty<>( producao.getProducaoData() );
            this.producaoProduto = new SimpleObjectProperty<>( producao.getProduto() );
            this.producaoSector = new SimpleObjectProperty<>( producao.getSetor() );
            this.producaoQuantidadeTotal = new SimpleObjectProperty<>( producao.getProducaoQuantidadeTotal() );
            this.producaoQuantidadeComerciavel = new SimpleObjectProperty<>( producao.getProducaoQuantidadeComerciavel() );
            this.producaoQuantidadeDefeituosa = new SimpleObjectProperty<>( producao.getProducaoQuantidadeDefeituosa() );
            this.producaoMontantePrevisto = new SimpleObjectProperty<>( producao.getProducaoMontantePrevisto() );
            this.producaoLancamentos = new SimpleObjectProperty<>( producao.getProducaoLancamentos() );
            this.producao = producao;
        }

    }
}
