package st.ggviario.house.controller;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import st.ggviario.house.model.ContentPage;
import st.jigahd.support.sql.postgresql.PostgresSQL;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane contentArea;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    private Node lastContentPage;
    private Node documentRoot;
    private HamburgerBackArrowBasicTransition basicTransition;
    private boolean show;


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

    public void setDocumentRoot( Node documentRoot ) {

        this.contentArea.getChildren().clear();
        this.contentArea.getChildren().add( documentRoot );
        AnchorPane.setTopAnchor( documentRoot, 0.0);
        AnchorPane.setLeftAnchor( documentRoot, 0.0);
        AnchorPane.setBottomAnchor( documentRoot, 0.0);
        AnchorPane.setRightAnchor( documentRoot, 0.0);
        this.documentRoot = documentRoot;
        this.processHamburger( this.basicTransition );
    }

}
