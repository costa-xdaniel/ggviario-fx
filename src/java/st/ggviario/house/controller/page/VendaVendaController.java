package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import st.ggviario.house.model.TipoVenda;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class VendaVendaController extends VendaController {


    @FXML
    private AnchorPane getLocalRootPage;

    @FXML
    private JFXTreeTableView< VendaViewModel > tableViewVendaVenda;


    //Inicaliz columns manuale
    private JFXTreeTableColumn<VendaViewModel, String> columnVendaDividaFaturaNumero = new JFXTreeTableColumn<>("FATURA" );
    private JFXTreeTableColumn<VendaViewModel, String> columnVendaDividaCliente =  new JFXTreeTableColumn<>( "CLIENTE" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaProduto = new JFXTreeTableColumn<>( "PRODUTO" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaQuantidade =  new JFXTreeTableColumn<>( "QUANTIDADE" );
    private JFXTreeTableColumn<VendaViewModel, Date > columnVendaDividaData =  new JFXTreeTableColumn<>( "DATA" );
    private JFXTreeTableColumn<VendaViewModel, Number > columnVendaDividaMontantePagar = new JFXTreeTableColumn<>( "MONTANTE" );

    @FXML
    private JFXButton buttonVendaVendaNew;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    @Override
    protected Pane getLocalRootPage() {
        return this.getLocalRootPage;
    }

    @Override
    protected JFXDrawer getDrawerVendaDetails() {
        return this.drawerVenda;
    }

    @Override
    String[] getAvalibleIcons() {
        return new String[]{ "panelIconAdd" };
    }

    @FXML
    private JFXDrawer drawerVenda;





    @Override
    public JFXButton getButonNew() {
        return this.buttonVendaVendaNew;
    }

    @Override
    protected TipoVenda getTipoVenda() {
        return TipoVenda.VENDA;
    }

    @Override
    String getFunctionLoadClienteNew() {
        return "funct_load_cliente_venda_venda";
    }

    @Override
    String getFunctionLoadVendaName() {
        return "funct_load_venda_venda";
    }

    @Override
    JFXTreeTableView<VendaViewModel> getTableVenda() {
        return this.tableViewVendaVenda;
    }

    @Override
    void structure() {
//
//        this.tableViewVendaVenda.setRowFactory( clienteTableView -> new TableRow<Venda>(){
//            @Override
//            protected void updateItem(Venda item, boolean empty) {
//                super.updateItem(item, empty);
//                if( item == null || empty ){
//                    setItem( item );
//                } else{
//                    this.getStyleClass().add("row-normal");
//                    setItem( item );
//                }
//            }
//        });

        columnVendaDividaFaturaNumero.setCellValueFactory(param -> param.getValue().getValue().vendaFaturaNumero );

        columnVendaDividaCliente.setCellValueFactory( param -> param.getValue().getValue().vendaCliente );
        columnVendaDividaCliente.setStyle("-fx-alignment: CENTER_LEFT;");

        columnVendaDividaProduto.setCellValueFactory( param -> param.getValue().getValue().vendaProduto );
        columnVendaDividaProduto.setStyle("-fx-alignment: CENTER_LEFT;");

        columnVendaDividaQuantidade.setCellValueFactory( param -> param.getValue().getValue().vendaQuantidade );
        columnVendaDividaQuantidade.setStyle("-fx-alignment: CENTER_RIGHT;");

        columnVendaDividaData.setCellValueFactory( param -> param.getValue().getValue().vendaData );

        columnVendaDividaMontantePagar.setCellValueFactory( param -> param.getValue().getValue().vendaMontantePagar );
        columnVendaDividaMontantePagar.setStyle("-fx-alignment: CENTER_RIGHT;");

        this.tableViewVendaVenda.getColumns().setAll(
                columnVendaDividaFaturaNumero,
                columnVendaDividaCliente,
                columnVendaDividaProduto,
                columnVendaDividaQuantidade,
                columnVendaDividaData,
                columnVendaDividaMontantePagar
        );

        this.columnVendaDividaFaturaNumero.setMinWidth( 110 );
        this.columnVendaDividaFaturaNumero.setMaxWidth( 110 );
        this.columnVendaDividaCliente.setMinWidth( 180 );


        this.columnVendaDividaData.setMaxWidth( 110 );
        this.columnVendaDividaData.setMinWidth( 110 );



        this.columnVendaDividaProduto.setMinWidth( 80 );


        this.columnVendaDividaQuantidade.setMaxWidth( 110 );
        this.columnVendaDividaQuantidade.setMinWidth( 110 );

        this.columnVendaDividaMontantePagar.setMinWidth( 130 );
        this.columnVendaDividaMontantePagar.setMaxWidth( 130 );
    }


}
