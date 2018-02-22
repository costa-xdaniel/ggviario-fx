package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class VendaVendaController extends VendaController {


    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private StackPane stackPane;

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
    public JFXButton getButonNew() {
        return this.buttonVendaVendaNew;
    }

    @Override
    public StackPane getStackPane() {
        return this.stackPane;
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
    Pane getRoot() {
        return this.anchorRoot;
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
