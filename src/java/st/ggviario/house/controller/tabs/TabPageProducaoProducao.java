package st.ggviario.house.controller.tabs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.TableClontroller;
import st.ggviario.house.controller.modals.ModalNovaProducao;
import st.ggviario.house.controller.modals.ModalNovoSetor;
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
    @FXML private JFXButton fabNewProducaoButtom;
    @FXML private MaterialDesignIconView fabNewProducaoIcon;

    private TreeTableColumn< ProducaoModelView, Date > columnPoducaoData = new TreeTableColumn<>( "Data" );
    private TreeTableColumn< ProducaoModelView, String > columnProducaoProduto = new TreeTableColumn<>("Produto" );
    private TreeTableColumn< ProducaoModelView, String > columnProducaoSetor = new TreeTableColumn<>("Setor");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoQuantidadeTotal = new TreeTableColumn<>("Qt.");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoQuantidadeComercial = new TreeTableColumn<>("Qt. Com");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoQuantidadeDefeituosa = new TreeTableColumn<>("Qt. Def.");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoMontantePrevisto = new TreeTableColumn<>("Mont. Prev.");
    private TreeTableColumn< ProducaoModelView, Number > columnProducaoLancamento  = new TreeTableColumn<>("Lanc.");

    private List< ProducaoModelView > originalProducaoModelView;
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
        loadProducaoVistaProduto();

    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    private void init(){
        this.sectorAtivosList = new LinkedList<>();
        this.setorProducaoList = new LinkedList<>();
        this.setorFechadoList = new LinkedList<>();
        this.originalProducaoModelView = new LinkedList<>();
        this.produtoList = new LinkedList<>();
    }

    private void structure(){
        this.columnPoducaoData.setCellValueFactory( param -> param.getValue().getValue().producaoData );
        this.columnProducaoProduto.setCellValueFactory( param -> param.getValue().getValue().producaoProduto );
        this.columnProducaoSetor.setCellValueFactory( param -> param.getValue().getValue().producaoSector );
        this.columnProducaoQuantidadeTotal.setCellValueFactory(param -> param.getValue().getValue().producaoQuantidadeTotal);
        this.columnProducaoQuantidadeComercial.setCellValueFactory( param -> param.getValue().getValue().producaoQuantidadeComercial );
        this.columnProducaoQuantidadeDefeituosa.setCellValueFactory( param -> param.getValue().getValue().producaoQuantidadeDefeituosa );
        this.columnProducaoMontantePrevisto.setCellValueFactory( param -> param.getValue().getValue().producaoMontantePrevisto );
        this.columnProducaoLancamento.setCellValueFactory( param -> param.getValue().getValue().producaoQuantidadeLancamento);
        this.push( new LinkedList<>(), this.treeTableProducao );
    }

    private void defineEvents(){
        this.fabNewProducaoArea.setOnMouseClicked(event -> onOpemModalNovaProducao() );
        this.fabNewProducaoButtom.setOnMouseClicked( this.fabNewProducaoArea.getOnMouseClicked() );
        this.fabNewProducaoIcon.setOnMouseClicked( this.fabNewProducaoArea.getOnMouseClicked() );
    }

    private void loadData(){
        this.loadSetorData();
        this.loadDataProduto();
    }

    private void loadProducaoVistaProduto( ){
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
            Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
            this.originalProducaoModelView.clear();
            Platform.runLater( ( ) ->{
                this.treeTableProducao.getRoot().getChildren().clear();
                this.treeTableProducao.getColumns().setAll(
                        this.columnPoducaoData,
                        this.columnProducaoProduto,
                        this.columnProducaoQuantidadeTotal,
                        this.columnProducaoQuantidadeComercial,
                        this.columnProducaoQuantidadeDefeituosa,
                        this.columnProducaoMontantePrevisto,
                        this.columnProducaoLancamento
                );
            });

            sql.query( "funct_load_producao_vista_produto" )
                    .withJsonb( (String ) null)
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater( () -> {
                            Produto prduto = produtoBuilder.load(row.asMapJsonn("produto")).build();
                            ProducaoModelView producao;
                            this.originalProducaoModelView.add( producao =  new ProducaoModelView() );
                            producao.setProducaoData( row.asDate( "producao_data" ) );
                            producao.setProducaoProduto( prduto.getProdutoNome() );
                            producao.setProducaoQuantidadeTotal( row.asDouble( "producao_quantidadetotal" ) );
                            producao.setProducaoQuantidadeDefeituosa( row.asDouble( "producao_quantidadedefeituosa" ) );
                            producao.setProducaoQuantidadeComercial( row.asDouble( "producao_quantidadecomercial" ) );
                            producao.setProducaoMontantePrevisto( row.asDouble( "producao_montanteprevisto" ) );
                            producao.setProducaoQuantidadeLancamento( row.asDouble( "producao_quantidadelancamento" ) );
                            this.treeTableProducao.getRoot().getChildren().add( new TreeItem<>( producao ) );
                        });
                    })
            ;

        });

        thread.start();

    }

    private void loadDataProduto(){
        Thread thread = new Thread(() -> {
            this.produtoList.clear();
            Produto.ProdutoBuilder builder = new Produto.ProdutoBuilder();
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            sql.query( "funct_load_produto_producao" )
                    .withJsonb( ( String ) null )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        produtoList.add( builder.load( row ).build() );
                    });
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
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

        /*
        	Data,
				**Produto**,
				Total,
				Defeitusas,
				Comercial,
				Montante previsto ( Preco base ),
				Numero de lançamento
         */
        private ObjectProperty<Date> producaoData;
        private StringProperty producaoProduto;
        private StringProperty producaoSector;
        private ObjectProperty< Number > producaoQuantidadeTotal;
        private ObjectProperty< Number > producaoQuantidadeDefeituosa;
        private ObjectProperty< Number > producaoQuantidadeComercial;
        private ObjectProperty< Number > producaoMontantePrevisto;
        private ObjectProperty< Number > producaoQuantidadeLancamento;

        public ProducaoModelView() {
            this.producaoData = new SimpleObjectProperty<>();
            this.producaoProduto = new SimpleStringProperty();
            this.producaoSector = new SimpleStringProperty();
            this.producaoQuantidadeTotal = new SimpleObjectProperty<>();
            this.producaoQuantidadeDefeituosa = new SimpleObjectProperty<>();
            this.producaoQuantidadeComercial = new SimpleObjectProperty<>();
            this.producaoMontantePrevisto = new SimpleObjectProperty<>();
            this.producaoQuantidadeLancamento = new SimpleObjectProperty<>();
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

        public void setProducaoQuantidadeTotal(Number producaoQuantidadeTotal) {
            this.producaoQuantidadeTotal.set(producaoQuantidadeTotal);
        }

        public void setProducaoQuantidadeLancamento(Number producaoQuantidadeLancamento) {
            this.producaoQuantidadeLancamento.set(producaoQuantidadeLancamento);
        }

        public void setProducaoQuantidadeDefeituosa(Number producaoQuantidadeDefeituosa) {
            this.producaoQuantidadeDefeituosa.set(producaoQuantidadeDefeituosa);
        }

        public void setProducaoQuantidadeComercial(Number producaoQuantidadeComercial) {
            this.producaoQuantidadeComercial.set(producaoQuantidadeComercial);
        }

        public void setProducaoMontantePrevisto(Number producaoMontantePrevisto) {
            this.producaoMontantePrevisto.set(producaoMontantePrevisto);
        }
    }
}
