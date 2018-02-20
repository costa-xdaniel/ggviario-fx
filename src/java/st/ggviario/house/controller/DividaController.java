package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javax.xml.soap.Node;
import java.net.URL;
import java.util.ResourceBundle;

public class DividaController implements Initializable {


    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton buttonNewDivida;
    private AnchorPane newDividaModalContent;
    private DividaNewController newDividaModalContentConttoller;
    private JFXDialogLayout dialogContent;
    private JFXDialog dialog;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttonNewDivida.setOnAction(actionEvent -> this.newDivida());
        this.dialogContent = new JFXDialogLayout();
        this.dialog = new JFXDialog( this.stackPane, this.dialogContent, JFXDialog.DialogTransition.CENTER );

    }

    private void newDivida() {
        this.loadNewDividaContent();
        dialogContent.setHeading( new Text("Nova divida") );
        dialogContent.setBody( this.newDividaModalContent );
        this.dialog.show();
    }

    private void loadNewDividaContent() {
        try{
            if( this.newDividaModalContent == null ){
                FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/divida_new.fxml" ) );
                this.newDividaModalContent = loader.load();
                this.newDividaModalContentConttoller = loader.getController();
            }
        } catch ( Exception ex ){
            ex.printStackTrace();
        }

    }
}
