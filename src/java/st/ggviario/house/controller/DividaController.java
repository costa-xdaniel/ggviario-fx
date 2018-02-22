package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import st.ggviario.house.model.Cliente;
import st.ggviario.house.model.Compra;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class DividaController implements Initializable, DividaNewController.OnNewClienteRequest, DividaNewController.OnNewCompraResult, ClienteNewController.OnNewClienteResult {


    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton buttonNewDivida;
    private Pane dividaNewPanel;
    private DividaNewController dividaNewController;

    private JFXDialog dialogCampraDividaNew;
    private JFXDialogLayout dialogLayoutCompraNew;

    private JFXDialog dialogClienteNew;
    private JFXDialogLayout dialogLayoutClienteNew;

    private Pane clienteNewPanel;
    private ClienteNewController clienteNewController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttonNewDivida.setOnAction(actionEvent -> this.newDivida());
        this.dialogLayoutCompraNew = new JFXDialogLayout();
        this.dialogCampraDividaNew = new JFXDialog( this.stackPane, this.dialogLayoutCompraNew, JFXDialog.DialogTransition.CENTER );
//
        this.dialogLayoutClienteNew = new JFXDialogLayout();
        this.dialogClienteNew = new JFXDialog( this.stackPane, this.dialogLayoutClienteNew, JFXDialog.DialogTransition.CENTER );

    }

    private void newDivida() {
        this.loadNewDividaContent();
        dialogLayoutCompraNew.setHeading( new Text("Nova divida") );
        dialogLayoutCompraNew.setBody( this.dividaNewPanel);
        this.dialogCampraDividaNew.show();
    }

    private void loadNewDividaContent() {
        try{
            if( this.dividaNewPanel == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/divida_new.fxml" ) );
                this.dividaNewPanel = loader.load();
                this.dividaNewController = loader.getController();
                this.dividaNewController.setOnNewClienteRequest( this );
                this.dividaNewController.setOnNewCompraResult( this );
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    private void loadNewCliente() {
        try{
            if( this.clienteNewPanel == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/cliente_new.fxml" ) );
                this.clienteNewPanel = loader.load();
                this.clienteNewController = loader.getController();
                this.clienteNewController.setOnNewClienteResult( this );
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }


    @Override
    public void onRegisterNewClienteRequest() {
        this.dialogCampraDividaNew.setOnDialogClosed(jfxDialogEvent -> this.newCliente());
        this.dialogCampraDividaNew.close();
    }

    private void newCliente() {
        this.loadNewCliente();
        this.dialogCampraDividaNew.setOnDialogClosed( null );

        this.dialogLayoutClienteNew.setHeading( new Text("Novo cliente") );
        this.dialogLayoutClienteNew.setBody( this.clienteNewPanel );
        this.dialogClienteNew.show();
    }

    @Override
    public void onNewCompraResult(boolean result, Compra compra, Map<String, Object> map) {

    }

    @Override
    public void onNewClienteResult(boolean result, Cliente cliente, Map<String, Object> data) {
        this.dividaNewController.onClienteRequestResult( result, cliente, data );
        this.dialogClienteNew.setOnDialogClosed( event -> {
            this.dialogClienteNew.setOnDialogClosed( null );
        });

        dialogClienteNew.setOnDialogClosed(jfxDialogEvent -> {
            this.dialogClienteNew.setOnDialogClosed( null );
            this.dialogCampraDividaNew.show();
        });
        dialogClienteNew.close();

    }
}
