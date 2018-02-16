package st.jigahd.support.sql.postgresql;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PostgresSQLRow implements Serializable {

    Map< String, Integer > headerMap;
    private Object[ ] values;

    protected PostgresSQLRow(Map<String, Integer> headerMap){
        this.headerMap = headerMap;
        this.values = new Object[ this.headerMap.size() ];
    }

    protected void set( int index, Object value ){
        this.values[ index ] = value;
    }

    private int indexOf( String name ){
        return headerMap.get( name );
    }

    public String toJson(){
        return null;
    }

    public Map< String, Object > toMap() {
        Map<String, Object > map = new HashMap<>();
        for( Map.Entry< String, Integer > column: this.headerMap.entrySet() ){
            map.put( column.getKey(), this.values[ column.getValue() ] );
        }
        return Collections.unmodifiableMap( map );
    }

    public Object valueOf(String columnName ) {
        return values[ this.indexOf( columnName ) ];
    }

    public Boolean asBoolean( String columnName ){
        return (Boolean) valueOf( columnName );
    }

    public Byte asByte(String columnName){
        return (Byte) valueOf( columnName );
    }

    public Short asShort( String columnName ){
        return (Short) valueOf( columnName );
    }

    public Integer asInteger( String columnName ){
        return (Integer) valueOf( columnName );
    }

    public Long asLong(String columnName ){
        return (Long) valueOf( columnName );
    }

    public Float asFloat( String columnName ){
        return (Float) valueOf( columnName );
    }

    public Double asDouble( String columnName ){
        return (Double) valueOf( columnName );
    }

    public BigDecimal asNumber( String columnName ){
        return (BigDecimal) valueOf( columnName );
    }

    public Character asCharater( String columnName ){
        return (Character) valueOf( columnName );
    }

    public String asString( String columnName ){
        return (String) valueOf( columnName );
    }

    public InputStream asInputSteran( String columnName ){
        return (InputStream) valueOf( columnName );
    }

    public Reader asReader( String columnName ){
        return (Reader) valueOf( columnName );
    }

    public Date asDate( String columnName ){
        return (Date) valueOf( columnName );
    }

    public Calendar asCalendar( String columnName ){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( asDate(columnName ) );
        return calendar;
    }
}
