package st.ggviario.house.controller.modal;

import com.google.gson.JsonObject;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.model.Movimento;
import st.ggviario.house.model.Venda;
import st.ggviario.house.singleton.PostgresSQLSingleton;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ModalAmoratizacoesDivida extends AbstractModal implements  Initializable {

    private JFXRippler ripplerCloseModal;

    public static ModalAmoratizacoesDivida load(StackPane stackPane){
        ControllerLoader<Pane, ModalAmoratizacoesDivida> loader = new ControllerLoader<>("/fxml/modal/modal_detalhes_venda_amortizacao.fxml");
        loader.getController().createDialogModal( stackPane );
        return loader.getController();
    }

    @FXML
    private AnchorPane root;

    @FXML
    private Label modalTitle;


    @FXML
    private AnchorPane anchorHeader;

    @FXML
    private JFXTreeTableView< VendaMovimento > treeTableView;

    @FXML
    private Label labelVendaTotalPagar;

    @FXML
    private Label labelVendaUltimoPagamento;

    @FXML
    private Label lavelVendaPendente;

    @FXML
    private Label labelVendaDatafim;

    @FXML
    private Label labelVendaMontanteAmortizado;

    @FXML
    private Label labelMovimentoLibele;

    @FXML
    private AnchorPane iconAreaCloseModal;

    private Venda venda;

    private DateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    private NumberFormat moneyFormat = NumberFormat.getInstance( Locale.FRANCE );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        this.structure();
        this.difineEvents();
    }

    private void init(){
        this.moneyFormat.setMaximumFractionDigits( 2 );
        this.moneyFormat.setMinimumFractionDigits( 2 );
        this.moneyFormat.setMaximumIntegerDigits( 1 );
    }

    void structure(){
        JFXTreeTableColumn<VendaMovimento, String > movimentoCodigo = new JFXTreeTableColumn<>("Cod");
        movimentoCodigo.setCellValueFactory(param -> param.getValue().getValue().movimentoCodigo );

        JFXTreeTableColumn<VendaMovimento, String > movimentoDocumento = new JFXTreeTableColumn<>( "Doc" );
        movimentoDocumento.setCellValueFactory( param -> param.getValue().getValue().movimentoDocumento );

        JFXTreeTableColumn< VendaMovimento, Number > movimentoMontante = new JFXTreeTableColumn<>( "Montante" );
        movimentoMontante.setCellValueFactory( param -> param.getValue().getValue().movimentoMontante );

        JFXTreeTableColumn< VendaMovimento, Date > movimentoData = new JFXTreeTableColumn<>("Data");
        movimentoData.setCellValueFactory(param -> param.getValue().getValue().movimentoData );


        JFXTreeTableColumn< VendaMovimento, Date > movimentoDataRegisto = new JFXTreeTableColumn<>( "Registo" );
        movimentoDataRegisto.setCellValueFactory( param -> param.getValue().getValue().movimentoDatargisto );
        movimentoDataRegisto.setCellFactory(param -> new TreeTableCell< VendaMovimento, Date> (){

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if( item != null && !empty) {
                    setText( dateFormat.format( item ) );
                }
                this.setItem( item );
            }
        });

        this.treeTableView.getColumns().setAll( movimentoCodigo, movimentoDocumento, movimentoMontante, movimentoData, movimentoDataRegisto );

        this.ripplerCloseModal = new JFXRippler( this.iconAreaCloseModal);
        this.ripplerCloseModal.setStyle("-jfx-rippler-fill: md-red-500");
        this.anchorHeader.getChildren().add( this.ripplerCloseModal );

        AnchorPane.setTopAnchor( this.ripplerCloseModal, 0x0.0p0 );
        AnchorPane.setRightAnchor( this.ripplerCloseModal, 0x0.0p0 );

    }

    private void difineEvents(){

        this.ripplerCloseModal.setOnMouseClicked(event -> this.closeModal());
        this.treeTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if( newValue == null ) return;
            this.labelMovimentoLibele.setText( newValue.getValue().movimento.getMovimentoLibele() );
        });
    }

    @Override
    public void closeModal() {
        super.closeModal();
        this.labelMovimentoLibele.setText("");
        this.lavelVendaPendente.setText( "");
        this.labelVendaDatafim.setText("");
        this.labelVendaMontanteAmortizado.setText("");
        this.labelVendaTotalPagar.setText("");
        this.labelVendaUltimoPagamento.setText("");
    }

    public void setVenda(Venda venda ){
        ObservableList<VendaMovimento> movimentoVenda = this.loadMovimentoData(venda);
        final TreeItem< VendaMovimento > root = new RecursiveTreeItem<>(movimentoVenda, RecursiveTreeObject::getChildren );
        this.treeTableView.setRoot(root);
        this.treeTableView.setShowRoot(false);
        this.venda = venda;
        this.labelMovimentoLibele.setText("");
        this.labelVendaDatafim.setText( this.venda.getVendaDataFim() == null? "" : this.dateFormat.format( this.venda.getVendaDataFim() ) );
        this.labelVendaUltimoPagamento.setText(  this.venda.getVendaDataUltimaMovimentacao() == null? "" : this.dateFormat.format( this.venda.getVendaDataUltimaMovimentacao() ) );

        this.lavelVendaPendente.setText( this.moneyFormat.format( this.venda.getVendaMontantePendente() )+" STN");
        this.labelVendaTotalPagar.setText( this.moneyFormat.format( this.venda.getVendaMontantePagar() ) + " STN" );
        this.labelVendaMontanteAmortizado.setText( this.moneyFormat.format( this.venda.getVendaMontanteAmortizado() )+" STN" );
        this.modalTitle.setText( "Amortização da fatura "+ this.venda.getVendaFaturaNumero() );
    }

    private ObservableList<VendaMovimento> loadMovimentoData(Venda venda) {
        Map<String, Object > map = new HashMap<>();
        map.put("venda_id", venda.getVendaId() );
        Movimento.MovimentoBuilder movimentoBuilder = new Movimento.MovimentoBuilder();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("venda_id", String.valueOf( venda.getVendaId() ) );

        List< VendaMovimento > vendaMovimentos = new LinkedList<>();
        PostgresSQLSingleton.loadPostgresSQL().query( "funct_load_movimento_venda" )
                .withUUID( venda.getVendaId() )
                .callFunctionTable()
                .onResultQuery( row -> {
                    movimentoBuilder.load( row ).setVenda( venda );
                    vendaMovimentos.add( new VendaMovimento( movimentoBuilder.build() ) );
                });
        return FXCollections.observableList( vendaMovimentos );
    }

    @Override
    JFXDialog.DialogTransition getDialogTransition() {
        return JFXDialog.DialogTransition.CENTER;
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

    private class VendaMovimento extends RecursiveTreeObject<VendaMovimento >{

        private StringProperty movimentoCodigo;
        private ObjectProperty<Date> movimentoData;
        private StringProperty movimentoDocumento;
        private ObjectProperty<Number> movimentoMontante;
        private ObjectProperty<Date> movimentoDatargisto;
        private Movimento movimento;

        private VendaMovimento (Movimento movimento){
            this.movimento = movimento;
            this.movimentoCodigo = new SimpleStringProperty( this.movimento.getMovimentoCodigo() );
            this.movimentoData = new SimpleObjectProperty<>( this.movimento.getMovimentoData() );
            this.movimentoDocumento = new SimpleStringProperty( this.movimento.getMovimentoDocumento() );
            this.movimentoMontante = new SimpleObjectProperty<>( this.movimento.getMovimentoMontante() );
            this.movimentoDatargisto = new SimpleObjectProperty<>( this.movimento.getMovimentoDataRegisto() );
        }

    }
}
