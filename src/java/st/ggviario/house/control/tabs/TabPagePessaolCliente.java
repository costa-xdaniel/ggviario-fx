package st.ggviario.house.control.tabs;

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
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.PercentDimension;
import st.ggviario.house.control.modals.ModalNovoCliente;
import st.ggviario.house.control.TableClontroller;
import st.ggviario.house.model.Cliente;
import st.ggviario.house.model.Distrito;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TabPagePessaolCliente extends TableClontroller<TabPagePessaolCliente.ClienteViewModel> implements TabPage, Initializable {


    @FXML private StackPane fabArea;
    @FXML private MaterialDesignIconView fabIcon;
    @FXML private Button fabButton;
    @FXML private JFXTreeTableView< ClienteViewModel > tableClientes;

    private JFXTreeTableColumn< ClienteViewModel, String> columnClienteCodigo = new JFXTreeTableColumn<>("CODIGO");
    private JFXTreeTableColumn< ClienteViewModel, String> columnClienteNome = new JFXTreeTableColumn<>("CLIENTE");
    private JFXTreeTableColumn< ClienteViewModel,  String> columnClienteContacto = new JFXTreeTableColumn<>("CONTACTO");
    private JFXTreeTableColumn< ClienteViewModel, String> columnClienteLocal = new JFXTreeTableColumn<>("LOCAL");
    private JFXTreeTableColumn< ClienteViewModel, Number> columnClienteMontanteCompra = new JFXTreeTableColumn<>("VENDA");
    private JFXTreeTableColumn< ClienteViewModel,  Number> columnClienteMontanteDivida = new JFXTreeTableColumn<>("DIVIDA");
    private JFXTreeTableColumn< ClienteViewModel, Number> columnClienteMontanteTotal = new JFXTreeTableColumn<>("TOTAL");
    private JFXTreeTableColumn< ClienteViewModel,  Number> columnClienteMontantePago = new JFXTreeTableColumn<>("PAGO");
    private JFXTreeTableColumn< ClienteViewModel,  Number> columnClienteMontantePendente = new JFXTreeTableColumn<>("PENDENTE");
    private JFXTreeTableColumn< ClienteViewModel,  Date> columnClienteRegisto = new JFXTreeTableColumn<>("REGISTRO");

    private ModalNovoCliente modalNovoCliente;


    private ModalNovoCliente newClienteModalContentController;
    private List<TreeItem<ClienteViewModel>> listCliente;
    private StackPane rootPagePane;

    @Override
    public void onSetRootPage( StackPane rootPage) {
        this.rootPagePane = rootPage;
    }

    @Override
    public void onAfterOpen() {
        this.loadDataCliente();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.init();
        this.structure();
        this.difineEvents();
    }

    private void init(){
        this.listCliente = new LinkedList<TreeItem<ClienteViewModel>>();
    }

    private void structure() {
        JFXDepthManager.setDepth( this.fabArea, 4 );
        this.columnClienteCodigo.setCellValueFactory( param -> param.getValue().getValue().codigo );
        this.columnClienteNome.setCellValueFactory( param -> param.getValue().getValue().nome );
        this.columnClienteContacto.setCellValueFactory( param -> param.getValue().getValue().contacto );
        this.columnClienteLocal.setCellValueFactory( param -> param.getValue().getValue().local );
        this.columnClienteMontanteCompra.setCellValueFactory( param -> param.getValue().getValue().compra );
        this.columnClienteMontanteDivida.setCellValueFactory( param -> param.getValue().getValue().divida );
        this.columnClienteMontanteTotal.setCellValueFactory( param -> param.getValue().getValue().total );
        this.columnClienteMontantePago.setCellValueFactory( param -> param.getValue().getValue().pago );
        this.columnClienteMontantePendente.setCellValueFactory( param -> param.getValue().getValue().pendente );
        this.columnClienteRegisto.setCellValueFactory( param -> param.getValue().getValue().registo );

        this.tableClientes.getColumns().setAll(
                this.columnClienteCodigo,
                this.columnClienteNome,
                this.columnClienteLocal,
                this.columnClienteMontanteCompra,
                this.columnClienteMontanteDivida,
                this.columnClienteMontanteTotal,
                this.columnClienteMontantePago,
                this.columnClienteMontantePendente,
                this.columnClienteRegisto
        );
        this.push( new LinkedList<>(), this.tableClientes );

        this.columnClienteNome.getStyleClass().add( CLASS_COLUMN_LEFT );
        this.columnClienteContacto.getStyleClass().add( CLASS_COLUMN_LEFT );
        this.columnClienteLocal.getStyleClass().add( CLASS_COLUMN_LEFT );
        this.columnClienteMontanteCompra.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnClienteMontanteDivida.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnClienteMontanteTotal.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnClienteMontantePago.getStyleClass().add( CLASS_COLUMN_MONEY );
        this.columnClienteMontantePendente.getStyleClass().add( CLASS_COLUMN_MONEY );

        this.columnClienteRegisto.setCellFactory( this.cellDateFormat( DD_MM_YYYY_FORMAT ) );

        PercentDimension.widthChangePercent( this.tableClientes, 9., (percent, percentValue) -> this.columnMinWidth( columnClienteRegisto, percentValue ) );
        PercentDimension.widthChangePercent( this.tableClientes, 8., (percent, percentValue) -> this.columnMinWidth( columnClienteCodigo, percentValue ) );
        PercentDimension.widthChangePercent( this.tableClientes, 23, (percent, percentValue) -> this.columnMinWidth( columnClienteNome, percentValue ) );
        PercentDimension.widthChangePercent( this.tableClientes, 10, (percent, percentValue) -> {
            this.columnMinWidth( this.columnClienteMontanteCompra, percentValue );
            this.columnMinWidth( this.columnClienteMontanteDivida, percentValue );
            this.columnMinWidth( this.columnClienteMontanteTotal, percentValue );
            this.columnMinWidth( this.columnClienteMontantePago, percentValue );
            this.columnMinWidth( this.columnClienteMontantePendente, percentValue );
            this.columnMinWidth( this.columnClienteLocal, percentValue );
        });

    }

    private void difineEvents(){
        this.fabButton.setOnMouseClicked( event -> this.newClient() );
        this.fabIcon.setOnMouseClicked( event ->fabButton.fireEvent( event ) );
    }

    private void loadDataCliente() {
        Thread thread = new Thread(() -> {
            PostgresSQL postgresSQL = PostgresSQLSingleton.getInstance();
            Cliente.ClienteBuilder builder = new Cliente.ClienteBuilder();
            Distrito.DistritoBuilder distritoBuilder = new Distrito.DistritoBuilder();
            Platform.runLater(() -> {
                this.listCliente.clear();
                this.tableClientes.getRoot().getChildren().clear();
                this.tableClientes.refresh();
            });
            postgresSQL.query( "ggviario.funct_load_cliente" )
                    .withOther( null )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            builder.load( row );
                            builder.distrito( distritoBuilder.load( row ).build() );
                            TreeItem<ClienteViewModel> cli = new TreeItem<>(new ClienteViewModel(builder.build()));
                            this.listCliente.add( cli );
                            this.tableClientes.getRoot().getChildren().add( cli );
                        });
                    });
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }


    private void newClient() {
        this.loadModalContent();
        this.modalNovoCliente.openModal();
    }

    private void loadModalContent() {
        if( this.modalNovoCliente == null ) {
            this.modalNovoCliente = ModalNovoCliente.load( this.rootPagePane );
            this.modalNovoCliente.setOnModalResult(operationResult -> {
                this.loadDataCliente();
            });
        }
    }


    protected class ClienteViewModel extends RecursiveTreeObject< ClienteViewModel > {

        private StringProperty codigo;
        private StringProperty nome;
        private StringProperty contacto;
        private StringProperty local;
        private ObjectProperty< Number > compra;
        private ObjectProperty< Number >  divida;
        private ObjectProperty< Number >  total;
        private ObjectProperty< Number >  pago;
        private ObjectProperty< Number > pendente;
        private ObjectProperty<Date > registo;

        private Cliente cliente;

        public ClienteViewModel(Cliente cliente) {
            this.cliente = cliente;
            this.codigo = new SimpleStringProperty( cliente.getClienteCodigo() );
            this.nome = new SimpleStringProperty( cliente.getClienteCompletName() );
            this.contacto = new SimpleStringProperty( cliente.getClienteContanto("") );
            this.local = new SimpleStringProperty( cliente.getClienteLocal( "" ) );
            this.compra = new SimpleObjectProperty<>( cliente.getClienteMonatenteCompra() );
            this.divida = new SimpleObjectProperty<>( cliente.getClienteMontanteDivida() );
            this.total = new SimpleObjectProperty<>( cliente.getClienteMontanteTotal() );
            this.pago = new SimpleObjectProperty<>( cliente.getClienteMontantePago() );
            this.pendente = new SimpleObjectProperty<>( cliente.getClienteMontantePendente() );
            this.registo = new SimpleObjectProperty<>( cliente.getClienteDataRegisto() );
        }
    }
}
