package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import st.ggviario.house.model.Categoria;

public class ModalNovaCategoria extends AbstractModal<Categoria > {

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconArea;
    @FXML private JFXTextField textFieldCategoriaNome;
    @FXML private JFXComboBox<?> comboxCategoriaSuper;
    @FXML private Label labelCategoriaNivel;
    @FXML private JFXButton buttomPayNow;


    @Override
    Region getContentRoot() {
        return this.root;
    }

    @Override
    Label getModalTitleView() {
        return this.modalTitle;
    }
}
