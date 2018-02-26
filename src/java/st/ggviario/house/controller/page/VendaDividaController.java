package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modal.ModalAmoratizacoesDivida;
import st.ggviario.house.controller.modal.ModalNovoPagamento;
import st.ggviario.house.model.TipoVenda;
import st.ggviario.house.model.Venda;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class VendaDividaController extends VendaController {
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

        this.columnVendaDividaFaturaNumero.setMinWidth( 110 );
        this.columnVendaDividaFaturaNumero.setMaxWidth( 110 );
        this.columnVendaDividaCliente.setMinWidth( 180 );

        this.columnVendaDividaEstado.setMinWidth( 135 );
        this.columnVendaDividaEstado.setMaxWidth( 135 );


        this.columnVendaDividaData.setMaxWidth( 110 );
        this.columnVendaDividaData.setMinWidth( 110 );

        this.columnVendaDividaDataFinalizar.setMaxWidth( 110 );
        this.columnVendaDividaDataFinalizar.setMinWidth( 110 );


        this.columnVendaDividaProduto.setMinWidth( 80 );


        this.columnVendaDividaQuantidade.setMaxWidth( 110 );
        this.columnVendaDividaQuantidade.setMinWidth( 110 );

        this.columnVendaDividaMontantePagar.setMinWidth( 130 );
        this.columnVendaDividaMontantePagar.setMaxWidth( 130 );

        this.columnVendaDividaMontantePago.setMinWidth( 130 );
        this.columnVendaDividaMontantePago.setMaxWidth( 130 );


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

//        this.columnVendaDividaMontanteUnitario.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getVendaMontantePagar() ) );
//        this.columnVendaDividaMontanteUnitario.setCellFactory(cell -> this.getMoneyCell("STN") );

        this.columnVendaDividaData.setCellValueFactory( data -> new SimpleObjectProperty<>( data.getValue().getVendaData() ));
        this.columnVendaDividaData.setCellFactory( cell -> this.getDateCell() );

        this.columnVendaDividaMontantePagar.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getVendaMontantePagar() ) );
        this.columnVendaDividaMontantePagar.setCellFactory(cell -> this.getMoneyCell("") );

        this.columnVendaDividaMontantePago.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getVendaMontanteAmortizado() ) );
        this.columnVendaDividaMontantePago.setCellFactory(cell -> this.getMoneyCell("" ) );

        this.columnVendaDividaDataFinalizar.setCellValueFactory(data -> new SimpleObjectProperty<>( data.getValue().getVendaDataFinalizar()));
        this.columnVendaDividaDataFinalizar.setCellFactory(cell -> this.getDateCell() );

        this.columnVendaDividaEstado.setCellValueFactory(data -> new SimpleStringProperty( data.getValue().getVendaEstadoDesc() ));
        this.columnVendaDividaEstado.setCellFactory(cell -> this.getSimpleTextCell() );
    }


    @Override
    protected void loadVendaDetailLayout() {
        super.loadVendaDetailLayout();
        this.drawerVendaDetalhesController.setOnNovoPagamento( this::openModalNovoPagamento );
        this.drawerVendaDetalhesController.setOnListPayment( this::openModalMovimentoVendaDivida );
    }

    @Override
    public void onSetRootPage(Node rootPage) {
        super.onSetRootPage(rootPage);
        this.modalAmoratizacoesDivida = ModalAmoratizacoesDivida.load((StackPane) rootPage);
    }


    private void openModalMovimentoVendaDivida(Venda venda ){
        if( venda == null ) {
            this.closeDetails();
            return;
        }
        this.modalAmoratizacoesDivida.setVenda( venda );
        this.modalAmoratizacoesDivida.setTitle(  "Pagamento de fatura " + venda.getVendaFaturaNumero() );
        this.modalAmoratizacoesDivida.openModal();
    }

    private void openModalNovoPagamento(Venda venda ){
        if( !canOpenModalOfVenda( venda ) ) return;
        this.loadModalNovoPagemnto();
        this.modalNovoPagamento.setVenda( venda );
        this.modalNovoPagamento.setTitle(  "Pagamento de fatura " + venda.getVendaFaturaNumero() );
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
            this.modalNovoPagamento = ModalNovoPagamento.load((StackPane) rootPage);
            this.modalNovoPagamento.setOnOperationResult(operationResult -> {
                if( operationResult.isSucceed() ){
                    System.out.println("Mandar mandar atualizar a de diviadas");
                    System.out.println("Selecionar o cliente");
                    System.out.println("Abrie o drawer do detalhes para o cliente selecionada");
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
        return new String[]{ "panelIconAdd", "panelIconPayNow", "panelIconListPayment" };
    }


    @Override
    public JFXButton getButonNew() {
        return this.buttonVendaDividaNew;
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
    TableView<Venda> getTableVenda() {
        return this.tableViewVendaDivida;
    }

}
