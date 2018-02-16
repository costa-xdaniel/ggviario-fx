package st.ggviario.house.controller;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Home implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource("../fxml/menuleft.fxml"));
        try {
            Node vBox = loader.load();
            drawer.setSidePane( vBox );
            drawer.setPadding( new Insets( -1, 0, -1, -1));
        } catch (IOException e) {
            e.printStackTrace();
        }


        HamburgerBackArrowBasicTransition basicTransition = new HamburgerBackArrowBasicTransition( hamburger );
        basicTransition.setRate( -1 );


        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, ( e ) -> {
            basicTransition.setRate( basicTransition.getRate() * -1 );
            basicTransition.play();

            if( drawer.isShown() ){
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }


}
