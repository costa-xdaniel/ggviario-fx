package st.ggviario.house.control.pages;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class PageVendaDespesa extends PageTabsConttoler implements Initializable {

    @FXML private JFXTabPane tabPane;
    private Tab tabDivida = new Tab("DIVIDA" );
    private Tab tabVenda = new Tab("VENDA" );
    private Tab tabDespesa = new Tab("DESPESA" );

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        super.initialize( url, resourceBundle );
        this.init();
        this.structure();
        this.defineEvents();
    }

    private void init(){

    }


    private void structure(){
        this.addTab( tabDivida, "/fxml/tabs/tab_vendadespesa_divida.fxml" );
        this.addTab( tabVenda, "/fxml/tabs/tab_vendadesvesa_venda.fxml" );
        this.addTab( tabDespesa, "/fxml/tabs/tab_vendadespesa_despesa.fxml" );
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
