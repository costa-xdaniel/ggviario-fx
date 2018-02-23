package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import st.ggviario.house.model.TipoVenda;
import st.ggviario.house.model.Venda;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class VendaVendaController extends VendaController {


    @FXML
    private AnchorPane getLocalRootPage;

    @FXML
    private TableView< Venda > tableViewVendaVenda;

    @FXML
    private TableColumn< Venda, String> columnVendaVendaCliente;

    @FXML
    private TableColumn< Venda, String > columnVendaVendaProduto;

    @FXML
    private TableColumn< Venda, Number > columnVendaVendaQuantidade;

    @FXML
    private TableColumn< Venda, String > columnVendaVendaUnidade;

    @FXML
    private TableColumn< Venda, Number > columnVendaVendaMontanteUnitario;

    @FXML
    private TableColumn< Venda, Date> columnVendaVendaData;

    @FXML
    private TableColumn< Venda, Number > columnVendaVendaMontantePagar;

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
    public String getNewTitle() {
        return "NOVA VEMDA";
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
    Pane getGetLocalRootPage() {
        return this.getLocalRootPage;
    }

    @Override
    TableView<Venda> getTableVenda() {
        return this.tableViewVendaVenda;
    }



    @Override
    void structureTableColumns() {

        this.tableViewVendaVenda.setRowFactory( clienteTableView -> new TableRow<Venda>(){
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

        this.columnVendaVendaCliente.setCellValueFactory( data -> {
            String nome = data.getValue().getCliente().getClienteCompletName();
            return  new SimpleStringProperty( nome );
        } );
        this.columnVendaVendaCliente.setCellFactory( cell -> this.getSimpleTextCell() );
        this.columnVendaVendaCliente.setMinWidth( 150 );

        this.columnVendaVendaProduto.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getProducto().getProdutoNome()) );
        this.columnVendaVendaProduto.setCellFactory( cell -> this.getSimpleTextCell());

        this.columnVendaVendaQuantidade.setCellValueFactory( data-> new SimpleDoubleProperty( data.getValue().getVandaQuantidade() ) );
        this.columnVendaVendaQuantidade.setCellFactory( cell -> this.getNumberCell() );

        this.columnVendaVendaUnidade.setCellValueFactory(data -> new SimpleStringProperty( data.getValue().getUnidade().getUnidadeCodigo() ) );
        this.columnVendaVendaUnidade.setCellFactory(cell -> getSimpleTextCell() );

        this.columnVendaVendaMontanteUnitario.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getVendaMontantePagar() ) );
        this.columnVendaVendaMontanteUnitario.setCellFactory(cell -> this.getMoneyCell("STN") );

        this.columnVendaVendaData.setCellValueFactory( data -> new SimpleObjectProperty<>( data.getValue().getVendaData() ));
        this.columnVendaVendaData.setCellFactory( cell -> this.getDateCell() );

        this.columnVendaVendaMontantePagar.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getVendaMontantePagar() ) );
        this.columnVendaVendaMontantePagar.setCellFactory(cell -> this.getMoneyCell("STN") );
    }


}
