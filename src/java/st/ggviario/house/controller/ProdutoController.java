package st.ggviario.house.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.model.ContentPage;
import st.ggviario.house.model.Producto;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.*;

public class ProdutoController extends TableController<Producto> implements Page, Initializable, ContentPage{

    private HomeController homeController;

    private PostgresSQL postgresSQL;
    private LinkedList<Categoria> listaCategoria;

    public ProdutoController(){
        this.postgresSQL = new PostgresSQLSingleton().getPostgresSQL();
    }


    @FXML
    private TableView<Producto> tableViewProduto;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton buttonNewProduto;

    @FXML
    private TableColumn<Producto, String> columnProdutoNome;

    @FXML
    private TableColumn<Producto, String> columnProdutoCodigo;

    @FXML
    private TableColumn<Producto, String> columnProdutoCategoria;

    @FXML
    private TableColumn<Producto, Number> columnProdutoStock;

    @FXML
    private TableColumn<Producto, Number> columnProdutoCusto;

    @FXML
    private TableColumn<Producto, Number> columnProdutoProducao;

    @FXML
    private TableColumn<Producto, Number> columnProdutoVenda;

    @FXML
    private TableColumn<Producto, Number> columnProdutoCompra;



    private List<Producto> produtoList = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        factoryColumns();
        this.loadProdutos();

        this.listaCategoria = new LinkedList<>();
        this.loadCategorias();
        this.buttonNewProduto.setOnAction(actionEvent -> this.newProduct() );

    }


    private void loadCategorias() {



    }

    private void newProduct() {
        try{
            FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/produto_new.fxml"));
            AnchorPane node = loader.load();
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading( new Text("Cadastro de novo produto"));
            content.setBody( node );

            JFXButton action;
            content.setActions(  action = new JFXButton("Cadastar") );


            JFXDialog dialog = new JFXDialog( this.stackPane, content, JFXDialog.DialogTransition.CENTER );
            dialog.show();

            action.setOnAction(actionEvent -> dialog.close());
            action.setStyle( "-fx-background-color: "+ MaterialColors.NAME_PRYMARY);
            action.setButtonType( JFXButton.ButtonType.RAISED );
            action.setPrefHeight( 40 );

        } catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    private void factoryColumns() {

        this.columnProdutoCodigo.setMinWidth( 40 );
        this.columnProdutoCodigo.setMaxWidth( 40 );
        this.columnProdutoNome.setMinWidth( 200 );
        this.columnProdutoCategoria.setMinWidth( 70 );

        this.tableViewProduto.setRowFactory(produtoTableView -> new TableRow<Producto>(){
            @Override
            protected void updateItem(Producto item, boolean empty) {
                super.updateItem(item, empty);
                if( item == null || empty ){
                    setItem( item );
                }else{
                    this.getStyleClass().add("row-normal");
                    this.setItem( item );
                }
            }
        });

        this.columnProdutoCodigo.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getProdutoCodigo()));
        this.columnProdutoNome.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getProdutoNome() ) );
        this.columnProdutoCategoria.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getProdutoCategoria().getCategoriaNome()));
        this.columnProdutoCusto.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getProdutoCusto() ) );
        this.columnProdutoProducao.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getProdutoProducao()));
        this.columnProdutoStock.setCellValueFactory( data ->  new SimpleDoubleProperty( data.getValue().getProdutoStock() ));
        this.columnProdutoVenda.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getProdutoVenda()));
        this.columnProdutoCompra.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getProdutoCompra()));

        this.columnProdutoCodigo.setCellFactory( cell -> getSimpleTextCell() );
        this.columnProdutoNome.setCellFactory( cell -> getSimpleTextCell());
        this.columnProdutoCategoria.setCellFactory( cell -> getSimpleTextCell());
        this.columnProdutoCusto.setCellFactory( cell -> getNumberCell() );
        this.columnProdutoProducao.setCellFactory( cell -> getNumberCell() );
        this.columnProdutoStock.setCellFactory( cell -> getNumberCell() );
        this.columnProdutoVenda.setCellFactory( cell -> getNumberCell() );
        this.columnProdutoCompra.setCellFactory( cell -> this.getNumberCell() );

    }


    public void loadProdutos() {

        Producto.ProdutoBuilder builder = new Producto.ProdutoBuilder();
        Categoria.CategoriaBuilder cat  = new Categoria.CategoriaBuilder();

        builder.categoria( cat.nome( "Aviario" ).build() );

            this.produtoList.add( builder.codigo("11").nome( "Ovos" ).build() );
            this.produtoList.add( builder.codigo("12").nome( "Ração" ).build() );
            this.produtoList.add( builder.codigo("121").nome( "Ração 104" ).build() );
            this.produtoList.add( builder.codigo("122").nome( "Ração 115" ).build() );
            this.produtoList.add( builder.codigo("123").nome( "Ração 120" ).build() );
            this.produtoList.add( builder.codigo("125").nome( "Ração 125" ).build() );
            this.produtoList.add( builder.codigo("13").nome( "Plastico" ).build() );

            this.produtoList.add( builder.codigo("14").nome( "Transporte" ).build() );

        builder.categoria( cat.nome("Diversos").build() );
            this.produtoList.add( builder.codigo("21").nome( "Cosinha" ).build() );


        ObservableList<Producto> observableListProduto = FXCollections.observableList(this.produtoList);
        this.tableViewProduto.setItems(observableListProduto);
    }

}
