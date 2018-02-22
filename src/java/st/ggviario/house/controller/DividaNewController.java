package st.ggviario.house.controller;

import com.google.gson.Gson;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class DividaNewController implements Initializable {



    public void onClienteRequestResult(boolean result, Cliente cliente, Map<String, Object> data) {
        if( result ){
            this.clienteNewId = cliente.getClienteId();
            this.loadClienteDatasource();
            if( this.clienteNew != null ){
                this.listViewCliente.getSelectionModel().select( clienteNew );
            }
            this.listViewCliente.refresh();
        } else {
            this.clienteNewId = null;
            this.clienteNew = null;
        }
    }

    interface OnNewCompraResult {
        void onNewCompraResult(boolean result, Compra compra, Map<String, Object > map );
    }


    interface OnNewClienteRequest{
        void onRegisterNewClienteRequest();
    }

    @FXML
    private JFXTextField textFieldClienteSearch;

    @FXML
    private JFXListView< Cliente > listViewCliente;

    @FXML
    private JFXComboBox< Producto > comboxProduto;

    @FXML
    private JFXComboBox<Preco> comboxPrecoUnidades;

    @FXML
    private JFXButton fabNewCliente;

    @FXML
    private JFXButton buttonCompraCadastrarDivida;

    @FXML
    private JFXTextField textFieldCompraQuantidade;

    @FXML
    private JFXTextField textFieldCompraMontanteBruto;

    @FXML
    private JFXTextField textFieldCompraMontanteUnitirio;

    @FXML
    private JFXTextField textFieldCompraDesconto;

    @FXML
    private JFXTextField textFieldCompraMontantePagar;

    @FXML
    private JFXDatePicker dataPickerPrazo;

    @FXML
    private JFXDatePicker dataPckerData;

    @FXML
    private JFXTextField textFieldClienteNome;

    @FXML
    private JFXTextField textFieldClienteMorada;

    @FXML
    private JFXTextField textFieldClienteContacto;

    @FXML
    private JFXTextField textFieldClienteMontanteTotal;

    @FXML
    private JFXTextField textFieldClienteMontantePendente;


    private List< Producto > productoList = new LinkedList<>();
    private List< Cliente > clienteList = new LinkedList<>();
    private Map< UUID, List<Preco> > mapListEquivalencia = new LinkedHashMap<>();

    private UUID clienteNewId;
    private Cliente clienteNew;
    private List<Cliente> clienteListFiltred = new LinkedList<>();

    private NumberFormat moneyFormater = NumberFormat.getInstance( Locale.FRANCE );
    private Cliente clienteAnonimo;


    private OnNewCompraResult onNewCompraResult;
    private OnNewClienteRequest onNewClienteRequest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneyFormater.setMinimumFractionDigits( 2 );
        moneyFormater.setMaximumFractionDigits( 2 );

        this.comboxProduto.getSelectionModel().selectedItemProperty().addListener( (observable, oldItem, newItem )->{
            this.comboxPrecoUnidades.setItems( FXCollections.observableList( this.mapListEquivalencia.get(newItem.getProdutoId())) );
            this.resetForProduto();
        });

        this.listViewCliente.getSelectionModel().selectedItemProperty().addListener((observableValue, oldCliente, newCliente) -> {
            if( newCliente == null ){
                this.textFieldClienteNome.setText( "" );
                this.textFieldClienteContacto.setText( "" );
                this.textFieldClienteMorada.setText( "" );
                this.textFieldClienteMontantePendente.setText( "" );
                this.textFieldClienteMontanteTotal.setText( "" );
            }else {
                this.textFieldClienteNome.setText( newCliente.getClienteCompletName() );
                this.textFieldClienteContacto.setText( newCliente.getClienteContanto( "(Sem contacto)") );
                this.textFieldClienteMorada.setText( newCliente.getClienteLocal( "(Sem local)") );
                this.textFieldClienteMontantePendente.setText( this.moneyFormater.format( newCliente.getClienteMontantePendente()) );
                this.textFieldClienteMontanteTotal.setText( this.moneyFormater.format( newCliente.getClienteMontanteTotal() ) );
            }
        });

        this.loadClienteDatasource();
        this.loadProdutoDatasource();
        this.pushToView();


        this.textFieldCompraQuantidade.setOnKeyReleased(keyEvent -> this.calculateNow() );
        this.textFieldCompraDesconto.setOnKeyReleased(keyEvent -> this.calculateNow() );
        this.textFieldClienteSearch.setOnKeyReleased( key -> this.searchCliente( key ) );
        this.fabNewCliente.setOnAction(actionEvent -> this.newClienteRequest() );
        this.buttonCompraCadastrarDivida.setOnAction( event -> this.registerCliente() );

    }

    private void searchCliente(KeyEvent key) {
        String text = SQLText.normalize( this.textFieldClienteSearch.getText() );
        this.clienteListFiltred.clear();;
        if( text == null ){
            this.clienteListFiltred.addAll( this.clienteList );
        } else {
            for( Cliente cli : this.clienteList ){
                if( cli.getClienteCompletName().toLowerCase().contains( text.toLowerCase() ) )
                    this.clienteListFiltred.add( cli );
            }
        }
        this.listViewCliente.setItems( FXCollections.observableList( this.clienteListFiltred) );
    }

    private void registerCliente() {
        if( this.validateForm( ) ){
            Compra compra = this.mountCompra();
        }
    }

    private Compra mountCompra() {
        Compra.CompraBuilder compraBuilder = new Compra.CompraBuilder()
                .cliente( this.listViewCliente.getSelectionModel().getSelectedItem() )
                .produto( this.comboxProduto.getSelectionModel().getSelectedItem() )
                .unidade( this.comboxPrecoUnidades.getSelectionModel().getSelectedItem().getUnidade() )
                .quantidade( this.getCompraQuantidade() )

                //TODO faltar continuar a partir daqui

                                ;
    }

    private boolean validateForm() {
        return true;
    }

    private void newClienteRequest() {
        if( this.onNewClienteRequest != null ){
            this.onNewClienteRequest.onRegisterNewClienteRequest();
        }
    }

    public DividaNewController setOnNewCompraResult(OnNewCompraResult onNewCompraResult) {
        this.onNewCompraResult = onNewCompraResult;
        return this;
    }

    public DividaNewController setOnNewClienteRequest(OnNewClienteRequest onNewClienteRequest) {
        this.onNewClienteRequest = onNewClienteRequest;
        return this;
    }

    private void pushToView() {

        this.comboxProduto.setItems(FXCollections.observableList( this.productoList ) );
        this.comboxPrecoUnidades.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPreco, newPreco ) -> this.onSeletedUnidade( newPreco ) );
        this.searchCliente( null );
    }

    private void resetForProduto() {
        this.textFieldCompraMontanteUnitirio.setText( "" );
        this.textFieldCompraMontanteBruto.setText( "" );
        this.textFieldCompraMontantePagar.setText( "" );
    }

    private void onSeletedUnidade(Preco newPreco) {
        if( newPreco == null ){
            this.textFieldCompraMontanteUnitirio.setText("");
        } else {
            this.textFieldCompraMontanteUnitirio.setText( this.moneyFormater.format( newPreco.getPrecoCustoUnidade() ) );
        }
        this.calculateNow();
    }

    private void calculateNow() {
        Preco preco = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();
        Double compraQuantidade = this.getCompraQuantidade();
        Double compraMontanteDesconto = this.getCompraDesconto();
        Double compraMontanteBruto = null;
        Double compraMontantePagar = null;

        if( preco != null && compraQuantidade != null ){
            compraMontanteBruto = preco.getPrecoCustoUnidade() * compraQuantidade;
            compraMontantePagar = compraMontanteBruto;
        }

        if( compraMontanteDesconto != null && compraMontanteBruto != null ){
            compraMontantePagar = compraMontanteBruto - compraMontanteDesconto;
        }

        if( compraMontanteBruto != null ){
            this.textFieldCompraMontanteBruto.setText( this.moneyFormater.format( compraMontanteBruto ));
        } else {
            this.textFieldCompraMontanteBruto.setText( "" );
        }

        if( compraMontantePagar != null ){
            this.textFieldCompraMontantePagar.setText( this.moneyFormater.format( compraMontantePagar ) );
        } else {
            this.textFieldCompraMontantePagar.setText( "" );
        }

    }

    private void loadClienteDatasource(){
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        this.clienteList.clear();
        this.clienteListFiltred.clear();

        Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
        sql.query( "funct_load_cliente_divida" ).withJsonb( null ).callFunctionTable() .onResultQuery( row ->{
            Cliente cliente;
            this.clienteList.add( cliente =  clienteBuilder.load( row ).build() );
            if( cliente.getClienteId().equals( new UUID(0, 1 ) ) )
                this.clienteAnonimo = cliente;
            if( this.clienteNewId != null && this.clienteNewId.equals( cliente.getClienteId() ) )
                this.clienteNew = cliente;
        });

        this.clienteListFiltred.addAll( this.clienteList );
    }

    private void loadProdutoDatasource() {
        this.productoList.clear();
        this.mapListEquivalencia.clear();

        Producto.ProdutoBuilder produtoBuilder = new Producto.ProdutoBuilder();
        Preco.PrecoBuilder precoBuilder = new Preco.PrecoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();

        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        Gson gson = new Gson();

        sql.query("funct_load_produto_venda") .withJsonb( null ) .callFunctionTable().onResultQuery( row ->{
            produtoBuilder.load( row );
            Producto produto;
            productoList.add( produto = produtoBuilder.build() );
            List<Preco> equivalenciaList = new LinkedList<>();

            String documentEquivalencia = String.valueOf( row.valueOf("produto_precos"));

            List<Map<String, Object> >  aux = gson.fromJson( documentEquivalencia, List.class );
            for( Map<String, Object > map : aux ){
                equivalenciaList.add(
                        precoBuilder.load( map )
                        .produto( produto )
                        .unidade( unidadeBuilder.load( map ).build() )
                        .build()
                );
            }
            this.mapListEquivalencia.put( produto.getProdutoId(), equivalenciaList );
        });

    }

    public Double getCompraQuantidade() {
        String text = this.textFieldCompraQuantidade.getText();
        text = SQLText.normalize( text );
        try{
            return Double.parseDouble( text );
        } catch ( Exception ignored){}
        return null;
    }

    public Double getCompraDesconto() {
        String desconto = textFieldCompraDesconto.getText();
        desconto = SQLText.normalize( desconto );
        try{
            return Double.parseDouble( desconto );
        }catch ( Exception ignored) {}

        return null;
    }
}
