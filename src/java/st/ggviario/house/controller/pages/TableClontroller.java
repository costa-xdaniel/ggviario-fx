package st.ggviario.house.controller.pages;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static st.ggviario.house.controller.StyleClass.*;

public class TableClontroller< Type extends RecursiveTreeObject< Type> > {

    public static final NumberFormat MONEY = NumberFormat.getInstance( Locale.FRANCE );
    public static final DateFormat DD_MM_YYYY = new SimpleDateFormat( "dd-MM-yyyy" );




    static {
        MONEY.setMaximumFractionDigits( 2 );
        MONEY.setMinimumFractionDigits( 2 );
    }


    protected <T> void useAsIconsColumn (JFXTreeTableColumn< Type, T > column, int icons ){
        if( !column.getStyleClass().contains( CLASS_COLUMN_ICONS_AREA ) )
            column.getStyleClass().add( CLASS_COLUMN_ICONS_AREA );
        column.setMinWidth( 48 * icons );
        column.setMaxWidth( 48 * icons );
    }

    protected Node newIconView(MaterialDesignIcon icon ){
        Node iconView = new MaterialDesignIconView( icon );
        return createIconPanel(iconView);
    }

    protected Node newIconView(MaterialIcon icon ){
        Node iconView = new MaterialIconView( icon );
        return createIconPanel(iconView);
    }

    protected Node newIconViewPrimary(MaterialDesignIcon icon ){
        Node iconView = new MaterialDesignIconView( icon );
        iconView =  createIconPanel( iconView );
        iconView.getStyleClass().add( CLASS_ICON_PRIMARY );
        return iconView;
    }

    protected Node newIconViewPrimary( MaterialIcon icon ){
        Node iconView = new MaterialIconView( icon );
        iconView =  createIconPanel( iconView );
        iconView.getStyleClass().add( CLASS_ICON_PRIMARY );
        return iconView;
    }

    protected Node newIconViewDestroy(MaterialDesignIcon icon ){
        Node iconView = new MaterialDesignIconView( icon );
        iconView =  createIconPanel( iconView );
        iconView.getStyleClass().add( CLASS_ICON_DESTROY );
        return iconView;
    }

    protected Node newIconViewDestroy(MaterialIcon icon ){
        Node iconView = new MaterialIconView( icon );
        iconView =  createIconPanel( iconView );
        iconView.getStyleClass().add( CLASS_ICON_DESTROY );
        return iconView;
    }

    private Node createIconPanel( Node iconView ) {
        StackPane anchorIcon = new StackPane( );
        JFXRippler rippler = new JFXRippler(anchorIcon);

        iconView.getStyleClass().add( CLASS_ICON_VIEW );
        anchorIcon.getStyleClass().add( CLASS_ICON_PANE );
        rippler.getStyleClass().add( CLASS_ICON_RIPPLER );
        anchorIcon.getChildren().add( iconView );
        JFXRippler.setMargin( anchorIcon, new Insets( 6, 3, 6, 3 ) );
        return rippler;
    }

    protected HBox newIconCellContainer(Node ... nodes ){
        int counts =0;
        HBox hBox = new HBox();
        for( Node node : nodes ){
            if( node != null ){
                hBox.getChildren().add( node );
                counts ++;
            }
        }
        hBox.setMinWidth( 48 * counts );
        hBox.setMaxWidth( 48 * counts );
        hBox.getStyleClass().add( "cell-icons" );
        HBox.setMargin( hBox, new Insets( 0, 3, 0, 3 ) );
        return hBox;
    }

    protected void push(List<Type> showValues, JFXTreeTableView<Type> tableView) {
        ObservableList< Type > observableListVenda = FXCollections.observableList( showValues );

        final RecursiveTreeItem< Type > root = new RecursiveTreeItem<>( observableListVenda, RecursiveTreeObject::getChildren );
        tableView.setRoot(root);
        tableView.setShowRoot(false);
        tableView.refresh();
    }

    class IconViewBuilder {

    }

    protected Callback<TreeTableColumn< Type , Date>, TreeTableCell< Type , Date> > cellDateFormat(DateFormat dateFormat) {
        return param -> new TreeTableCell< Type , Date>(){
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if( item != null && !empty ){
                    this.setText( dateFormat.format( item ) );
                }
            }
        };
    }

    protected Callback<TreeTableColumn< Type , IconsActions>, TreeTableCell< Type , IconsActions> > cellIconsView() {
        return param -> new TreeTableCell< Type , IconsActions>(){
            @Override
            protected void updateItem(IconsActions item, boolean empty) {
                super.updateItem(item, empty);
                if( item != null && !empty ){
                    Node node = item.createIcons();
                    this.setGraphic( node );
                }
                setItem( item );
            }
        };
    }



    protected void columnMinWidth(TreeTableColumn column, double width){
        column.setMinWidth( width );
    }

    public interface IconsActions {
         Node createIcons();
    }

}
