package st.jigahd.support.sql;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SQLRow implements Serializable {

    Map<String, Integer> headerMap;
    Map< String, Object > map;
    private Object[ ] values;

    protected SQLRow(Map<String, Integer> headerMap){
        this.headerMap = headerMap;
        this.values = new Object[ this.headerMap.size() ];
    }

    protected void set( int index, Object value ){
        this.values[ index ] = value;
    }

    private int indexOf( String name ){
        Integer i = headerMap.get( name );
        return i == null? -1 : i;
    }

    public String toJson(){
        Gson gson = new Gson();
        Map<String, Object > map = new LinkedHashMap<>();
        for( Map.Entry< String, Integer > item: this.headerMap.entrySet()){
            map.put( item.getKey(), this.values[ item.getValue() ] );
        }

        return gson.toJson( map );
    }

    public Map< String, Object > toMap() {
        if( this.map == null ){
            Map<String, Object > prepareMap = new HashMap<>();
            for( Map.Entry< String, Integer > column: this.headerMap.entrySet() ){
                prepareMap.put( column.getKey(), this.values[ column.getValue() ] );
            }
            this.map = Collections.unmodifiableMap( prepareMap );
        }
        return this.map;
    }


    @Override
    public String toString() {
        return this.toJson();
    }

    public Object valueOf(String columnName ) {
        int index = this.indexOf( columnName );
        return index < 0 || index > values.length? null : values[ index ];
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
        return SQLRow.doubleOf( valueOf( columnName ) );
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

    public UUID asUUID(String columnName ){
        return (UUID) valueOf( columnName );
    }

    public InputStream asInputSteran( String columnName ){
        return (InputStream) valueOf( columnName );
    }

    public Reader asReader( String columnName ){
        return (Reader) valueOf( columnName );
    }

    public Date asDate( String columnName ){
        return SQLRow.dateOf( valueOf( columnName ) );
    }

    public Calendar asCalendar( String columnName ){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( asDate(columnName ) );
        return calendar;
    }

    public static Short shortOf(Object o ){
        if( o == null ) return null;
        if( o instanceof Short ) return (Short) o;
        if( o instanceof Double ) return ((Double) o).shortValue();
        return (Short) o;
    }

    public static UUID uuidOf(Object o) {
        if( o == null ) return null;
        if( o instanceof UUID ) return (UUID) o;
        if( o instanceof String ) return UUID.fromString((String) o);
        return (UUID) o;
    }

    public static String stringOf(Object o) {
        if( o == null ) return null;
        if( o instanceof String )return (String) o;
        return (String) o;
    }

    public static Double doubleOf(Object o) {
        if( o == null ) return null;
        if( o instanceof Double ) return (Double) o;
        if( o instanceof BigDecimal ) return ((BigDecimal)o ).doubleValue();
        return (Double) o;
    }

    public static Date dateOf( Object o) {
        try {
            if( o == null ) return null;
            if( o instanceof Date ) return ( Date) o;
            if( o instanceof String ) {
                String format = null;
                String campo[] = String.valueOf(o).split("[-T]");
                if( campo[0].length() == 4 && campo[1].length()== 2 && campo[2].length() == 2 ){
                    int p1 = Integer.parseInt( campo[1] );
                    int p2 = Integer.parseInt( campo[2] );
                    if( p1 > 0 && p1 < 13 && p2 > 0 && p2 < 32 ){
                        format = "yyyy-MM-dd";
                    }
                }

                if( format != null ) {
                    DateFormat dateFormat = new SimpleDateFormat(  format);
                    return dateFormat.parse( String.valueOf( o ) );
                }
            }

            return (Date) o;
        } catch ( Exception ex ){
            ex.printStackTrace();
            throw new RuntimeException( String.valueOf( o ), ex );
        }

    }
}
