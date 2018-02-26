package st.ggviario.house.controller.page;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.lib.SQLText;

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TableController<Type> {

    private NumberFormat moneyNumberFormat = NumberFormat.getInstance( Locale.FRANCE );
    private DateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );

    public TableController(){
        this.moneyNumberFormat.setMaximumFractionDigits( 2 );
        this.moneyNumberFormat.setMinimumFractionDigits( 2 );
    }


    protected TableCell<Type, String> getSimpleTextCell() {
        return new TableCell<Type, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                /**
                 * Get column index
                 TableColumn<Type, String> column = getTableColumn();
                 int colIndex = getTableView().getColumns().indexOf(column);

                 */

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText( item );
                    setPadding( new Insets(16, 16, 16, 16));
                    setAlignment( Pos.CENTER_LEFT );
                }
            }
        };
    }

    protected TableCell<Type, Number> getNumberCell() {
        return new TableCell<Type, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText( String.valueOf( item ) );
                    setPadding( new Insets(16, 16, 16, 16));
                    setAlignment( Pos.CENTER_RIGHT );
                }
            }
        };
    }

    TableCell<Type, Date> getDateCell() {
        return this.getCellFormate( this.dateFormat );
    }

    TableCell<Type, Number > getMoneyCell( String currency ) {
        return new TableCell<Type, Number> () {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem( item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText( SQLText.normalize( moneyNumberFormat.format( item ) +" "+SQLResource.coalesce( currency, "" )) );
                    setPadding( new Insets(16, 16, 16, 16));
                    setAlignment( Pos.CENTER_RIGHT );
                }
            }
        };
    }

    private <S> TableCell< Type, S >  getCellFormate(Format format ){
        return new TableCell<Type, S>() {
            @Override
            protected void updateItem(S item, boolean empty) {
                super.updateItem( item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText( format.format( item )  );
                    setPadding( new Insets(16, 16, 16, 16));
                    setAlignment( Pos.CENTER_RIGHT );
                }
            }
        };
    }
}
