package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSnackbar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class VendaController extends TableController<Venda> implements Initializable, VendaNewController.OnNewClienteRequest, VendaNewController.OnNewVendaResult, ClienteNewController.OnNewClienteResult, VendaNewController.OnVendaFeito {

    private Pane dividaNewPanel;
    private VendaNewController vendaNewController;

    private JFXDialog dialogVendaNew;
    private JFXDialogLayout dialogLayoutCompraNew;

    private JFXDialog dialogClienteNew;
    private JFXDialogLayout dialogLayoutClienteNew;

    private Pane clienteNewPanel;
    private ClienteNewController clienteNewController;
    private List<Venda> vendaList = new LinkedList<>();
    private List<Venda> filtredList = new LinkedList<>();
    private ObservableList<Venda> observableListCliente;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.getButonNew().setOnAction(actionEvent -> this.newDivida());
        this.dialogLayoutCompraNew = new JFXDialogLayout();
        this.dialogVendaNew = new JFXDialog( this.getStackPane(), this.dialogLayoutCompraNew, JFXDialog.DialogTransition.CENTER );

        this.dialogLayoutClienteNew = new JFXDialogLayout();
        this.dialogClienteNew = new JFXDialog( this.getStackPane(), this.dialogLayoutClienteNew, JFXDialog.DialogTransition.CENTER );
        this.observableListCliente = FXCollections.observableList( this.filtredList );
        this.getTableVenda().setItems( this.observableListCliente );

        this.structureTableColumns();
        this.reloadVendaData( null );
        this.pushAll();
    }

    private void pushAll() {
        this.filtredList.clear();
        this.filtredList.addAll( this.vendaList );
        this.getTableVenda().refresh();

    }


    private void newDivida() {
        this.loadNewVendaContent();
        dialogLayoutCompraNew.setHeading( new Text( this.getNewTitle() ) );
        dialogLayoutCompraNew.setBody( this.dividaNewPanel);
        this.dialogVendaNew.show();
    }

    private void loadNewVendaContent() {
        try{
            if( this.dividaNewPanel == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/venda_new.fxml") );
                this.dividaNewPanel = loader.load();
                this.vendaNewController = loader.getController();
                this.vendaNewController.setOnNewClienteRequest( this );
                this.vendaNewController.setOnNewVendaResult( this );
                this.vendaNewController.setOnVendaFeito( this );
                this.vendaNewController.setTipoVenda( this.getTipoVenda() );
                this.vendaNewController.setFunctionLoadClienteNew( this.getFunctionLoadClienteNew() );
                this.vendaNewController.ok();
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    private void loadNewCliente() {
        try{
            if( this.clienteNewPanel == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/cliente_new.fxml" ) );
                this.clienteNewPanel = loader.load();
                this.clienteNewController = loader.getController();
                this.clienteNewController.setOnNewClienteResult( this );
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }


    @Override
    public void onRegisterNewClienteRequest() {
        this.dialogVendaNew.setOnDialogClosed(jfxDialogEvent -> this.newCliente());
        this.dialogVendaNew.close();
    }

    private void newCliente() {
        this.loadNewCliente();
        this.dialogVendaNew.setOnDialogClosed( null );

        this.dialogLayoutClienteNew.setHeading( new Text("Novo cliente") );
        this.dialogLayoutClienteNew.setBody( this.clienteNewPanel );
        this.dialogClienteNew.show();

        dialogClienteNew.setOnDialogClosed(jfxDialogEvent -> {
            this.dialogClienteNew.setOnDialogClosed( null );
            this.dialogVendaNew.show();
        });
    }


    @Override
    public void onNewClienteResult(boolean result, Cliente cliente, Map<String, Object> data) {
        this.vendaNewController.onClienteRequestResult( result, cliente, data );
        dialogClienteNew.close();

    }

    @Override
    public void onNewVendaResult(VendaNewController.RegisterVendaResult registerVendaResult) {
        StringBuilder builder = new StringBuilder();
        String message  = registerVendaResult.getMessage();
        if ( message == null ) message = "Operação não concluida!";
        if( registerVendaResult.isSucess() ){
            builder.append( "Nova " ).append( this.getTipoVenda().name().toLowerCase() ).append(" cadatrada." );
            message = builder.toString();

            String css = getClass().getResource("/styles/snackbar-sucess.css").toExternalForm();
            JFXSnackbar snackbar = new JFXSnackbar( this.getRoot() );
            snackbar.getStylesheets().add( css );
            snackbar.show( message, "Entendi", 5000, event -> snackbar.unregisterSnackbarContainer( this.getRoot() ) );
        }else {
            String css = getClass().getResource("/styles/snackbar-unsucess.css").toExternalForm();
            JFXSnackbar snackbar = new JFXSnackbar( this.getRoot() );
            snackbar.getStylesheets().add( css );
            snackbar.show( message, "Entendi", 8000, event -> snackbar.unregisterSnackbarContainer( this.getRoot() ) );
        }
    }


    @Override
    public void onVendaFeinto(List<VendaNewController.RegisterVendaResult> results) {
        this.dialogVendaNew.close();
        this.dialogVendaNew.setOnDialogClosed( null );
        this.reloadVendaData( results );
        this.pushAll( );
    }

    void reloadVendaData(List<VendaNewController.RegisterVendaResult> results) {
        Venda.VendaBuilder vendaBuilder = new Venda.VendaBuilder();
        Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
        Producto.ProdutoBuilder produtoBuilder = new Producto.ProdutoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();

        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        this.vendaList.clear();

        sql.query( this.getFunctionLoadVendaName() ).withJsonb( null ).callFunctionTable().onResultQuery( row -> {
            vendaBuilder.load( row );
            vendaBuilder.cliente( clienteBuilder.load( row ).build() );
            vendaBuilder.produto( produtoBuilder.load( row ).build() );
            vendaBuilder.unidade( unidadeBuilder.load( row ).build() );
            this.vendaList.add( vendaBuilder.build() );
        });
    }

    abstract JFXButton getButonNew() ;

    abstract StackPane getStackPane();

    abstract String getNewTitle();

    abstract TipoVenda getTipoVenda();

    abstract String getFunctionLoadClienteNew();

    abstract String getFunctionLoadVendaName();


    abstract Pane getRoot() ;

    abstract TableView<Venda> getTableVenda();

    abstract void structureTableColumns();
}
