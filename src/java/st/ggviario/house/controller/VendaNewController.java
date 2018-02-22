package st.ggviario.house.controller;

import com.google.gson.Gson;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import javafx.util.StringConverter;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLQueryBuilder;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class VendaNewController implements Initializable {


    interface OnNewVendaResult {
        void onNewVendaResult( NewVendaResult newVendaResult );
    }


    interface OnNewClienteRequest{
        void onRegisterNewClienteRequest();
    }

    private interface VendaRegister {
        NewVendaResult register( Venda venda );
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
    private JFXButton buttonVendaCadastrarDivida;

    @FXML
    private JFXTextField textFieldVendaQuantidade;

    @FXML
    private JFXTextField textFieldVendaMontanteBruto;

    @FXML
    private JFXTextField textFieldVendaMontanteUnitirio;

    @FXML
    private JFXTextField textFieldVendaDesconto;

    @FXML
    private JFXTextField textFieldVendaMontantePagar;

    @FXML
    private JFXDatePicker datePickerVendaDataFinalizar;

    @FXML
    private JFXDatePicker datePickerVendaData;

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

    @FXML
    private Label labelVendaMontanteFinalPagar;


    private List< Producto > productoList = new LinkedList<>();
    private List< Cliente > clienteList = new LinkedList<>();
    private Map< UUID, List<Preco> > mapListEquivalencia = new LinkedHashMap<>();

    private Map< TipoVenda, VendaRegister > actionRegister = new LinkedHashMap<>();

    private UUID clienteNewId;
    private Cliente clienteNew;
    private List<Cliente> clienteListFiltred = new LinkedList<>();

    private NumberFormat moneyFormater = NumberFormat.getInstance( Locale.FRANCE );
    private Cliente clienteAnonimo;


    private OnNewVendaResult onNewVendaResult;
    private OnNewClienteRequest onNewClienteRequest;
    private TipoVenda tipoVenda;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneyFormater.setMinimumFractionDigits(2);
        moneyFormater.setMaximumFractionDigits(2);
        this.newForm();

        String pattern = "dd-MM-yyyy";

        datePickerVendaData.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        datePickerVendaDataFinalizar.setConverter(this.datePickerVendaData.getConverter());

        this.comboxProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem) -> {
            this.comboxPrecoUnidades.setItems(FXCollections.observableList(this.mapListEquivalencia.get(newItem.getProdutoId())));
            this.resetForProduto();
        });

        this.listViewCliente.getSelectionModel().selectedItemProperty().addListener((observableValue, oldCliente, newCliente) -> {
            if (newCliente == null) {
                this.textFieldClienteNome.setText("");
                this.textFieldClienteContacto.setText("");
                this.textFieldClienteMorada.setText("");
                this.textFieldClienteMontantePendente.setText("");
                this.textFieldClienteMontanteTotal.setText("");
            } else {
                this.textFieldClienteNome.setText(newCliente.getClienteCompletName());
                this.textFieldClienteContacto.setText(newCliente.getClienteContanto("(Sem contacto)"));
                this.textFieldClienteMorada.setText(newCliente.getClienteLocal("(Sem local)"));
                this.textFieldClienteMontantePendente.setText(this.moneyFormater.format(newCliente.getClienteMontantePendente()));
                this.textFieldClienteMontanteTotal.setText(this.moneyFormater.format(newCliente.getClienteMontanteTotal()));
            }
        });

        this.loadClienteDatasource();
        this.loadProdutoDatasource();
        this.pushToView();

        this.textFieldVendaQuantidade.setOnKeyReleased(keyEvent -> this.calculateNow());
        this.textFieldVendaDesconto.setOnKeyReleased(keyEvent -> this.calculateNow());
        this.textFieldClienteSearch.setOnKeyReleased(key -> this.searchCliente(key));
        this.fabNewCliente.setOnAction(actionEvent -> this.newClienteRequest());
        this.buttonVendaCadastrarDivida.setOnAction(event -> this.registerCliente());
        this.datePickerVendaData.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (newDate != null) this.datePickerVendaDataFinalizar.setValue(newDate.plusDays(30));
            else this.datePickerVendaDataFinalizar.setValue(null);

        });

        this.setTipoVendaActions();
    }

    private void setTipoVendaActions() {
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        Colaborador colaborador = AuthSingleton.getAuth();
        this.actionRegister.put( TipoVenda.VENDA, (venda )-> this.execute( venda,
            sql.query("funct_reg_venda_venda")
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( venda.getProducto().getProdutoId() )
                .withUUID( venda.getUnidade().getUnidadeId() )
                .withUUID( venda.getCliente().getClienteId() )
                .withNumeric( venda.getVandaQuantidade() )
                .withNumeric( venda.getVendaMontenteUnidario() )
                .withNumeric( venda.getVendaMontanteBruto() )
                .withNumeric( venda.getVendaMontanteDesconto() )
                .withNumeric( venda.getVendaMontantePagar() )
                .withDate( venda.getVendaData() )
        ));
        this.actionRegister.put(TipoVenda.DIVIDA, venda ->{
            PostgresSQLQueryBuilder query = sql.query( "funct_reg_venda_divida" );
            query.withUUID( colaborador.getColaboradorId() );
            Producto p = venda.getProducto();
            query.withUUID( p.getProdutoId() );
            query.withUUID( venda.getUnidade().getUnidadeId() );
            query.withUUID( venda.getCliente().getClienteId() );
            query.withNumeric( venda.getVandaQuantidade() );
            query.withNumeric( venda.getVendaMontenteUnidario() );
            query.withNumeric( venda.getVendaMontanteBruto() );
            query.withNumeric( venda.getVendaMontanteDesconto() );
            query.withNumeric( venda.getVendaMontantePagar() );
            query.withDate( venda.getVendaData() );
            query.withDate( venda.getVendaDataFinalizar() );
            return this.execute( venda, query );
        });
    }

    private NewVendaResult execute( final Venda venda,  PostgresSQLQueryBuilder funct_reg_venda ) {
        Venda.VendaBuilder vendaBuilder =  new Venda.VendaBuilder();
        Movimento.MovimentoBuilder movimentoBuilder = new Movimento.MovimentoBuilder();

        Pair <Boolean, Map<String, Object> > mapPair = null;
        Gson gson = new Gson();
        NewVendaResult newVendaResult = new NewVendaResult();

        funct_reg_venda.callFunctionTable().onResultQuery( row ->{
            String documentRegister = String.valueOf( row.valueOf("message") );

            newVendaResult.resultData = gson.fromJson( documentRegister, Map.class );
            //movimento
            if( row.asBoolean("result") ){
                Venda registedVenda = vendaBuilder.load( (Map<String, Object>) newVendaResult.resultData.get("venda" ) ).build();
                newVendaResult.resultData =  new LinkedHashMap<>();
                newVendaResult.venda =  registedVenda;
                if( newVendaResult.resultData.get( "movimento" ) != null )
                    newVendaResult.movimento =  movimentoBuilder.load( ( Map<String, Object > ) newVendaResult.resultData.get( "movimento" ) ).build();
                newVendaResult.sucess = true;
            } else {
                newVendaResult.sucess = false;
                newVendaResult.movimento = null;
                newVendaResult.venda = venda;
            }
        });

        return newVendaResult;
    }

    private void newForm() {
        this.textFieldVendaMontanteUnitirio.setText( "" );
        this.textFieldVendaMontanteBruto.setText( "" );
        this.textFieldVendaMontantePagar.setText( "" );
        this.textFieldClienteSearch.setText( "" );
        this.labelVendaMontanteFinalPagar.setText( this.moneyFormater.format( 0.0 )+" STN");
        this.datePickerVendaData.setValue(LocalDate.now());
        this.datePickerVendaDataFinalizar.setValue( LocalDate.now().plusDays( 30 ) );
    }

    private void searchCliente(KeyEvent key) {
        String text = SQLText.normalize( this.textFieldClienteSearch.getText() );
        this.clienteListFiltred.clear();
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
            Venda venda = this.mountVenda();
            NewVendaResult result = this.actionRegister.get(this.tipoVenda).register(venda);
            if( this.onNewVendaResult != null ){
                this.onNewVendaResult.onNewVendaResult( result );
            }
        }
    }

    private Venda mountVenda() {
        Preco precoSelected = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();

        Date vendaData = null;
        if( this.datePickerVendaData.getValue() != null  )
            vendaData = java.sql.Date.valueOf( this.datePickerVendaData.getValue() );

        Date vendaDataFinalizar = null;
        if( this.datePickerVendaDataFinalizar.getValue() != null  )
            vendaDataFinalizar = java.sql.Date.valueOf( this.datePickerVendaDataFinalizar.getValue() );

        VendaNewController.CalcResult cal = this.calculateNow();
        Venda.VendaBuilder vendaBuilder = new Venda.VendaBuilder()
                .cliente( this.listViewCliente.getSelectionModel().getSelectedItem() )
                .produto( cal.producto )
                .unidade( cal.unidade )
                .montanteUnitario( cal.vendaMontanteUnitario )
                .quantidade( cal.vendaQuantidade )
                .montanteBruto( cal.vendaMontanteBruto )
                .montanteDesconto( cal.vendaMontanteDesconto )
                .montantePagar( cal.vendaMontantePagar )
                .data( vendaData )
                .dataFilanizar( vendaDataFinalizar )
                .tipoVenda( this.tipoVenda );
        return vendaBuilder.build();
    }

    private boolean validateForm() {
        VendaNewController.CalcResult cal = this.calculateNow();
        if( cal.vendaMontantePagar == null ) return false;
        if( this.listViewCliente.getSelectionModel().getSelectedItem() == null ) return  false;
        return true;
    }

    private void newClienteRequest() {
        if( this.onNewClienteRequest != null ){
            this.onNewClienteRequest.onRegisterNewClienteRequest();
        }
    }

    public VendaNewController setOnNewVendaResult(OnNewVendaResult onNewVendaResult) {
        this.onNewVendaResult = onNewVendaResult;
        return this;
    }

    public VendaNewController setOnNewClienteRequest(OnNewClienteRequest onNewClienteRequest) {
        this.onNewClienteRequest = onNewClienteRequest;
        return this;
    }

    private void pushToView() {

        this.comboxProduto.setItems(FXCollections.observableList( this.productoList ) );
        this.comboxPrecoUnidades.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPreco, newPreco ) -> this.onSeletedUnidade( newPreco ) );
        this.searchCliente( null );
    }

    private void resetForProduto() {
        this.textFieldVendaMontanteUnitirio.setText( "" );
        this.textFieldVendaMontanteBruto.setText( "" );
        this.textFieldVendaMontantePagar.setText( "" );
        this.labelVendaMontanteFinalPagar.setText( this.moneyFormater.format( 0.0 ) +" STN" );
    }

    private void onSeletedUnidade(Preco newPreco) {
        if( newPreco == null ){
            this.textFieldVendaMontanteUnitirio.setText("");
        } else {
            this.textFieldVendaMontanteUnitirio.setText( this.moneyFormater.format( newPreco.getPrecoCustoUnidade() ) );
        }
        this.calculateNow();
    }

    private CalcResult calculateNow() {
        CalcResult calcResult = new CalcResult();

        calcResult.preco = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();
        calcResult.unidade = calcResult.preco.getUnidade();
        calcResult.producto = this.comboxProduto.getSelectionModel().getSelectedItem();
        calcResult.vendaQuantidade = this.getVendaQuantidade();
        calcResult.vendaMontanteDesconto = this.getVendaDesconto();
        calcResult.vendaMontanteUnitario = calcResult.preco.getPrecoCustoUnidade();

        if( calcResult.preco != null && calcResult.vendaQuantidade != null ){
            calcResult.vendaMontanteBruto = calcResult.vendaMontanteUnitario * calcResult.vendaQuantidade;
            calcResult.vendaMontantePagar = calcResult.vendaMontanteBruto;
        }
        if( calcResult.vendaMontanteDesconto != null && calcResult.vendaMontanteBruto != null ){
            calcResult.vendaMontantePagar = calcResult.vendaMontanteBruto - calcResult.vendaMontanteDesconto;
        }

        if( calcResult.vendaMontanteBruto != null ){
            this.textFieldVendaMontanteBruto.setText( this.moneyFormater.format( calcResult.vendaMontanteBruto ));
        } else {
            this.textFieldVendaMontanteBruto.setText( "" );
        }

        if( calcResult.vendaMontantePagar != null ){
            this.textFieldVendaMontantePagar.setText( this.moneyFormater.format( calcResult.vendaMontantePagar ) );
            this.labelVendaMontanteFinalPagar.setText( this.moneyFormater.format( calcResult.vendaMontantePagar ) +" STN" );
        } else {
            this.textFieldVendaMontantePagar.setText( "" );
            this.labelVendaMontanteFinalPagar.setText( moneyFormater.format( 0.0 )+" STN" );
        }
        return calcResult;
    }

    private void loadClienteDatasource(){
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        this.clienteList.clear();
        this.clienteListFiltred.clear();

        Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
        sql.query( "funct_load_cliente_vendadivida" ).withJsonb( null ).callFunctionTable() .onResultQuery( row ->{
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

    public Double getVendaQuantidade() {
        String text = this.textFieldVendaQuantidade.getText();
        text = SQLText.normalize( text );
        try{
            return Double.parseDouble( text );
        } catch ( Exception ignored){}
        return null;
    }

    public Double getVendaDesconto() {
        String desconto = textFieldVendaDesconto.getText();
        desconto = SQLText.normalize( desconto );
        try{
            return Double.parseDouble( desconto );
        }catch ( Exception ignored) {}

        return null;
    }

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

    public void setTipoVenda(TipoVenda tipoVenda) {
        this.tipoVenda = tipoVenda;
        if( tipoVenda == TipoVenda.DIVIDA ){
            this.buttonVendaCadastrarDivida.setText( "REGISTAR DIVIDA" );
            this.datePickerVendaDataFinalizar.setVisible( true );
            this.datePickerVendaDataFinalizar.setEditable( true );
        } else {
            this.buttonVendaCadastrarDivida.setText( "REGISTAR VENDA" );
            this.datePickerVendaDataFinalizar.setVisible( false );
            this.datePickerVendaDataFinalizar.setEditable( false );
        }
    }

    private class  CalcResult {

        private Preco preco;
        private Double vendaQuantidade;
        private Double vendaMontanteDesconto;
        private Double vendaMontanteBruto;
        private Double vendaMontantePagar;
        private Double vendaMontanteUnitario;
        private Unidade unidade;
        private Producto producto;
    }

    public class NewVendaResult {
        private Map<String, Object > resultData;
        private boolean sucess;
        private Movimento movimento;
        private Venda venda;

        public Map<String, Object> getResultData() {
            return resultData;
        }

        public NewVendaResult setResultData(Map<String, Object> resultData) {
            this.resultData = resultData;
            return this;
        }

        public boolean isSucess() {
            return sucess;
        }

        public NewVendaResult setSucess(boolean sucess) {
            this.sucess = sucess;
            return this;
        }

        public Movimento getMovimento() {
            return movimento;
        }

        public NewVendaResult setMovimento(Movimento movimento) {
            this.movimento = movimento;
            return this;
        }

        public Venda getVenda() {
            return venda;
        }

        public NewVendaResult setVenda(Venda venda) {
            this.venda = venda;
            return this;
        }
    }


}
