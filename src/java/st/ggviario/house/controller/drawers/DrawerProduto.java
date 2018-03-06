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
import st.ggviario.house.model.Produto;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DrawerProduto implements Initializable {


    private Produto produto;
    private JFXRippler ripplerNewUnit;
    private JFXRippler ripplerListUnits;
    private JFXRippler ripplerClose;

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
    @FXML private StackPane stackPaneContent;
    @FXML private HBox headerPageIcon;
    @FXML private AnchorPane panelIconClose;
    @FXML private AnchorPane panelIconInformation;
    @FXML private AnchorPane panelIconNewPreco;
    @FXML private AnchorPane panelIconListUnits;
    @FXML private Label labelHeaderTitle;

    private JFXRippler ripplerInformation;

    private NumberFormat moneyFormatter = NumberFormat.getInstance( Locale.FRANCE );
    private NumberFormat numeberFormat = NumberFormat.getInstance( Locale.FRANCE );
    private DateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );

    private JFXDrawer drawer;
    private IncludProdutoInformation includProdutoInformation;
    private OnNovoPreco onNovoPreco;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXDepthManager.pop( this.root );
    }

    private void structureLayout(){
        JFXDepthManager.pop( this.root );
        this.ripplerClose = new JFXRippler( panelIconClose );
        this.ripplerInformation = new JFXRippler( panelIconInformation );
        this.ripplerNewUnit = new JFXRippler(panelIconNewPreco);
        this.ripplerListUnits = new JFXRippler( panelIconListUnits );
        this.ripplerClose.setStyle("-jfx-rippler-fill: md-red-500");
        this.ripplerInformation.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerNewUnit.setStyle("-jfx-rippler-fill: md-primary-color");
        this.ripplerListUnits.setStyle("-jfx-rippler-fill: md-primary-color");

        this.headerPageIcon.getChildren().addAll(
            this.ripplerClose,
            this.ripplerInformation,
            this.ripplerNewUnit,
            this.ripplerListUnits
        );

        this.loadIncludsInformacao();
        this.stackPaneContent.getChildren().clear();
        this.stackPaneContent.getChildren().add( this.includProdutoInformation.getRoot() );


    }

    private void defineEvents(){
        this.panelIconClose.setOnMouseClicked(mouseEvent -> {
            this.drawer.close();
        });
        this.panelIconNewPreco.setOnMouseClicked(event -> { if( this.onNovoPreco != null ) onNovoPreco.onNovoPreco( this.produto ); } );
    }


    private void loadIncludsInformacao(){
        if (this.includProdutoInformation == null) {
            this.includProdutoInformation = IncludProdutoInformation.newInstance();
        }
    }


    public DrawerProduto setOnNovoPreco(OnNovoPreco onNovoPreco) {
        this.onNovoPreco = onNovoPreco;
        return this;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    public interface OnNovoPreco{
        void onNovoPreco( Produto produto );
    }

    public AnchorPane getRoot() {
        return root;
    }
}
