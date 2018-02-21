package st.ggviario.house.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import st.ggviario.house.model.Producto;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class TableController<Type> {

    public TableController(){
        this.moneyNumberFormat.setMaximumFractionDigits( 2 );
        this.moneyNumberFormat.setMinimumFractionDigits( 2 );
        this.moneyNumberFormat.setCurrency( Currency.getInstance(Locale.FRANCE ) );
    }

    private NumberFormat moneyNumberFormat = NumberFormat.getNumberInstance();


    protected TableCell<Type, String> getTextCell() {
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
                    System.out.println( item+" | "+getIndex() );
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
                    setText( moneyNumberFormat.format( item )  );
                    setPadding( new Insets(16, 16, 16, 16));
                    setAlignment( Pos.CENTER_RIGHT );
                }
            }
        };
    }
}
