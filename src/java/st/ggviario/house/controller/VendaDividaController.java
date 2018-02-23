package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.model.TipoVenda;
import st.ggviario.house.model.Venda;

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
    private TableColumn< Venda, String> columnVendaDividaCliente;

    @FXML
    private TableColumn< Venda, String > columnVendaDividaProduto;

    @FXML
    private TableColumn< Venda, Number > columnVendaDividaQuantidade;

    @FXML
    private TableColumn< Venda, String > columnVendaDividaUnidade;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize( url, resourceBundle );

        this.structureTableColumns();
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


    void structureTableColumns() {
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


        this.columnVendaDividaCliente.setCellValueFactory( data -> {
            String nome = data.getValue().getCliente().getClienteCompletName();
            return  new SimpleStringProperty( nome );
        } );
        this.columnVendaDividaCliente.setCellFactory( cell -> this.getSimpleTextCell() );
        this.columnVendaDividaCliente.setMinWidth( 150 );

        this.columnVendaDividaProduto.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getProducto().getProdutoNome()) );
        this.columnVendaDividaProduto.setCellFactory( cell -> this.getSimpleTextCell());

        this.columnVendaDividaQuantidade.setCellValueFactory( data-> new SimpleDoubleProperty( data.getValue().getVandaQuantidade() ) );
        this.columnVendaDividaQuantidade.setCellFactory( cell -> this.getNumberCell() );

        this.columnVendaDividaUnidade.setCellValueFactory(data -> new SimpleStringProperty( data.getValue().getUnidade().getUnidadeCodigo() ) );
        this.columnVendaDividaUnidade.setCellFactory(cell -> getSimpleTextCell() );

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
}
