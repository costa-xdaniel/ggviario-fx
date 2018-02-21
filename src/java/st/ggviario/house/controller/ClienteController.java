package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton fabNewCliente;
    private JFXDialogLayout dialogContent;
    private JFXDialog dialog;
    private Node newClientModalContent;
    private ClienteNewController newClienteModalContentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fabNewCliente.setOnAction( event -> this.newClient() );
        this.dialogContent = new JFXDialogLayout();
        this.dialog = new JFXDialog( this.stackPane, this.dialogContent, JFXDialog.DialogTransition.CENTER );
    }

    private void newClient() {
        this.loadModal();
        dialogContent.setHeading( new Text("Novo cliente") );
        dialogContent.setBody( this.newClientModalContent);
        this.newClienteModalContentController.newClient();
        this.newClienteModalContentController.setOnResultSucess( ( cliente, data )->{
           this.dialog.close();
        });
        this.dialog.show();
    }

    private void loadModal() {
        try{
            if( this.newClientModalContent == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/cliente_new.fxml" ) );
                this.newClientModalContent = loader.load();
                this.newClienteModalContentController = loader.getController();
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }
}
