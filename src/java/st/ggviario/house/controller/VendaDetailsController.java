package st.ggviario.house.controller;

import com.jfoenix.controls.JFXRippler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import st.ggviario.house.model.Venda;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class VendaDetailsController  {

    @FXML
    private Label labelClienteNome;

    @FXML
    private Label labelClienteMontentePagar;

    @FXML
    private Label labelClienteMontantePendente;

    @FXML
    private Label labelProdutoNome;

    @FXML
    private Label labelUnidadeNome;

    @FXML
    private Label labelVendaQuantidade;

    @FXML
    private Label labelVendaMontanteUnitario;

    @FXML
    private Label labelVendaMontanteDesconto;

    @FXML
    private Label lableVendaMontanteBruto;

    @FXML
    private Label labelVendaData;

    @FXML
    private Label labelVendaDataFinalizar;

    @FXML
    private Label labelVendaMontantePagar;

    @FXML
    private Label labelVendaMontanteAmortizado;

    @FXML
    private Label labelVendaMontantePagarAccent;

    @FXML
    private HBox headerPageIcon;

    @FXML
    private AnchorPane panelIconClose;

    @FXML
    private AnchorPane panelIconAdd;

    @FXML
    private AnchorPane panelIconPayNow;

    @FXML
    private AnchorPane panelIconListPayment;

    @FXML
    private Label labelHeaderTitle;

    private OnCloseVendaDetaisCallback onCloseVendaDetaisCallback;
    private OnPayNow onPayNow;

    private NumberFormat moneyFormatter = NumberFormat.getInstance( Locale.FRANCE );
    private NumberFormat numeberFormat = NumberFormat.getInstance( Locale.FRANCE );
    private DateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    private Venda venda;
    private String[] iconsId;

    public void setIconAvalible( String [] iconsId ){
        this.iconsId = iconsId;
    }

    public void ok(){
        this.panelIconClose.setOnMouseClicked(event -> this.onCloseDetails() );
        this.moneyFormatter.setMinimumFractionDigits( 2 );
        this.moneyFormatter.setMaximumFractionDigits( 2 );
        this.moneyFormatter.setMinimumIntegerDigits( 1 );

        this.structureLayout();
        this.defineEvents();


    }

    private void structureLayout(){
        JFXRippler ripplerClose = new JFXRippler( panelIconClose );
        JFXRippler ripplerAdd = new JFXRippler( panelIconAdd );
        JFXRippler ripplerPayNow = new JFXRippler( panelIconPayNow );
        JFXRippler ripplerListPay = new JFXRippler( panelIconListPayment );

        ripplerClose.setStyle("-jfx-rippler-fill: md-primary-color");
        ripplerAdd.setStyle("-jfx-rippler-fill: md-primary-color");
        ripplerPayNow.setStyle("-jfx-rippler-fill: md-primary-color");
        ripplerListPay.setStyle("-jfx-rippler-fill: md-primary-color");

        this.headerPageIcon.getChildren().add( ripplerClose );
        this.showIcon( panelIconAdd, ripplerAdd );
        this.showIcon( panelIconPayNow, ripplerPayNow );
        this.showIcon( panelIconListPayment, ripplerListPay );
    }

    private void defineEvents(){
        this.panelIconPayNow.setOnMouseClicked(event -> this.onPayNow.acceptOnPayNow( this.venda ));
    }

    private void showIcon(Pane pane, JFXRippler rippler ){
        if( this.conteinsId( pane.getId() ) ){
            this.headerPageIcon.getChildren().add( rippler );
        } else {
            this.headerPageIcon.getChildren().remove( pane );
        }
    }

    private boolean conteinsId( String id ){
        if( id == null ) return false;
        for( String s: this.iconsId ){
            if( id.equals( s ) ) return true;
        }
        return false;
    }



    private void onCloseDetails() {
        if( this.onCloseVendaDetaisCallback != null ) this.onCloseVendaDetaisCallback.acceptCloseVendaDetails();
    }

    public VendaDetailsController setOnCloseVendaDetaisCallback(OnCloseVendaDetaisCallback onCloseVendaDetaisCallback) {
        this.onCloseVendaDetaisCallback = onCloseVendaDetaisCallback;
        return this;
    }

    public VendaDetailsController setOnPayNow(OnPayNow onPayNow) {
        this.onPayNow = onPayNow;
        return this;
    }

    public  void setVenda(Venda venda ){
        this.venda = venda;
        this.labelClienteNome.setText( venda.getCliente().getClienteCompletName() );
        this.labelClienteMontentePagar.setText( moneyFormatter(this.venda.getCliente().getClienteMontanteTotal())  );
        this.labelClienteMontantePendente.setText( moneyFormatter(this.venda.getCliente().getClienteMontantePendente())  );
        this.labelProdutoNome.setText( this.venda.getProducto().getProdutoNome() );
        this.labelUnidadeNome.setText( this.venda.getUnidade().getUnidadeNome()+" ("+this.venda.getUnidade().getUnidadeCodigo()+")" );
        this.labelHeaderTitle.setText( this.venda.getCliente().getClienteCompletName() );
        this.labelVendaQuantidade.setText( this.numeberFormat.format( this.venda.getVandaQuantidade() ) );
        this.labelVendaMontanteUnitario.setText( this.numeberFormat.format( this.venda.getVendaMontanteUnidario() ) );
        this.labelVendaMontanteDesconto.setText(moneyFormatter(this.venda.getVendaMontanteDesconto()));
        this.lableVendaMontanteBruto.setText(moneyFormatter(this.venda.getVendaMontanteBruto()));
        this.labelVendaData.setText( this.dateFormat.format( this.venda.getVendaData() ) );
        this.labelVendaDataFinalizar.setText( this.dateFormat.format( this.venda.getVendaDataFinalizar() ) );
        this.labelVendaMontantePagar.setText(moneyFormatter(this.venda.getVendaMontantePagar()));
        this.labelVendaMontanteAmortizado.setText(moneyFormatter(this.venda.getVendaMontanteAmortizado()));
        this.labelVendaMontantePagarAccent.setText( moneyFormatter( this.venda.getVendaMontantePagar() ) );

    }

    private String moneyFormatter(Double vendaMontanteAmortizado) {
        return this.moneyFormatter.format(vendaMontanteAmortizado)+" STN";
    }

    public interface OnCloseVendaDetaisCallback {
        void acceptCloseVendaDetails();
    }


    public interface OnPayNow {
        void acceptOnPayNow( Venda venda );
    }


}
