package st.ggviario.house.cell;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import st.ggviario.house.controller.MenuLeftHeader;
import st.ggviario.house.model.MenuItem;

import java.io.IOException;

public class MenuItemCell extends ListCell<MenuItem> {

    private MenuLeftHeader controller;
    private Node root;

    public MenuItemCell() {

        try {
            FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/menuleft_header.fxml") );
            this.root = loader.load();
            this.controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void updateItem(MenuItem item, boolean empty ) {
        super.updateItem( item, empty );
        setText(null);
        MenuItem lastItem;

        lastItem = item;

        this.controller.setHeader( lastItem );
        setGraphic( this.root );
        double padding = -1;
        setPadding( new Insets( padding, padding, padding, padding ));

        if (empty) {
            setGraphic(null);
        } else { }
    }

}
