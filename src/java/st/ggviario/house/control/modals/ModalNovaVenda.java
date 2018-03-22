package st.ggviario.house.control.modals;

import com.google.gson.Gson;
import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.effects.JFXDepthManager;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.SnackbarBuilder;
import st.ggviario.house.control.component.DatePickerRange;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLQueryBuilder;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class ModalNovaVenda extends AbstractModal< List > implements Initializable {

    public static ModalNovaVenda load(TipoVenda tipoVenda, String functionLoadCliente, StackPane stackPane ){
        ControllerLoader< AnchorPane, ModalNovaVenda> loader = new ControllerLoader<>("/fxml/modal/modal_nova_venda.fxml");
        ModalNovaVenda modal = loader.getController();
        modal.functionLoadCliente = functionLoadCliente;
        modal.createDialogModal( stackPane );
        modal.loadClienteDatasource();
        modal.pushToView();
        modal.setTipoVenda( tipoVenda );

        return modal;
    }

    @FXML private AnchorPane root;
    @FXML private Label modalTitle;
    @FXML private AnchorPane anchorHeader;
    @FXML private AnchorPane iconAreaCloseModal;
    @FXML private JFXTextField textFieldClienteSearch;
    @FXML private JFXListView< Cliente > listViewCliente;
    @FXML private JFXComboBox<Produto> comboxProduto;
    @FXML private JFXComboBox<Preco> comboxPrecoUnidades;
    @FXML private StackPane fabArea;
    @FXML private JFXButton fabButton;
    @FXML private JFXButton buttonNovaVenda;
    @FXML private JFXTextField textFieldVendaQuantidade;
    @FXML private JFXTextField textFieldVendaMontanteBruto;
    @FXML private JFXTextField textFieldVendaMontanteUnitirio;
    @FXML private JFXTextField textFieldVendaDesconto;
    @FXML private JFXTextField textFieldVendaMontantePagar;
    @FXML private JFXDatePicker datePickerVendaDataFinalizar;
    @FXML private JFXDatePicker datePickerVendaData;
    @FXML private Label textFieldClienteNome;
    @FXML private Label textFieldClienteMorada;
    @FXML private Label textFieldClienteContacto;
    @FXML private Label textFieldClienteMontanteTotal;
    @FXML private Label textFieldClienteMontantePendente;
    @FXML private Label labelVendaMontanteFinalPagar;
    @FXML private JFXButton buttonVendaFeito;


    private final  Produto PRODUTO_VASIO = new Produto();
    private final Preco PRECO_VASIO = new Preco();

    private String functionLoadCliente;

    private List< Cliente > originalClienteList = new LinkedList<>();
    private Map< UUID, List<Preco> > mapPrecos = new LinkedHashMap<>();

    private Map< TipoVenda, VendaRegister > actionRegister = new LinkedHashMap<>();

    private NumberFormat moneyFormater = NumberFormat.getInstance( Locale.FRANCE );
    private Cliente clienteAnonimo;

    private TipoVenda tipoVenda;

    private ModalNovoCliente modalNovoCliente;
    private List< RegisterVendaResult > vendasResult = new LinkedList<>();


    @Override
    Region getContentRoot() {
        return this.root;
    }

    @Override
    Label getModalTitleView() {
        return this.modalTitle;
    }

    @Override
    AnchorPane getIconAreaCloseModal() {
        return this.iconAreaCloseModal;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize( location, resources );
        moneyFormater.setMinimumFractionDigits(2);
        moneyFormater.setMaximumFractionDigits(2);
        this.clear();
        structure();
        defineEvents();
        postgresSaveAction();
    }

    @Override
    void structure() {
        super.structure();

        JFXDepthManager.setDepth( this.fabArea, 4 );

        this.datePickerVendaData.setConverter( createDateConverter( FORMAT_DD_MM_YYYY ) );
        this.datePickerVendaDataFinalizar.setConverter( createDateConverter( FORMAT_DD_MM_YYYY ) );

        this.datePickerVendaData.setDayCellFactory( DatePickerRange.getDayCellFactory( null, LocalDate.now() ) );
        this.datePickerVendaDataFinalizar.setDayCellFactory( DatePickerRange.getDayCellFactory( LocalDate.now(), null ) );
        this.datePickerVendaDataFinalizar.setValue( LocalDate.now().plusDays( 30 ) );

        this.comboxProduto.setItems( FXCollections.observableList( new LinkedList<>() ) );
        this.comboxPrecoUnidades.setItems( FXCollections.observableList( new LinkedList<>( ) ) );
    }

    void defineEvents() {

        this.comboxProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem) -> this.onSelectProduto( oldItem, newItem ));
        this.comboxPrecoUnidades.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPreco, newPreco ) -> this.onSeletedUnidade( newPreco ) );

        this.listViewCliente.getSelectionModel().selectedItemProperty().addListener((observableValue, oldCliente, newCliente) -> onSelectCliente(newCliente));

        this.textFieldVendaQuantidade.setOnKeyReleased(keyEvent -> this.onCalculateValue());
        this.textFieldVendaDesconto.setOnKeyReleased(keyEvent -> this.onCalculateValue());
        this.textFieldClienteSearch.setOnKeyReleased(this::onSearchCliente);
        this.fabButton.setOnAction(actionEvent -> this.onNewCliente());

        this.datePickerVendaData.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (newDate != null) {
                this.datePickerVendaDataFinalizar.setValue(newDate.plusDays(30));
                this.datePickerVendaDataFinalizar.setDayCellFactory( DatePickerRange.getDayCellFactory( newDate, null ) );
            }
            else this.datePickerVendaDataFinalizar.setValue(null);
            this.loadProdutoDatasource( newDate );
        });

        this.buttonNovaVenda.setOnAction(event -> this.onNovaVenda() );
        this.buttonVendaFeito.setOnAction( event -> this.onVendaFeito() );
    }

    @Override
    public void clear() {
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
        this.loadProdutoDatasource( LocalDate.now() );
        this.textFieldClienteSearch.setEditable( true );
        this.textFieldClienteSearch.setDisable( false );
        this.listViewCliente.setDisable( false );
        if( this.tipoVenda == TipoVenda.DIVIDA ) this.modalTitle.setText("Nova divida");
        else this.modalTitle.setText( "Nova venda" );
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

        funct_reg_venda.callFunctionTable().onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
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
        text = SQLText.normalize( text );
        this.listViewCliente.getItems().clear();
        this.listViewCliente.refresh();
        if( text == null ){
            this.listViewCliente.getItems().addAll( this.originalClienteList );
        } else {
            for( Cliente cli : this.originalClienteList ){
                if( cli.getClienteCompletName().toLowerCase().contains( text.toLowerCase() ) )
                    this.listViewCliente.getItems().add( cli );
            }
        }
    }

    private boolean findClienteSelecter( Cliente cliente  ) {
        if( cliente == null ) return  false;
        this.loadProdutoDatasource( this.datePickerVendaData.getValue() );
        this.listViewCliente.getItems().clear();
        for( Cliente cli : this.originalClienteList ) {
            if (cliente.equals(cli)) {
                this.listViewCliente.getItems().add(cli);
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
                this.clear();
            }
        }

        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        this.vendasResult.add( result );
        if( result.isSuccess() ){
            snackbarBuilder.showSucess( "Nova "+this.tipoVenda.name().toLowerCase()+" cadastrada com sucesso");
        } else {
            snackbarBuilder.showError( result.message );
        }
    }

    private void onVendaFeito() {
        RegisterVendaResult result = new RegisterVendaResult();
        result.success = true;
        result.vendasResult = this.vendasResult;
        int countSuccess = 0;
        for( RegisterVendaResult result1 : this.vendasResult ){
            if( result1.isSuccess() ) countSuccess ++;
        }
        this.closeModal();
        this.clear();

        super.executeOnOperationResult( result );
        this.vendasResult = new LinkedList<>();
        SnackbarBuilder builder = new SnackbarBuilder( this.getStakePane() );
        if( countSuccess == 1 ) builder.showInformation( countSuccess+" novas "+this.tipoVenda.name().toLowerCase()+" foi cadastradas!" );
        else if( countSuccess > 1 ) builder.showInformation( countSuccess+" novas "+this.tipoVenda.name().toLowerCase()+" foram cadastradas!" );
        else builder.showWarning( "Nenhuma nova "+this.tipoVenda.name().toLowerCase()+" foi cadastrada!");
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
                .produto( cal.produto)
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
        Produto produto = this.comboxProduto.getSelectionModel().getSelectedItem();
        Preco preco = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();


        registerVendaResult.success = false;
        if( this.listViewCliente.getSelectionModel().getSelectedItem() == null ){
            registerVendaResult.message = "Nenum cliente seleicionado!";
        } else if( produto  == null || produto.equals( this.PRODUTO_VASIO) ){
            registerVendaResult.message = "Nenhum produto selecionado!";
        } else if( preco == null || preco.equals( this.PRECO_VASIO) ){
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
            this.modalNovoCliente.setOnModalResult(operationResult -> {
                if( operationResult.isSuccess() ){
                    this.loadClienteDatasource();
                    boolean res = this.findClienteSelecter( operationResult.getValue() );
                    this.textFieldClienteSearch.setText( operationResult.getValue().getClienteCompletName() );
                }
            });
        }
    }

    private void pushToView() {
        this.onSearchCliente( null );
    }

    private void onSelectProduto(Produto oldItem, Produto newItem ) {
        if( newItem != null && !newItem.equals( this.PRODUTO_VASIO) ){
            this.comboxPrecoUnidades.getItems().clear();
            this.comboxPrecoUnidades.getItems().addAll( this.mapPrecos.get( newItem.getProdutoId() ) );
            if( this.comboxPrecoUnidades.getItems().size() > 1 ) this.comboxPrecoUnidades.getItems().add( 0, PRECO_VASIO );
        } else{
            this.comboxPrecoUnidades.getItems().clear();
            this.comboxPrecoUnidades.getItems().add( PRECO_VASIO );
        }

        this.textFieldVendaMontanteUnitirio.setText( "" );
        this.textFieldVendaMontanteBruto.setText( "" );
        this.textFieldVendaMontantePagar.setText( "" );
        this.labelVendaMontanteFinalPagar.setText( this.moneyFormater.format( 0.0 ) +" STN" );
    }

    private void onSeletedUnidade( Preco newPreco) {
        if( newPreco == null || newPreco.equals( this.PRECO_VASIO) ){
            this.textFieldVendaMontanteUnitirio.setText("");
        } else {
            this.textFieldVendaMontanteUnitirio.setText( this.moneyFormater.format( newPreco.getPrecoCustoUnidade() ) );
        }
        this.onCalculateValue();
    }

    private CalcResult onCalculateValue() {
        CalcResult calcResult = new CalcResult();

        calcResult.preco = this.comboxPrecoUnidades.getSelectionModel().getSelectedItem();
        if( calcResult.preco == null || calcResult.preco.equals( this.PRECO_VASIO) ) calcResult.preco = null;
        else {
            calcResult.unidade = calcResult.preco.getUnidade();
            calcResult.vendaMontanteUnitario = calcResult.preco.getPrecoCustoUnidade();
        }

        calcResult.produto = this.comboxProduto.getSelectionModel().getSelectedItem();
        if( calcResult.produto == null || calcResult.produto.equals( this.PRODUTO_VASIO) ) calcResult.produto = null;

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
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Platform.runLater(() -> {
                this.originalClienteList.clear();
                this.listViewCliente.getItems().clear();
                this.listViewCliente.refresh();
            });

            Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
            sql.query( this.functionLoadCliente)
                .withJsonb( (String) null )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            Cliente cli = clienteBuilder.load(row).build();
                            this.originalClienteList.add( cli );
                            this.listViewCliente.getItems().add( cli );
                        });
                    });

        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    private void loadProdutoDatasource( LocalDate date ) {
        this.comboxProduto.getItems().clear();
        this.comboxPrecoUnidades.getItems().clear();
        this.mapPrecos.clear();
        if( date  == null ) return;

        Thread thread = new Thread(() -> {
            Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
            Preco.PrecoBuilder precoBuilder = new Preco.PrecoBuilder();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Gson gson = new Gson();
            sql.query( "funct_load_produto_venda" )
                .withDate( date )
                .callFunctionTable()
                .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                    Platform.runLater( ( ) -> {
                        Produto produto = produtoBuilder.load( row ).build();
                        this.comboxProduto.getItems().add( produto );

                        List<Preco> precoList = new LinkedList<>();
                        String documentEquivalencia = String.valueOf( row.get("produto_precos"));

                        List<Map<String, Object> >  aux = gson.fromJson( documentEquivalencia, List.class );
                        for( Map<String, Object > map : aux ){
                            precoList.add(
                                precoBuilder.load( map )
                                    .setProduto( produto )
                                    .setUnidade( unidadeBuilder.load( map ).build() )
                                    .build()
                            );
                        }
                        this.mapPrecos.put( produto.getProdutoId(), precoList );
                    });
                });

                Platform.runLater( ( ) -> {
                    if( this.comboxProduto.getItems().size() > 1 ){
                        this.comboxProduto.getItems().add( 0 , this.PRODUTO_VASIO );
                    }
                });

        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    private Double getVendaQuantidade() {
        String text = this.textFieldVendaQuantidade.getText();
        text = SQLText.normalize( text );
        try{
            return Double.parseDouble( text );
        } catch ( Exception ignored){}
        return null;
    }

    private Double getVendaDesconto() {
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
        PostgresSQL sql = PostgresSQLSingleton.getInstance();
        Colaborador colaborador = AuthSingleton.getInstance();
        this.actionRegister.put(TipoVenda.VENDA, venda -> {
            ContaManager contaManager = new ContaManager();
            Conta conta = contaManager.getContaFor(ContaManager.ContaOperacao.PAGAMENTO_VENDA );
            return  execute( venda,
                    sql.query("funct_reg_venda_venda")
                            .withUUID( colaborador.getColaboradorId() )
                            .withUUID( venda.getProduto().getProdutoId() )
                            .withUUID( venda.getUnidade().getUnidadeId() )
                            .withUUID( venda.getCliente().getClienteId() )
                            .withUUID( conta == null? null : conta.getContaId() )
                            .withNumeric( venda.getVandaQuantidade() )
                            .withNumeric( venda.getVendaMontanteUnitario() )
                            .withNumeric( venda.getVendaMontanteBruto() )
                            .withNumeric( venda.getVendaMontanteDesconto() )
                            .withNumeric( venda.getVendaMontantePagar() )
                            .withDate( venda.getVendaData() )
            );
        });
        this.actionRegister.put(TipoVenda.DIVIDA, venda -> {
            PostgresSQLQueryBuilder query = sql.query( "funct_reg_venda_divida" );
            query.withUUID( colaborador.getColaboradorId() );
            Produto p = venda.getProduto();
            query.withUUID( p.getProdutoId() );
            query.withUUID( venda.getUnidade().getUnidadeId() );
            query.withUUID( venda.getCliente().getClienteId() );
            query.withNumeric( venda.getVandaQuantidade() );
            query.withNumeric( venda.getVendaMontanteUnitario() );
            query.withNumeric( venda.getVendaMontanteBruto() );
            query.withNumeric( venda.getVendaMontanteDesconto() );
            query.withNumeric( venda.getVendaMontantePagar() );
            query.withDate( venda.getVendaData() );
            query.withDate( venda.getVendaDataFinalizar() );
            return execute( venda, query );
        });
    }

    @Override
    public void openModal(){
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
        private Produto produto;
    }

    public static class RegisterVendaResult implements ModalResult< List > {

        private Map<String, Object > resultData;
        private boolean success;
        private Venda venda;

        private List< RegisterVendaResult > vendasResult;
        private String message;


        @Override
        public boolean isSuccess() {
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
        public List< RegisterVendaResult > getValue() {
            return this.vendasResult;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return null;
        }

        @Override
        public Map<String, Object> getData() {
            return resultData;
        }
    }



}
