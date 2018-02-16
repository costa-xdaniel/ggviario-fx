package st.jigahd.support.sql.postgresql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PostgresSQLResult {

    protected final ResultSet resultSet;
    protected Map<String, Integer > headerMap;
    protected Map<Integer, Integer > headerTypes;
    protected Map<Integer, String > headerMapReverse;


    public PostgresSQLResult(ResultSet resultSet ) throws SQLException {
        this.headerMap = new LinkedHashMap<>();
        this.headerMapReverse = new LinkedHashMap<>();
        this.headerTypes = new LinkedHashMap<>();
        for( int i =0; i< resultSet.getMetaData().getColumnCount(); i ++ ) {
            headerMap.put(resultSet.getMetaData().getColumnName(i), i);
            headerMapReverse.put( i, resultSet.getMetaData().getColumnName(i));
            headerTypes.put( i, resultSet.getMetaData().getColumnType( i ) );
        }
        this.headerMap = Collections.unmodifiableMap( this.headerMap );
        this.headerMapReverse = Collections.unmodifiableMap( this.headerMapReverse );
        this.headerTypes = Collections.unmodifiableMap( this.headerTypes );
        this.resultSet = resultSet;
    }

    public Object valueOf( int indexColumn, String columnName, int typeColumn ) throws SQLException {
       /* return PostgresSQLParameterManager
                .getGetters()
                    .get( typeColumn )
                        .get( this.resultSet, columnName, typeColumn );*/
       return null;
    }
}
