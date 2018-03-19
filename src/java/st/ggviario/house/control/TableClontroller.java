package st.ggviario.house.control;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.GlyphIcons;
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
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.lib.SQLText;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static st.ggviario.house.control.StyleClass.*;

public class TableClontroller< Type extends RecursiveTreeObject< Type> > {

    public static final NumberFormat MONEY_FORMAT = NumberFormat.getInstance( Locale.FRANCE );
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance( Locale.FRANCE );
    public static final DateFormat DD_MM_YYYY_FORMAT = new SimpleDateFormat( "dd-MM-yyyy" );


    static {
        MONEY_FORMAT.setMaximumFractionDigits( 2 );
        MONEY_FORMAT.setMinimumFractionDigits( 2 );
    }

    protected void push(List<Type> showValues, JFXTreeTableView<Type> tableView) {
        ObservableList< Type > observableListVenda = FXCollections.observableList( showValues );

        final RecursiveTreeItem< Type > root = new RecursiveTreeItem<>( observableListVenda, RecursiveTreeObject::getChildren );
        tableView.setRoot(root);
        tableView.setShowRoot(false);
        tableView.refresh();
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

    protected Callback<TreeTableColumn< Type , Number>, TreeTableCell< Type , Number> > cellNumber( NumberFormat numberFormat) {
        return param -> new TreeTableCell< Type , Number>(){
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if( item != null && !empty ){
                    this.setText( numberFormat.format( item ) );
                }
            }
        };
    }
    protected Callback<TreeTableColumn< Type , Number>, TreeTableCell< Type , Number> > cellMoney( NumberFormat moneyFormater, String currency) {
        return param -> new TreeTableCell< Type , Number>(){
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if( item != null && !empty ){
                    String format = SQLText.normalize(  moneyFormater.format( item ) +" "+ SQLResource.coalesce( currency, "" ) );
                    this.setText( format );
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
                } else  {
                    this.setGraphic( null );
                }
                setItem( item );
            }
        };
    }

    protected <T> void useAsIconsColumn (JFXTreeTableColumn< Type, T > column, int icons ){
        if( !column.getStyleClass().contains( CLASS_COLUMN_ICONS_AREA ) )
            column.getStyleClass().add( CLASS_COLUMN_ICONS_AREA );
        column.setMinWidth( 48 * icons );
        column.setMaxWidth( 48 * icons );
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

    protected Node newIconView( GlyphIcons icon ){
        Node iconView = this.createIcon( icon );
        return createIconPanel(iconView);
    }

    protected Node newIconViewPrimary( GlyphIcons icon ){
        Node iconView = createIcon( icon);
        iconView =  createIconPanel( iconView );
        iconView.getStyleClass().add( CLASS_ICON_PRIMARY );
        return iconView;
    }

    protected Node newIconViewDestroy(GlyphIcons icon ){
        Node iconView = createIcon( icon );
        iconView =  createIconPanel( iconView );
        iconView.getStyleClass().add( CLASS_ICON_DESTROY );
        return iconView;
    }

    private Node createIcon( GlyphIcons icon ) {
        Node iconView = null;
        if( icon instanceof MaterialIcon)
            iconView = new MaterialIconView((MaterialIcon) icon);
        else if( icon instanceof MaterialDesignIcon)
            iconView = new MaterialDesignIconView((MaterialDesignIcon) icon);
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

    protected void columnMinWidth(TreeTableColumn column, double width){
        column.setMinWidth( width );
    }

    public interface IconsActions {
         Node createIcons();
    }

}
