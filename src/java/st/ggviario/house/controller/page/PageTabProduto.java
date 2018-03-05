package st.ggviario.house.controller.page;

import com.google.gson.JsonElement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modal.ModalNovoProduto;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class PageTabProduto extends RowsController<PageTabProduto.ProdutoModelView > implements PageTab, Initializable{

    @FXML private JFXTreeTableView< ProdutoModelView > treeTableViewUnidade;
    @FXML private AnchorPane fabArea;
    @FXML private JFXButton fabButton;
    @FXML private MaterialDesignIconView fabIcon;

    private ModalNovoProduto modalNovoProduto;
    private StackPane rootPage;
    private List< ProdutoModelView > produtoModelViewList = new LinkedList<>();
    private List< Categoria > categoriaList = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
        this.loadDataCategoria();
        this.loadDatProduto();
        this.pushProduto();
    }

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    private void structure(){
    }

    private void defineEvents() {
        this.fabArea.setOnMouseClicked(mouseEvent -> this.onOpenModalNOvoProduto()  );
        this.fabButton.setOnMouseClicked( fabArea.getOnMouseClicked() );
        this.fabIcon.setOnMouseClicked( fabArea.getOnMouseClicked() );
    }

    private void loadDatProduto() {
        this.categoriaList.clear();
        Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        sql.query( "ggviario.funct_load_categoria_simple" )
            .withJsonb( (JsonElement ) null )
            .callFunctionTable()
                .onResultQuery( row -> {
                    categoriaBuilder.load( row );
                    this.categoriaList.add( categoriaBuilder.build() );
                });
        ;
    }

    private void pushProduto(){
    }

    private void loadDataCategoria(){
    }

    private void onOpenModalNOvoProduto(){
        this.loadModalNovoProduto();
        this.modalNovoProduto.setCategoriaList( this.categoriaList );
        this.modalNovoProduto.openModal();
    }

    private void loadModalNovoProduto(){
        if (this.modalNovoProduto == null) {
            this.modalNovoProduto = ModalNovoProduto.load( this.rootPage );
            this.modalNovoProduto.setOnModalResult(modalResult -> {
                if (modalResult.isSuccess()) {
                    this.loadDatProduto();
                    this.pushProduto();
                }
            });
        }
    }

    class ProdutoModelView extends RecursiveTreeObject< ProdutoModelView >{

    }
}
