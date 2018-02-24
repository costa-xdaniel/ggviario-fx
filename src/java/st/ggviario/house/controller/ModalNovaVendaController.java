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

public class ModalNovaVendaController implements Initializable {

    private Producto productoVasio;
    private Preco precoVasio;

    private String functionLoadClienteNew;
    private boolean ok;
    private boolean loaded;

    private List<RegisterVendaResult> listVendaResult = new LinkedList<>();

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
    private OnVendaFeito onVendaFeito;



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

    @FXML
    private JFXButton buttonVendaFeito;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneyFormater.setMinimumFractionDigits(2);
        moneyFormater.setMaximumFractionDigits(2);

        createVoidItems();
        this.newForm();
        componentStruture();
        setEventes();
        postgresSaveAction();

        if ( this.ok ){
            this.loadClienteDatasource();
            this.loadProdutoDatasource();
            this.pushToView();
            this.loaded = true;
        }
    }



    private void createVoidItems() {
        Producto.ProdutoBuilder builder = new Producto.ProdutoBuilder();
        builder.nome("Selecione");
        this.productoVasio = builder.build();

        Preco.PrecoBuilder precoBuilder = new Preco.PrecoBuilder();
        precoBuilder.unidade( new Unidade.UnidadeBuilder().nome( "Selecione" ).build() );
        this.precoVasio = precoBuilder.build();
    }

    private void newForm() {
        this.listViewCliente.getSelectionModel().select( null );
        this.comboxProduto.getSelectionModel().select( null );
        this.comboxPrecoUnidades.getSelectionModel().select( null );
        this.textFieldVendaQuantidade.setText( null );
        this.textFieldVendaMontanteUnitirio.setText( null );
        this.textFieldVendaMontanteBruto.setText( null );
        this.textFieldVendaMontantePagar.setText( null );
        this.textFieldClienteSearch.setText( null );
        this.labelVendaMontanteFinalPagar.setText( this.moneyFormater.format( 0.0 )+" STN");
        this.datePickerVendaData.setValue(LocalDate.now());
        this.datePickerVendaDataFinalizar.setValue( LocalDate.now().plusDays( 30 ) );
    }

    private void componentStruture() {
        datePickerVendaData.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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

        this.comboxPrecoUnidades.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPreco, newPreco ) -> this.onSeletedUnidade( newPreco ) );
    }

    private void setEventes() {

        this.textFieldVendaQuantidade.setOnKeyReleased(keyEvent -> this.calculateNow());
        this.textFieldVendaDesconto.setOnKeyReleased(keyEvent -> this.calculateNow());
        this.textFieldClienteSearch.setOnKeyReleased(key -> this.searchCliente(key));
        this.fabNewCliente.setOnAction(actionEvent -> this.newClienteRequest());
        this.buttonVendaCadastrarDivida.setOnAction(event -> this.registerCliente() );
        this.buttonVendaFeito.setOnAction( event -> this.vendaFeito() );
        this.comboxProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem) -> {
            this.onProdutoChange( oldItem, newItem );
        });

        this.datePickerVendaData.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (newDate != null) this.datePickerVendaDataFinalizar.setValue(newDate.plusDays(30));
            else this.datePickerVendaDataFinalizar.setValue(null);

        });

        this.listViewCliente.getSelectionModel().selectedItemProperty().addListener((observableValue, oldCliente, newCliente) -> clienteSelect(newCliente));
    }



    private void clienteSelect(Cliente newCliente) {
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
    }


    public void setFunctionLoadClienteNew(String functionLoadClienteNew) {
        this.functionLoadClienteNew = functionLoadClienteNew;
    }

    public void ok() {
        this.ok = true;
        if( !this.loaded ){
            this.loadClienteDatasource();
            this.loadProdutoDatasource();
            this.pushToView();
            this.loaded = true;
        }
    }

    private RegisterVendaResult execute(final Venda venda, PostgresSQLQueryBuilder funct_reg_venda ) {
        Venda.VendaBuilder vendaBuilder =  new Venda.VendaBuilder();
        Movimento.MovimentoBuilder movimentoBuilder = new Movimento.MovimentoBuilder();

        Pair <Boolean, Map<String, Object> > mapPair = null;
        Gson gson = new Gson();
        RegisterVendaResult registerVendaResult = new RegisterVendaResult();

        funct_reg_venda.callFunctionTable().onResultQuery( row ->{
            String documentRegister = String.valueOf( row.valueOf("message") );

            registerVendaResult.resultData = gson.fromJson( documentRegister, Map.class );
            //movimento
            if( row.asBoolean("result") ){
                Venda registedVenda = vendaBuilder.load( (Map<String, Object>) registerVendaResult.resultData.get("venda" ) ).build();
                registerVendaResult.resultData =  new LinkedHashMap<>();
                registerVendaResult.venda =  registedVenda;
                if( registerVendaResult.resultData.get( "movimento" ) != null )
                    registerVendaResult.movimento =  movimentoBuilder.load( ( Map<String, Object > ) registerVendaResult.resultData.get( "movimento" ) ).build();
                registerVendaResult.sucess = true;
            } else {
                registerVendaResult.sucess = false;
                registerVendaResult.movimento = null;
                registerVendaResult.venda = venda;
                registerVendaResult.message = String.valueOf( registerVendaResult.resultData.get("text") );
            }
        });

        return registerVendaResult;
    }



    private void searchCliente( KeyEvent key) {
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
        RegisterVendaResult result = this.validateForm();
        if( result.sucess ){
            Venda venda = this.mountVenda();
            result = this.actionRegister.get(this.tipoVenda).register(venda);

            if( result.sucess ){
                this.listVendaResult.add( result );
                this.newForm();
            }
        }

        if( this.onNewVendaResult != null ){
            this.onNewVendaResult.onNewVendaResult( result );
        }
    }

    private void vendaFeito() {
        if( this.onVendaFeito != null ){
            this.onVendaFeito.onAfertOperation( this.listVendaResult );
        }
        this.listVendaResult = new LinkedList<>();
        this.newForm();
    }


    private Venda mountVenda() {
        Preco precoSelected = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();

        Date vendaData = null;
        if( this.datePickerVendaData.getValue() != null  )
            vendaData = java.sql.Date.valueOf( this.datePickerVendaData.getValue() );

        Date vendaDataFinalizar = null;
        if( this.datePickerVendaDataFinalizar.getValue() != null  )
            vendaDataFinalizar = java.sql.Date.valueOf( this.datePickerVendaDataFinalizar.getValue() );

        ModalNovaVendaController.CalcResult cal = this.calculateNow();
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

    private RegisterVendaResult validateForm() {
        RegisterVendaResult registerVendaResult = new RegisterVendaResult();
        ModalNovaVendaController.CalcResult cal = this.calculateNow();
        Producto produto = this.comboxProduto.getSelectionModel().getSelectedItem();
        Preco preco = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();


        registerVendaResult.sucess = false;
        if( this.listViewCliente.getSelectionModel().getSelectedItem() == null ){
            registerVendaResult.message = "Nenum cliente seleicionado!";
        } else if( produto  == null || produto.equals( this.productoVasio ) ){
            registerVendaResult.message = "Nenhum produto selecionado!";
        } else if( preco == null || preco.equals( this.precoVasio ) ){
            registerVendaResult.message = "Nenhuma unidade selecionada!";
        } else if ( this.getVendaQuantidade() == null ) {
            registerVendaResult.message = "Quantidade de venda não informada!";
        } else {
            registerVendaResult.sucess = true;
        }
        return registerVendaResult;
    }

    private void newClienteRequest() {
        if( this.onNewClienteRequest != null ){
            this.onNewClienteRequest.onRegisterNewClienteRequest();
        }
    }

    public ModalNovaVendaController setOnNewVendaResult(OnNewVendaResult onNewVendaResult) {
        this.onNewVendaResult = onNewVendaResult;
        return this;
    }

    public ModalNovaVendaController setOnVendaFeito(OnVendaFeito onVendaFeito ) {
        this.onVendaFeito = onVendaFeito;
        return this;
    }

    public ModalNovaVendaController setOnNewClienteRequest(OnNewClienteRequest onNewClienteRequest) {
        this.onNewClienteRequest = onNewClienteRequest;
        return this;
    }

    private void pushToView() {

        this.comboxProduto.setItems( FXCollections.observableList( this.productoList ) );
        this.searchCliente( null );
    }

    private void onProdutoChange( Producto oldItem, Producto newItem ) {
        if( newItem != null && !newItem.equals( this.productoVasio ) )
            this.comboxPrecoUnidades.setItems( FXCollections.observableList( this.mapListEquivalencia.get( newItem.getProdutoId()) ) );
        else this.comboxPrecoUnidades.setItems( FXCollections.observableList( Arrays.asList( this.precoVasio ) ) );

        this.textFieldVendaMontanteUnitirio.setText( "" );
        this.textFieldVendaMontanteBruto.setText( "" );
        this.textFieldVendaMontantePagar.setText( "" );
        this.labelVendaMontanteFinalPagar.setText( this.moneyFormater.format( 0.0 ) +" STN" );
    }

    private void onSeletedUnidade(Preco newPreco) {
        if( newPreco == null || newPreco.equals( this.precoVasio ) ){
            this.textFieldVendaMontanteUnitirio.setText("");
        } else {
            this.textFieldVendaMontanteUnitirio.setText( this.moneyFormater.format( newPreco.getPrecoCustoUnidade() ) );
        }
        this.calculateNow();
    }

    private CalcResult calculateNow() {
        CalcResult calcResult = new CalcResult();

        calcResult.preco = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();
        if( calcResult.preco == null || calcResult.preco.equals( this.precoVasio ) ) calcResult.preco = null;
        else {
            calcResult.unidade = calcResult.preco.getUnidade();
            calcResult.vendaMontanteUnitario = calcResult.preco.getPrecoCustoUnidade();
        }

        calcResult.producto = this.comboxProduto.getSelectionModel().getSelectedItem();
        if( calcResult.producto == null || calcResult.producto.equals( this.productoVasio ) ) calcResult.producto = null;

        calcResult.vendaQuantidade = this.getVendaQuantidade();
        calcResult.vendaMontanteDesconto = this.getVendaDesconto();


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
        sql.query( this.functionLoadClienteNew ).withJsonb( (String) null ).callFunctionTable() .onResultQuery( row ->{
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

        this.productoList.add( this.productoVasio );

        Producto.ProdutoBuilder produtoBuilder = new Producto.ProdutoBuilder();
        Preco.PrecoBuilder precoBuilder = new Preco.PrecoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();

        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        Gson gson = new Gson();

        sql.query("funct_load_produto_venda") .withJsonb( (String) null ) .callFunctionTable().onResultQuery( row ->{
            produtoBuilder.load( row );
            Producto produto;
            productoList.add( produto = produtoBuilder.build() );
            List<Preco> equivalenciaList = new LinkedList<>();
            equivalenciaList.add( this.precoVasio );
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

    private void postgresSaveAction() {
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        Colaborador colaborador = AuthSingleton.getAuth();
        this.actionRegister.put(TipoVenda.VENDA, venda -> {
            ContaManager contaManager = new ContaManager();
            Conta conta = contaManager.getContaFor(ContaManager.ContaOperacao.PAGAMENTO_VENDA );
            return  execute( venda,
                    sql.query("funct_reg_venda_venda")
                            .withUUID( colaborador.getColaboradorId() )
                            .withUUID( venda.getProducto().getProdutoId() )
                            .withUUID( venda.getUnidade().getUnidadeId() )
                            .withUUID( venda.getCliente().getClienteId() )
                            .withUUID( conta == null? null : conta.getContaId() )
                            .withNumeric( venda.getVandaQuantidade() )
                            .withNumeric( venda.getVendaMontanteUnidario() )
                            .withNumeric( venda.getVendaMontanteBruto() )
                            .withNumeric( venda.getVendaMontanteDesconto() )
                            .withNumeric( venda.getVendaMontantePagar() )
                            .withDate( venda.getVendaData() )
            );
        });
        this.actionRegister.put(TipoVenda.DIVIDA, venda ->{
            PostgresSQLQueryBuilder query = sql.query( "funct_reg_venda_divida" );
            query.withUUID( colaborador.getColaboradorId() );
            Producto p = venda.getProducto();
            query.withUUID( p.getProdutoId() );
            query.withUUID( venda.getUnidade().getUnidadeId() );
            query.withUUID( venda.getCliente().getClienteId() );
            query.withNumeric( venda.getVandaQuantidade() );
            query.withNumeric( venda.getVendaMontanteUnidario() );
            query.withNumeric( venda.getVendaMontanteBruto() );
            query.withNumeric( venda.getVendaMontanteDesconto() );
            query.withNumeric( venda.getVendaMontantePagar() );
            query.withDate( venda.getVendaData() );
            query.withDate( venda.getVendaDataFinalizar() );
            return this.execute( venda, query );
        });
    }

    interface OnNewVendaResult {
        void onNewVendaResult( RegisterVendaResult registerVendaResult);
    }

    interface OnVendaFeito {
        void onAfertOperation(List<RegisterVendaResult> results );
    }


    interface OnNewClienteRequest{
        void onRegisterNewClienteRequest();
    }

    private interface VendaRegister {
        RegisterVendaResult register(Venda venda );
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

    public class RegisterVendaResult {

        private Map<String, Object > resultData;
        private boolean sucess;
        private Movimento movimento;
        private Venda venda;
        private String message;

        public Map<String, Object> getResultData() {
            return resultData;
        }

        public RegisterVendaResult setResultData(Map<String, Object> resultData) {
            this.resultData = resultData;
            return this;
        }

        public boolean isSucess() {
            return sucess;
        }

        public RegisterVendaResult setSucess(boolean sucess) {
            this.sucess = sucess;
            return this;
        }

        public Movimento getMovimento() {
            return movimento;
        }

        public RegisterVendaResult setMovimento(Movimento movimento) {
            this.movimento = movimento;
            return this;
        }

        public Venda getVenda() {
            return venda;
        }

        public RegisterVendaResult setVenda(Venda venda) {
            this.venda = venda;
            return this;
        }

        public String getMessage() {
            return message;
        }
    }



}
