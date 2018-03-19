package st.ggviario.house.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;

public class ControllerLoader< NodeView extends Node, Controller> {

    private URL location;
    private FXMLLoader loader;
    private NodeView nodeView;
    private Controller controller;

    public ControllerLoader(String location) {
        this.location = getClass().getResource( location );
        this.load();
    }

    public ControllerLoader(URL location) {
        this.location = location;
        this.load();
    }

    private  void load(){
        try{
            this.loader = new FXMLLoader( this.location );
            this.nodeView = this.loader.load();
            this.controller = this.loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException( e.getMessage()+ " | url: "+String.valueOf( location ), e );
        }
    }


    public URL getLocation() {
        return location;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public NodeView getNodeView() {
        return nodeView;
    }

    public Controller getController() {
        return controller;
    }
}
