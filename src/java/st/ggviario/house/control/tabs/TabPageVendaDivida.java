package st.ggviario.house.control.tabs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.modals.ModalAmoratizacoesDivida;
import st.ggviario.house.control.modals.ModalNovoPagamento;
import st.ggviario.house.model.TipoVenda;
import st.ggviario.house.model.Venda;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TabPageVendaDivida extends VendaController {
    @FXML
    private AnchorPane localRootPage;

    @FXML
    private JFXTreeTableView<VendaViewModel> treeTableViewVendaDivida;


    //Inicaliz columns manuale
    private JFXTreeTableColumn<VendaViewModel, String> columnVendaDividaFaturaNumero = new JFXTreeTableColumn<>("FATURA" );
    private JFXTreeTableColumn<VendaViewModel, String> columnVendaDividaCliente =  new JFXTreeTableColumn<>( "CLIENTE" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaProduto = new JFXTreeTableColumn<>( "PRODUTO" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaQuantidade =  new JFXTreeTableColumn<>( "Qt." );
    private JFXTreeTableColumn<VendaViewModel, Date > columnVendaDividaData =  new JFXTreeTableColumn<>( "DATA" );
    private JFXTreeTableColumn<VendaViewModel, Date> columnVendaDividaDataFinalizar =  new JFXTreeTableColumn<>( "PRAZO" );
    private JFXTreeTableColumn<VendaViewModel, Number > columnVendaDividaMontantePagar = new JFXTreeTableColumn<>( "MONTANTE" );
    private JFXTreeTableColumn<VendaViewModel, Number > columnVendaDividaMontanteAmortizado =  new JFXTreeTableColumn<>( "AMORTIZADO" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaEstado = new JFXTreeTableColumn<>( "ESTADO" );

    @FXML
    private JFXButton fabButton;

    @FXML
    private JFXDrawer drawerVendaDetails;


    //Modal Novo agamento
    private ModalNovoPagamento modalNovoPagamento;
    //


    //Modal Lista de movimento
    private ModalAmoratizacoesDivida modalAmoratizacoesDivida;
    //



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize( url, resourceBundle );
        this.structure();
    }

    protected void structure() {
        super.structure();

        columnVendaDividaFaturaNumero.setCellValueFactory(param -> param.getValue().getValue().vendaFaturaNumero );
        columnVendaDividaCliente.setCellValueFactory( param -> param.getValue().getValue().vendaCliente );
        columnVendaDividaCliente.getStyleClass().add( "table-column-left" );

        columnVendaDividaProduto.setCellValueFactory( param -> param.getValue().getValue().vendaProduto );
        columnVendaDividaProduto.getStyleClass().add( "table-column-left" );

        columnVendaDividaQuantidade.setCellValueFactory( param -> param.getValue().getValue().vendaQuantidade );
        columnVendaDividaQuantidade.getStyleClass().add( "table-column-number" );

        columnVendaDividaData.setCellValueFactory( param -> param.getValue().getValue().vendaData );

        columnVendaDividaDataFinalizar.setCellValueFactory( param -> param.getValue().getValue().vendaDataFinalizar );

        columnVendaDividaMontantePagar.setCellValueFactory( param -> param.getValue().getValue().vendaMontantePagar );
        columnVendaDividaMontantePagar.getStyleClass().add( "table-column-money" );

        columnVendaDividaMontanteAmortizado.setCellValueFactory(param -> param.getValue().getValue().vendaMontanteAmortizado );
        columnVendaDividaMontanteAmortizado.getStyleClass().add( "table-column-money" );

        columnVendaDividaEstado.setCellValueFactory( param -> param.getValue().getValue().vendaEstado );
        columnVendaDividaEstado.getStyleClass().add( "table-clumn-money" );

        this.treeTableViewVendaDivida.getColumns().setAll(
                columnVendaDividaFaturaNumero,
                columnVendaDividaCliente,
                columnVendaDividaProduto,
                columnVendaDividaQuantidade,
                columnVendaDividaData,
                columnVendaDividaDataFinalizar,
                columnVendaDividaMontantePagar,
                columnVendaDividaMontanteAmortizado,
                columnVendaDividaEstado
        );

        this.columnVendaDividaFaturaNumero.setMinWidth( 115 );
        this.columnVendaDividaFaturaNumero.setMaxWidth( 115 );
        this.columnVendaDividaCliente.setMinWidth( 180 );

        this.columnVendaDividaEstado.setMinWidth( 135 );
        this.columnVendaDividaEstado.setMaxWidth( 135 );


        this.columnVendaDividaData.setMaxWidth( 110 );
        this.columnVendaDividaData.setMinWidth( 110 );
        this.columnVendaDividaData.setCellFactory( this.cellDateFormat(DD_MM_YYYY_FORMAT) );

        this.columnVendaDividaDataFinalizar.setMaxWidth( 110 );
        this.columnVendaDividaDataFinalizar.setMinWidth( 110 );
        this.columnVendaDividaDataFinalizar.setCellFactory( this.cellDateFormat(DD_MM_YYYY_FORMAT) );

        this.columnVendaDividaProduto.setMinWidth( 80 );

        this.columnVendaDividaQuantidade.setMaxWidth( 110 );
        this.columnVendaDividaQuantidade.setMinWidth( 110 );

        this.columnVendaDividaMontantePagar.setMinWidth( 130 );
        this.columnVendaDividaMontantePagar.setMaxWidth( 130 );

        this.columnVendaDividaMontanteAmortizado.setMinWidth( 130 );
        this.columnVendaDividaMontanteAmortizado.setMaxWidth( 130 );
    }


    @Override
    protected void loadDrawerDetahesVenda() {
        super.loadDrawerDetahesVenda();
        this.drawerVendaDetalhes.setOnNovoPagamento( this::openModalNovoPagamento );
        this.drawerVendaDetalhes.setOnListPayment( this::openModalMovimentoVendaDivida );
    }

    @Override
    public void onSetRootPage( StackPane rootPage ) {
        super.onSetRootPage( rootPage );
        this.modalAmoratizacoesDivida = ModalAmoratizacoesDivida.newInstance( rootPage );
    }

    private void openModalMovimentoVendaDivida(Venda venda ){
        if( venda == null ) {
            this.closeDetails();
            return;
        }
        this.modalAmoratizacoesDivida.setVenda( venda );
        this.modalAmoratizacoesDivida.setTitle(  "FATURA " + venda.getVendaFaturaNumero() );
        this.modalAmoratizacoesDivida.openModal();
    }



    private void openModalNovoPagamento(Venda venda ){
        if( !canOpenModalOfVenda( venda ) ) return;
        this.loadModalNovoPagemnto();
        this.modalNovoPagamento.setVenda( venda );
        this.modalNovoPagamento.setTitle(  "FATURA " + venda.getVendaFaturaNumero() );
        this.modalNovoPagamento.openModal();
    }

    private boolean canOpenModalOfVenda( Venda venda ){
        if( venda == null ){
            this.closeDetails();
            return false;
        }
        return true;
    }

    private void loadModalNovoPagemnto( ){
        if( this.modalNovoPagamento == null ){
            this.modalNovoPagamento = ModalNovoPagamento.newInstance( rootPage );
            this.modalNovoPagamento.setOnModalResult(operationResult -> {
                if( operationResult.isSuccess() ){
                }
            });
        }
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
        return new String[]{ "panelIconAdd", "panelIconPayNow", "panelIconListPayment", "panelIconDelete" };
    }


    @Override
    public JFXButton getButonNew() {
        return this.fabButton;
    }

    @Override
    protected TipoVenda getTipoVenda() {
        return TipoVenda.DIVIDA;
    }

    @Override
    protected String getFunctionLoadClienteNew() {
        return "funct_load_cliente_venda_divida";
    }

    @Override
    protected String getFunctionLoadVendaName() {
        return "funct_load_venda_divida";
    }

    @Override
    protected JFXTreeTableView<VendaViewModel> getTableVenda() {
        return this.treeTableViewVendaDivida;
    }

}
