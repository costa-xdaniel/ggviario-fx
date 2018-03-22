package st.ggviario.house.control.pages;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class PageOperacoes extends PageTabsConttoler implements Initializable {

    @FXML private JFXTabPane tabPane;

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
        this.addTab( "DIVIDA", "/fxml/tabs/tab_vendadespesa_divida.fxml" );
        this.addTab( "VENDA", "/fxml/tabs/tab_vendadesvesa_venda.fxml" );
        this.addTab( "DESPESA", "/fxml/tabs/tab_vendadespesa_despesa.fxml" );
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
