package st.ggviario.house.controller.pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modals.ModalNovaCategoria;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class PageTabCategoria implements PageTab, Initializable{


    @FXML private AnchorPane root;
    @FXML private JFXTreeTableView<?> treeTableViewUnidade;
    @FXML private AnchorPane fabArea;
    @FXML private JFXButton fabButton;
    @FXML private MaterialDesignIconView fabIcon;

    private ModalNovaCategoria modalNovaCategoria;
    private StackPane rootPage;
    private List< Categoria > categoriaList = new LinkedList<>();

    @Override
    public void onSetRootPage(StackPane rootPage) {
        this.rootPage = rootPage;
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
        this.loadDataCategoria();
    }

    private void structure(){

    }

    private void defineEvents(){
        this.fabButton.setOnAction( actionEvent -> this.onOpenModalNovaCategoria() );
        this.fabArea.setOnMouseClicked(mouseEvent -> this.onOpenModalNovaCategoria());
        this.fabIcon.setOnMouseClicked(mouseEvent -> this.onOpenModalNovaCategoria());
    }

    private void loadDataCategoria(){
        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        this.categoriaList.clear();
        Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();
        Categoria.CategoriaBuilder categoriaBuilderSuper = new Categoria.CategoriaBuilder();
        sql.query( "funct_load_categoria" )
            .withJsonb( ( String ) null )
            .callFunctionTable()
                .onResultQuery( row -> {
                    categoriaBuilder.load( row );
                    if( row.get( "categoria_super" ) != null ){
                        categoriaBuilderSuper.load( row.asMapJsonn("categoria_super" ) );
                        categoriaBuilder.setCategoriaSuper( categoriaBuilderSuper.build() );
                    } else {
                        categoriaBuilder.setCategoriaSuper( null );
                    }
                    this.categoriaList.add( categoriaBuilder.build() );
                })
        ;
    }


    private void onOpenModalNovaCategoria() {
        this.loadModalNovaCategoria();
        this.modalNovaCategoria.pushCategoriaSupers( this.categoriaList );
        this.modalNovaCategoria.openModal();
    }

    private void loadModalNovaCategoria(){
        if( this.modalNovaCategoria == null ){
            this.modalNovaCategoria = ModalNovaCategoria.load( this.rootPage );
        }
    }

}
