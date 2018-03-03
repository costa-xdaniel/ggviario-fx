package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRippler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.model.Cliente;
import st.ggviario.house.model.TipoVenda;
import st.ggviario.house.model.Venda;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DrawerVendaDetalhesController implements Initializable {

    private JFXRippler ripplerAdd;

    public static DrawerVendaDetalhesController load(JFXDrawer drawerView, TipoVenda tipoVenda, String [] avalibleIcons ) {
        ControllerLoader< BorderPane, DrawerVendaDetalhesController > loader = new ControllerLoader<>("/fxml/drawer/drawer_venda_details.fxml");
        DrawerVendaDetalhesController drawer = loader.getController();
        drawer.avalibleIcons = avalibleIcons;
        drawer.drawer = drawerView;
        drawer.tipoVenda = tipoVenda;
        drawer.structureLayout();
        drawer.defineEvents();
        drawer.moneyFormatter.setMinimumFractionDigits( 2 );
        drawer.moneyFormatter.setMaximumFractionDigits( 2 );
        drawer.moneyFormatter.setMinimumIntegerDigits( 1 );
        return drawer;
    }

    @FXML
    private AnchorPane root;

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
    private Label lableVendaArreaInformacaoTitle;

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

    private OnPayNow onPayNow;
    private OnListPayment onListPayment;
    private OnNewVendaForClinet onNewVendaForClinet;

    private NumberFormat moneyFormatter = NumberFormat.getInstance( Locale.FRANCE );
    private NumberFormat numeberFormat = NumberFormat.getInstance( Locale.FRANCE );
    private DateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    private Venda venda;
    private String[] avalibleIcons;
    private TipoVenda tipoVenda;
    private ViewConfigurarion viewConfigurarion;

    private JFXDrawer drawer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.viewConfigurarion = new ViewConfigurarion();

    }

    private void structureLayout(){
        JFXRippler ripplerClose = new JFXRippler( panelIconClose );
        this.ripplerAdd = new JFXRippler( panelIconAdd );
        JFXRippler ripplerPayNow = new JFXRippler( panelIconPayNow );
        JFXRippler ripplerListPay = new JFXRippler( panelIconListPayment );

        ripplerClose.setStyle("-jfx-rippler-fill: md-red-500");
        ripplerAdd.setStyle("-jfx-rippler-fill: md-primary-color");
        ripplerPayNow.setStyle("-jfx-rippler-fill: md-primary-color");
        ripplerListPay.setStyle("-jfx-rippler-fill: md-primary-color");

        this.headerPageIcon.getChildren().add( ripplerClose );
        this.showIcon( panelIconAdd, ripplerAdd );
        this.showIcon( panelIconPayNow, ripplerPayNow );
        this.showIcon( panelIconListPayment, ripplerListPay );

        if( this.viewConfigurarion.difine ){
            this.lableVendaArreaInformacaoTitle.setText( this.viewConfigurarion.descricaoTituloInformacao );
        }
    }

    private void defineEvents(){
        this.panelIconClose.setOnMouseClicked(mouseEvent -> {
            this.drawer.close();
        });

        this.panelIconPayNow.setOnMouseClicked(event ->{
            if( onPayNow != null )
                this.onPayNow.acceptOnPayNow( this.venda );
        });

        this.panelIconListPayment.setOnMouseClicked( event -> {
            if( this.onListPayment != null) onListPayment.acceptNewListPaymentVenda( this.venda);
        });

        this.ripplerAdd.setOnMouseClicked(event -> {
            if( this.onNewVendaForClinet != null && this.venda != null ) this.onNewVendaForClinet.onNewVendaForCliente( this.venda.getCliente() );
        });
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
        for( String s: this.avalibleIcons){
            if( id.equals( s ) ) return true;
        }
        return false;
    }

    public DrawerVendaDetalhesController setOnNovoPagamento(OnPayNow onPayNow) {
        this.onPayNow = onPayNow;
        return this;
    }

    public DrawerVendaDetalhesController setOnListPayment(OnListPayment onListPayment) {
        this.onListPayment = onListPayment;
        return this;
    }

    public DrawerVendaDetalhesController setOnNewVendaForClinet(OnNewVendaForClinet onNewVendaForClinet) {
        this.onNewVendaForClinet = onNewVendaForClinet;
        return this;
    }

    public DrawerVendaDetalhesController setTipoVenda(TipoVenda tipoVenda ){
        this.tipoVenda = tipoVenda;

        if( this.tipoVenda == TipoVenda.DIVIDA ){
            this.viewConfigurarion.descricaoTituloInformacao = "Informações da divia";
        } else if( this.tipoVenda == TipoVenda.VENDA ){
            this.viewConfigurarion.descricaoTituloInformacao = "Informações da venda";
        } else throw  new RuntimeException("Tipo de venda invalido ou não definido!" );
        this.viewConfigurarion.difine = true;
        return this;
    }

    public  void setVenda(Venda venda ){
        this.venda = venda;

        this.labelHeaderTitle.setText(  "FATURA " + venda.getVendaFaturaNumero()  );
        this.labelClienteNome.setText( venda.getCliente().getClienteCompletName() );
        this.labelClienteMontentePagar.setText( moneyFormatter(this.venda.getCliente().getClienteMontanteTotal())  );
        this.labelClienteMontantePendente.setText( moneyFormatter(this.venda.getCliente().getClienteMontantePendente())  );
        this.labelProdutoNome.setText( this.venda.getProduto().getProdutoNome() );
        this.labelUnidadeNome.setText( this.venda.getUnidade().getUnidadeNome()+" ("+this.venda.getUnidade().getUnidadeCodigo()+")" );
        this.labelVendaQuantidade.setText( this.numeberFormat.format( this.venda.getVandaQuantidade() ) );
        this.labelVendaMontanteUnitario.setText( this.numeberFormat.format( this.venda.getVendaMontanteUnitario() ) );
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

    public AnchorPane getRoot() {
        return root;
    }

    public interface OnCloseVendaDetaisCallback {
        void acceptCloseVendaDetails();
    }


    public interface OnPayNow {
        void acceptOnPayNow( Venda venda );
    }

    public interface OnListPayment {
        void acceptNewListPaymentVenda( Venda venda );
    }


    public interface OnNewVendaForClinet {
        void onNewVendaForCliente( Cliente cliente );
    }

    private class ViewConfigurarion {
        private String descricaoTituloInformacao;
        private boolean difine;
    }


}
