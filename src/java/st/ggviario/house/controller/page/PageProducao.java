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
import st.ggviario.house.controller.modal.ModalNovoSetor;
import st.ggviario.house.model.Producao;
import st.ggviario.house.model.Setor;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class PageProducao extends RowsController< PageProducao.ProducaoModelView > implements Page, Initializable {

    @FXML private JFXTreeTableView< ProducaoModelView > tableProducao;
    @FXML private AnchorPane fabNewPorducaoArea;
    @FXML  private JFXButton fabNewPorducaoButtom;
    @FXML  private MaterialDesignIconView fabNewPorducaoIcon;
    @FXML  private JFXListView< Setor > listViewSetor;
    @FXML private AnchorPane fabNewCategoriaArea;
    @FXML  private JFXButton fabNewCategoriaButton;
    @FXML private MaterialDesignIconView fabNewCategoriaIcon;


    private List< ProducaoModelView > producaoModelViewList;
    private List<Setor> setorAtivoList;
    private List<Setor> setorFechadoList;

    private ModalNovoSetor modalNovoSetor;
    private StackPane rootPage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.init();
        this.structure();
        this.defineEvents();
        this.loadData();
        this.pushSetorAtivoProtegido();
        this.push( producaoModelViewList, this.tableProducao );
    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    private void init(){
        this.setorAtivoList = new LinkedList<>();
        this.setorFechadoList = new LinkedList<>();
        this.producaoModelViewList = new LinkedList<>();
    }

    private void structure(){

    }

    private void defineEvents(){
        this.fabNewCategoriaArea.setOnMouseClicked(event -> {
            openModalNovoSetor();
        });
    }

    private void loadData(){
        this.loadSetorData();
        this.loadProducaoData();
    }


    private void loadProducaoData(){

    }

    private void loadSetorData(){
        Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        sql.query( "funct_load_setor" )
            .withJsonb( ( String ) null )
            .callFunctionTable()
                .onResultQuery(row -> {
                    Setor setor = setorBuilder.load( row ).build();
                    if(SQLResource.existIn( setor.getSetorEstado(), Setor.SetorEstado.ATIVO, Setor.SetorEstado.PROTEGIDO ) )
                        this.setorAtivoList.add( setor );
                    else this.setorFechadoList.add( setor );
                })
        ;
    }

    private void pushSetorAtivoProtegido(){
        this.listViewSetor.setItems(FXCollections.observableList( this.setorAtivoList ) );

    }

    private void pushSetorFechadoProtegido(){
        this.listViewSetor.setItems(FXCollections.observableList( this.setorFechadoList ) );
    }

    private void openModalNovoSetor( ){
        loadModalNovoSetor();
        this.modalNovoSetor.openModal();
    }


    private void loadModalNovoSetor(){
        if( this.modalNovoSetor == null ){
            this.modalNovoSetor = ModalNovoSetor.load( this.rootPage );
            this.modalNovoSetor.setOnModalResult(modalResult -> {
                if( modalResult.isSucceed() ){
                    this.loadSetorData();
                    this.pushSetorAtivoProtegido();
                }
            });
        }
    }


    class ProducaoModelView extends RecursiveTreeObject< ProducaoModelView >{
        private Producao producto;
    }
}
