package st.ggviario.house.controller;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;

import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class ModalNovoPagamentoController implements Initializable{


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

    private Venda venda;

    private NumberFormat moneyFormatter = NumberFormat.getInstance( Locale.FRANCE );
    private Double lastVendaDividaMontentePendente;
    private Double lastMovimentoMontente;

    private OnResultPayNow onResultPayNow;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.structure();
        this.defineEvents();
    }

    private void structure(){
        moneyFormatter.setMinimumFractionDigits( 2 );
        moneyFormatter.setMaximumFractionDigits( 2 );
        moneyFormatter.setMinimumIntegerDigits( 1 );
    }

    private void defineEvents(){
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
    }

    public void setVenda(Venda venda) {
        this.clear();
        this.venda = venda;
        this.lastVendaDividaMontentePendente = this.venda.getVendaMontantePendente();
        this.lastMovimentoMontente = .0;
        this.labelVendaDividaMontantePendente.setText( this.moneyFormatter.format( this.lastVendaDividaMontentePendente) );
        this.labelVendaDividaMontanteTotalPagar.setText( this.moneyFormatter.format( this.venda.getVendaMontantePagar() ) );
    }

    private void payNow( ){
        Movimento movimento = this.mountMovimento();
        MovimentoResult movimentoResult = new MovimentoResult();
        movimentoResult.sucess = false;
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
                            String documentRegister = String.valueOf( row.valueOf("message") );

                            movimentoResult.resultData = gson.fromJson( documentRegister, Map.class );
                            //movimento
                            if( row.asBoolean("result") ){
                                movimentoResult.movimento = movimentoBuilder.load( (Map<String, Object>) movimentoResult.resultData.get("movimento" ) )
                                        .setVenda( this.venda )
                                        .build();
                                movimentoResult.resultData =  new LinkedHashMap<>();
                                movimentoResult.sucess = true;
                            } else {
                                movimentoResult.sucess = false;
                                movimentoResult.movimento = null;
                                movimentoResult.message = String.valueOf( movimentoResult.resultData.get("text") );
                            }
                        }
                    });
        }

        if( this.onResultPayNow != null ) this.onResultPayNow.onResultCadastrarDivida(movimentoResult);
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



    public ModalNovoPagamentoController setOnResultPayNow(OnResultPayNow onResultPayNow) {
        this.onResultPayNow = onResultPayNow;
        return this;
    }

    private Double asDouble(String text ){
        try{
            return Double.valueOf( text );
        } catch ( Exception ex ){
            return null;
        }
    }



    public interface OnResultPayNow {
        void onResultCadastrarDivida( MovimentoResult movimentoResult );
    }

    public class MovimentoResult{
        private MovimentoResult(){

        }

        private Map<String, Object > resultData;
        private boolean sucess;
        private Movimento movimento;
        private String message;

        public Map<String, Object> getResultData() {
            return resultData;
        }

        public boolean isSucess() {
            return sucess;
        }

        public Movimento getMovimento() {
            return movimento;
        }

        public Venda getVenda() {
            return venda;
        }

        public String getMessage() {
            return message;
        }
    }
}
