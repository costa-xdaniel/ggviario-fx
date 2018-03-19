package st.ggviario.house.control.drawers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.includs.IncludProdutoInformation;
import st.ggviario.house.control.includs.IncludProdutoLocalProducao;
import st.ggviario.house.control.includs.IncludProdutoPrecos;
import st.ggviario.house.model.LocalProducao;
import st.ggviario.house.model.Preco;
import st.ggviario.house.model.Produto;
import st.jigahd.support.sql.lib.SQLResource;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DrawerProduto implements Initializable {


    public static DrawerProduto newInstance( JFXDrawer drawerView ) {
        ControllerLoader< BorderPane, DrawerProduto> loader = new ControllerLoader<>("/fxml/drawer/drawer_produto.fxml");
        DrawerProduto drawer = loader.getController();
        drawer.drawer = drawerView;
        drawer.structureLayout();
        drawer.defineEvents();
        drawer.moneyFormatter.setMinimumFractionDigits( 2 );
        drawer.moneyFormatter.setMaximumFractionDigits( 2 );
        drawer.moneyFormatter.setMinimumIntegerDigits( 1 );
        return drawer;
    }


    @FXML private AnchorPane root;
    @FXML private AnchorPane headerPane;
    @FXML private HBox headerPageIcon;
    @FXML private AnchorPane panelIconClose;
    @FXML private AnchorPane panelIconInformation;
    @FXML private AnchorPane panelListPrecos;
    @FXML private AnchorPane panelIconLocalProducao;
    @FXML private AnchorPane panelIconEdit;
    @FXML private AnchorPane panelProdutoIconDelete;
    @FXML private MaterialDesignIconView iconViewProdutoDelete;
    @FXML private Label labelHeaderTitle;
    @FXML private StackPane stackPaneContent;

    private JFXRippler ripplerClose;
    private JFXRippler ripplerInformation;
    private JFXRippler ripplerListaPrecos;
    private JFXRippler ripplerLocalProducao;
    private JFXRippler ripplerProdutoEdit;
    private JFXRippler ripplerProdutoDelete;

    private Produto produto;
    private NumberFormat moneyFormatter = NumberFormat.getInstance( Locale.FRANCE );
    private NumberFormat numeberFormat = NumberFormat.getInstance( Locale.FRANCE );
    private DateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );

    private JFXDrawer drawer;
    private IncludProdutoInformation includProdutoInformation;
    private IncludProdutoPrecos includProdutoPrecos;
    private IncludProdutoLocalProducao includProdutoLocalProducao;
    private OnChangeProdutoEstado onChangeProdutoEstado;

    private OnProdutoEdit onProdutoEdit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXDepthManager.pop( this.root );
    }

    private void structureLayout(){

        JFXDepthManager.pop( this.root );
        this.ripplerClose = new JFXRippler( panelIconClose );
        this.ripplerInformation = new JFXRippler( panelIconInformation );
        this.ripplerListaPrecos = new JFXRippler(panelListPrecos);
        this.ripplerLocalProducao = new JFXRippler( this.panelIconLocalProducao );
        this.ripplerProdutoEdit = new JFXRippler( this.panelIconEdit );
        this.ripplerProdutoDelete = new JFXRippler( this.panelProdutoIconDelete);

        this.ripplerClose.setStyle("-jfx-rippler-fill: md-red-500");
        this.ripplerInformation.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerListaPrecos.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerLocalProducao.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerProdutoEdit.setStyle("-jfx-rippler-fill: md-primary-color");

        this.headerPageIcon.getChildren().addAll(
            this.ripplerClose,
            this.ripplerInformation,
            this.ripplerListaPrecos,
            this.ripplerLocalProducao,
            this.ripplerProdutoEdit,
            this.ripplerProdutoDelete
        );

        this.includProdutoInformation = IncludProdutoInformation.newInstance();
        this.includProdutoPrecos = IncludProdutoPrecos.newInstance();
        this.includProdutoLocalProducao = IncludProdutoLocalProducao.newInstance();

        this.includProdutoInformation.getRoot().heightProperty().addListener((observableValue, number, t1) -> {
            this.stackPaneContent.setMinHeight(
                SQLResource.max(
                    includProdutoInformation.getRoot().getHeight(),
                    includProdutoPrecos.getRoot().getHeight(),
                    includProdutoLocalProducao.getRoot().getHeight()
                )
            );
        });

        this.stackPaneContent.getChildren().clear();
        this.stackPaneContent.getChildren().add( this.includProdutoInformation.getRoot() );
    }

    private void defineEvents(){
        this.panelIconClose.setOnMouseClicked(mouseEvent -> {
            this.drawer.close();
        });

        this.ripplerInformation.setOnMouseClicked(mouseEvent -> {
            onInformation();
        });

        this.ripplerListaPrecos.setOnMouseClicked(mouseEvent -> {
            this.onListaPrecos();
        });

        this.ripplerLocalProducao.setOnMouseClicked(mouseEvent -> {
            this.onListaLocalProducao();
        });

        this.ripplerProdutoEdit.setOnMouseClicked(mouseEvent -> {
            if (this.onProdutoEdit != null) this.onProdutoEdit.onProdutoEdit(this.produto);
        });

        this.ripplerProdutoDelete.setOnMouseClicked( mouseEvent -> {
            if( this.onChangeProdutoEstado != null ) this.onChangeProdutoEstado.onChangeProdutoEstado( this.produto );
        });
    }

    private void onInformation(){
        this.stackPaneContent.getChildren().clear();
        this.stackPaneContent.getChildren().add( this.includProdutoInformation.getRoot() );
    }

    private void onListaPrecos( ){
        this.stackPaneContent.getChildren().clear();
        this.stackPaneContent.getChildren().add( this.includProdutoPrecos.getRoot() );
    }

    private void onListaLocalProducao( ){
        this.stackPaneContent.getChildren().clear();
        this.stackPaneContent.getChildren().add( this.includProdutoLocalProducao.getRoot() );
    }


    public void setProduto(Produto produto) {
        this.produto = produto;
        this.includProdutoInformation.setProdudo( produto );
        this.includProdutoPrecos.setProduto( produto );
        this.includProdutoLocalProducao.setProduto( produto );
        this.labelHeaderTitle.setText( this.produto.getProdutoCodigo() );
        if( this.produto.getProdutoEstado() == Produto.ProdutoEstado.FECHADO ){
            this.iconViewProdutoDelete.setIcon( MaterialDesignIcon.BACKUP_RESTORE);
        } else {
            this.iconViewProdutoDelete.setIcon( MaterialDesignIcon.DELETE );
        }
    }

    public DrawerProduto setOnNovoPreco(OnNovoPreco onNovoPreco) {
        this.includProdutoPrecos.setOnNovoPreco( onNovoPreco );
        return this;
    }




    public DrawerProduto setOnPrecoDestroy( OnPrecoDestroy onPrecoDestroy) {
        this.includProdutoPrecos.setOnPrecoDestroy(onPrecoDestroy);
        return this;
    }

    public IncludProdutoPrecos setOnEditarPreco(OnEditarPreco onEditarPreco) {
        return includProdutoPrecos.setOnEditarPreco(onEditarPreco);
    }

    public DrawerProduto setOnChangeProdutoEstado(OnChangeProdutoEstado onChangeProdutoEstado) {
        this.onChangeProdutoEstado = onChangeProdutoEstado;
        return this;
    }

    public DrawerProduto setOnProdutoEdit(OnProdutoEdit onProdutoEdit) {
        this.onProdutoEdit = onProdutoEdit;
        return this;
    }

    public DrawerProduto setOnNovoLocalProducao(OnNovoLocalProducao onNovoLocalProducao) {
        this.includProdutoLocalProducao.setOnNovoLocalProducao(onNovoLocalProducao);
        return this;
    }

    public DrawerProduto onChangeLocalProducaoDisable(OnChangeLocalProducaoDisable onChangeLocalProducaoDisable) {
        this.includProdutoLocalProducao.onChangeLocalProducaoDisable(onChangeLocalProducaoDisable);
        return this;
    }

    public void notifyPrecoDestroy() {
        this.includProdutoPrecos.setProduto( this.produto );
    }

    public void notifyLocalProducao() {
        this.includProdutoLocalProducao.setProduto( this.produto );
    }

    public interface OnProdutoEdit {
        void onProdutoEdit( Produto produto  );
    }

    public interface OnNovoPreco{
        void onNovoPreco( Produto produto );
    }

    public interface OnPrecoDestroy{
        void onPrecoDestroy( Preco produto );
    }

    public interface OnEditarPreco{
        void onEditarPreco( Preco produto );
    }

    public interface OnNovoLocalProducao{
        void onNovoLocalProducao( Produto produto );
    }

    public interface OnChangeProdutoEstado{
        void onChangeProdutoEstado( Produto produto );
    }

    public interface OnChangeLocalProducaoDisable {
        void onChangeLocalProducaoDisable(LocalProducao localProducao );
    }


    public AnchorPane getRoot() {
        return root;
    }
}
