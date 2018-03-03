package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class PageProducao implements Page, Initializable {

    @FXML private JFXTreeTableView<?> tableProducao;
    @FXML private AnchorPane fabNewPorducaoArea;
    @FXML  private JFXButton fabNewPorducaoButtom;
    @FXML  private MaterialDesignIconView fabNewPorducaoIcon;
    @FXML  private JFXListView<?> listViewCategoria;
    @FXML private AnchorPane fabNewCategoriaArea;
    @FXML  private JFXButton fabNewCategoriaButton;
    @FXML private MaterialDesignIconView fabNewCategoriaIcon;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
