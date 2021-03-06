package st.jigahd.support.sql.postgresql;

import st.jigahd.support.sql.SQLRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PostgresSQLResultSet extends PostgresSQLResult {

    private final int numberColumns;
    private List<PostgresSQLRow> fethsRow;
    private boolean recordCursorPint;
    private int currentPoint = 0;
    private PostgresSQLRow currentRow;

    public PostgresSQLResultSet(ResultSet resultSet) throws SQLException {
        super(resultSet);
        this.numberColumns = this.resultSet.getMetaData().getColumnCount();
        this.fethsRow = new LinkedList<>( );
    }

    public void setRecordCursorPint(boolean recordCursorPint) {
        this.recordCursorPint = recordCursorPint;
    }

    public boolean isRecordCursorPint() {
        return recordCursorPint;
    }


    public PostgresSQLRow nextRow()  {
        try {
            if( this.resultSet == null ) return null;
            if( this.resultSet.isClosed() ) return null;
            if( ! this.resultSet.next() ) return null;

            if( recordCursorPint && currentPoint <= fethsRow.size() && currentPoint > 0 ){
                this.currentRow = this.fethsRow.get( currentPoint++ -1);
                return currentRow;
            }
            currentRow = new PostgresSQLRow( this.headerMap );
            for( int i =1; i<=this.numberColumns; i++  ){
                String columnName = headerMapReverse.get( i );
                int typeColumn = this.headerTypes.get( i );
                currentRow.set( i-1, valueOf( i, columnName , typeColumn ) );
            }

            if( this.recordCursorPint ){
                this.fethsRow.add( currentRow );
            }

            this.currentPoint++;
            return currentRow;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SQLRow previewRow() {
        if( ! isRecordCursorPint() ) return null;
        if( this.fethsRow.size() < 1 ) return  null;
        this.currentRow = this.fethsRow.get( --currentPoint -1 );
        return currentRow;
    }

    /**
     * @param position base 0
     * @return
     */
    public SQLRow getCurrentRow(int position ) {
        if( !isRecordCursorPint() ) return  null;
        this.currentRow = fethsRow.get( position );
        this.currentPoint = position +1;
        return currentRow;
    }

    public List<SQLRow> getFethsRow() {
        return Collections.unmodifiableList( this.fethsRow );
    }

    public SQLRow currentRow(){
        return this.currentRow;
    }

    public void onResultQuery( OnResultQuery onResultQuery ) {
        SQLRow row;
        while( (row = this.nextRow() )!= null ){
            if( onResultQuery != null && onResultQuery.accept( row ) ) break;
        }
        PostgresSQL.closeResultSet( this.resultSet );
    }


    public interface OnResultQuery {
        boolean accept(SQLRow row );
    }

    public interface OnReadAllResultQuery extends OnResultQuery {
        default boolean accept(SQLRow row ) {
            onNext( row );
            return false;
        }
        void onNext( SQLRow row );
    }

}
