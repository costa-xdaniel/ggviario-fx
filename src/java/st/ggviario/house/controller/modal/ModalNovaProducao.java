package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.SnackbarBuilder;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ModalNovaProducao extends AbstractModal< Producao > {
    private static Setor SECTOR_VOID = new Setor();
    private static Produto PRODUTO_VOID = new Produto();

    public static ModalNovaProducao load(StackPane stackPane ) {
        ControllerLoader< AnchorPane, ModalNovaProducao > loader = new ControllerLoader<>("/fxml/modal/modal_nova_producao.fxml");
        ModalNovaProducao novoSetor = loader.getController();
        novoSetor.createDialogModal( stackPane );
        novoSetor.structure();
        novoSetor.defineEvent();
        return novoSetor;
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconArea;
    @FXML private JFXTextField textFieldProducaoQuantidade;
    @FXML private JFXComboBox< Setor > comboxSector;
    @FXML private JFXComboBox< Produto > comboxProduto;
    @FXML private JFXDatePicker datePickeProducaoData;
    @FXML private Label labelTotalDia;
    @FXML private Label labelTotalSetor;
    @FXML private JFXButton buttonRegistar;

    private List<Setor> setorList = new LinkedList<>();
    private List< Produto > produtoList = new LinkedList<>();
    private NumberFormat numberFormat = NumberFormat.getInstance( Locale.FRENCH );

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
        return this.iconArea;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    @Override
    public void clear() {
        this.textFieldProducaoQuantidade.setText(null);
        this.comboxSector.setValue( null );
        this.comboxProduto.setValue(  null );
        this.labelTotalDia.setText( null );
        this.labelTotalSetor.setText( null );
        this.datePickeProducaoData.setValue( LocalDate.now() );
    }

    private void structure(){

    }

    private void defineEvent(){
        this.buttonRegistar.setOnAction(event -> this.onRegistarNovoSetor() );
        this.comboxSector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.onChangeDateReload();
        });

        this.comboxSector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.onChangeDateReload();
        });

        this.datePickeProducaoData.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.onChangeDateReload();
        });
    }

    public void pushSetor( List<Setor> setorList ){
        this.setorList.clear();
        this.setorList.add( SECTOR_VOID );
        this.setorList.addAll( setorList );
        this.comboxSector.setItems( FXCollections.observableList( this.setorList) );
    }

    public void pushProduto( List< Produto > produtoList ){
        this.produtoList.clear();
        this.produtoList.add( PRODUTO_VOID );
        this.produtoList.addAll( produtoList );
        this.comboxProduto.setItems( FXCollections.observableList( this.produtoList) );
    }

    private void onRegistarNovoSetor() {
        ModalNovaProducaoResult res = this.validateForm();
        if( res.isSucceed() ){
            /*
            funct_reg_producao(
            arg_colaborador_id uuid, arg_producao_data date, arg_producao_quantidade numeric, arg_produto_id uuid, arg_setor_id uuid)
             */
            PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
            Colaborador colaborador = AuthSingleton.getAuth();
            sql.query( "ggviario.funct_reg_producao" )
                .withUUID( colaborador.getColaboradorId() )
                .withDate( res.getResultValue().getProducaoData() )
                .withNumeric( res.getResultValue().getProducaoQuantidade() )
                .withUUID( res.getResultValue().getProduto().getProdutoId() )
                .withUUID( res.getResultValue().getSetor().getSetorId() )
                .callFunctionTable()
                .onResultQuery(row -> {
                    SQLResult result = new SQLResult( row );
                    if( result.isSuccess() ){
                        res.succeed = true;
                        res.message = "Nova produção cadastrada com sucesso";
                        res.level = SnackbarBuilder.MessageLevel.SUCCESS;
                        res.terminated = true;
                    } else {
                        res.message = result.getMessage();
                        res.map = result.getData();
                        res.level = SnackbarBuilder.MessageLevel.ERROR;
                        res.succeed = false;
                    }
                })
            ;

            SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
            snackbarBuilder.show( res.message, res.level );
            if( res.isSucceed() ){
                this.clear();
                this.closeModal();
            }
            executeOnOperationResult( res );
        }
    }

    private void onChangeDateReload( ){
        Produto produto = this.comboxProduto.getValue();
        Setor setor = this.comboxSector.getValue();
        LocalDate date = datePickeProducaoData.getValue();

        if( produto == null || setor == null || date == null ){
            this.labelTotalSetor.setText("");
            this.labelTotalDia.setText( "" );
        } else {
            PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
            sql.query( "funct_load_producao_data" )
                .withUUID( produto.getProdutoId() )
                .withUUID( setor.getSetorId() )
                .withDate( date )
                .callFunctionTable()
                    .onResultQuery(row -> {
                        this.labelTotalDia.setText( this.numberFormat.format( row.asDouble("producao_dia" ) ) );
                        this.labelTotalSetor.setText( this.numberFormat.format( row.asDouble("producao_setor" ) ) );
                    });
            ;

        }


    }

    private ModalNovaProducaoResult validateForm(){
        ModalNovaProducaoResult res = new ModalNovaProducaoResult();
        Producao.ProducaoBuilder producaoBuilder = new Producao.ProducaoBuilder();
        producaoBuilder.setProduto( this.comboxProduto.getValue() );
        producaoBuilder.setSetor( this.comboxSector.getValue() );
        producaoBuilder.setData( this.datePickeProducaoData.getValue() == null? null : java.sql.Date.valueOf( this.datePickeProducaoData.getValue() ) );
        producaoBuilder.setQuantidade(SQLRow.doubleOf( this.textFieldProducaoQuantidade.getText() ) );

        res.resultVale = producaoBuilder.build();
        res.level = SnackbarBuilder.MessageLevel.WARNING;

        if( res.resultVale.getProduto() == null || res.resultVale.getProduto().equals( PRODUTO_VOID ) )
            res.message = "Informe o produto!";
        else if( res.resultVale.getProducaoQuantidade() == null )
            res.message = "Informe a quantidade do " + res.getResultValue().getProduto().getProdutoNome() + "!";
        else if( res.resultVale.getSetor() == null || res.resultVale.getSetor().equals( SECTOR_VOID ) )
            res.message = "Informe o setor!";
        else if( res.resultVale.getProducaoData() == null )
            res.message = "Informe a data da produção!";
        else
            res.succeed = true;
        return res;
    }

    class ModalNovaProducaoResult implements ModalResult< Producao >{

        private boolean succeed;
        private String message;
        private boolean terminated;
        private Producao resultVale;
        private Map< String, Object > map;
        private SnackbarBuilder.MessageLevel level;

        @Override
        public boolean isSucceed() {
            return this.succeed;
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
        public Producao getResultValue() {
            return this.resultVale;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return level;
        }

        @Override
        public Map<String, Object> mapResults() {
            return this.map;
        }
    }

}
