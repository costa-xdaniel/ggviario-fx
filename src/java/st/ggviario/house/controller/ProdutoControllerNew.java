package st.ggviario.house.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import st.ggviario.house.singleton.PostgresSOLSingleton;
import st.ggviario.house.model.Categoria;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ProdutoControllerNew implements Initializable{

    @FXML
    private JFXComboBox<Categoria> comboxNewProdutoCategoria;
    private List<Categoria> listaCategoria = new LinkedList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loadCategoria();
    }

    private void loadCategoria() {

        this.listaCategoria.clear();

        PostgresSQL postgresSQL = PostgresSOLSingleton.loadPostgresSQL();
        Categoria.CategoriaBuilder builder = new Categoria.CategoriaBuilder();
        postgresSQL.query( "ggviario.funct_load_categoria" )
                .withOther( null )
                .callFunctionTable()
                .setOnResultQuery(row -> listaCategoria.add(
                        builder
                                .nome( row.asString("categoria_nome") )
                                .id( row.asUUID( "categoria_id" ) )
                                .build()
                ));

        comboxNewProdutoCategoria.setItems(FXCollections.observableList( this.listaCategoria ) );
        comboxNewProdutoCategoria.setCellFactory(new Callback<ListView<Categoria>, ListCell<Categoria>>() {
            @Override
            public ListCell<Categoria> call(ListView<Categoria> categoriaListView) {
                return new ListCell< Categoria> (){
                    @Override
                    protected void updateItem(Categoria item, boolean empty) {
                        super.updateItem(item, empty);
                        if( item != null ){
                            setText( item.getCategoriaNome() );
                        }else {
                            setText( null );
                        }
                        setItem( item );
                    }
                };
            }
        });
    }
}
