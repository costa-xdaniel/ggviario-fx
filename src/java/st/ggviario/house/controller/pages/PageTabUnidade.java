package st.ggviario.house.controller.pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modals.ModalNovaUnidade;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class PageTabUnidade extends RowsController <PageTabUnidade.UnidaModelView> implements PageTab, Initializable{

    @FXML
    private JFXTreeTableView< UnidaModelView > treeTableViewUnidade;

    @FXML
    private AnchorPane fabArea;

    @FXML
    private JFXButton fabButton;

    @FXML
    private MaterialDesignIconView fabIcon;

    private ModalNovaUnidade modalNovaUnidade;
    private StackPane rootPage;
    private List< UnidaModelView > unidaModelViews = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
        this.structure();
        this.defineEvets();
        this.loadDataUnidade();
        this.pushUnidade();
    }

    private void structure() {

    }

    private void defineEvets(){
        this.fabArea.setOnMouseClicked( envet -> onOpenModalNovaUnidade() );
        this.fabIcon.setOnMouseClicked( this.fabArea.getOnMouseClicked() );
        this.fabButton.setOnMouseClicked( this.fabArea.getOnMouseClicked() );
    }

    private void loadDataUnidade( ){
    }

    private void pushUnidade(){
        this.push( this.unidaModelViews, this.treeTableViewUnidade );
    }


    private void onOpenModalNovaUnidade(){
        this.loadModalNovaUnidade();
        this.modalNovaUnidade.openModal();
    }

    private void loadModalNovaUnidade( ){
        if( this.modalNovaUnidade == null ){
            this.modalNovaUnidade = ModalNovaUnidade.load( this.rootPage );
            this.modalNovaUnidade.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ){
                    this.loadDataUnidade();
                    this.pushUnidade();
                }
            });
        }
    }

    class UnidaModelView extends RecursiveTreeObject < UnidaModelView > {
    }
}
