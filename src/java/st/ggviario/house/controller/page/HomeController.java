package st.ggviario.house.controller.page;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private StackPane rootPage;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    private Node currentDocumentoPage;
    private HamburgerBackArrowBasicTransition basicTransition;
    private boolean show;
    private Page currentPage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource("/fxml/drawer.fxml") );
        try {
            Node vBox = loader.load();
            DrawerController drawerController = loader.getController();
            drawerController.setHomeController( this );
            drawer.setSidePane( vBox );
            drawer.setPadding( new Insets( -1, 0, -1, -1));
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.basicTransition = new HamburgerBackArrowBasicTransition( hamburger );
        this.basicTransition.setRate( -1 );
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, ( e ) -> {
            processHamburger(basicTransition);
        });

    }


    private void processHamburger(HamburgerBackArrowBasicTransition basicTransition) {
        basicTransition.setRate( basicTransition.getRate() * -1 );
        basicTransition.play();
        basicTransition.setOnFinished(actionEvent -> {
            if(!this.show) {
                this.root.getChildren().remove( this.drawer );
            }
        });


        if( drawer.isShown() ){
            drawer.close();
            this.show = false;

        } else {
            drawer.open();
            if( !this.root.getChildren().contains( this.drawer ))
                this.root.getChildren().add( this.drawer );

            this.show = true;
        }
    }

    public void setDocumentRoot( Node documentRoot, Page page ) {

        if( this.currentPage != null ) this.currentPage.onBeforeRemove();
        this.rootPage.getChildren().clear();
        if( this.currentPage != null ) this.currentPage.onAfterRemove();

        page.onBeforeAppend();

        AnchorPane.setTopAnchor( documentRoot, 0.0);
        AnchorPane.setLeftAnchor( documentRoot, 0.0);
        AnchorPane.setBottomAnchor( documentRoot, 0.0);
        AnchorPane.setRightAnchor( documentRoot, 0.0);

        this.rootPage.getChildren().add( documentRoot );
        page.onAfterAppend();

        this.currentDocumentoPage = documentRoot;
        this.currentPage = page;
        this.processHamburger( this.basicTransition );
    }

    public Node getRootPage() {
        if( rootPage == null ) throw new RuntimeException( "Root page esta nullo" );
        return this.rootPage;
    }

    public Node getRoot() {

        return root;
    }
}
