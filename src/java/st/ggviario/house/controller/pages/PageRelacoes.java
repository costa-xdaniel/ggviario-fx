package st.ggviario.house.controller.pages;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class PageRelacoes extends PageTabsConttoler implements Initializable {

    @FXML private JFXTabPane tabPane;
    private Tab tabCliente = new Tab("CLIENTE" );
    private Tab tabFornecedor = new Tab("FORNECEDOR" );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.init();
        this.structure();
        this.defineEvents();
    }

    private void init(){

    }


    private void structure(){
        this.addTab(tabCliente, "/fxml/tabs/tab_entidade_cliente.fxml");
        this.addTab(tabFornecedor, "/fxml/tabs/tab_entidade_fornecedor.fxml");
    }

    private void defineEvents(){
        this.tabPane.getSelectionModel().selectedItemProperty().addListener((observableValue, oldTab, newTab) -> {
        });
    }

    @Override
    JFXTabPane getTabPane() {
        return this.tabPane;
    }
}
