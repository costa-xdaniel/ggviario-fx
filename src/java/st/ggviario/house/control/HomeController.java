package st.ggviario.house.control;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import st.ggviario.house.control.drawers.DrawerMenus;
import st.ggviario.house.control.pages.Page;
import st.jigahd.support.sql.lib.SQLText;

import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    @FXML private AnchorPane root;
    @FXML private HBox decoratorArea;
    @FXML StackPane closePanel;
    @FXML private StackPane rootPage;
    @FXML private AnchorPane iconAreaMenu;
    @FXML private AnchorPane drawerArea;
    @FXML private HBox vboxMenuArea;
    @FXML  private JFXTextField textFieldSearch;
    @FXML private JFXDrawer drawer;

    private Node currentDocumentoPage;
    private Page currentPage;
    private Stage primaryStage;
    private Scene scene;

    private JFXRippler rippler;
    private JFXRippler ripplerCloseWidows;
    private AnchorPane draweView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.structureCloseWindows();
        this.defineEvents();

        ControllerLoader< AnchorPane, DrawerMenus> loader = new ControllerLoader<>("/fxml/drawer/drawer.fxml");
        DrawerMenus controller = loader.getController();
        controller.setOnClickMenuIcon( this::closeDrawer );
        controller.setHomeController( this );
        this.draweView = loader.getNodeView();
        this.drawer.setSidePane(draweView);
        StackPane.setAlignment(draweView, Pos.TOP_LEFT );

        this.rippler = new JFXRippler( this.iconAreaMenu);
        this.rippler.setOnMouseClicked(event -> openDrawer(  ) );
        this.vboxMenuArea.getChildren().add( 0, this.rippler );

        this.root.getChildren().remove( this.drawer );
        this.openDrawer();
    }

    private void structureCloseWindows(){
        this.ripplerCloseWidows = new JFXRippler( this.closePanel );
        this.decoratorArea.getChildren().add( this.ripplerCloseWidows);
        AnchorPane.setTopAnchor( this.ripplerCloseWidows, 0.0 );
        AnchorPane.setRightAnchor( this.ripplerCloseWidows, 0.0 );
        this.ripplerCloseWidows.getStyleClass().add( "rippler" );
        this.ripplerCloseWidows.getStyleClass().add( "close" );
    }

    private void defineEvents( ){
        this.root.heightProperty().addListener((observable, oldValue, newValue) -> {
            draweView.setPrefHeight( newValue.doubleValue() );
        });

        this.textFieldSearch.setOnKeyReleased(event -> {
            if( this.currentPage != null ) this.currentPage.onSearch( event, SQLText.normalize( this.textFieldSearch.getText().toLowerCase() ) );
        });

        this.drawer.setOnDrawerClosed(event -> {
            if( this.root.getChildren().contains( this.drawer ) ){
                this.root.getChildren().remove( this.drawer );
            }
        });
//        this.ripplerCloseWidows.setOnMouseClicked(event -> {
//            System.exit( 0 );
//        });
    }

    private void openDrawer() {
        if( !this.root.getChildren().contains( this.drawer ) ){
            this.root.getChildren().add( this.drawer );
        }
        drawer.open();
    }

    void closeDrawer(){
        drawer.close();
    }

    public void setDocumentRoot( Node documentRoot, Page page ) {

        if( this.currentPage != null ) this.currentPage.onBeforeClose();
        this.rootPage.getChildren().clear();
        if( this.currentPage != null ) this.currentPage.onAfterClose();

        page.onBeforeOpen();

        AnchorPane.setTopAnchor( documentRoot, 0.0);
        AnchorPane.setLeftAnchor( documentRoot, 0.0);
        AnchorPane.setBottomAnchor( documentRoot, 0.0);
        AnchorPane.setRightAnchor( documentRoot, 0.0);

        this.rootPage.getChildren().add( documentRoot );
        page.onAfterOpen();

        this.currentDocumentoPage = documentRoot;
        this.currentPage = page;
        this.currentPage.onSearch( null, SQLText.normalize( this.textFieldSearch.getText().toLowerCase() ) );
        this.closeDrawer();
    }

    public StackPane getRootPage() {
        if( rootPage == null ) throw new RuntimeException( "Root pages esta nullo" );
        return this.rootPage;
    }

    public Node getRoot() {

        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public void accept(Stage primaryStage, Scene scene) {
        this.primaryStage = primaryStage;
        this.scene = scene;
    }
}
