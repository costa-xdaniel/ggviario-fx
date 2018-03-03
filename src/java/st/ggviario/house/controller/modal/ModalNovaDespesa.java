package st.ggviario.house.controller.modal;

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
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.SnackbarBuilder;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;

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
    @FXML private JFXComboBox< Producto > comboxProduto;
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



    private JFXRippler ripllerCloseModa;
    private List< Fornecedor > fornecedorList;
    private List< Producto > productoList;
    private Map< UUID, List< Unidade > > productoListMap;
    private NumberFormat moneyFormat;
    private Unidade unidadeVoid;
    private Producto productoVoid;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.fornecedorList = new LinkedList<>();
        this.productoListMap = new LinkedHashMap<>();
        this.productoList = new LinkedList<>();
        this.moneyFormat = NumberFormat.getInstance( Locale.FRANCE );
        this.moneyFormat.setMaximumFractionDigits( 2 );
        this.moneyFormat.setMinimumFractionDigits( 2 );
        this.moneyFormat.setMinimumIntegerDigits( 1 );
        this.unidadeVoid = new Unidade();
        this.productoVoid = new Producto();
        this.datePickeDespesaData.setValue( LocalDate.now() );
    }

    private void structure() {

        //Striucture rippler
        this.ripllerCloseModa = new JFXRippler( this.iconAreaCloseModal );
        this.ripllerCloseModa.setStyle( "-jfx-rippler-fill: md-red-500" );
        this.anchorHeader.getChildren().add( this.ripllerCloseModa );
        AnchorPane.setRightAnchor( this.ripllerCloseModa, 0x0.0p0 );
        AnchorPane.setTopAnchor( this.ripllerCloseModa, 0x0.0p0 );

        this.datePickeDespesaData.setConverter(new StringConverter<LocalDate>() {
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

    }

    private void difineEvents( ){

        this.ripllerCloseModa.setOnMouseClicked(mouseEvent -> {
            this.clear();
            this.closeModal();
        });

        this.textFieldFornecedorSearch.setOnKeyReleased(keyEvent -> {
            this.onSearchFornecedor( keyEvent, SQLText.normalize( this.textFieldFornecedorSearch.getText().toLowerCase()));
        });

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

    private void onSelectProduto( Producto oldProduto, Producto newProduto ){
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
        despesaBuilder.setProducto( this.comboxProduto.getValue() );
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
            res.message = "Informe a unidade da despesa!";
        }

        res.despesa = despesaBuilder.build();

        if( quant != null && unit != null ){
            this.labelPrecoFinalPagar.setText( this.moneyFormat.format( res.despesa.getDespesaMontanteTotal() )+" STN" );
            this.textFieldMontantePagar.setText( this.moneyFormat.format( res.despesa.getDespesaMontanteTotal() )+" STN" );
        } else {
            this.labelPrecoFinalPagar.setText( "0,00 STN" );
            this.textFieldMontantePagar.setText( null );
        }

        if( res.despesa.getFornecedor() == null ) res.message = "Selecione um fornecedor!";
        else if( res.despesa.getProducto() == null ) res.message = "Selecione um produto!";
        else if( res.despesa.getUnidade() == null ) res.message = "Selecione uma uniade";
        else if( res.despesa.getDespesaMontanteUnitario() == null ) res.message = "Informe o custo unitario";
        else if( quant == null ) res.message = "Informe a quantidade do produto!";
        else res.success = true;

        res.level = SnackbarBuilder.MessageLevel.WARNING;
        return res;
    }

    private void onRegister ( ){
        ModalDepesaResult res = this.onCalculateNow();
        if( res.isSucceed() ){

            ContaManager contaManager = new ContaManager();
            Conta conta = contaManager.getContaFor(ContaManager.ContaOperacao.PAGAMENTO_DESPESA);
            PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
            Despesa.DespesaBuilder builder = new Despesa.DespesaBuilder();
/*
funct_reg_despesa(
arg_colaborador_id uuid,
 arg_fornecedor_id uuid,
  arg_produto_id uuid,
   arg_unidade_id uuid,
    arg_despesa_quantidade numeric,
     arg_despesa_custounitario numeric,
      arg_despesa_custototal numeric,
       arg_despesa_data date,
        arg_despesa_numerofatura date,
         arg_despesa_paga boolean, arg_conta_id uuid)
 */

            Colaborador colaborador = AuthSingleton.getAuth();
            sql.query( "ggviario.funct_reg_despesa" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( res.despesa.getFornecedor().getFornecedorId() )
                .withUUID( res.despesa.getProducto().getProdutoId() )
                .withUUID( res.despesa.getUnidade().getUnidadeId() )
                .withNumeric( res.despesa.getDespesaQuantidade() )
                .withNumeric( res.despesa.getDespesaMontanteUnitario() )
                .withNumeric( res.despesa.getDespesaMontanteTotal() )
                .withDate( res.despesa.getDespesaData() )
                .withVarchar( res.despesa.getDespesaFaturaNumero() )
                .withBoolean( this.toggleButtonPago.isSelected() )
                .withUUID( conta ==  null? null : conta.getContaId() )
                .callFunctionTable()
                    .onResultQuery(row -> {
                        SQLResult result = new SQLResult( row );
                        res.result = result.getData();
                        if( result.isSuccess() ){
                            res.level = SnackbarBuilder.MessageLevel.SUCCESS;
                            res.message = "Nova despesa cadastrado com sucesso";
                            res.success = true;
                            res.despesa = builder.load((Map<String, Object>) result.getData().get("despesa") ).build();
                        } else {
                            res.level = SnackbarBuilder.MessageLevel.ERROR;
                            res.message = result.getMessage();
                        }
                    });
        }

        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        snackbarBuilder.show( res.message, res.level );
        if( res.isSucceed() ){
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
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        this.fornecedorList.clear();
        //Load fornecedor
        sql.query( "funct_load_fornecedor" )
                .withJsonb( ( String ) null )
                .callFunctionTable()
                .onResultQuery(row -> {
                    this.fornecedorList.add( fornecedorBuilder.load( row ).build() );
                });
    }

    private void loadDataProduto(){
        this.productoList.clear();
        this.productoListMap.clear();
        this.productoList.add( this.productoVoid );

        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        Producto.ProdutoBuilder produtoBuilder = new Producto.ProdutoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
        Gson gson = new Gson();
        //Load produtos
        sql.query( "funct_load_produto_despesa" )
                .withJsonb( ( String )null )
                .callFunctionTable()
                .onResultQuery(row -> {
                    Producto producto = produtoBuilder.load( row ).build();
                    String documnetUnidade = row.asString( "produto_unidades" );
                    List< Object > list = gson.fromJson( documnetUnidade, List.class );

                    List< Unidade > unidades =  new LinkedList<>( );
                    unidades.add( unidadeVoid );
                    for( Object o : list ){
                        System.out.println( String.valueOf( o ) );
                        unidades.add( unidadeBuilder.load((Map<String, Object>) o).build() );
                    }
                    this.productoListMap.put( producto.getProdutoId(), unidades );
                    this.productoList.add( producto );
                });
    }

    private void pushAll(){
        this.pushFornecedor( this.fornecedorList );
        this.pushPorduto( this.productoList );
    }

    private void pushFornecedor( List<Fornecedor > list ){
        this.listViewFornecedor.setItems(FXCollections.observableList( list ) );
    }

    private void pushPorduto( List<Producto > list ){
        this.comboxProduto.setItems(FXCollections.observableList( list ) );
    }

    private void pushUnidade( List<Unidade> list ){
        this.comboxUnidades.setItems( FXCollections.observableList( list ) );
    }

    public class ModalDepesaResult implements   ModalResult < Despesa > {

        private boolean success;
        private boolean terminated;
        private String message;
        private Despesa despesa;
        private SnackbarBuilder.MessageLevel level;
        private Map<String,Object> result;

        @Override
        public boolean isSucceed() {
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
        public Despesa getResltValue() {
            return this.despesa;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return this.level;
        }

        @Override
        public Map<String, Object> mapResults() {
            return this.result;
        }
    }


}
