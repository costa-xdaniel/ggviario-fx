package st.ggviario.house.controller;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ControllerLoader<Node, Controller> {

    private final ViewResource<Node, Controller> viewController;
    private String location;

    public ControllerLoader(String location) {
        this.location = location;
        this.viewController = load();
    }

    public ViewResource<Node, Controller> getViewController() {
        return viewController;
    }

    private   ViewResource< Node, Controller > load(){
        ViewResource< Node, Controller > view = new ViewResource<>( );
        try{
            view.loader = new FXMLLoader( getClass().getResource( location ) );
            view.nodeView = view.loader.load();
            view.controller = view.loader.getController();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        return view;
    }

    public static class ViewResource< Node, Controller> {
        private ViewResource() {}
        private Node nodeView;
        private Controller controller;
        private FXMLLoader loader;

        public Node getNodeView() {
            return nodeView;
        }

        public Controller getController() {
            return controller;
        }

        public FXMLLoader getLoader() {
            return loader;
        }
    }
}
