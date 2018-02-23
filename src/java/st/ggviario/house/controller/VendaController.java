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
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class VendaController extends TableController<Venda> implements Page,  Initializable, VendaNewController.OnNewClienteRequest, VendaNewController.OnNewVendaResult, ClienteNewController.OnNewClienteResult, VendaNewController.OnVendaFeito {

    private Pane dividaNewPanel;
    private VendaNewController vendaNewController;

    private JFXDialog dialogVendaNew;
    private JFXDialogLayout dialogLayoutCompraNew;

    private JFXDialog dialogClienteNew;
    private JFXDialogLayout dialogLayoutClienteNew;

    private Pane clienteNewPanel;
    private List<Venda> vendaList = new LinkedList<>();
    private List<Venda> filtredList = new LinkedList<>();
    private Node rootPage;


    private VendaDetailsController vendaDetailController;
    private Node vendaDetailContente;
    private JFXDialogLayout dialogLayoutPayNow;
    private JFXDialog dialogPayNow;
    private Pane vendaDividaPayNowPanel;
    private VendaDividaPayNowController vendaDividaPayNowController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.getButonNew().setOnAction(actionEvent -> this.newDivida());
        this.structureTableColumns();
        this.reloadVendaData( null );
        pushAll();
        events();
    }



    @Override
    public void setRootPage(Node rootPage) {
        this.rootPage = rootPage;
        if( this.dialogVendaNew == null ){

            this.dialogLayoutCompraNew = new JFXDialogLayout();
            this.dialogVendaNew = new JFXDialog( (StackPane) this.rootPage, this.dialogLayoutCompraNew, JFXDialog.DialogTransition.CENTER );

            this.dialogLayoutClienteNew = new JFXDialogLayout();
            this.dialogClienteNew = new JFXDialog( (StackPane) this.rootPage, this.dialogLayoutClienteNew, JFXDialog.DialogTransition.CENTER );

            this.dialogLayoutPayNow = new JFXDialogLayout();
            this.dialogPayNow = new JFXDialog( (StackPane) this.rootPage, this.dialogLayoutPayNow, JFXDialog.DialogTransition.CENTER );

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
                this.getDrawerVendaDetails().setSidePane( vendaDetailContente );
                this.vendaDetailController.setVenda( newVenda );

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

    protected abstract Pane getLocalRootPage();

    protected abstract JFXDrawer getDrawerVendaDetails();

    private void loadVendaDetailLayout() {
        try{
            if( this.vendaDetailController == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/venda_venda_details.fxml") );
                this.vendaDetailContente = loader.load();
                this.vendaDetailController = loader.getController();
                this.vendaDetailController.setIconAvalible( this.getAvalibleIcons() );
                this.vendaDetailController.setOnCloseVendaDetaisCallback( this::closeDetails );
                this.vendaDetailController.setOnPayNow( this::payNow );
                this.vendaDetailController.ok();
            }
        }catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    abstract String[] getAvalibleIcons();

    private void closeDetails() {
        if( this.getDrawerVendaDetails().isShown() )
            this.getDrawerVendaDetails().close();
    }

    private void payNow(Venda venda ){
        if( venda == null ) {
            this.closeDetails();
            return;
        }
        this.loadVendaDividaPayNowPanel();
        this.vendaDividaPayNowController.setNewVenda( venda );
        this.dialogLayoutPayNow.setHeading( new Text( "Pagamento de fatura " + venda.getVendaFaturaNumero() )  );
        this.dialogLayoutPayNow.setBody( this.vendaDividaPayNowPanel );
        this.dialogPayNow.show();
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

    private void loadVendaDividaPayNowPanel() {
        try{
            if( this.vendaDividaPayNowPanel == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/venda_divida_pagamento.fxml") );
                this.vendaDividaPayNowPanel = loader.load();
                this.vendaDividaPayNowController = loader.getController();
                this.vendaDividaPayNowController.ok();
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
                ClienteNewController clienteNewController = loader.getController();
                clienteNewController.setOnNewClienteResult( this );
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
            JFXSnackbar snackbar = new JFXSnackbar( this.getGetLocalRootPage() );
            snackbar.getStylesheets().add( css );
            snackbar.show( message, "Entendi", 5000, event -> snackbar.unregisterSnackbarContainer( this.getGetLocalRootPage() ) );
        }else {
            String css = getClass().getResource("/styles/snackbar-unsucess.css").toExternalForm();
            JFXSnackbar snackbar = new JFXSnackbar( this.getGetLocalRootPage() );
            snackbar.getStylesheets().add( css );
            snackbar.show( message, "Entendi", 8000, event -> snackbar.unregisterSnackbarContainer( this.getGetLocalRootPage() ) );
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

    abstract String getNewTitle();

    abstract TipoVenda getTipoVenda();

    abstract String getFunctionLoadClienteNew();

    abstract String getFunctionLoadVendaName();


    abstract Pane getGetLocalRootPage() ;

    abstract TableView<Venda> getTableVenda();

    abstract void structureTableColumns();
}
