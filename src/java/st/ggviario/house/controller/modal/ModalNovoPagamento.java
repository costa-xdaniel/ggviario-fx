package st.ggviario.house.controller.modal;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ModalNovoPagamento extends AbstractModal<Movimento> implements Initializable{


    private JFXRippler ripplerCloeseModal;

    public static ModalNovoPagamento load(StackPane stackPane ){
        ControllerLoader<AnchorPane, ModalNovoPagamento > loader = new ControllerLoader<>("/fxml/modal/modal_novo_pagamento.fxml");
        loader.getController().createDialogModal( stackPane );
        return loader.getController();
    }

    @FXML
    private AnchorPane root;

    @FXML
    private Label vendaFaturaNumero;

    @FXML
    private Label modalTitle;

    @FXML
    private AnchorPane anchorHeader;

    @FXML
    private JFXTextField textFieldMovimentoMontante;

    @FXML
    private JFXDatePicker datePickerMovimentoData;

    @FXML
    private JFXTextField textFieldMovimentoDocumento;

    @FXML
    private Label labelVendaDividaMontantePendente;

    @FXML
    private Label labelVendaMontantePendenteDesc;

    @FXML
    private Label labelVendaDividaMontanteTotalPagar;

    @FXML
    private JFXButton buttomPayNow;

    @FXML
    private AnchorPane iconAreaCloseModal;

    private Venda venda;

    private NumberFormat moneyFormatter = NumberFormat.getInstance( Locale.FRANCE );
    private Double lastVendaDividaMontentePendente;
    private Double lastMovimentoMontente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.structure();
        this.defineEvents();
    }

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


    private void structure(){
        moneyFormatter.setMinimumFractionDigits( 2 );
        moneyFormatter.setMaximumFractionDigits( 2 );
        moneyFormatter.setMinimumIntegerDigits( 1 );
        this.ripplerCloeseModal = new JFXRippler( this.iconAreaCloseModal);
        this.ripplerCloeseModal.setStyle( "-jfx-rippler-fill: md-red-500" );
        this.anchorHeader.getChildren().add(ripplerCloeseModal);
        AnchorPane.setTopAnchor( this.ripplerCloeseModal, 0.0 );
        AnchorPane.setRightAnchor( this.ripplerCloeseModal, 0.0 );

        this.datePickerMovimentoData.setConverter(new StringConverter<LocalDate>() {
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

    private void defineEvents(){
        this.ripplerCloeseModal.setOnMouseClicked(event -> this.closeModal() );
        this.labelVendaDividaMontantePendente.setOnMouseClicked(mouseEvent -> {
            if(this.lastMovimentoMontente.equals(this.venda.getVendaMontantePendente())){
                this.lastMovimentoMontente = .0;
                this.lastVendaDividaMontentePendente = this.venda.getVendaMontantePendente();
                this.textFieldMovimentoMontante.setText( "" );
                this.labelVendaDividaMontantePendente.setText( this.moneyFormatter.format( this.lastVendaDividaMontentePendente ) );
             } else {
                this.lastMovimentoMontente = this.venda.getVendaMontantePendente();
                this.lastVendaDividaMontentePendente = .0;
                this.textFieldMovimentoMontante.setText( String.valueOf( this.lastMovimentoMontente ) );
                this.labelVendaDividaMontantePendente.setText( this.moneyFormatter.format( this.lastVendaDividaMontentePendente ) );
            }

        });

        this.labelVendaMontantePendenteDesc.setOnMouseClicked( this.labelVendaDividaMontantePendente.getOnMouseClicked() );

        this.textFieldMovimentoMontante.setOnKeyReleased(event -> {
            Double aDouble = this.asDouble( this.textFieldMovimentoMontante.getText() );
            if( acceptNewValue( aDouble ) ){
                this.lastMovimentoMontente = aDouble;
                this.lastVendaDividaMontentePendente = this.venda.getVendaMontantePendente() - aDouble;
                this.labelVendaDividaMontantePendente.setText( this.moneyFormatter.format( this.lastVendaDividaMontentePendente) );
            } else {
                this.textFieldMovimentoMontante.setText( String.valueOf( this.lastMovimentoMontente) );
                this.labelVendaDividaMontantePendente.setText( this.moneyFormatter.format( this.lastVendaDividaMontentePendente) );
            }
        });

        this.buttomPayNow.setOnAction(event -> this.payNow());
        this.vendaFaturaNumero.setOnMouseClicked(event -> this.onClickVendaFaturaNumero());

    }

    private void onClickVendaFaturaNumero( ){
        if( this.venda != null ) this.textFieldMovimentoDocumento.setText(this.venda.getVendaFaturaNumero());
        else this.textFieldMovimentoDocumento.setText( null );
    }

    @Override
    public void closeModal() {
        super.closeModal();
        this.textFieldMovimentoDocumento.setText( "" );
        this.textFieldMovimentoMontante.setText( "" );
        this.datePickerMovimentoData.setValue( null );
    }

    private boolean acceptNewValue(Double novoValorAmortizar) {
        if( novoValorAmortizar == null ) return false;
        return this.venda.getVendaMontantePendente() - novoValorAmortizar >= 0;
    }

    public void ok() {
        this.clear();
    }

    public void clear() {
        this.venda = null;
        this.lastVendaDividaMontentePendente = null;
        this.lastMovimentoMontente = null;
        this.labelVendaDividaMontantePendente.setText( "" );
        this.labelVendaDividaMontanteTotalPagar.setText( "" );
        this.textFieldMovimentoMontante.setText( "" );
        this.textFieldMovimentoDocumento.setText( "" );
        this.datePickerMovimentoData.setValue( null );
        this.vendaFaturaNumero.setText( null );
        this.modalTitle.setText( null );
    }

    public void setVenda(Venda venda) {
        this.clear();
        this.venda = venda;
        this.lastVendaDividaMontentePendente = this.venda.getVendaMontantePendente();
        this.lastMovimentoMontente = .0;
        this.labelVendaDividaMontantePendente.setText( this.moneyFormatter.format( this.lastVendaDividaMontentePendente) );
        this.labelVendaDividaMontanteTotalPagar.setText( this.moneyFormatter.format( this.venda.getVendaMontantePagar() ) );
        this.vendaFaturaNumero.setText( this.venda.getVendaFaturaNumero() );
        this.setTitle( "FATURA "+this.venda.getVendaFaturaNumero() );
    }

    private void payNow( ){
        Movimento movimento = this.mountMovimento();
        MovimentoResult movimentoResult = new MovimentoResult();
        movimentoResult.succeed = false;
        Gson gson = new Gson();
        Movimento.MovimentoBuilder movimentoBuilder = new Movimento.MovimentoBuilder();

        if( movimento.getMovimentoMontante() == null || movimento.getMovimentoMontante() == 0.0) {
            movimentoResult.message = "Valor de movimentação não definida";
        } else if ( this.datePickerMovimentoData.getValue() == null ){
            movimentoResult.message = "Data não foi definia";
        } else {
            PostgresSQLSingleton.loadPostgresSQL().query( "funct_reg_movimento_amortizacao_venda" )
                .withUUID(AuthSingleton.getAuth().getColaboradorId() )
                .withUUID( this.venda.getVendaId() )
                .withUUID( movimento.getConta() == null? null : movimento.getConta().getContaId() )
                .withNumeric( movimento.getMovimentoMontante() )
                .withDate( movimento.getMovimentoData() )
                .withVarchar( movimento.getMovimentoDocumento() )
                .callFunctionTable()
                    .onResultQuery( row -> { if( row.asBoolean("result" ) ){
                            String documentRegister = String.valueOf( row.get("message") );

                            movimentoResult.resultData = gson.fromJson( documentRegister, Map.class );
                            //movimento
                            if( row.asBoolean("result") ){
                                movimentoResult.movimento = movimentoBuilder.load( (Map<String, Object>) movimentoResult.resultData.get("movimento" ) )
                                        .setVenda( this.venda )
                                        .build();
                                movimentoResult.resultData =  new LinkedHashMap<>();
                                movimentoResult.succeed = true;
                            } else {
                                movimentoResult.succeed = false;
                                movimentoResult.movimento = null;
                                movimentoResult.message = String.valueOf( movimentoResult.resultData.get("text") );
                            }
                        }
                    });
        }

        result( movimentoResult );

    }

    private void result( MovimentoResult movimentoResult ){
        StringBuilder builder = new StringBuilder();
        String message = movimentoResult.getMessage();
        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( getStakePane() );
        if( message == null ) message = "Operação falhou!";

        if( movimentoResult.isSucceed() ){
            message = "Novo pagamento para a divida com documento numero "
                    + movimentoResult.movimento.getMovimentoDocumento()
                    + " cadatrado com sucesso!";
            this.closeModal();
            this.clear();
            snackbarBuilder.showSucess( message );
        } else {
            snackbarBuilder.showError( message );
        }


        super.executeOnOperationResult( movimentoResult );
    }

    private Movimento mountMovimento( ){
        Double montante = this.asDouble( this.textFieldMovimentoMontante.getText() );
        Conta conta = null;
        ContaManager contaManager = new ContaManager();
        TipoVenda tipoVenda = this.venda.getTipoVenda();

        if( tipoVenda.equals( TipoVenda.DIVIDA ) ){
            conta = contaManager.getContaFor( ContaManager.ContaOperacao.PAGAMENTO_DIVIA );
        } else if( this.venda.getTipoVenda().equals( TipoVenda.VENDA ) ){
            conta = contaManager.getContaFor(ContaManager.ContaOperacao.PAGAMENTO_VENDA );
        }

        Date movimentDataPagamento = null;
        if( this.datePickerMovimentoData.getValue() != null  )
            movimentDataPagamento = java.sql.Date.valueOf( this.datePickerMovimentoData.getValue() );

        String documento = SQLText.normalize( this.textFieldMovimentoDocumento.getText() );
        if( documento == null ) documento = venda.getVendaFaturaNumero();
        Movimento.MovimentoBuilder builder = new Movimento.MovimentoBuilder();
        builder.setVenda( this.venda )
                .setMontante( this.asDouble( this.textFieldMovimentoMontante.getText() ) )
                .setConta( conta )
                .setDocumento( documento )
                .setMontante( montante )
                .setData( movimentDataPagamento );

        return builder.build();
    }


    private Double asDouble(String text ){
        try{
            return Double.valueOf( text );
        } catch ( Exception ex ){
            return null;
        }
    }


    public class MovimentoResult implements ModalResult<Movimento> {
        private MovimentoResult(){

        }

        private Map<String, Object > resultData;
        private boolean succeed;
        private boolean terminated;
        private Movimento movimento;

        private String message;

        public boolean isSucceed() {
            return succeed;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean isTerminated() {
            return this.terminated;
        }

        @Override
        public Movimento getResultValue() {

            return movimento;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return null;
        }

        @Override
        public Map<String, Object> mapResults() {
            return this.resultData;
        }
    }
}
