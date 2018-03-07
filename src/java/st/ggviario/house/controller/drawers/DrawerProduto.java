package st.ggviario.house.controller.drawers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.includs.IncludProdutoInformation;
import st.ggviario.house.controller.includs.IncludProdutoUnidades;
import st.ggviario.house.model.Produto;
import st.jigahd.support.sql.lib.SQLResource;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DrawerProduto implements Initializable {


    public static DrawerProduto newInstance(JFXDrawer drawerView  ) {
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
    @FXML private AnchorPane panelListUnidades;
    @FXML private AnchorPane panelIconListaPreco;
    @FXML private AnchorPane panelIconEdit;
    @FXML private AnchorPane panelIconNovoPreco;
    @FXML private Label labelHeaderTitle;
    @FXML private StackPane stackPaneContent;

    private JFXRippler ripplerClose;
    private JFXRippler ripplerInformation;
    private JFXRippler ripplerListUnidades;
    private JFXRippler ripplerListPrecos;
    private JFXRippler ripplerProdutoEdit;
    private JFXRippler ripplerNovoPreco;

    private Produto produto;
    private NumberFormat moneyFormatter = NumberFormat.getInstance( Locale.FRANCE );
    private NumberFormat numeberFormat = NumberFormat.getInstance( Locale.FRANCE );
    private DateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );

    private JFXDrawer drawer;
    private IncludProdutoInformation includProdutoInformation;
    private IncludProdutoUnidades includProdutoUnidades;

    private OnNovoPreco onNovoPreco;
    private OnListPrecos onListaPrecos;
    private OnProdutoEdit onProdutoEdit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXDepthManager.pop( this.root );
    }

    private void structureLayout(){

        JFXDepthManager.pop( this.root );
        this.ripplerClose = new JFXRippler( panelIconClose );
        this.ripplerInformation = new JFXRippler( panelIconInformation );
        this.ripplerListUnidades = new JFXRippler( panelListUnidades );
        this.ripplerListPrecos = new JFXRippler( this.panelIconListaPreco );
        this.ripplerProdutoEdit = new JFXRippler( this.panelIconEdit );
        this.ripplerNovoPreco = new JFXRippler( panelIconNovoPreco );

        this.ripplerClose.setStyle("-jfx-rippler-fill: md-red-500");
        this.ripplerInformation.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerListUnidades.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerListPrecos.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerProdutoEdit.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerNovoPreco.setStyle("-jfx-rippler-fill: md-primary-color");

        this.headerPageIcon.getChildren().addAll(
            this.ripplerClose,
            this.ripplerInformation,
            this.ripplerListUnidades,
            this.ripplerListPrecos,
            this.ripplerProdutoEdit,
            this.ripplerNovoPreco
        );

        this.includProdutoInformation = IncludProdutoInformation.newInstance();
        this.includProdutoUnidades = IncludProdutoUnidades.newInstance();
        this.includProdutoInformation.getRoot().heightProperty().addListener((observableValue, number, t1) -> {
            this.stackPaneContent.setMinHeight(
                    SQLResource.max(
                            includProdutoInformation.getRoot().getHeight(),
                            includProdutoUnidades.getRoot().getHeight()
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

        this.ripplerListUnidades.setOnMouseClicked(mouseEvent -> {
            this.onListaUnidades();
        });

        this.ripplerListPrecos.setOnMouseClicked(mouseEvent -> {
            if( this.onListaPrecos != null ) this.onListaPrecos.onListPreco( this.produto );
        });

        this.ripplerProdutoEdit.setOnMouseClicked(mouseEvent -> {
            if (this.onProdutoEdit != null) this.onProdutoEdit.onProdutoEdit(this.produto);
        });

        this.ripplerNovoPreco.setOnMouseClicked(event -> { if( this.onNovoPreco != null ) onNovoPreco.onNovoPreco( this.produto ); } );
    }

    private void onInformation(){
        this.stackPaneContent.getChildren().clear();
        this.stackPaneContent.getChildren().add( this.includProdutoInformation.getRoot() );
    }

    private void onListaUnidades( ){
        this.stackPaneContent.getChildren().clear();
        this.stackPaneContent.getChildren().add( this.includProdutoUnidades.getRoot() );
    }


    public void setProduto(Produto produto) {
        this.produto = produto;
        this.includProdutoInformation.setProdudo( produto );
        this.includProdutoUnidades.setProduto( produto );
        this.labelHeaderTitle.setText( this.produto.getProdutoCodigo() );
        this.onInformation();
    }

    public DrawerProduto setOnNovoPreco(OnNovoPreco onNovoPreco) {
        this.onNovoPreco = onNovoPreco;
        return this;
    }

    public DrawerProduto setOnListaPrecos(OnListPrecos onListaPrecos) {
        this.onListaPrecos = onListaPrecos;
        return this;
    }

    public DrawerProduto setOnProdutoEdit(OnProdutoEdit onProdutoEdit) {
        this.onProdutoEdit = onProdutoEdit;
        return this;
    }

    public interface OnProdutoEdit {
        void onProdutoEdit( Produto produto  );
    }

    public interface OnListPrecos{
        void onListPreco( Produto produto );
    }

    public interface OnNovoPreco{
        void onNovoPreco( Produto produto );
    }


    public AnchorPane getRoot() {
        return root;
    }
}