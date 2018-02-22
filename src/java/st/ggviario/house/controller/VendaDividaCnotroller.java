package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import st.ggviario.house.model.Cliente;
import st.ggviario.house.model.TipoVenda;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class VendaDividaCnotroller implements Initializable, VendaNewController.OnNewClienteRequest, VendaNewController.OnNewVendaResult, ClienteNewController.OnNewClienteResult {


    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton buttonNewDivida;
    private Pane dividaNewPanel;
    private VendaNewController vendaNewController;

    private JFXDialog dialogVendaNew;
    private JFXDialogLayout dialogLayoutCompraNew;

    private JFXDialog dialogClienteNew;
    private JFXDialogLayout dialogLayoutClienteNew;

    private Pane clienteNewPanel;
    private ClienteNewController clienteNewController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttonNewDivida.setOnAction(actionEvent -> this.newDivida());
        this.dialogLayoutCompraNew = new JFXDialogLayout();
        this.dialogVendaNew = new JFXDialog( this.stackPane, this.dialogLayoutCompraNew, JFXDialog.DialogTransition.CENTER );
//
        this.dialogLayoutClienteNew = new JFXDialogLayout();
        this.dialogClienteNew = new JFXDialog( this.stackPane, this.dialogLayoutClienteNew, JFXDialog.DialogTransition.CENTER );

    }

    private void newDivida() {
        this.loadNewVendaContent();
        dialogLayoutCompraNew.setHeading( new Text("Nova divida") );
        dialogLayoutCompraNew.setBody( this.dividaNewPanel);
        this.dialogVendaNew.show();
    }

    private void loadNewVendaContent() {
        try{
            if( this.dividaNewPanel == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/venda_new.fxml") );
                this.dividaNewPanel = loader.load();
                this.vendaNewController = loader.getController();
                this.vendaNewController.setOnNewClienteRequest( this );
                this.vendaNewController.setOnNewVendaResult( this );
                this.vendaNewController.setTipoVenda( TipoVenda.DIVIDA );
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
        this.dialogVendaNew.setOnDialogClosed(jfxDialogEvent -> this.newCliente());
        this.dialogVendaNew.close();
    }

    private void newCliente() {
        this.loadNewCliente();
        this.dialogVendaNew.setOnDialogClosed( null );

        this.dialogLayoutClienteNew.setHeading( new Text("Novo cliente") );
        this.dialogLayoutClienteNew.setBody( this.clienteNewPanel );
        this.dialogClienteNew.show();

        dialogClienteNew.setOnDialogClosed(jfxDialogEvent -> {
            this.dialogClienteNew.setOnDialogClosed( null );
            this.dialogVendaNew.show();
        });
    }


    @Override
    public void onNewClienteResult(boolean result, Cliente cliente, Map<String, Object> data) {
        this.vendaNewController.onClienteRequestResult( result, cliente, data );
        dialogClienteNew.close();

    }

    @Override
    public void onNewVendaResult(VendaNewController.NewVendaResult newVendaResult) {
        if( newVendaResult.isSucess() ){
            this.dialogVendaNew.close();
            this.dialogVendaNew.setOnDialogClosed( null );
        }
    }
}
