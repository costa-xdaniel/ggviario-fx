package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class PageProduto extends PageTabsConttoler implements Initializable {

    @FXML private JFXTabPane tabPane;
    private Tab tabProduto = new Tab("PRODUTO" );
    private Tab tabUnidade = new Tab("UNIDADE" );
    private Tab tabCategoria = new Tab("CATEGORIA" );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.init();
        this.structure();
        this.defineEvents();
    }

    private void init(){
    }

    private void structure(){
        this.addTab( tabProduto, "/fxml/page/page_tab_produto.fxml");
        this.addTab( tabUnidade, "/fxml/page/page_tab_produto_unidade.fxml" );
        this.addTab( tabCategoria, "/fxml/page/page_tab_produto_categoria.fxml" );
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
