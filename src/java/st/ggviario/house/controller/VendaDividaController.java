package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import st.ggviario.house.model.TipoVenda;
import st.ggviario.house.model.Venda;
import st.jigahd.support.sql.lib.SQLText;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class VendaDividaController extends VendaController {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane localRootPage;

    @FXML
    private TableView< Venda > tableViewVendaDivida;

    @FXML
    private TableColumn< Venda, String> columnVendaDividaFaturaNumero;

    @FXML
    private TableColumn< Venda, String> columnVendaDividaCliente;

    @FXML
    private TableColumn< Venda, String > columnVendaDividaProduto;

    @FXML
    private TableColumn< Venda, String > columnVendaDividaQuantidade;

    @FXML
    private TableColumn< Venda, Date > columnVendaDividaData;

    @FXML
    private TableColumn< Venda, Number > columnVendaDividaMontanteUnitario;

    @FXML
    private TableColumn< Venda, Number > columnVendaDividaMontantePagar;

    @FXML
    private TableColumn< Venda, Number > columnVendaDividaMontantePago;

    @FXML
    private TableColumn< Venda, Date> columnVendaDividaDataFinalizar;

    @FXML
    private TableColumn< Venda, String > columnVendaDividaEstado;

    @FXML
    private JFXButton buttonVendaDividaNew;

    @FXML
    private JFXDrawer drawerVendaDetails;


    //Modal Novo agamento
    private Pane modalNovoPagamentoPage;
    private ModalNovoPagamentoController modalNovoPagamentoController;
    private JFXDialogLayout modalNovoPagamentoDialogLayout;
    private JFXDialog modalNovoPagamentoDialog;
    //


    //Modal Lista de movimento
    private Pane modalListaMovimentoVendaPage;
    private ModalListaMovimentoVendaController modalListaMovimentoVendaController;
    private JFXDialogLayout modalListaMovimentoVendaDialogLayout;
    private JFXDialog modalListaMovimentoVendaDialog;
    //



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize( url, resourceBundle );

        this.structure();
    }



    void structure() {
        this.tableViewVendaDivida.setRowFactory( clienteTableView -> new TableRow<Venda>(){
            @Override
            protected void updateItem(Venda item, boolean empty) {
                super.updateItem(item, empty);
                if( item == null || empty ){
                    setItem( item );
                } else{
                    this.getStyleClass().add("row-normal");
                    setItem( item );
                }
            }
        });

        this.columnVendaDividaCliente.setMinWidth( 140 );

        this.columnVendaDividaFaturaNumero.setMaxWidth( 72 );
        this.columnVendaDividaFaturaNumero.setMinWidth( 72 );

        this.columnVendaDividaEstado.setMaxWidth( 100 );
        this.columnVendaDividaEstado.setMinWidth( 100 );


        this.columnVendaDividaFaturaNumero.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getVendaFaturaNumero() ));
        this.columnVendaDividaFaturaNumero.setCellFactory( cell -> this.getSimpleTextCell() );

        this.columnVendaDividaCliente.setCellValueFactory( data -> {
            String nome = data.getValue().getCliente().getClienteCompletName();
            return  new SimpleStringProperty( nome );
        } );
        this.columnVendaDividaCliente.setCellFactory( cell -> this.getSimpleTextCell() );

        this.columnVendaDividaProduto.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getProducto().getProdutoNome()) );
        this.columnVendaDividaProduto.setCellFactory( cell -> this.getSimpleTextCell());

        this.columnVendaDividaQuantidade.setCellValueFactory( data-> new SimpleStringProperty( data.getValue().getVandaQuantidade()+" "+data.getValue().getUnidade().getUnidadeCodigo() ) );
        this.columnVendaDividaQuantidade.setCellFactory( cell -> this.getSimpleTextCell() );

        this.columnVendaDividaMontanteUnitario.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getVendaMontantePagar() ) );
        this.columnVendaDividaMontanteUnitario.setCellFactory(cell -> this.getMoneyCell("STN") );

        this.columnVendaDividaData.setCellValueFactory( data -> new SimpleObjectProperty<>( data.getValue().getVendaData() ));
        this.columnVendaDividaData.setCellFactory( cell -> this.getDateCell() );

        this.columnVendaDividaMontantePagar.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getVendaMontantePagar() ) );
        this.columnVendaDividaMontantePagar.setCellFactory(cell -> this.getMoneyCell("STN" ) );

        this.columnVendaDividaMontantePago.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getVendaMontanteAmortizado() ) );
        this.columnVendaDividaMontantePago.setCellFactory(cell -> this.getMoneyCell("STN" ) );

        this.columnVendaDividaDataFinalizar.setCellValueFactory(data -> new SimpleObjectProperty<>( data.getValue().getVendaDataFinalizar()));
        this.columnVendaDividaDataFinalizar.setCellFactory(cell -> this.getDateCell() );

        this.columnVendaDividaEstado.setCellValueFactory(data -> new SimpleStringProperty( data.getValue().getVendaEstadoDesc() ));
        this.columnVendaDividaEstado.setCellFactory(cell -> this.getSimpleTextCell() );

    }

    @Override
    protected void loadVendaDetailLayout() {
        try{
            if( this.drawerVendaDetalhesController == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/venda_details.fxml") );
                this.drawerVendaDetalhesPane = loader.load();
                this.drawerVendaDetalhesController = loader.getController();
                this.drawerVendaDetalhesController.setIconAvalible( this.getAvalibleIcons() )
                        .setOnCloseVendaDetaisCallback( this::closeDetails )
                        .setOnPayNow( this::openModalPaymentNow )
                        .setOnListPayment( this::openModalMovimentoVendaDivida)
                        .setTipoVenda( this.getTipoVenda() )
                        .ok();
            }
        }catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    @Override
    public void onSetRootPage(Node rootPage) {
        super.onSetRootPage(rootPage);

        this.modalNovoPagamentoDialogLayout = new JFXDialogLayout();
        ( (Pane) modalNovoPagamentoDialogLayout.getChildren().get( 0 ) ).getChildren().remove( 2 );
        this.modalNovoPagamentoDialog = new JFXDialog( (StackPane) this.rootPage, this.modalNovoPagamentoDialogLayout, JFXDialog.DialogTransition.CENTER );

        this.modalListaMovimentoVendaDialogLayout = new JFXDialogLayout();
        this.modalListaMovimentoVendaDialog = new JFXDialog((StackPane) this.rootPage, this.modalListaMovimentoVendaDialogLayout, JFXDialog.DialogTransition.CENTER );
    }

    private void loadModalNovoPagamento() {
        try{
            if( this.modalNovoPagamentoPage == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/modal_novo_pagamento.fxml") );
                this.modalNovoPagamentoPage = loader.load();
                this.modalNovoPagamentoController = loader.getController();
                this.modalNovoPagamentoController.setOnResultPayNow( this::onResultPayment );
                this.modalNovoPagamentoController.ok( );

                this.modalNovoPagamentoDialogLayout.setBody( this.modalNovoPagamentoPage);

                this.modalNovoPagamentoDialogLayout.getStylesheets().add( getClass().getResource("/styles/styles.css").toExternalForm() );
                this.modalNovoPagamentoDialogLayout.getStyleClass().add( "modal-width-1" );
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    private void loadModalMovimentoDialog() {
        try{
            if( this.modalListaMovimentoVendaPage == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/modal_lista_movimento_venda.fxml") );
                this.modalListaMovimentoVendaPage = loader.load();
                this.modalListaMovimentoVendaController = loader.getController();
                this.modalListaMovimentoVendaController.setOnOkCliek( this::onCliekOkListPayment );
                this.modalListaMovimentoVendaController.ok( );
                this.modalListaMovimentoVendaDialogLayout.setBody( this.modalListaMovimentoVendaPage  );
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    private void openModalMovimentoVendaDivida(Venda venda ){
        if( venda == null ) {
            this.closeDetails();
            return;
        }
        this.loadModalMovimentoDialog();
        this.modalListaMovimentoVendaController.setVenda( venda );
        this.modalListaMovimentoVendaDialogLayout.setHeading( new Text( "Pagamento de fatura " + venda.getVendaFaturaNumero() )  );
        this.modalListaMovimentoVendaDialog.show();
    }

    private void openModalPaymentNow(Venda venda ){
        if( venda == null ) {
            this.closeDetails();
            return;
        }
        this.loadModalNovoPagamento();
        this.modalNovoPagamentoController.setVenda( venda );
        this.modalNovoPagamentoDialogLayout.setHeading( new Text( "Pagamento de fatura " + venda.getVendaFaturaNumero() )  );
        this.modalNovoPagamentoDialog.show();
    }


    private void onResultPayment( ModalNovoPagamentoController.MovimentoResult movimentoResult ) {
        StringBuilder builder = new StringBuilder();
        String message = movimentoResult.getMessage();
        if( message == null ) message = "Operação falhou!";

        if( movimentoResult.isSucess() ){
            message = builder.append( "Novo pagamento para ")
                    .append(" a ").append( movimentoResult.getMovimento().getVenda().getTipoVenda().name().toLowerCase() )
                    .append(" com documento numero ").append( movimentoResult.getMovimento().getMovimentoDocumento() )
                    .append(" cadastrado com sucesso")
                    .toString();

            this.showSucessMessage( SQLText.normalize(message) );
            this.modalNovoPagamentoDialog.close();
            this.modalNovoPagamentoController.clear();
            this.onAfertOperation( null );
        } else {
            this.showFailedMessage(message);
        }
    }

    private void onCliekOkListPayment() {
        modalListaMovimentoVendaDialog.close();
    }




    @Override
    protected Pane getLocalRootPage() {
        return this.localRootPage;
    }

    @Override
    protected JFXDrawer getDrawerVendaDetails() {
        return this.drawerVendaDetails;
    }

    @Override
    String[] getAvalibleIcons() {
        return new String[]{ "panelIconAdd", "panelIconPayNow", "panelIconListPayment" };
    }


    @Override
    public JFXButton getButonNew() {
        return this.buttonVendaDividaNew;
    }

    @Override
    public String getNewTitle() {
        return "NOVA DIVIDA";
    }

    @Override
    protected TipoVenda getTipoVenda() {
        return TipoVenda.DIVIDA;
    }

    @Override
    String getFunctionLoadClienteNew() {
        return "funct_load_cliente_venda_divida";
    }

    @Override
    String getFunctionLoadVendaName() {
        return "funct_load_venda_divida";
    }

    @Override
    Pane getGetLocalRootPage() {
        return this.localRootPage;
    }

    @Override
    TableView<Venda> getTableVenda() {
        return this.tableViewVendaDivida;
    }

}
