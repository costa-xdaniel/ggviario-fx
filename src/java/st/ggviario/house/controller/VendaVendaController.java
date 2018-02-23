package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
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
import st.ggviario.house.model.TipoVenda;
import st.ggviario.house.model.Venda;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class VendaVendaController extends VendaController {


    @FXML
    private AnchorPane root;

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

    @FXML
    private JFXDrawer drawerVenda;
    private VendaVendaDetaisController vendaDetailController;
    private Node vendaDetailContente;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        this.events();
        drawerVenda.close();
        this.root.getChildren().remove(drawerVenda);
    }

    private void events() {
        this.tableViewVendaVenda.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVenda, newVenda) -> {
            if( !this.root.getChildren().contains( this.drawerVenda ) ){
                int index = root.getChildren().indexOf( this.tableViewVendaVenda );
                this.root.getChildren().add( index+1, this.drawerVenda );
            }
            this.loadVendaDetailLayout();
            this.drawerVenda.setSidePane( vendaDetailContente );
            drawerVenda.open();



        });
    }

    private void loadVendaDetailLayout() {
        try{
            if( this.vendaDetailController == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/venda_venda_detais.fxml"  ) );
                this.vendaDetailContente = loader.load();
                this.vendaDetailController = loader.getController();
            }
        }catch ( Exception ex ){
            ex.printStackTrace();
        }
    }


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
    Pane getRoot() {
        return this.root;
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
