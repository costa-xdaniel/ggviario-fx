package st.ggviario.house.controller.includs;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import st.ggviario.house.controller.ControllerLoader;

public class IncludProdutoInformation {

    public static IncludProdutoInformation newInstance( ){
        ControllerLoader< VBox, IncludProdutoInformation > loader = new ControllerLoader<VBox, IncludProdutoInformation>( "/fxml/includs/includ_produto_detalhes.fxml" );
        return loader.getController();
    }

    @FXML private VBox root;


    public VBox getRoot() {
        return root;
    }
}
