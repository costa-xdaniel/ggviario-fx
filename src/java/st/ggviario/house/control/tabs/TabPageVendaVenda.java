package st.ggviario.house.control.tabs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.model.TipoVenda;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TabPageVendaVenda extends VendaController {


    @FXML private AnchorPane getLocalRootPage;
    @FXML private JFXTreeTableView< VendaViewModel > tableViewVendaVenda;
    @FXML private StackPane fabArea;
    @FXML private JFXButton fabButton;



    //Inicaliz columns manuale
    private JFXTreeTableColumn<VendaViewModel, String> columnVendaDividaFaturaNumero = new JFXTreeTableColumn<>("FATURA" );
    private JFXTreeTableColumn<VendaViewModel, String> columnVendaDividaCliente =  new JFXTreeTableColumn<>( "CLIENTE" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaProduto = new JFXTreeTableColumn<>( "PRODUTO" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaQuantidade =  new JFXTreeTableColumn<>( "Qt." );
    private JFXTreeTableColumn<VendaViewModel, Date > columnVendaDividaData =  new JFXTreeTableColumn<>( "DATA" );
    private JFXTreeTableColumn<VendaViewModel, Number > columnVendaDividaMontantePagar = new JFXTreeTableColumn<>( "MONTANTE" );
    private JFXTreeTableColumn<VendaViewModel, Date > columnVendaVendaRegisto = new JFXTreeTableColumn<>( "REGISTO" );


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
        return new String[]{ "panelIconAdd", "panelIconDelete" };
    }

    @FXML
    private JFXDrawer drawerVenda;





    @Override
    public JFXButton getButonNew() {
        return this.fabButton;
    }

    @Override
    protected TipoVenda getTipoVenda() {
        return TipoVenda.VENDA;
    }

    @Override
    protected String getFunctionLoadClienteNew() {
        return "funct_load_cliente_venda_venda";
    }

    @Override
    protected String getFunctionLoadVendaName() {
        return "funct_load_venda_venda";
    }

    @Override
    protected JFXTreeTableView<VendaViewModel> getTableVenda() {
        return this.tableViewVendaVenda;
    }

    @Override
    protected void structure() {
        super.structure();
        JFXDepthManager.setDepth( this.fabArea, 4 );
        columnVendaDividaFaturaNumero.setCellValueFactory(param -> param.getValue().getValue().vendaFaturaNumero );

        columnVendaDividaCliente.setCellValueFactory( param -> param.getValue().getValue().vendaCliente );
        columnVendaDividaCliente.getStyleClass().add( CLASS_COLUMN_LEFT );

        columnVendaDividaProduto.setCellValueFactory( param -> param.getValue().getValue().vendaProduto );
        columnVendaDividaProduto.getStyleClass().add( CLASS_COLUMN_LEFT );


        columnVendaDividaQuantidade.setCellValueFactory( param -> param.getValue().getValue().vendaQuantidade );
        columnVendaDividaQuantidade.getStyleClass().add( CLASS_COLUMN_NUMBER );

        columnVendaDividaData.setCellValueFactory( param -> param.getValue().getValue().vendaData );
        columnVendaDividaData.setCellFactory( cellDateFormat( DD_MM_YYYY_FORMAT ) );


        columnVendaDividaMontantePagar.setCellValueFactory( param -> param.getValue().getValue().vendaMontantePagar );
        columnVendaDividaMontantePagar.getStyleClass().add( CLASS_COLUMN_MONEY );

        this.columnVendaVendaRegisto.setCellValueFactory( param -> param.getValue().getValue().vendaDataRegisto );
        this.columnVendaVendaRegisto.setCellFactory( cellDateFormat(DD_MM_YYYY_FORMAT));


        this.tableViewVendaVenda.getColumns().setAll(
                this.columnVendaDividaFaturaNumero,
                this.columnVendaDividaCliente,
                this.columnVendaDividaProduto,
                this.columnVendaDividaQuantidade,
                this.columnVendaDividaData,
                this.columnVendaDividaMontantePagar,
                this.columnVendaVendaRegisto
        );

        this.columnVendaDividaFaturaNumero.setMinWidth( 115 );
        this.columnVendaDividaFaturaNumero.setMaxWidth( 115 );


        this.columnVendaDividaCliente.setMinWidth( 180 );


        this.columnVendaDividaData.setMaxWidth( 110 );
        this.columnVendaDividaData.setMinWidth( 110 );



        this.columnVendaDividaProduto.setMinWidth( 80 );


        this.columnVendaDividaQuantidade.setMaxWidth( 110 );
        this.columnVendaDividaQuantidade.setMinWidth( 110 );

        this.columnVendaDividaMontantePagar.setMinWidth( 130 );
        this.columnVendaDividaMontantePagar.setMaxWidth( 130 );

        this.columnVendaVendaRegisto.setMinWidth( 110 );
        this.columnVendaVendaRegisto.setMaxWidth( 110 );
    }
}
