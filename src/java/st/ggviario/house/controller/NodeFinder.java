package st.ggviario.house.controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;

import java.util.LinkedList;
import java.util.List;

public class NodeFinder {

    public <T extends Node > List < T > findNodeByObjectClass(Parent root, Class< T > nodeClass ) {
        List< T > nodes = new LinkedList<>();

        this.navigateInParent(root, node -> {
            if( nodeClass.isAssignableFrom( node.getClass() ) )
                nodes.add( ( T ) node);
            return true;
        });
        return nodes;
    }

    public List< Node > findNodeByCssClass( Parent root, String ... cssClass ){
        List< Node > nodes = new LinkedList<>();
        this.navigateInParent(root, node -> {
            for( String css : cssClass )
                if( !node.getStyleClass().contains( css ) ) return true;
            nodes.add( node );
            return true;
        });
        return nodes;
    }

    public Node findNodeById( Parent root, String nodeId ){
        List< Node > nodes = new LinkedList<>();
        this.navigateInParent(root, node -> {
            if( node.getId().contains( nodeId ) ) {
                nodes.add( node );
                return false;
            }
            return true;
        });

        return nodes.size() > 0? nodes.get( 0 ) : null;
    }

    public void navigateInParent(Node parent, OnNextNode onNextNodeNode ) {
        if( parent instanceof  Parent ){
            for (Node node : ((Parent) parent).getChildrenUnmodifiable() ) {
                if( !onNextNodeNode.continueNavigate(node) ) return;
                navigateInParent( node, onNextNodeNode );
            }
        }


    }

    public interface OnNextNode {
        boolean continueNavigate( Node node );
    }


    private static class  A{

    }

    private static class Ab extends A {

    }

    private static class  Abb extends  Ab {

    }
}
