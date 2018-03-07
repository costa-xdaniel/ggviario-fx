package st.ggviario.house.controller.tabs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modals.ModalNovaProducao;
import st.ggviario.house.controller.modals.ModalNovoSetor;
import st.ggviario.house.controller.pages.TableClontroller;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.Setor;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class TabPageProducaoProducao extends TableClontroller< TabPageProducaoProducao.ProducaoModelView > implements TabPage, Initializable {

    @FXML private JFXTreeTableView< ProducaoModelView > treeTableProducao;
    @FXML private AnchorPane fabNewProducaoArea;
    @FXML  private JFXButton fabNewProducaoButtom;
    @FXML  private MaterialDesignIconView fabNewProducaoIcon;
    @FXML  private JFXListView< Setor > listViewSetor;
    @FXML private AnchorPane fabNewSetorArea;
    @FXML  private JFXButton fabNewSetorButton;
    @FXML private MaterialDesignIconView fabNewSetorIcon;

    private TreeTableColumn< ProducaoModelView, Date > columnPoducaoData = new TreeTableColumn<>( "Data" );
    private TreeTableColumn< ProducaoModelView, String > columnProducaoProduto = new TreeTableColumn<>("Produto" );
    private TreeTableColumn< ProducaoModelView, String > columnProducaoSetor = new TreeTableColumn<>("Setor");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoQuantidade = new TreeTableColumn<>("Qt.");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoLancamento  = new TreeTableColumn<>("Lanc.");

    private List< ProducaoModelView > producaoModelViewList;
    private List< Setor> setorProducaoList;
    private List< Setor> sectorAtivosList;
    private List< Setor> setorFechadoList;
    private List< Produto > produtoList;

    private ModalNovoSetor modalNovoSetor;
    private ModalNovaProducao modalNovaProducao;
    private StackPane rootPage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.init();
        this.structure();
        this.defineEvents();
        this.loadData();
        this.pushSetor( this.setorProducaoList );
        pushFunctLodaProducao();

    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    private void init(){
        this.sectorAtivosList = new LinkedList<>();
        this.setorProducaoList = new LinkedList<>();
        this.setorFechadoList = new LinkedList<>();
        this.producaoModelViewList = new LinkedList<>();
        this.produtoList = new LinkedList<>();
    }

    private void structure(){
        this.columnPoducaoData.setCellValueFactory( param -> param.getValue().getValue().producaoData );
        this.columnProducaoProduto.setCellValueFactory( param -> param.getValue().getValue().producaoProduto );
        this.columnProducaoSetor.setCellValueFactory( param -> param.getValue().getValue().producaoSector );
        this.columnProducaoQuantidade.setCellValueFactory( param -> param.getValue().getValue().producaoQuantidade );
        this.columnProducaoLancamento.setCellValueFactory( param -> param.getValue().getValue().producaoLancamento );
    }

    private void defineEvents(){
        this.fabNewSetorArea.setOnMouseClicked(event -> onOpenModalNovoSetor());
        this.fabNewProducaoArea.setOnMouseClicked(event -> onOpemModalNovaProducao() );
        this.fabNewProducaoButtom.setOnMouseClicked( this.fabNewProducaoArea.getOnMouseClicked() );
        this.fabNewProducaoIcon.setOnMouseClicked( this.fabNewProducaoArea.getOnMouseClicked() );
    }

    private void loadData(){
        this.loadSetorData();
        this.loadDataProduto();
    }

    private void pushFunctLodaProducao( ){
        PostgresSQL sql = PostgresSQLSingleton.getInstance();
        this.producaoModelViewList.clear();
        sql.query( "funct_load_producao" )
            .withJsonb( (String ) null)
            .callFunctionTable()
                .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                    ProducaoModelView producao;
                    this.producaoModelViewList.add( producao =  new ProducaoModelView() );
                    producao.setProducaoData( row.asDate( "producao_data" ) );
                    producao.setProducaoLancamento( row.asDouble( "producao_lancamento" ) );
                    producao.setProducaoProduto( row.asString( "produto_nome" ) );
                    producao.setProducaoSector( row.asString( "setor_nome" ) );
                    producao.setProducaoQuantidade( row.asDouble( "producao_quantidade" ) );
                })
        ;

        this.treeTableProducao.getColumns().setAll(
                this.columnPoducaoData,
                this.columnProducaoProduto,
                this.columnProducaoSetor,
                this.columnProducaoQuantidade,
                this.columnProducaoLancamento
        );

        this.push( this.producaoModelViewList, this.treeTableProducao );
    }

    private void loadDataProduto(){
        this.produtoList.clear();
        Produto.ProdutoBuilder builder = new Produto.ProdutoBuilder();
        PostgresSQL sql = PostgresSQLSingleton.getInstance();
        sql.query( "funct_load_produto_producao" )
            .withJsonb( ( String ) null )
            .callFunctionTable()
                .onResultQuery(row -> produtoList.add( builder.load( row ).build() ));
    }

    private void loadSetorData(){
        Setor.SetorBuilder builder = new Setor.SetorBuilder();
        Setor.SetorBuilder setorSuperBuilder = new Setor.SetorBuilder();
        PostgresSQL sql = PostgresSQLSingleton.getInstance();
        this.setorProducaoList.clear();
        this.setorFechadoList.clear();
        sql.query( "funct_load_setor" )
            .withJsonb( ( String ) null )
            .callFunctionTable()
                .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                    builder.load( row );

                    if( row.get( "setor_super" ) !=  null ){
                        setorSuperBuilder.load( row.asMapJsonn( "setor_super" ) );
                        builder.setSetorSuper( setorSuperBuilder.build() );
                    }

                    Setor setor  = builder.build();
                    if(SQLResource.existIn( setor.getSetorEstado(), Setor.SetorEstado.ATIVO, Setor.SetorEstado.PROTEGIDO ) )
                        this.setorProducaoList.add( setor );
                    else this.setorFechadoList.add( setor );

                    if(SQLResource.existIn( setor.getSetorEstado(), Setor.SetorEstado.ATIVO ) ) this.sectorAtivosList.add( setor );
                })
        ;
    }

    private void pushSetor(List<Setor> setorList ){
        this.listViewSetor.setItems(FXCollections.observableList( setorList ) );
        this.listViewSetor.refresh();

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
                    this.loadSetorData();
                    this.pushSetor( this.setorProducaoList);
                }
            });
        }
    }

    private void loadModalNovaProducao(){
        if( this.modalNovaProducao == null ){
            this.modalNovaProducao = ModalNovaProducao.load( this.rootPage );
            this.modalNovaProducao.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){
                    this.pushFunctLodaProducao();
                    this.push( this.producaoModelViewList, this.treeTableProducao );
                }
            });
        }
    }


    class ProducaoModelView extends RecursiveTreeObject< ProducaoModelView >{

        private ObjectProperty<Date> producaoData;
        private StringProperty producaoProduto;
        private StringProperty producaoSector;
        private ObjectProperty< Number > producaoQuantidade;
        private ObjectProperty< Number > producaoLancamento;

        public ProducaoModelView() {
            this.producaoData = new SimpleObjectProperty<>();
            this.producaoProduto = new SimpleStringProperty();
            this.producaoSector = new SimpleStringProperty();
            this.producaoQuantidade = new SimpleObjectProperty<>();
            this.producaoLancamento = new SimpleObjectProperty<>();
        }

        public void setProducaoData(Date producaoData) {
            this.producaoData.set(producaoData);
        }

        public void setProducaoProduto(String producaoProduto) {
            this.producaoProduto.set(producaoProduto);
        }

        public void setProducaoSector(String producaoSector) {
            this.producaoSector.set(producaoSector);
        }

        public void setProducaoQuantidade(Number producaoQuantidade) {
            this.producaoQuantidade.set(producaoQuantidade);
        }

        public void setProducaoLancamento(Number producaoLancamento) {
            this.producaoLancamento.set(producaoLancamento);
        }
    }
}
