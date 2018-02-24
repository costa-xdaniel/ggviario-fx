package st.ggviario.house.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class VendaController extends TableController<Venda> implements Page,  Initializable, ModalNovaVendaController.OnNewClienteRequest, ModalNovaVendaController.OnNewVendaResult, ModalNovoClienteController.OnNewClienteResult, ModalNovaVendaController.OnVendaFeito {


    //Modal Nova venda
    private Pane modalNovaVendaPane;
    private ModalNovaVendaController modalNovaVendaController;
    private JFXDialogLayout modalNovaVendaDialogLayout;
    private JFXDialog modalNovaVendaDialog;
    //


    //Modal Novo cliente
    private Pane modalNovoClientePane;
    private ModalNovoClienteController modalNovoClienteController;
    private JFXDialogLayout modalNovoClienteDialogLaout;
    private JFXDialog modalNovoClienteDialog;
    //


    //Detalhes da venda
    protected DrawerVendaDetalhesController drawerVendaDetalhesController;
    protected Pane drawerVendaDetalhesPane;
    //

    private List<Venda> vendaList = new LinkedList<>();
    private List<Venda> filtredList = new LinkedList<>();
    protected Node rootPage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.getButonNew().setOnAction(actionEvent -> this.openModalVendaDividaNew());
        this.structure();
        this.reloadVendaData( null );
        pushAll();
        events();
    }



    @Override
    public void onSetRootPage(Node rootPage) {
        this.rootPage = rootPage;
        if( this.modalNovaVendaDialog == null ){

            this.modalNovaVendaDialogLayout = new JFXDialogLayout();
            this.modalNovaVendaDialog = new JFXDialog( (StackPane) this.rootPage, this.modalNovaVendaDialogLayout, JFXDialog.DialogTransition.CENTER );

            this.modalNovoClienteDialogLaout = new JFXDialogLayout();
            this.modalNovoClienteDialog = new JFXDialog( (StackPane) this.rootPage, this.modalNovoClienteDialogLaout, JFXDialog.DialogTransition.CENTER );
        }
    }

    private void pushAll() {
        this.filtredList.clear();
        this.filtredList.addAll( this.vendaList );
        ObservableList<Venda> observableListCliente = FXCollections.observableList(this.filtredList);
        this.getTableVenda().setItems(observableListCliente);

    }

    private void events() {
        this.getTableVenda().getSelectionModel().selectedItemProperty().addListener((observableValue, oldVenda, newVenda) -> {
            if( newVenda != null ){
                this.loadVendaDetailLayout();
                this.getDrawerVendaDetails().setSidePane( this.drawerVendaDetalhesPane);
                this.drawerVendaDetalhesController.setVenda( newVenda );

                if( !this.getLocalRootPage().getChildren().contains( this.getDrawerVendaDetails() ) ){
                    int index = this.getLocalRootPage().getChildren().indexOf( this.getTableVenda() );
                    this.getLocalRootPage().getChildren().add( index+1, this.getDrawerVendaDetails() );
                }
                this.getDrawerVendaDetails().open();
            } else {
                this.closeDetails();
            }
        });
        this.getDrawerVendaDetails().setOnDrawerClosed(event -> {
            if( this.getLocalRootPage().getChildren().contains( this.getDrawerVendaDetails() ) )
                this.getLocalRootPage().getChildren().remove( this.getDrawerVendaDetails() );
        });
    }



    private void openModalVendaDividaNew() {
        this.loadModalVendaDividaNew();
        modalNovaVendaDialogLayout.setHeading( new Text( this.getNewTitle() ) );
        modalNovaVendaDialogLayout.setBody( this.modalNovaVendaPane);
        this.modalNovaVendaDialog.show();
    }


    protected void loadVendaDetailLayout() {
        try{
            if( this.drawerVendaDetalhesController == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/venda_details.fxml") );
                this.drawerVendaDetalhesPane = loader.load();
                this.drawerVendaDetalhesController = loader.getController();
                this.drawerVendaDetalhesController.setIconAvalible( this.getAvalibleIcons() )
                        .setOnCloseVendaDetaisCallback( this::closeDetails )
                        .setTipoVenda( this.getTipoVenda() )
                        .ok();
            }
        }catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    private void loadModalVendaDividaNew() {
        try{
            if( this.modalNovaVendaPane == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/modal_nova_venda.fxml") );
                this.modalNovaVendaPane = loader.load();
                this.modalNovaVendaController = loader.getController();
                this.modalNovaVendaController.setOnNewClienteRequest( this );
                this.modalNovaVendaController.setOnNewVendaResult( this );
                this.modalNovaVendaController.setOnVendaFeito( this );
                this.modalNovaVendaController.setTipoVenda( this.getTipoVenda() );
                this.modalNovaVendaController.setFunctionLoadClienteNew( this.getFunctionLoadClienteNew() );
                this.modalNovaVendaController.ok();
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    protected void closeDetails() {
        if( this.getDrawerVendaDetails().isShown() )
            this.getDrawerVendaDetails().close();
    }




    private void loadNewCliente() {
        try{
            if( this.modalNovoClientePane == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/modal_novo_cliente.fxml") );
                this.modalNovoClientePane = loader.load();
                modalNovoClienteController = loader.getController();
                modalNovoClienteController.setOnNewClienteResult( this );
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }


    @Override
    public void onRegisterNewClienteRequest() {
        this.modalNovaVendaDialog.setOnDialogClosed(jfxDialogEvent -> this.newCliente());
        this.modalNovaVendaDialog.close();
    }

    private void newCliente() {
        this.loadNewCliente();
        this.modalNovaVendaDialog.setOnDialogClosed( null );

        this.modalNovoClienteDialogLaout.setHeading( new Text("Novo cliente") );
        this.modalNovoClienteDialogLaout.setBody( this.modalNovoClientePane);
        this.modalNovoClienteDialog.show();

        this.modalNovoClienteDialog.setOnDialogClosed(jfxDialogEvent -> {
            this.modalNovoClienteDialog.setOnDialogClosed( null );
            this.modalNovaVendaDialog.show();
        });
    }


    @Override
    public void onNewClienteResult(boolean result, Cliente cliente, Map<String, Object> data) {
        this.modalNovaVendaController.onClienteRequestResult( result, cliente, data );
        this.modalNovoClienteDialog.close();
    }

    @Override
    public void onNewVendaResult(ModalNovaVendaController.RegisterVendaResult registerVendaResult) {
        StringBuilder builder = new StringBuilder();
        String message  = registerVendaResult.getMessage();
        if ( message == null ) message = "Operação não concluida!";
        if( registerVendaResult.isSucess() ){
            builder.append( "Nova " ).append( this.getTipoVenda().name().toLowerCase() ).append(" cadatrada." );
            showSucessMessage( SQLText.normalize( builder.toString() ) );
        }else showFailedMessage(message);
    }

    protected void showSucessMessage(String message) {
        String css = getClass().getResource("/styles/snackbar-success.css" ).toExternalForm();
        JFXSnackbar snackbar = new JFXSnackbar(this.getGetLocalRootPage());
        snackbar.getStylesheets().add(css);
        snackbar.show(message, "Entendi", 5000, event -> snackbar.unregisterSnackbarContainer(this.getGetLocalRootPage()));
    }

    protected void showFailedMessage(String message) {
        String css = getClass().getResource("/styles/snackbar-failed.css").toExternalForm();
        JFXSnackbar snackbar = new JFXSnackbar( this.getGetLocalRootPage() );
        snackbar.getStylesheets().add( css );
        snackbar.show( message, "Entendi", 8000, event -> snackbar.unregisterSnackbarContainer( this.getGetLocalRootPage() ) );
    }


    @Override
    public void onAfertOperation(List<ModalNovaVendaController.RegisterVendaResult> results) {
        this.modalNovaVendaDialog.close();
        this.modalNovaVendaDialog.setOnDialogClosed( null );
        this.reloadVendaData( results );
        this.pushAll( );
    }

    void reloadVendaData(List<ModalNovaVendaController.RegisterVendaResult> results) {
        Venda.VendaBuilder vendaBuilder = new Venda.VendaBuilder();
        Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
        Producto.ProdutoBuilder produtoBuilder = new Producto.ProdutoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();

        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        this.vendaList.clear();

        sql.query( this.getFunctionLoadVendaName() ).withJsonb( (String) null).callFunctionTable().onResultQuery(row -> {
            vendaBuilder.load( row );
            vendaBuilder.cliente( clienteBuilder.load( row ).build() );
            vendaBuilder.produto( produtoBuilder.load( row ).build() );
            vendaBuilder.unidade( unidadeBuilder.load( row ).build() );
            this.vendaList.add( vendaBuilder.build() );
        });
    }

    abstract JFXButton getButonNew() ;

    abstract String getNewTitle();

    abstract TipoVenda getTipoVenda();

    abstract String getFunctionLoadClienteNew();

    abstract String getFunctionLoadVendaName();


    abstract Pane getGetLocalRootPage() ;

    abstract TableView<Venda> getTableVenda();

    abstract void structure();

    abstract String[] getAvalibleIcons();

    protected abstract Pane getLocalRootPage();

    protected abstract JFXDrawer getDrawerVendaDetails();
}
