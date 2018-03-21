package st.ggviario.house.control.tabs;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import st.ggviario.house.control.modals.ModalNovoFornecedor;
import st.ggviario.house.control.TableClontroller;
import st.ggviario.house.model.Fornecedor;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TabPagePessoalFornecedor extends TableClontroller< TabPagePessoalFornecedor.FornecedorViewModel > implements TabPage, Initializable {

    @FXML private AnchorPane root;
    @FXML private JFXTreeTableView< FornecedorViewModel > tableViewFornecedor;
    @FXML private JFXDrawer drawerFornecedor;
    @FXML private StackPane fabArea;
    @FXML private JFXButton fabButton;

    private List<TreeItem<FornecedorViewModel>> listForenecedor = new LinkedList<TreeItem<FornecedorViewModel>>();

    private StackPane rootPage;
    private ModalNovoFornecedor modalNovoFornecedor;
    private JFXTreeTableColumn< FornecedorViewModel, String > columnFornecedorNIF = new JFXTreeTableColumn<>( "NIF" );
    private JFXTreeTableColumn< FornecedorViewModel, String > columnFornecedorNome = new JFXTreeTableColumn<>( "NOME" );
    private JFXTreeTableColumn< FornecedorViewModel, String > columnFornecedorTelemovel = new JFXTreeTableColumn<>( "TELEMOVEL" );
    private JFXTreeTableColumn< FornecedorViewModel, String > columnFornecedorTelefone = new JFXTreeTableColumn<>( "TELEFONE" );
    private JFXTreeTableColumn< FornecedorViewModel, Number > columnFornecedorCompras = new JFXTreeTableColumn<>( "COMPRAS" );
    private JFXTreeTableColumn< FornecedorViewModel, Number > columnFornecedorComprasPagas = new JFXTreeTableColumn<>( "PAGOS" );
    private JFXTreeTableColumn< FornecedorViewModel, Number > columnFornecedorComprasPendentes = new JFXTreeTableColumn<>( "PENDENTES" );
    private JFXTreeTableColumn< FornecedorViewModel, Date > columnFornecedorRegisto = new JFXTreeTableColumn<>( "REGISTO" );
    private String oldTextSearch;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
    }

    @Override
    public void onAfterOpen() {
        this.loadFornecedorData();
    }

    @Override
    public void onSetRootPage( StackPane rootPage) {
        this.rootPage = rootPage;
    }

    private void structure(){
        JFXDepthManager.setDepth( this.fabArea, 4 );
        Text text = new Text( "Nenhum fornecedor encontrado!" );
        text.getStyleClass().add( "table-no-data-found" );

        this.tableViewFornecedor.setPlaceholder( text  );
        this.columnFornecedorNIF.setCellValueFactory( param -> param.getValue().getValue().fornecedorNif );
        this.columnFornecedorNome.setCellValueFactory( param -> param.getValue().getValue().fornecedorNome );
        this.columnFornecedorTelemovel.setCellValueFactory( param -> param.getValue().getValue().fornecedorTelemovel );
        this.columnFornecedorTelefone.setCellValueFactory( param -> param.getValue().getValue().fornecedorTelefone );
        this.columnFornecedorCompras.setCellValueFactory( param -> param.getValue().getValue().fornecedorCompras );
        this.columnFornecedorComprasPagas.setCellValueFactory( param -> param.getValue().getValue().fornecedorComprasPagas );
        this.columnFornecedorComprasPendentes.setCellValueFactory( param -> param.getValue().getValue().fornecedorComprasPenentes  );
        this.columnFornecedorRegisto.setCellValueFactory( param -> param.getValue().getValue().fornecedorDataRegisto );
        this.tableViewFornecedor.getColumns().setAll(
            this.columnFornecedorNIF,
            this.columnFornecedorNome,
            this.columnFornecedorTelemovel,
            this.columnFornecedorTelefone,
            this.columnFornecedorCompras,
            this.columnFornecedorComprasPagas,
            this.columnFornecedorComprasPendentes,
            this.columnFornecedorRegisto
        );
        this.push( new LinkedList<>(), this.tableViewFornecedor );

        this.columnFornecedorNIF.getStyleClass().add( CLASS_COLUMN_NIF );
        this.columnFornecedorRegisto.setCellFactory( cellDateFormat(DD_MM_YYYY_FORMAT) );
        this.columnFornecedorNome.getStyleClass().add( CLASS_COLUMN_LEFT );
        this.columnFornecedorTelemovel.getStyleClass().add( CLASS_COLUMN_LEFT  );
        this.columnFornecedorTelefone.getStyleClass().add( CLASS_COLUMN_LEFT );
        this.columnFornecedorCompras.getStyleClass().add( CLASS_COLUMN_MONEY  );
        this.columnFornecedorComprasPagas.getStyleClass().add( CLASS_COLUMN_MONEY  );
        this.columnFornecedorComprasPendentes.getStyleClass().add( CLASS_COLUMN_MONEY );

    }


    private void defineEvents(){
        this.fabButton.setOnAction(actionEvent -> this.onOpenModalNovoFornecedor() );
    }

    private void onOpenModalNovoFornecedor(){
        this.loadModalNovoForncedro();
        this.modalNovoFornecedor.openModal();
    }

    private void loadModalNovoForncedro(){
        if( this.modalNovoFornecedor == null ){
            this.modalNovoFornecedor = ModalNovoFornecedor.load((StackPane) this.rootPage );
            this.modalNovoFornecedor.setOnModalResult(modalResult -> {
                this.loadFornecedorData();
            });
        }
    }

    private void loadFornecedorData( ){
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Fornecedor.FornecedorBuilder builder = new Fornecedor.FornecedorBuilder();
            Platform.runLater(() -> {
                this.listForenecedor.clear();
                this.tableViewFornecedor.getRoot().getChildren().clear();
                this.tableViewFornecedor.refresh();
            });
            sql.query( "funct_load_fornecedor" )
                    .withJsonb(  ( String ) null )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            TreeItem<FornecedorViewModel> fornecedorRow = new TreeItem<>(new FornecedorViewModel(builder.load(row).build()));
                            this.tableViewFornecedor.getRoot().getChildren().add( fornecedorRow );
                            this.listForenecedor.add( fornecedorRow );
                        });
                    });

        });

        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    @Override
    public void onSearch(KeyEvent event, String textFilter) {
//        if( event == null && textFilter == null ) return;
//
//        if( event  != null && event.getCode() == KeyCode.ENTER ){
//            this.loadFornecedorData();
//        }
//
//        if( textFilter == null ){
//            return;
//        } else if( !textFilter.equals( this.oldTextSearch ) || (  event != null && event.getCode() == KeyCode.ENTER ) ) {
//            List<TreeItem<FornecedorViewModel>> searchResult = new LinkedList<>();
//            for( TreeItem<FornecedorViewModel> model : this.listForenecedor ){
//                if( model.getValue().fornecedor.getFornecedorNome().toLowerCase().contains( textFilter ) ) searchResult.add( model );
//            }
//            ObservableList<TreeItem<FornecedorViewModel>> observableListVenda = FXCollections.observableList( searchResult );
//            final TreeItem< FornecedorViewModel > root = new RecursiveTreeItem<>( observableListVenda, RecursiveTreeObject::getChildren );
//            this.tableViewFornecedor.setRoot( root );
//            this.tableViewFornecedor.setShowRoot( false );
//            this.tableViewFornecedor.refresh();
//        }
//
//        this.oldTextSearch = textFilter;
    }

    class FornecedorViewModel extends  RecursiveTreeObject<FornecedorViewModel> {

        private StringProperty fornecedorNif;
        private StringProperty fornecedorNome;
        private StringProperty fornecedorTelefone;
        private StringProperty fornecedorTelemovel;
        private ObjectProperty<Number> fornecedorCompras;
        private ObjectProperty<Number> fornecedorComprasPagas;
        private ObjectProperty<Number> fornecedorComprasPenentes;
        private ObjectProperty<Date> fornecedorDataRegisto;
        private Fornecedor fornecedor;

        public FornecedorViewModel( Fornecedor fornecedor ) {
            this.fornecedor = fornecedor;
            this.fornecedorNome = new SimpleStringProperty( this.fornecedor.getFornecedorNome() );
            this.fornecedorNif = new SimpleStringProperty( this.fornecedor.getFornecedorNif() );
            this.fornecedorTelefone = new SimpleStringProperty( this.fornecedor.getFornecedorTelefone() );
            this.fornecedorTelemovel = new SimpleStringProperty( this.fornecedor.getFornecedorTelemovel() );
            this.fornecedorDataRegisto = new SimpleObjectProperty<>(this.fornecedor.getFornecedorDataregisto());
            this.fornecedorCompras = new SimpleObjectProperty<>( this.fornecedor.getFornecedorMontanteCompras() );
            this.fornecedorComprasPenentes = new SimpleObjectProperty<>( this.fornecedor.getFornecedorMonntantePendentes() );
            this.fornecedorComprasPagas = new SimpleObjectProperty<>( this.fornecedor.getFornecedorMontantePagos() );
        }
    }
}
