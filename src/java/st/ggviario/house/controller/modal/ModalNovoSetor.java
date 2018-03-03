package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.model.Categoria;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalNovoSetor extends AbstractModal< Categoria > {

    public static ModalNovoSetor load(StackPane stackPane){
        ControllerLoader< AnchorPane, ModalNovoSetor > loader = new ControllerLoader<AnchorPane, ModalNovoSetor>("/fxml/modal/modal_novo_setor.fxml");
        ModalNovoSetor novoSetor = loader.getController();
        novoSetor.createDialogModal( stackPane );
        return novoSetor;
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconArea;
    @FXML private JFXTextField textFieldCategoriaNome;
    @FXML private JFXComboBox< Categoria > comboxCategoriaSuper;
    @FXML private Label labelCategoriaNivel;
    @FXML private JFXButton buttomPayNow;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize( location, resources );
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
        return this.iconArea;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    private void loadCategoria(){

    }
}
