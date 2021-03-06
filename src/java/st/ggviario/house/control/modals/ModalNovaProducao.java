package st.ggviario.house.control.modals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.SnackbarBuilder;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

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
        novoSetor.defineEvents();
        novoSetor.clear();
        return novoSetor;
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconArea;
    @FXML private JFXTextField textFieldProducaoTotal;
    @FXML private JFXTextField textFieldProducaoDefeituosos;
    @FXML private JFXTextField textFieldProducaoComerciavel;
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
        this.textFieldProducaoTotal.setText( null );
        this.textFieldProducaoDefeituosos.setText( null );
        this.textFieldProducaoComerciavel.setText( null );
        this.comboxSector.setValue( null );
        this.comboxProduto.setValue(  null );
        this.labelTotalDia.setText( null );
        this.labelTotalSetor.setText( null );
        this.datePickeProducaoData.setValue( LocalDate.now() );
    }

    void structure(){
        this.datePickeProducaoData.setConverter( super.createDateConverter( FORMAT_DD_MM_YYYY ) );
    }

    void defineEvents(){
        this.buttonRegistar.setOnAction(event -> this.onRegistarNovoSetor() );
        this.comboxProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.onChangeProduto( oldValue, newValue );
        });

        this.comboxSector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.onChangeDateReload();
        });

        this.datePickeProducaoData.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.onChangeDateReload();
        });

        this.textFieldProducaoTotal.setOnKeyReleased(keyEvent ->{
            this.textFieldProducaoComerciavel.setText( SQLResource.coalesceText( this.onChangeQuantidade(), "" ) );
        });

        this.textFieldProducaoDefeituosos.setOnKeyReleased(keyEvent -> {
            this.textFieldProducaoComerciavel.setText( SQLResource.coalesceText( this.onChangeQuantidade(), "" ) );
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

    private Double onChangeQuantidade( ){
        Double total = SQLRow.doubleOf( this.textFieldProducaoTotal.getText() );
        Double defeituosos = SQLResource.coalesce(SQLRow.doubleOf( textFieldProducaoDefeituosos.getText() ), 0d );
        Double comerciavel = null;
        if( total != null && defeituosos != null ){
            comerciavel = total - defeituosos;
            if( comerciavel < 0 ) comerciavel = null;
        }
        return comerciavel;
    }

    private void onRegistarNovoSetor() {
        ModalNovaProducaoResult res = this.validateForm();
        if( res.isSuccess() ){
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();

            sql.query( "ggviario.funct_reg_producao" )
                .withUUID( colaborador.getColaboradorId() )
                .withDate( res.getValue().getProducaoData() )
                .withNumeric( res.getValue().getProducaoQuantidadeTotal() )
                .withNumeric( res.getValue().getProducaoQuantidadeComerciavel() )
                .withNumeric( res.getValue().getProducaoQuantidadeDefeituosa() )
                .withUUID( res.getValue().getProduto().getProdutoId() )
                .withUUID( res.getValue().getSetor().getSetorId() )
                .callFunctionTable()
                .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
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
        }

        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        snackbarBuilder.show( res.message, res.level );
        if( res.isSuccess() ){
            this.clear();
            this.closeModal();
        }
        executeOnOperationResult( res );
    }

    private void onChangeProduto( Produto oldProduto, Produto newProduto ){
        if( newProduto == null || newProduto.getProdutoId() == null ) {
            this.comboxSector.getItems().clear();
            return;
        }

        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
            Setor.SetorBuilder superSetorBuilder = new Setor.SetorBuilder();
            Platform.runLater( ( ) -> this.comboxSector.getItems().clear());
            sql.query( "ggviario.funct_load_setor_by_localproducao_to_producao")
                .withUUID( newProduto.getProdutoId() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            setorBuilder.load( row );
                            if( row.asMapJsonn("setor_super" ) != null  ){
                                superSetorBuilder.load( row.asMapJsonn("setor_super" ) );
                                setorBuilder.setSetorSuper( superSetorBuilder.build() );
                            }
                            this.comboxSector.getItems().add( setorBuilder.build() );
                        });
                    });

            Platform.runLater(() -> {
                if( this.comboxSector.getItems().size() > 1 ){
                    this.comboxSector.getItems().add( 0 , SECTOR_VOID );
                } else if( this.comboxSector.getItems().size() == 1 ){
                    this.comboxSector.getSelectionModel().select( 0 );
                }
            });
        });


        thread.start();

    }

    private void onChangeDateReload( ){
        Thread thread = new Thread(() -> {
            Produto produto = this.comboxProduto.getValue();
            Setor setor = this.comboxSector.getValue();
            LocalDate date = datePickeProducaoData.getValue();

            if( produto == null || setor == null || date == null ){
                Platform.runLater( () -> {
                    this.labelTotalSetor.setText( "" );
                    this.labelTotalDia.setText( "" );
                });
            } else {
                PostgresSQL sql = PostgresSQLSingleton.getInstance();
                sql.query( "funct_load_producao_data" )
                        .withUUID( produto.getProdutoId() )
                        .withUUID( setor.getSetorId() )
                        .withDate( date )
                        .callFunctionTable()
                        .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                            Platform.runLater(() -> {
                                this.labelTotalDia.setText( this.numberFormat.format( row.asDouble("producao_dia" ) ) );
                                this.labelTotalSetor.setText( this.numberFormat.format( row.asDouble("producao_setor" ) ) );
                            });
                        });
                ;

            }
        });
        thread.start();
    }

    private ModalNovaProducaoResult validateForm(){
        ModalNovaProducaoResult res = new ModalNovaProducaoResult();
        Producao.ProducaoBuilder producaoBuilder = new Producao.ProducaoBuilder();
        producaoBuilder.setProduto( this.comboxProduto.getValue() );
        producaoBuilder.setSetor( this.comboxSector.getValue() );
        producaoBuilder.setData( this.datePickeProducaoData.getValue() == null? null : java.sql.Date.valueOf( this.datePickeProducaoData.getValue() ) );
        producaoBuilder.setQuantidadeTotal(SQLRow.doubleOf( this.textFieldProducaoTotal.getText() ) );
        producaoBuilder.setQuantidadeComerciavel(SQLRow.doubleOf( this.textFieldProducaoComerciavel.getText() ) );
        producaoBuilder.setQuantidadeDefeituosa( SQLResource.coalesce( SQLRow.doubleOf( this.textFieldProducaoDefeituosos.getText() ) , 0d ));

        res.resultVale = producaoBuilder.build();
        res.level = SnackbarBuilder.MessageLevel.WARNING;

        if( res.resultVale.getProduto() == null || res.resultVale.getProduto().equals( PRODUTO_VOID ) )
            res.message = "Informe o produto!";
        else if( res.resultVale.getProducaoQuantidadeTotal() == null )
            res.message = "Informe a quantidade valida do " + res.getValue().getProduto().getProdutoNome() + "!";
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
        public boolean isSuccess() {
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
        public Producao getValue() {
            return this.resultVale;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return level;
        }

        @Override
        public Map<String, Object> getData() {
            return this.map;
        }
    }

}
