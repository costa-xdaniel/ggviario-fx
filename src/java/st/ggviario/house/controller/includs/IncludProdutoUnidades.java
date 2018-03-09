package st.ggviario.house.controller.includs;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableRow;
import javafx.scene.layout.AnchorPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.pages.TableClontroller;
import st.ggviario.house.model.Produto;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class IncludProdutoUnidades extends TableClontroller<IncludProdutoUnidades.ProdutoUnidadeModelView > implements Initializable {

    public static IncludProdutoUnidades newInstance( ){
        ControllerLoader< AnchorPane, IncludProdutoUnidades > loader = new ControllerLoader<>("/fxml/includs/includ_produto_unidades.fxml" );
        return loader.getController();
    }

    @FXML private AnchorPane root;
    @FXML private JFXTreeTableView< ProdutoUnidadeModelView > tableProdutoUnidades;
    private JFXTreeTableColumn < ProdutoUnidadeModelView, String  > columnUnidadeNome = new JFXTreeTableColumn<>( "UNIDADE" );
    private JFXTreeTableColumn < ProdutoUnidadeModelView, String  > columnUnidadeCodigo = new JFXTreeTableColumn<>( "COD" );
    private JFXTreeTableColumn < ProdutoUnidadeModelView, Number  > columnPrecoQuantidade = new JFXTreeTableColumn<>( "QT." );
    private JFXTreeTableColumn < ProdutoUnidadeModelView, Number  > columnPrecoCustoUnidade = new JFXTreeTableColumn<>("PREÇO");
    private JFXTreeTableColumn < ProdutoUnidadeModelView, IconsActions > columnIconsAction = new JFXTreeTableColumn<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
    }

    private void structure(){
        this.columnUnidadeNome.setCellValueFactory( param -> param.getValue().getValue().unidadeNome );
        this.columnUnidadeCodigo.setCellValueFactory( param -> param.getValue().getValue().unidadeCodigo );
        this.columnPrecoQuantidade.setCellValueFactory( param -> param.getValue().getValue().quantidadeProduto );
        this.columnPrecoCustoUnidade.setCellValueFactory( param -> param.getValue().getValue().custoUnidade );

        IconsActions iconsActionsFatory = () -> {
            Node delete = this.newIconViewDestroy( MaterialDesignIcon.DELETE );
            delete.setOnMouseClicked( mouseEvent -> onDelectePreco() );
            return this.newIconCellContainer( delete );
        };
        ObjectProperty< IconsActions > property = new SimpleObjectProperty<>( iconsActionsFatory );
        this.columnIconsAction.setCellValueFactory( param -> property );
        this.columnIconsAction.setCellFactory( this.cellIconsView() );
        this.useAsIconsColumn( this.columnIconsAction, 1 );

        this.tableProdutoUnidades.getStyleClass().add( "produto-unidade-preco" );

        this.tableProdutoUnidades.getColumns().setAll(
                this.columnUnidadeNome,
                this.columnUnidadeCodigo,
                this.columnPrecoCustoUnidade,
                this.columnPrecoQuantidade,
                this.columnIconsAction
        );

        this.tableProdutoUnidades.setRowFactory(produtoUnidadeModelViewTreeTableView -> new TreeTableRow<ProdutoUnidadeModelView>() {
            @Override
            protected void updateItem(ProdutoUnidadeModelView item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    if (item.base && !this.getStyleClass().contains( "base" ) ) {
                        this.getStyleClass().add("base");
                    } else if( !item.base ){
                        this.getStyleClass().remove( "base" );
                    }
                }
                this.setItem(item);
            }
        });
        this.push( new LinkedList<>(), this.tableProdutoUnidades );
    }

    private void onDelectePreco() {

    }

    public void setProduto (Produto produto ){
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            this.tableProdutoUnidades.getRoot().getChildren().clear();
            sql.query( "ggviario.funct_load_produto_unidades" )
                .with( produto.getProdutoId() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater( ( ) -> {
                            this.tableProdutoUnidades.getRoot().getChildren().add( new TreeItem<>( new ProdutoUnidadeModelView( row ) ));
                        });
                    })
            ;

        });
        thread.start();
    }

    public AnchorPane getRoot() {
        return root;
    }

    class ProdutoUnidadeModelView extends RecursiveTreeObject < ProdutoUnidadeModelView> {

        private StringProperty unidadeNome;
        private StringProperty unidadeCodigo;
        private ObjectProperty< Number > quantidadeProduto;
        private ObjectProperty< Number > custoUnidade;
        private boolean base;

        private ProdutoUnidadeModelView(SQLRow row){
            this.unidadeNome = new SimpleStringProperty( row.asString( "unidade_nome"  ) );
            this.unidadeCodigo = new SimpleStringProperty( row.asString( "unidade_codigo" ) );
            this.quantidadeProduto = new SimpleObjectProperty<>( row.asDouble( "preco_quantidadeproduto" ) );
            this.custoUnidade = new SimpleObjectProperty<>( row.asDouble( "preco_custounidade" ) );
            this.base = row.asBoolean( "preco_base" );
        }

        @Override
        public String toString() {
            return "ProdutoUnidadeModelView{" +
                    "unidadeNome=" + unidadeNome +
                    ", unidadeCodigo=" + unidadeCodigo +
                    ", quantidadeProduto=" + quantidadeProduto +
                    ", custoUnidade=" + custoUnidade +
                    ", base=" + base +
                    '}';
        }
    }

}
