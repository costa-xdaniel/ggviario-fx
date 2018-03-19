package st.ggviario.house.control.drawers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.model.Cliente;
import st.ggviario.house.model.TipoVenda;
import st.ggviario.house.model.Venda;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DrawerVenda implements Initializable {

    private JFXRippler ripplerAdd;

    public static DrawerVenda load(JFXDrawer drawerView, TipoVenda tipoVenda, String [] avalibleIcons ) {
        ControllerLoader< BorderPane, DrawerVenda> loader = new ControllerLoader<>("/fxml/drawer/drawer_venda_details.fxml");
        DrawerVenda drawer = loader.getController();
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

    @FXML private AnchorPane root;
    @FXML private Label labelClienteNome;
    @FXML private Label labelClienteMontentePagar;
    @FXML private Label labelClienteMontantePendente;
    @FXML private Label labelProdutoNome;
    @FXML private Label labelUnidadeNome;
    @FXML private Label labelVendaQuantidade;
    @FXML private Label labelVendaMontanteUnitario;
    @FXML private Label labelVendaMontanteDesconto;
    @FXML private Label lableVendaMontanteBruto;
    @FXML private Label labelVendaData;
    @FXML private Label labelVendaDataFinalizar;
    @FXML private Label labelVendaMontantePagar;
    @FXML private Label labelVendaMontanteAmortizado;
    @FXML private Label labelVendaMontantePagarAccent;
    @FXML private Label lableVendaArreaInformacaoTitle;
    @FXML private HBox headerPageIcon;
    @FXML private AnchorPane panelIconClose;
    @FXML private AnchorPane panelIconAdd;
    @FXML private AnchorPane panelIconPayNow;
    @FXML private AnchorPane panelIconDelete;
    @FXML private AnchorPane panelIconListPayment;
    @FXML private Label labelHeaderTitle;

    private OnNovoPagamento onNovoPagamento;
    private OnListPayment onListPayment;
    private OnNewVendaForClinet onNewVendaForClinet;
    private OnDeleteVenda onDeleteVenda;

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
        JFXDepthManager.pop( this.root );

    }

    private void structureLayout(){
        JFXRippler ripplerClose = new JFXRippler( panelIconClose );
        this.ripplerAdd = new JFXRippler( panelIconAdd );
        JFXRippler ripplerPayNow = new JFXRippler( panelIconPayNow );
        JFXRippler ripplerListPay = new JFXRippler( panelIconListPayment );
        JFXRippler ripplerListDelete = new JFXRippler( this.panelIconDelete  );

        ripplerClose.setStyle("-jfx-rippler-fill: md-red-500");
        this.ripplerAdd.setStyle("-jfx-rippler-fill: md-primary-color");
        ripplerPayNow.setStyle("-jfx-rippler-fill: md-primary-color");
        ripplerListPay.setStyle("-jfx-rippler-fill: md-primary-color");
        ripplerListDelete.setStyle("-jfx-rippler-fill: md-primary-color");

        this.headerPageIcon.getChildren().add( ripplerClose );
        this.showIcon( panelIconAdd, ripplerAdd );
        this.showIcon( panelIconListPayment, ripplerListPay );
        this.showIcon( panelIconPayNow, ripplerPayNow );
        this.showIcon( this.panelIconDelete, ripplerListDelete );

        if( this.viewConfigurarion.difine ){
            this.lableVendaArreaInformacaoTitle.setText( this.viewConfigurarion.descricaoTituloInformacao );
        }
    }

    private void defineEvents(){
        this.panelIconClose.setOnMouseClicked(mouseEvent -> {
            this.drawer.close();
        });

        this.panelIconPayNow.setOnMouseClicked(event ->{
            if( onNovoPagamento != null )
                this.onNovoPagamento.acceptOnPayNow( this.venda );
        });

        this.panelIconListPayment.setOnMouseClicked( event -> {
            if( this.onListPayment != null ) onListPayment.acceptNewListPaymentVenda( this.venda);
        });

        this.ripplerAdd.setOnMouseClicked(event -> {
            if( this.onNewVendaForClinet != null && this.venda != null )
                this.onNewVendaForClinet.onNewVendaForCliente( this.venda.getCliente() );
        });

        this.panelIconDelete.setOnMouseClicked( mouseEvent -> {
            if( this.onDeleteVenda != null ) this.onDeleteVenda.onDeleteVenda( this.venda );
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

    public DrawerVenda setOnNovoPagamento(OnNovoPagamento onNovoPagamento) {
        this.onNovoPagamento = onNovoPagamento;
        return this;
    }

    public DrawerVenda setOnListPayment(OnListPayment onListPayment) {
        this.onListPayment = onListPayment;
        return this;
    }

    public DrawerVenda setOnNewVendaForClinet(OnNewVendaForClinet onNewVendaForClinet) {
        this.onNewVendaForClinet = onNewVendaForClinet;
        return this;
    }

    public DrawerVenda setOnDeleteVenda(OnDeleteVenda onDeleteVenda) {
        this.onDeleteVenda = onDeleteVenda;
        return this;
    }


    public DrawerVenda setTipoVenda(TipoVenda tipoVenda ){
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


    public interface OnNovoPagamento {
        void acceptOnPayNow( Venda venda );
    }

    public interface OnListPayment {
        void acceptNewListPaymentVenda( Venda venda );
    }


    public interface OnNewVendaForClinet {
        void onNewVendaForCliente( Cliente cliente );
    }


    public interface  OnDeleteVenda {
        void onDeleteVenda( Venda venda );
    }

    private class ViewConfigurarion {
        private String descricaoTituloInformacao;
        private boolean difine;
    }


}
