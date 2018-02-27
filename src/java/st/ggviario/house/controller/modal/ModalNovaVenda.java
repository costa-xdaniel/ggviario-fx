package st.ggviario.house.controller.modal;

import com.google.gson.Gson;
import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.SnackbarBuilder;
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

public class ModalNovaVenda extends AbstractModal< List > implements Initializable {

    private JFXRippler ripplerCloseModal;

    public static ModalNovaVenda load(TipoVenda tipoVenda, String functionLoadCliente, StackPane stackPane ){
        ControllerLoader< AnchorPane, ModalNovaVenda> loader = new ControllerLoader<>("/fxml/includs/modal_nova_venda.fxml");
        ModalNovaVenda modal = loader.getViewController().getController();
        modal.functionLoadCliente = functionLoadCliente;
        modal.createDialogModal( stackPane );
        modal.loadClienteDatasource();
        modal.loadProdutoDatasource();
        modal.pushToView();
        modal.setTipoVenda( tipoVenda );

        return modal;
    }


    @FXML
    private AnchorPane root;

    @FXML
    private Label modalTitle;

    @FXML
    private AnchorPane anchorHeader;

    @FXML
    private AnchorPane iconAreaCloseModal;

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
    private JFXButton buttonNovaVenda;

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
    private Label textFieldClienteNome;

    @FXML
    private Label textFieldClienteMorada;

    @FXML
    private Label textFieldClienteContacto;

    @FXML
    private Label textFieldClienteMontanteTotal;

    @FXML
    private Label textFieldClienteMontantePendente;

    @FXML
    private Label labelVendaMontanteFinalPagar;

    @FXML
    private JFXButton buttonVendaFeito;


    private Producto productoVasio;
    private Preco precoVasio;

    private String functionLoadCliente;

    private List< Producto > productoList = new LinkedList<>();
    private List< Cliente > clienteList = new LinkedList<>();
    private Map< UUID, List<Preco> > mapListEquivalencia = new LinkedHashMap<>();

    private Map< TipoVenda, VendaRegister > actionRegister = new LinkedHashMap<>();
    private List<Cliente> clienteListFiltred = new LinkedList<>();

    private NumberFormat moneyFormater = NumberFormat.getInstance( Locale.FRANCE );
    private Cliente clienteAnonimo;

    private TipoVenda tipoVenda;

    private ModalNovoCliente modalNovoCliente;
    private List< RegisterVendaResult > vendasResult = new LinkedList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneyFormater.setMinimumFractionDigits(2);
        moneyFormater.setMaximumFractionDigits(2);

        this.ripplerCloseModal = new JFXRippler( this.iconAreaCloseModal);
        this.anchorHeader.getChildren().add( this.ripplerCloseModal );
        AnchorPane.setTopAnchor( ripplerCloseModal, 0.0 );
        AnchorPane.setRightAnchor( ripplerCloseModal, 0.0 );
        this.ripplerCloseModal.setStyle( "-jfx-rippler-fill: md-red-500" );

        createVoidItems();
        this.newForm();
        componentStruture();
        defineEvents();
        postgresSaveAction();
    }


    @Override
    Region getContentRoot() {
        return this.root;
    }

    @Override
    Label getTitleNode() {
        return this.modalTitle;
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
        this.newSearch("");
        this.labelVendaMontanteFinalPagar.setText( this.moneyFormater.format( 0.0 )+" STN");
        this.datePickerVendaData.setValue(LocalDate.now());
        this.datePickerVendaDataFinalizar.setValue( LocalDate.now().plusDays( 30 ) );
        this.textFieldClienteSearch.setEditable( true );
        this.textFieldClienteSearch.setDisable( false );
        this.listViewCliente.setDisable( false );
        if( this.tipoVenda == TipoVenda.DIVIDA ) this.modalTitle.setText("Nova divida");
        else this.modalTitle.setText( "Nova venda" );
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

    private void defineEvents() {

        this.comboxProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem) -> this.onSelectProduto( oldItem, newItem ));
        this.listViewCliente.getSelectionModel().selectedItemProperty().addListener((observableValue, oldCliente, newCliente) -> onSelectCliente(newCliente));

        this.textFieldVendaQuantidade.setOnKeyReleased(keyEvent -> this.onCalculateValue());
        this.textFieldVendaDesconto.setOnKeyReleased(keyEvent -> this.onCalculateValue());
        this.textFieldClienteSearch.setOnKeyReleased(this::onSearchCliente);
        this.fabNewCliente.setOnAction(actionEvent -> this.onNewCliente());

        this.datePickerVendaData.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (newDate != null) this.datePickerVendaDataFinalizar.setValue(newDate.plusDays(30));
            else this.datePickerVendaDataFinalizar.setValue(null);

        });

        this.buttonNovaVenda.setOnAction(event -> this.onNovaVenda() );
        this.buttonVendaFeito.setOnAction( event -> this.onVendaFeito() );
        this.ripplerCloseModal.setOnMouseClicked(mouseEvent -> {
            this.newForm();
            this.closeModal();
        });

    }



    private void onSelectCliente(Cliente newCliente) {
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

    private RegisterVendaResult execute(final Venda venda, PostgresSQLQueryBuilder funct_reg_venda ) {
        Venda.VendaBuilder vendaBuilder =  new Venda.VendaBuilder();

        Gson gson = new Gson();
        RegisterVendaResult res = new RegisterVendaResult();
        res.venda = venda;

        funct_reg_venda.callFunctionTable().onResultQuery( row ->{
            String documentRegister = row.asString("message");

            res.resultData = gson.fromJson( documentRegister, Map.class );

            //movimento
            if( row.asBoolean("result") ){
                res.venda = vendaBuilder.load( (Map<String, Object>) res.resultData.get("venda" ) ).build();
                res.success = true;
            } else {
                res.message = String.valueOf( res.resultData.get("text") );
                res.success = false;
            }
        });

        return res;
    }



    private void onSearchCliente(KeyEvent key ) {
        String text = SQLText.normalize( this.textFieldClienteSearch.getText() );
        newSearch(text);
    }

    private void newSearch(String text) {
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

    private boolean findClienteSelecter(Cliente cliente ) {
        if( cliente == null ) return  false;
        this.loadProdutoDatasource();
        this.clienteListFiltred.clear();
        for( Cliente cli : this.clienteList ) {
            if (cliente.equals(cli)) {
                this.clienteListFiltred.add(cli);
                this.listViewCliente.setItems( FXCollections.observableList( this.clienteListFiltred ) );
                this.listViewCliente.getSelectionModel().select(cli);
                return true;

            }
        }
        return false;
    }

    private void onNovaVenda() {
        RegisterVendaResult result = this.validateForm();
        if( result.success){
            Venda venda = this.mountVenda();
            result = this.actionRegister.get(this.tipoVenda).register(venda);
            if( result.success){
                this.newForm();
            }
        }

        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        this.vendasResult.add( result );
        if( result.isSucceed() ){
            snackbarBuilder.success( "Nova "+this.tipoVenda.name().toLowerCase()+" cadastrada com sucesso");
        } else {
            snackbarBuilder.error( result.message );
        }
    }

    private void onVendaFeito() {
        RegisterVendaResult result = new RegisterVendaResult();
        result.success = true;
        result.vendasResult = this.vendasResult;
        int countSuccess = 0;
        for( RegisterVendaResult result1 : this.vendasResult ){
            if( result1.isSucceed() ) countSuccess ++;
        }
        this.closeModal();
        this.newForm();

        super.executeOnOperationResult( result );
        this.vendasResult = new LinkedList<>();
        SnackbarBuilder builder = new SnackbarBuilder( this.getStakePane() );
        if( countSuccess == 1 ) builder.information( countSuccess+" novas "+this.tipoVenda.name().toLowerCase()+" foi cadastradas!" );
        else if( countSuccess > 1 ) builder.information( countSuccess+" novas "+this.tipoVenda.name().toLowerCase()+" foram cadastradas!" );
        else builder.warning( "Nenhuma nova "+this.tipoVenda.name().toLowerCase()+" foi cadastrada!");
    }


    private Venda mountVenda() {
        Date vendaData = null;
        if( this.datePickerVendaData.getValue() != null  )
            vendaData = java.sql.Date.valueOf( this.datePickerVendaData.getValue() );

        Date vendaDataFinalizar = null;
        if( this.datePickerVendaDataFinalizar.getValue() != null  )
            vendaDataFinalizar = java.sql.Date.valueOf( this.datePickerVendaDataFinalizar.getValue() );

        ModalNovaVenda.CalcResult cal = this.onCalculateValue();
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
        ModalNovaVenda.CalcResult cal = this.onCalculateValue();
        Producto produto = this.comboxProduto.getSelectionModel().getSelectedItem();
        Preco preco = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();


        registerVendaResult.success = false;
        if( this.listViewCliente.getSelectionModel().getSelectedItem() == null ){
            registerVendaResult.message = "Nenum cliente seleicionado!";
        } else if( produto  == null || produto.equals( this.productoVasio ) ){
            registerVendaResult.message = "Nenhum produto selecionado!";
        } else if( preco == null || preco.equals( this.precoVasio ) ){
            registerVendaResult.message = "Nenhuma unidade selecionada!";
        } else if ( this.getVendaQuantidade() == null ) {
            registerVendaResult.message = "Quantidade de venda não informada!";
        } else {
            registerVendaResult.success = true;
        }
        return registerVendaResult;
    }

    private void onNewCliente() {
        EventHandler<? super JFXDialogEvent> oldOnClose = this.getDialogModal().getOnDialogClosed();
        this.loadModalNovoCliente();
        this.getDialogModal().setOnDialogClosed(jfxDialogEvent -> {
            this.modalNovoCliente.openModal();
            this.getDialogModal().setOnDialogClosed( oldOnClose );
        });
        this.closeModal();
    }

    private void loadModalNovoCliente() {
        if( this.modalNovoCliente == null ){
            this.modalNovoCliente = ModalNovoCliente.load( this.getStakePane() );
            this.modalNovoCliente.getDialogModal().setOnDialogClosed(jfxDialogEvent -> this.openModal());
            this.modalNovoCliente.setOnOperationResult( operationResult -> {
                if( operationResult.isSucceed() ){
                    this.loadClienteDatasource();
                    boolean res = this.findClienteSelecter( operationResult.getResltValue() );
                    this.textFieldClienteSearch.setText( operationResult.getResltValue().getClienteCompletName() );
                }
            });
        }
    }

    private void pushToView() {

        this.comboxProduto.setItems( FXCollections.observableList( this.productoList ) );
        this.onSearchCliente( null );
    }

    private void onSelectProduto(Producto oldItem, Producto newItem ) {
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
        this.onCalculateValue();
    }

    private CalcResult onCalculateValue() {
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
        sql.query( this.functionLoadCliente).withJsonb( (String) null ).callFunctionTable() .onResultQuery(row ->{
            Cliente cliente;
            this.clienteList.add( cliente =  clienteBuilder.load( row ).build() );
            if( cliente.getClienteId().equals( new UUID(0, 1 ) ) )
                this.clienteAnonimo = cliente;
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

    private void setTipoVenda(TipoVenda tipoVenda) {
        this.tipoVenda = tipoVenda;
        if( tipoVenda == TipoVenda.DIVIDA ){
            this.buttonNovaVenda.setText( "REGISTAR DIVIDA" );
            this.datePickerVendaDataFinalizar.setVisible( true );
            this.datePickerVendaDataFinalizar.setEditable( true );
            this.modalTitle.setText( "Nova divida" );
        } else {
            this.buttonNovaVenda.setText( "REGISTAR VENDA" );
            this.datePickerVendaDataFinalizar.setVisible( false );
            this.datePickerVendaDataFinalizar.setEditable( false );
            this.modalTitle.setText( "Nova venda" );
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
        this.actionRegister.put(TipoVenda.DIVIDA, new VendaRegister() {
            @Override
            public RegisterVendaResult register(Venda venda) {
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
                return execute( venda, query );
            }
        });
    }

    @Override
    public void openModal() {
        super.openModal();
    }

    public void openModalForCliente(Cliente cliente ) {
        this.openModal();
        this.findClienteSelecter( cliente );
        this.textFieldClienteSearch.setEditable( false );
        this.textFieldClienteSearch.setDisable( true );
        this.textFieldClienteSearch.setText( cliente.getClienteCompletName() );
        this.listViewCliente.setDisable( true );
        if( this.tipoVenda == TipoVenda.DIVIDA ) this.modalTitle.setText("Nova divida para " + cliente.getClienteCompletName());
        else this.modalTitle.setText("Nova venda para " + cliente.getClienteCompletName());
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

    public static class RegisterVendaResult implements OperationResult< List > {

        private Map<String, Object > resultData;
        private boolean success;
        private Venda venda;

        private List< RegisterVendaResult > vendasResult;
        private String message;


        @Override
        public boolean isSucceed() {
            return this.success;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public boolean isTerminated() {
            return false;
        }

        @Override
        public List< RegisterVendaResult > getResltValue() {
            return this.vendasResult;
        }

        @Override
        public Map<String, Object> mapResults() {
            return resultData;
        }
    }



}
