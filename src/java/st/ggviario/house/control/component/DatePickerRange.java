package st.ggviario.house.control.component;

import javafx.scene.control.DateCell;
import javafx.util.Callback;

import java.time.LocalDate;

public class DatePickerRange {
    public static Callback<javafx.scene.control.DatePicker,DateCell> getDayCellFactory(LocalDate minDate, LocalDate maxDate ) {
        RejectedDate minRejectedDate = localDate -> minDate != null && localDate.isBefore( minDate );
        RejectedDate maxRejectedDate = localDate -> maxDate != null && localDate.isAfter( maxDate );
        OnRejectDate onRejectDate = ( dateCell, item ) -> {
            dateCell.setDisable( true );
            dateCell.setStyle( "-fx-text-fill: md-grey-500;" );
        };

        return datePicker -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if( item != null && !empty ){
                    if( maxRejectedDate.isReject( item ) || minRejectedDate.isReject( item ) )
                        onRejectDate.onRejectedDate( this, item );
                }
            }
        };
    }


    interface OnRejectDate{
        void onRejectedDate(DateCell dateCell, LocalDate item);
    }

    interface RejectedDate {
        boolean isReject(LocalDate localDate );
    }
}
