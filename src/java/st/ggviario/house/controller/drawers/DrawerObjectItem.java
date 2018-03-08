package st.ggviario.house.controller.drawers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.pages.TableClontroller;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.model.Unidade;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class DrawerObjectItem extends TableClontroller<DrawerObjectItem.UnidadeCategoria> implements Initializable{


    public static DrawerObjectItem newInstance(JFXDrawer drawer ){
        ControllerLoader< AnchorPane, DrawerObjectItem> loader = new ControllerLoader<>("/fxml/drawer/drawer_objectitem.fxml");
        loader.getController().drawer = drawer;
        return loader.getController();
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane headerPane;
    @FXML private HBox headerPageIcon;
    @FXML private AnchorPane panelIconClose;
    @FXML private AnchorPane panelIconPlus;
    @FXML private AnchorPane panelIconEdit;
    @FXML private Label labelHeaderTitle;
    @FXML private JFXTreeTableView< UnidadeCategoria > tableUnidadeCategoria;
    @FXML private AnchorPane panelFullList;

    private JFXDrawer drawer;

    private JFXTreeTableColumn< UnidadeCategoria, String > columnCategoriaCodigo = new JFXTreeTableColumn<>( "COD" );
    private JFXTreeTableColumn< UnidadeCategoria, String > columnCategoriaNome = new JFXTreeTableColumn<>( "NOME" );
    private JFXTreeTableColumn< UnidadeCategoria, String > columnCategoriaSuper = new JFXTreeTableColumn<>( "SUPER" );
    private JFXTreeTableColumn< UnidadeCategoria, Date > columnCategoriaDataregisto = new JFXTreeTableColumn<>( "DATA" );
    private JFXTreeTableColumn< UnidadeCategoria, String > columnUnidadeCodigo = new JFXTreeTableColumn<>( "COD" );
    private JFXTreeTableColumn< UnidadeCategoria, String> columnUnidadeNome = new JFXTreeTableColumn<>( "NOME" );
    private JFXTreeTableColumn< UnidadeCategoria, Date> columnUnidadeDataRegegisto = new JFXTreeTableColumn<>( "DATA" );

    private JFXRippler ripplerClose;
    private JFXRippler ripplerAddItem;
    private JFXRippler ripplerEditIten;
    private JFXRippler ripplerFullList;


    private List< Unidade > unidadeList = new LinkedList<>();
    private List< Categoria > categoriaList = new LinkedList<>();
    private ObjectType objectType;

    private OnNovaUnidade onNovaUnidade;
    private OnNovaCategoria onNovaCategoria;
    private OnEditarUnidade onEditarUnidade;
    private OnEditarCategoria onEditarCategoria;
    private OnFullListUnidade onFullListUnidade;
    private OnFullListCategoria onFullListCategoria;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
    }

    private void structure() {
        this.ripplerClose = new JFXRippler( this.panelIconClose );
        this.ripplerFullList = new JFXRippler( this.panelFullList );
        this.ripplerAddItem = new JFXRippler( this.panelIconPlus );
        this.ripplerEditIten = new JFXRippler( this.panelIconEdit );

        this.ripplerClose.setStyle("-jfx-rippler-fill: md-red-500");
        this.ripplerFullList.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerAddItem.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerEditIten.setStyle("-jfx-rippler-fill: md-primary-color");

        this.headerPageIcon.getChildren().addAll( this.ripplerClose, this.ripplerFullList, this.ripplerAddItem, this.ripplerEditIten );

        this.columnCategoriaCodigo.setCellValueFactory( param -> param.getValue().getValue().categoriaCodigo );
        this.columnCategoriaNome.setCellValueFactory( param -> param.getValue().getValue().categoriaNome );
        this.columnCategoriaSuper.setCellValueFactory( param -> param.getValue().getValue().categoriaSuper );
        this.columnCategoriaDataregisto.setCellValueFactory( param -> param.getValue().getValue().categoriaDataregisto );

        this.columnUnidadeCodigo.setCellValueFactory( param -> param.getValue().getValue().unidadeCodigo );
        this.columnUnidadeNome.setCellValueFactory( param -> param.getValue().getValue().unidadeNome );
        this.columnUnidadeDataRegegisto.setCellValueFactory( param -> param.getValue().getValue().unidadeDataregisto );
        JFXDepthManager.pop( this.root );
        this.push( new LinkedList<>(), this.tableUnidadeCategoria );
    }

    public AnchorPane getRoot() {
        return root;
    }

    private void defineEvents(){
        this.ripplerClose.setOnMouseClicked(mouseEvent -> this.onCloseDrawer() );
        this.ripplerFullList.setOnMouseClicked( mouseEvent -> this.onFullList() );
        this.ripplerAddItem.setOnMouseClicked( mouseEvent ->  onAddItem() );
        this.ripplerEditIten.setOnMouseClicked( mouseEvent -> onEditItem() );
    }
    private void onCloseDrawer(){
        this.drawer.close();
    }

    private void onFullList(){
        if( this.objectType == ObjectType.CATEGORIA && this.onFullListCategoria != null )
            this.onFullListCategoria.onFullListCategoria( this.getCategoria() );
        else if( this.objectType == ObjectType.UNIDADE && this.onFullListUnidade != null )
            this.onFullListUnidade.onFulListUnidade( this.getUnidade() );
    }

    private void onAddItem(){
        if( this.objectType == ObjectType.UNIDADE && this.onNovaUnidade != null ) this.onNovaUnidade.onNovaUnidade();
        else if( this.objectType == ObjectType.CATEGORIA && this.onNovaCategoria != null ) this.onNovaCategoria.onNovaCategoria();
    }

    private void onEditItem() {

        if( this.objectType == ObjectType.UNIDADE && this.getUnidade() != null )
            this.onEditarUnidade.onEditarUnidade( this.getUnidade() );
        else if( this.objectType == ObjectType.CATEGORIA && this.getCategoria() != null )
            this.onEditarCategoria.onEditarCategoria( this.getCategoria() );
    }

    public Categoria getCategoria( ) {
        TreeItem<UnidadeCategoria> seleted = this.tableUnidadeCategoria.getSelectionModel().getSelectedItem();
        if( seleted == null ) return null;
        if( this.objectType == ObjectType.CATEGORIA ) return seleted.getValue().categoria;
        return null;
    }

    public Unidade getUnidade( ) {
        TreeItem<UnidadeCategoria> seleted = this.tableUnidadeCategoria.getSelectionModel().getSelectedItem();
        if( seleted == null ) return null;
        if( this.objectType == ObjectType.UNIDADE ) return seleted.getValue().unidade;
        return null;
    }

    public DrawerObjectItem setObjectType(ObjectType objectType) {
        this.objectType = objectType;
        this.labelHeaderTitle.setText( objectType.showName );
        if( objectType == ObjectType.CATEGORIA ){
            this.tableUnidadeCategoria.getRoot().getChildren().clear();
            this.tableUnidadeCategoria.getColumns().setAll(
                    this.columnCategoriaCodigo,
                    this.columnCategoriaNome,
                    this.columnCategoriaSuper,
                    this.columnCategoriaDataregisto
            );
            this.loadCategoria();
        } else if( objectType == ObjectType.UNIDADE ){
            this.tableUnidadeCategoria.getRoot().getChildren().clear();
            this.tableUnidadeCategoria.getColumns().setAll(
                    this.columnUnidadeCodigo,
                    this.columnUnidadeNome,
                    this.columnUnidadeDataRegegisto
            );
            this.loadUnidade();
        }
        return this;
    }

    public DrawerObjectItem setOnNovaUnidade(OnNovaUnidade onNovaUnidade) {
        this.onNovaUnidade = onNovaUnidade;
        return this;
    }

    public DrawerObjectItem setOnNovaCategoria(OnNovaCategoria onNovaCategoria) {
        this.onNovaCategoria = onNovaCategoria;
        return this;
    }

    public DrawerObjectItem setOnEditarUnidade(OnEditarUnidade onEditarUnidade) {
        this.onEditarUnidade = onEditarUnidade;
        return this;
    }

    public DrawerObjectItem setOnEditarCategoria(OnEditarCategoria onEditarCategoria) {
        this.onEditarCategoria = onEditarCategoria;
        return this;
    }

    public DrawerObjectItem setOnFullListUnidade(OnFullListUnidade onFullListUnidade) {
        this.onFullListUnidade = onFullListUnidade;
        return this;
    }

    public DrawerObjectItem setOnFullListCategoria(OnFullListCategoria onFullListCategoria) {
        this.onFullListCategoria = onFullListCategoria;
        return this;
    }

    synchronized public void loadCategoria( ){
        Thread thread = new Thread(() -> {
            Platform.runLater( ( ) ->{
                if( this.objectType == ObjectType.CATEGORIA ) {
                    this.tableUnidadeCategoria.getRoot().getChildren().clear();
                }
            });
            this.categoriaList.clear();
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();
            Categoria.CategoriaBuilder suberBuilder = new Categoria.CategoriaBuilder();
            sql.query( "ggviario.funct_load_categoria" )
                .withJsonb( ( String ) null )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            categoriaBuilder.load( row );
                            if( row.get( "categoria_super" )  != null )
                                categoriaBuilder.setCategoriaSuper(suberBuilder.load(row.asMapJsonn("categoria_super")).build());
                            Categoria next = categoriaBuilder.build();
                            this.categoriaList.add( next );
                            if( this.objectType == ObjectType.CATEGORIA )
                                this.tableUnidadeCategoria.getRoot().getChildren().add( new TreeItem<>( new UnidadeCategoria( next ) ) );
                        });
                    })
            ;

        });
        thread.start();
    }
    synchronized public void loadUnidade( ){
        Thread thread = new Thread(() -> {
            Platform.runLater( ( ) ->{
                if( this.objectType == ObjectType.UNIDADE ) {
                    this.tableUnidadeCategoria.getRoot().getChildren().clear();
                }
            } );
            this.unidadeList.clear();
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
            sql.query( "ggviario.funct_load_unidade" )
                .withJsonb( ( String ) null )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            Unidade next = unidadeBuilder.load(row).build();
                            this.unidadeList.add( next );
                            if( this.objectType == ObjectType.UNIDADE )
                                this.tableUnidadeCategoria.getRoot().getChildren().add( new TreeItem<>( new UnidadeCategoria( next ) ) );
                        });
                    })
            ;

        });
        thread.start();
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public List<Unidade> getUnidadeList() {
        return unidadeList;
    }

    public enum ObjectType {
        CATEGORIA ( "Categoria" ),
        UNIDADE( "Unidade" );
        private String showName;

        ObjectType(String showName) {
            this.showName = showName;
        }
    }

    class UnidadeCategoria extends RecursiveTreeObject< UnidadeCategoria > {
        private StringProperty unidadeNome;
        private StringProperty unidadeCodigo;
        private ObjectProperty< Date > unidadeDataregisto;

        private StringProperty categoriaNome;
        private StringProperty categoriaCodigo;
        private StringProperty categoriaSuper;
        private ObjectProperty< Date > categoriaDataregisto;
        private Categoria categoria;
        private Unidade unidade;

        UnidadeCategoria(Categoria categoria) {
            this.categoriaNome = new SimpleStringProperty( categoria.getCategoriaNome() );
            this.categoriaCodigo = new SimpleStringProperty( categoria.getCategoriaCodigo() );
            this.categoriaSuper = new SimpleStringProperty( categoria.getCategoriaSuper() != null ? categoria.getCategoriaSuper().getCategoriaNome() : null );
            this.categoriaDataregisto = new SimpleObjectProperty<>( categoria.getCategoriaDataRegisto() );
            this.categoria = categoria;
        }

        UnidadeCategoria(Unidade unidade) {
            this.unidadeNome = new SimpleStringProperty( unidade.getUnidadeNome() );
            this.unidadeCodigo = new SimpleStringProperty( unidade.getUnidadeCodigo() );
            this.unidadeDataregisto = new SimpleObjectProperty<>( unidade.getUnidadeDataregisto() );
            this.unidade = unidade;
        }
    }


    public interface OnFullListUnidade{
        void onFulListUnidade( Unidade unidade );
    }

    public interface OnFullListCategoria{
        void onFullListCategoria( Categoria categoria );
    }

    public interface OnNovaUnidade{

        void onNovaUnidade();
    }
    public interface OnNovaCategoria{
        void onNovaCategoria();

    }
    public interface OnEditarUnidade{
        void onEditarUnidade( Unidade unidade );
    }

    public interface OnEditarCategoria{
        void onEditarCategoria( Categoria categoria );
    }
}
