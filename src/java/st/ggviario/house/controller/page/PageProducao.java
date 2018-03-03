package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modal.ModalNovaProducao;
import st.ggviario.house.controller.modal.ModalNovoSetor;
import st.ggviario.house.model.Producao;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.Setor;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class PageProducao extends RowsController< PageProducao.ProducaoModelView > implements Page, Initializable {

    @FXML private JFXTreeTableView< ProducaoModelView > treeTableProducao;
    @FXML private AnchorPane fabNewProducaoArea;
    @FXML  private JFXButton fabNewProducaoButtom;
    @FXML  private MaterialDesignIconView fabNewProducaoIcon;
    @FXML  private JFXListView< Setor > listViewSetor;
    @FXML private AnchorPane fabNewSetorArea;
    @FXML  private JFXButton fabNewSetorButton;
    @FXML private MaterialDesignIconView fabNewSetorIcon;


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
        this.push( producaoModelViewList, this.treeTableProducao );
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
    }

    private void defineEvents(){
        this.fabNewSetorArea.setOnMouseClicked(event -> onOpenModalNovoSetor());
        this.fabNewProducaoArea.setOnMouseClicked(event -> onOpemModalNovaProducao() );
        this.fabNewProducaoButtom.setOnMouseClicked( this.fabNewProducaoArea.getOnMouseClicked() );
        this.fabNewProducaoIcon.setOnMouseClicked( this.fabNewProducaoArea.getOnMouseClicked() );
    }

    private void loadData(){
        this.loadSetorData();
        this.loadDataProducao();
        this.loadDataProduto();
    }


    private void loadDataProducao(){

    }

    private void loadDataProduto(){
        this.produtoList.clear();
        Produto.ProdutoBuilder builder = new Produto.ProdutoBuilder();
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        sql.query( "funct_load_produto_producao" )
            .withJsonb( ( String ) null )
            .callFunctionTable()
                .onResultQuery(row -> produtoList.add( builder.load( row ).build() ));
    }

    private void loadSetorData(){
        Setor.SetorBuilder builder = new Setor.SetorBuilder();
        Setor.SetorBuilder setorSuperBuilder = new Setor.SetorBuilder();
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        this.setorProducaoList.clear();
        this.setorFechadoList.clear();
        sql.query( "funct_load_setor" )
            .withJsonb( ( String ) null )
            .callFunctionTable()
                .onResultQuery(row -> {
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
                if( modalResult.isSucceed() ){
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
                if( modalResult.isSucceed() ){
                    this.loadDataProducao();
                    this.push( this.producaoModelViewList, this.treeTableProducao );
                }
            });
        }
    }


    class ProducaoModelView extends RecursiveTreeObject< ProducaoModelView >{
        private Producao producto;
    }
}
