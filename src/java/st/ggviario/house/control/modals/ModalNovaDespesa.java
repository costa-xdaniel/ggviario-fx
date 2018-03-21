package st.ggviario.house.control.modals;

import com.google.gson.Gson;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.SnackbarBuilder;
import st.ggviario.house.control.component.DatePickerRange;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ModalNovaDespesa  extends AbstractModal <Despesa > implements Initializable{

    public static ModalNovaDespesa load(StackPane stackPane ){
        ControllerLoader< AnchorPane, ModalNovaDespesa > loader = new ControllerLoader<>("/fxml/modal/modal_nova_despesa.fxml");
        loader.getController().structure();
        loader.getController().difineEvents();
        loader.getController().loadData();
        loader.getController().pushAll();
        loader.getController().createDialogModal( stackPane );
        loader.getController().clear();
        return loader.getController();
    }

    @FXML private AnchorPane root;
    @FXML private Label modalTitle;
    @FXML private AnchorPane anchorHeader;
    @FXML private AnchorPane iconAreaCloseModal;
    @FXML private JFXListView< Fornecedor > listViewFornecedor;
    @FXML private JFXComboBox<Produto> comboxProduto;
    @FXML private JFXComboBox< Unidade > comboxUnidades;
    @FXML private Label labelFornecedorNome;
    @FXML private Label labelFornecedorLocal;
    @FXML private Label labelFornecedorContacto;
    @FXML private Label labelFornecedorMontanteTotal;
    @FXML private Label labelFornecedorMontantePendete;
    @FXML private Label labelPrecoFinalPagar;
    @FXML private JFXTextField textFieldMontantePagar;
    @FXML private JFXTextField textFieldDespesaMontanteUnitirio;
    @FXML private JFXTextField textFieldDespesaQuantidade;
    @FXML private JFXTextField textFieldFaturaNumero;
    @FXML private JFXTextField textFieldFornecedorSearch;
    @FXML private JFXDatePicker datePickeDespesaData;
    @FXML private JFXToggleButton toggleButtonPago;
    @FXML private JFXButton buttonDespesaRegistar;



    private List< Fornecedor > fornecedorList;
    private List<Produto> produtoList;
    private Map< UUID, List< Unidade > > productoListMap;
    private NumberFormat moneyFormat;
    private Unidade unidadeVoid;
    private Produto produtoVoid;
    private String oldText;


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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize( url, resourceBundle );
        this.fornecedorList = new LinkedList<>();
        this.productoListMap = new LinkedHashMap<>();
        this.produtoList = new LinkedList<>();
        this.moneyFormat = NumberFormat.getInstance( Locale.FRANCE );
        this.moneyFormat.setMaximumFractionDigits( 2 );
        this.moneyFormat.setMinimumFractionDigits( 2 );
        this.moneyFormat.setMinimumIntegerDigits( 1 );
        this.unidadeVoid = new Unidade();
        this.produtoVoid = new Produto();
        this.datePickeDespesaData.setValue( LocalDate.now() );
    }

    void structure() {
        super.structure();
        this.datePickeDespesaData.setConverter( createDateConverter( FORMAT_DD_MM_YYYY ) );
        this.datePickeDespesaData.setDayCellFactory( DatePickerRange.getDayCellFactory( null, LocalDate.now() ) );
    }

    @Override
    public void closeModal() {
        super.closeModal();
        this.clear();
    }

    private void difineEvents( ){
        this.textFieldFornecedorSearch.setOnKeyReleased(keyEvent -> this.onSearchFornecedor( keyEvent, SQLText.normalize( this.textFieldFornecedorSearch.getText().toLowerCase())));

        this.listViewFornecedor.getSelectionModel().selectedItemProperty().addListener((observableValue, oldFornecedor, newFornecedor) -> {
            this.onSelectFornecedor( oldFornecedor, newFornecedor );
        });

        this.comboxProduto.getSelectionModel().selectedItemProperty().addListener((observableValue, oldProduto, newProduto) ->{
            this.onSelectProduto( oldProduto, newProduto );
        });

        this.comboxUnidades.getSelectionModel().selectedItemProperty().addListener((observableValue, unidade, newUnidade) -> {
            this.onSelectUnidade( unidade, newUnidade);
        });

        this.textFieldDespesaMontanteUnitirio.setOnKeyReleased(keyEvent -> {
            this.onCalculateNow();
        });

        this.textFieldDespesaQuantidade.setOnKeyReleased(keyEvent -> {
            this.onCalculateNow();
        });

        this.buttonDespesaRegistar.setOnAction(actionEvent -> {
            this.onRegister();
        });

    }

    private void onSearchFornecedor( KeyEvent event, String text ){
        if( event.getCode() == KeyCode.ENTER ){
            this.loadDataFornecedor();
        }

        if( text != null ){
            List<Fornecedor> filter = new LinkedList<>();
            for( Fornecedor fornecedor : this.fornecedorList ){
                if( fornecedor.getFornecedorNome().toLowerCase().contains( text ) ) filter.add( fornecedor );
            }
            this.pushFornecedor( filter );
        } else {
            this.pushFornecedor( this.fornecedorList );
        }
    }

    private void onSelectProduto(Produto oldProduto, Produto newProduto ){
        if( newProduto == null || newProduto.getProdutoId() == null ){
            this.comboxProduto.setValue( null );
            this.comboxUnidades.setItems( null );
            this.comboxUnidades.setValue( null );
        } else {
            this.comboxUnidades.setItems( FXCollections.observableList( this.productoListMap.get( newProduto.getProdutoId() ) ) );
        }
    }

    private void onSelectUnidade( Unidade oldUnidade, Unidade newUnidade ){
        if( newUnidade == null || newUnidade.getUnidadeId() == null ){
            this.comboxUnidades.setValue( null );
        }
    }

    private void onSelectFornecedor( Fornecedor oldFornecedor, Fornecedor newFornecedor ){
        this.labelFornecedorNome.setText( newFornecedor == null ? null : newFornecedor.getFornecedorNome() );
        this.labelFornecedorContacto.setText( newFornecedor == null ? null : SQLResource.coalesce( newFornecedor.getFornecedorTelefone(), newFornecedor.getFornecedorTelemovel() ) );
        this.labelFornecedorLocal.setText( newFornecedor == null ? null : newFornecedor.getFornecedorLocal() );
        this.labelFornecedorMontanteTotal.setText( newFornecedor == null ? null : this.moneyFormat.format( newFornecedor.getFornecedorMontanteCompras() )+" STN" );
        this.labelFornecedorMontantePendete.setText( newFornecedor == null ? null : this.moneyFormat.format( newFornecedor.getFornecedorMonntantePendentes() )+" STN" );
    }

    private ModalDepesaResult onCalculateNow(){
        Despesa.DespesaBuilder despesaBuilder = new Despesa.DespesaBuilder();
        ModalDepesaResult res = new ModalDepesaResult();
        res.level = SnackbarBuilder.MessageLevel.WARNING;
        Double quant;
        Double unit;

        despesaBuilder.setFornecedor( this.listViewFornecedor.getSelectionModel().getSelectedItem() );
        despesaBuilder.setProduto( this.comboxProduto.getValue() );
        despesaBuilder.setUnidade( this.comboxUnidades.getValue() );
        despesaBuilder.setQuantidade( quant = SQLRow.doubleOf( this.textFieldDespesaQuantidade.getText() ) );
        despesaBuilder.setMontanteUnitario( unit = SQLRow.doubleOf( this.textFieldDespesaMontanteUnitirio.getText() ) );
        despesaBuilder.setFaturaNumero( this.textFieldFaturaNumero.getText() );

        if( this.datePickeDespesaData.getValue() != null  ) {
            despesaBuilder.setData(java.sql.Date.valueOf(this.datePickeDespesaData.getValue()));
        }
        else despesaBuilder.setData( null );

        if( quant != null && unit != null ){
            despesaBuilder.setMontanteTotal( quant * unit );
            res.success = true;
            res.level = SnackbarBuilder.MessageLevel.SUCCESS;
        } else if( quant != null ) {
            res.message = "Informe a quantidade do produto!";
        } else if( unit == null ){
            res.message = "Informe a unidade da value!";
        }

        res.value = despesaBuilder.build();

        if( quant != null && unit != null ){
            this.labelPrecoFinalPagar.setText( this.moneyFormat.format( res.value.getDespesaMontanteTotal() )+" STN" );
            this.textFieldMontantePagar.setText( this.moneyFormat.format( res.value.getDespesaMontanteTotal() )+" STN" );
        } else {
            this.labelPrecoFinalPagar.setText( "0,00 STN" );
            this.textFieldMontantePagar.setText( null );
        }

        if( res.value.getFornecedor() == null || res.value.getFornecedor().getFornecedorId() == null ) res.message = "Selecione um fornecedor!";
        else if( res.value.getProduto() == null  || res.value.getProduto().getProdutoId() == null ) res.message = "Selecione um produto!";
        else if( res.value.getUnidade() == null  || res.value.getUnidade().getUnidadeId() ==null ) res.message = "Selecione uma uniade";
        else if( res.value.getDespesaMontanteUnitario() == null ) res.message = "Informe o custo unitario";
        else if( quant == null ) res.message = "Informe a quantidade do produto!";
        else res.success = true;

        res.level = SnackbarBuilder.MessageLevel.WARNING;
        return res;
    }

    private void onRegister ( ){
        ModalDepesaResult res = this.onCalculateNow();
        if( res.isSuccess() ){

            ContaManager contaManager = new ContaManager();
            Conta conta = contaManager.getContaFor(ContaManager.ContaOperacao.PAGAMENTO_DESPESA);
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Despesa.DespesaBuilder builder = new Despesa.DespesaBuilder();

            Colaborador colaborador = AuthSingleton.getInstance();
            sql.query( "ggviario.funct_reg_despesa" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( res.value.getFornecedor().getFornecedorId() )
                .withUUID( res.value.getProduto().getProdutoId() )
                .withUUID( res.value.getUnidade().getUnidadeId() )
                .withNumeric( res.value.getDespesaQuantidade() )
                .withNumeric( res.value.getDespesaMontanteUnitario() )
                .withNumeric( res.value.getDespesaMontanteTotal() )
                .withDate( res.value.getDespesaData() )
                .withVarchar( res.value.getDespesaFaturaNumero() )
                .withBoolean( this.toggleButtonPago.isSelected() )
                .withUUID( conta ==  null? null : conta.getContaId() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        res.result = result.getData();
                        if( result.isSuccess() ){
                            res.level = SnackbarBuilder.MessageLevel.SUCCESS;
                            res.message = "Nova value cadastrado com sucesso";
                            res.success = true;
                            res.value = builder.load((Map<String, Object>) result.getData().get("despesa") ).build();
                        } else {
                            res.level = SnackbarBuilder.MessageLevel.ERROR;
                            res.message = result.getMessage();
                        }
                    });
        }

        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        snackbarBuilder.show( res.message, res.level );
        if( res.isSuccess() ){
            this.clear();
            this.closeModal();
            this.executeOnOperationResult( res );
        }
    }

    @Override
    public void clear() {
        this.textFieldFornecedorSearch.setText( "skdjskj" );
        this.textFieldFornecedorSearch.clear();
        this.listViewFornecedor.getSelectionModel().select( null );
        this.comboxProduto.setValue( null );
        this.comboxUnidades.setItems( null );
        this.textFieldDespesaMontanteUnitirio.setText( null );
        this.textFieldDespesaQuantidade.setText( null );
        this.textFieldMontantePagar.setText( null );
        this.labelFornecedorNome.setText( null );
        this.labelFornecedorLocal.setText( null );
        this.labelFornecedorContacto.setText( null );
        this.labelFornecedorMontanteTotal.setText( null );
        this.labelFornecedorMontantePendete.setText( null );
        this.labelPrecoFinalPagar.setText( "0,00 STN" );
    }

    private void loadData() {
        this.loadDataFornecedor();
        this.loadDataProduto();
    }

    private void loadDataFornecedor( ){
        Fornecedor.FornecedorBuilder  fornecedorBuilder = new Fornecedor.FornecedorBuilder();
        PostgresSQL sql = PostgresSQLSingleton.getInstance();
        this.fornecedorList.clear();
        //Load fornecedor
        sql.query( "funct_load_fornecedor" )
                .withJsonb( ( String ) null )
                .callFunctionTable()
                .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery)
                    row -> this.fornecedorList.add( fornecedorBuilder.load( row ).build() ));
    }

    private void loadDataProduto(){
        this.produtoList.clear();
        this.productoListMap.clear();
        this.produtoList.add( this.produtoVoid);

        PostgresSQL sql = PostgresSQLSingleton.getInstance();
        Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
        Gson gson = new Gson();
        //Load produtos
        sql.query( "funct_load_produto_despesa" )
                .withJsonb( ( String )null )
                .callFunctionTable()
                .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                    Produto produto = produtoBuilder.load( row ).build();
                    String documnetUnidade = row.asString( "produto_unidades" );
                    List< Object > list = gson.fromJson( documnetUnidade, List.class );

                    List< Unidade > unidades =  new LinkedList<>( );
                    unidades.add( unidadeVoid );
                    for( Object o : list ){
                        unidades.add( unidadeBuilder.load((Map<String, Object>) o).build() );
                    }
                    this.productoListMap.put( produto.getProdutoId(), unidades );
                    this.produtoList.add(produto);
                });
    }

    private void pushAll(){
        this.pushFornecedor( this.fornecedorList );
        this.pushPorduto( this.produtoList);
    }

    private void pushFornecedor( List<Fornecedor > list ){
        this.listViewFornecedor.setItems(FXCollections.observableList( list ) );
    }

    private void pushPorduto( List<Produto> list ){
        this.comboxProduto.setItems(FXCollections.observableList( list ) );
    }

    private void pushUnidade( List<Unidade> list ){
        this.comboxUnidades.setItems( FXCollections.observableList( list ) );
    }

    public class ModalDepesaResult implements   ModalResult < Despesa > {

        private boolean success;
        private boolean terminated;
        private String message;
        private Despesa value;
        private SnackbarBuilder.MessageLevel level;
        private Map<String,Object> result;

        @Override
        public boolean isSuccess() {
            return this.success;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public boolean isTerminated() {
            return this.terminated;
        }

        @Override
        public Despesa getValue() {
            return this.value;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return this.level;
        }

        @Override
        public Map<String, Object> getData() {
            return this.result;
        }
    }


}
