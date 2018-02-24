package st.ggviario.house.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import st.ggviario.house.model.Movimento;
import st.ggviario.house.model.Venda;
import st.ggviario.house.singleton.PostgresSQLSingleton;

import java.net.URL;
import java.util.*;

public class ModalListaMovimentoVendaController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private VBox rootTable;

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
    private Label labelVendaMontantePago;

    @FXML
    private Label labelMovimentoLibele;

    @FXML
    private JFXButton buttomMovimentosOk;

    private OnOkCliek onOkCliek;
    private Venda venda;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.structure();
        this.difineEvents();
    }

    public void ok(){

    }

    private void structure(){
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

        this.treeTableView.getColumns().setAll( movimentoCodigo, movimentoDocumento, movimentoMontante, movimentoData, movimentoDataRegisto );
    }

    private void difineEvents(){
        this.buttomMovimentosOk.setOnAction(event -> {
            if( this.onOkCliek != null  ) onOkCliek.onOkCliek();
        });
    }


    public void setVenda( Venda venda ){
        ObservableList<VendaMovimento> movimentoVenda = this.loadMovimentoData(venda);
        final TreeItem< VendaMovimento > root = new RecursiveTreeItem< VendaMovimento >( movimentoVenda, RecursiveTreeObject::getChildren );
        this.treeTableView.setRoot(root);
        this.treeTableView.setShowRoot(false);
        this.venda = venda;
    }

    public ModalListaMovimentoVendaController setOnOkCliek(OnOkCliek onOkCliek) {
        this.onOkCliek = onOkCliek;
        return this;
    }

    private ObservableList<VendaMovimento> loadMovimentoData(Venda venda) {
        Gson gson = new Gson();
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

    interface OnOkCliek {
        void onOkCliek( );
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
