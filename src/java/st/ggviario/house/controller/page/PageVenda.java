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

public class PageVenda extends VendaController {


    @FXML
    private AnchorPane getLocalRootPage;

    @FXML
    private JFXTreeTableView< VendaViewModel > tableViewVendaVenda;


    //Inicaliz columns manuale
    private JFXTreeTableColumn<VendaViewModel, String> columnVendaDividaFaturaNumero = new JFXTreeTableColumn<>("FATURA" );
    private JFXTreeTableColumn<VendaViewModel, String> columnVendaDividaCliente =  new JFXTreeTableColumn<>( "CLIENTE" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaProduto = new JFXTreeTableColumn<>( "PRODUTO" );
    private JFXTreeTableColumn<VendaViewModel, String > columnVendaDividaQuantidade =  new JFXTreeTableColumn<>( "Qt." );
    private JFXTreeTableColumn<VendaViewModel, Date > columnVendaDividaData =  new JFXTreeTableColumn<>( "DATA" );
    private JFXTreeTableColumn<VendaViewModel, Number > columnVendaDividaMontantePagar = new JFXTreeTableColumn<>( "MONTANTE" );
    private JFXTreeTableColumn<VendaViewModel, Date > columnVendaVendaRegisto = new JFXTreeTableColumn<>( "REGISTO" );

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
        super.structure();

        columnVendaDividaFaturaNumero.setCellValueFactory(param -> param.getValue().getValue().vendaFaturaNumero );

        columnVendaDividaCliente.setCellValueFactory( param -> param.getValue().getValue().vendaCliente );
        columnVendaDividaCliente.setStyle("-fx-alignment: CENTER_LEFT;");

        columnVendaDividaProduto.setCellValueFactory( param -> param.getValue().getValue().vendaProduto );
        columnVendaDividaProduto.setStyle("-fx-alignment: CENTER_LEFT;");


        columnVendaDividaQuantidade.setCellValueFactory( param -> param.getValue().getValue().vendaQuantidade );
        columnVendaDividaQuantidade.setStyle("-fx-alignment: CENTER_RIGHT;");

        columnVendaDividaData.setCellValueFactory( param -> param.getValue().getValue().vendaData );
        columnVendaDividaData.setCellFactory( createDateFormatTableCell( DD_MM_YYYY ));

        columnVendaDividaMontantePagar.setCellValueFactory( param -> param.getValue().getValue().vendaMontantePagar );
        columnVendaDividaMontantePagar.setStyle("-fx-alignment: CENTER_RIGHT;");

        this.columnVendaVendaRegisto.setCellValueFactory( param -> param.getValue().getValue().vendaDataRegisto );
        this.columnVendaVendaRegisto.setCellFactory( createDateFormatTableCell( DD_MM_YYYY ));


        this.tableViewVendaVenda.getColumns().setAll(
                columnVendaDividaFaturaNumero,
                columnVendaDividaCliente,
                columnVendaDividaProduto,
                columnVendaDividaQuantidade,
                columnVendaDividaData,
                columnVendaDividaMontantePagar,
                columnVendaVendaRegisto
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

        this.columnVendaVendaRegisto.setMinWidth( 110 );
        this.columnVendaVendaRegisto.setMaxWidth( 110 );
    }




}
