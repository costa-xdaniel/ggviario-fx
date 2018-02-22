package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import st.ggviario.house.model.Cliente;
import st.ggviario.house.model.Distrito;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteController extends TableController< Cliente > implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton fabNewCliente;


    @FXML
    private TableView< Cliente > tableViewCliente;

    @FXML
    private TableColumn<Cliente, String> columnClienteNome;

    @FXML
    private TableColumn<Cliente, String> columnClienteContacto;

    @FXML
    private TableColumn<Cliente, String> columnClienteMorada;

    @FXML
    private TableColumn<Cliente, Number> columnClienteMontanteCompra;

    @FXML
    private TableColumn<Cliente, Number> columnClienteMontanteDivida;

    @FXML
    private TableColumn<Cliente, Number> columnClienteMontanteTotal;

    @FXML
    private TableColumn<Cliente, Number> columnClienteMontantePago;

    @FXML
    private TableColumn<Cliente, Number> columnClienteMontantePorPagar;

    private JFXDialogLayout dialogContent;
    private JFXDialog dialog;
    private Node newClientModalContent;
    private ClienteNewController newClienteModalContentController;
    private List<Cliente> listCliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fabNewCliente.setOnAction( event -> this.newClient() );
        this.dialogContent = new JFXDialogLayout();
        this.dialog = new JFXDialog( this.stackPane, this.dialogContent, JFXDialog.DialogTransition.CENTER );
        this.listCliente = new LinkedList<Cliente>();
        this.structureColumns();
        this.reloadData();
    }

    private void reloadData() {
        PostgresSQL postgresSQL = PostgresSQLSingleton.loadPostgresSQL();
        Cliente.ClienteBuilder builder = new Cliente.ClienteBuilder();
        Distrito.DistritoBuilder distritoBuilder = new Distrito.DistritoBuilder();
        this.listCliente.clear();
        postgresSQL.query( "ggviario.funct_load_cliente" )
                .withOther( null )
                .callFunctionTable()
                .onResultQuery(
                        row -> this.listCliente.add(
                                builder.load( row )
                                        .distrito( distritoBuilder.load( row ).build() )
                                        .build()
                        )
                );
        this.tableViewCliente.setItems(FXCollections.observableList( this.listCliente ) );
    }

    private void structureColumns() {
        this.tableViewCliente.setRowFactory( clienteTableView -> new TableRow<Cliente>(){
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                if( item == null || empty ){
                    setItem( item );
                } else{
                    this.getStyleClass().add("row-normal");
                    setItem( item );
                }
            }
        });
        
        this.columnClienteNome.setCellValueFactory( data -> {
            String nome = data.getValue().getClienteNome();
            nome = nome +" "+ SQLResource.coalesce( data.getValue().getClienteApelido(), "" );
            nome = SQLText.normalize( nome );
            return  new SimpleStringProperty( nome );
        } );
        this.columnClienteNome.setCellFactory( cell -> this.getTextCell() );
        this.columnClienteNome.setMinWidth( 150 );

        this.columnClienteMorada.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getClienteMorada() ) );
        this.columnClienteMorada.setCellFactory( cell -> this.getTextCell());

        this.columnClienteContacto.setCellValueFactory( data-> new SimpleStringProperty( data.getValue().getClienteMorada() ) );
        this.columnClienteContacto.setCellFactory( cell -> this.getTextCell() );

        this.columnClienteMontanteCompra.setCellValueFactory( data -> new SimpleDoubleProperty( data.getValue().getClienteMonatenteCompra() ) );
        this.columnClienteMontanteCompra.setCellFactory( cell -> getNumberCell() );

        this.columnClienteMontanteDivida.setCellValueFactory( data -> new SimpleDoubleProperty( data.getValue().getClienteMontanteDivida() ) );
        this.columnClienteMontanteDivida.setCellFactory( cell -> this.getNumberCell() );

        this.columnClienteMontanteTotal.setCellValueFactory( data -> new SimpleDoubleProperty( data.getValue().getClienteMontanteTotal() ) );
        this.columnClienteMontanteTotal.setCellFactory( cell -> this.getNumberCell() );

        this.columnClienteMontantePago.setCellValueFactory( data -> new SimpleDoubleProperty( data.getValue().getClienteMontantePago() ) );
        this.columnClienteMontantePago.setCellFactory( cell -> this.getNumberCell() );

        this.columnClienteMontantePorPagar.setCellValueFactory( data -> new SimpleDoubleProperty( data.getValue().getClienteMontantePendente() ) );
        this.columnClienteMontantePorPagar.setCellFactory( cell -> this.getNumberCell() );
    }


    private void newClient() {
        this.loadModalContent();
        dialogContent.setHeading( new Text("Novo cliente") );
        dialogContent.setBody( this.newClientModalContent);
        this.newClienteModalContentController.newClient();
        this.newClienteModalContentController.setOnNewClienteResult( (result, cliente, data )->{
            this.reloadData();
            this.dialog.close();
        });
        this.dialog.show();
    }

    private void loadModalContent() {
        try{
            if( this.newClientModalContent == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/cliente_new.fxml" ) );
                this.newClientModalContent = loader.load();
                this.newClienteModalContentController = loader.getController();
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }
}
