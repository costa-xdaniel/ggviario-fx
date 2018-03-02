package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TableController< Type extends RecursiveTreeObject< Type> > {

    public static final NumberFormat MONEY = NumberFormat.getInstance( Locale.FRANCE );
    public static final DateFormat DD_MM_YYYY = new SimpleDateFormat( "dd-MM-yyyy" );


    static {
        MONEY.setMaximumFractionDigits( 2 );
        MONEY.setMinimumFractionDigits( 2 );
    }


    void push(List<Type> showValues, JFXTreeTableView<Type> tableView) {
        ObservableList< Type > observableListVenda = FXCollections.observableList( showValues );
        final TreeItem< Type > root = new RecursiveTreeItem<>( observableListVenda, RecursiveTreeObject::getChildren );
        tableView.setRoot(root);
        tableView.setShowRoot(false);
        tableView.refresh();
    }


    Callback<TreeTableColumn< Type , Date>, TreeTableCell< Type , Date> > createDateFormatTableCell(DateFormat dateFormat) {
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


}
