package st.ggviario.house.factory;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import st.ggviario.house.controller.HomeController;
import st.ggviario.house.model.DrawerItem;
import st.ggviario.house.model.Menu;

public class MenuItemCell extends ListCell<Menu> {

    private final HomeController homeController;

    public MenuItemCell(HomeController homeController) {
        this.homeController = homeController;

    }


    @Override
    protected void updateItem(Menu item, boolean empty ) {
        try {
            super.updateItem(item, empty);
            setText(null);

            if( item != null ){
                FXMLLoader loader = new FXMLLoader(item.getFXMLUrl());
                Node root = loader.load();
                DrawerItem controller = loader.getController();

                controller.setRootController(  this.homeController );
                controller.setMenu( item );
                setGraphic(root);
            }



            double padding = -1;
            setPadding(new Insets(padding, padding, padding, padding));

            if (empty) {
                setGraphic(null);
            } else {
            }
        }catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

}
