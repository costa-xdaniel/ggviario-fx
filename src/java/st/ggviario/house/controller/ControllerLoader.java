package st.ggviario.house.controller;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class ControllerLoader<Node, Controller> {

    private URL location;
    private FXMLLoader loader;
    private Node node;
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
            this.node = this.loader.load();
            this.controller = this.loader.getController();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }


    public URL getLocation() {
        return location;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Node getNode() {
        return node;
    }

    public Controller getController() {
        return controller;
    }
}
