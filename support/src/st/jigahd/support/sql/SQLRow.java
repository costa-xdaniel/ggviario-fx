package st.jigahd.support.sql;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SQLRow implements Serializable, Map< String, Object >{

    Map<String, Integer> headerMap;
    Map< String, Object > map;
    private Object[ ] values;
    private Gson gson;

    protected SQLRow(Map<String, Integer> headerMap){
        this.headerMap = headerMap;
        this.values = new Object[ this.headerMap.size() ];
        this.gson = new Gson();
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


    @Override
    public Object get(Object o) {
        if( o == null ) return null;
        return  this.get( String.valueOf( o ) );
    }


    public Object get(String columnName ) {
        int index = this.indexOf( columnName );
        return index < 0 || index > values.length? null : values[ index ];
    }

    public Boolean asBoolean( String columnName ){
        return SQLRow.booleanOf( this.get( columnName ) );
    }

    public Byte asByte(String columnName){
        return SQLRow.byteOf( get( columnName ) );
    }

    public Short asShort( String columnName ){
        return SQLRow.shortOf(  get( columnName ) );
    }

    public Integer asInteger( String columnName ){
        return SQLRow.integerOf( get( columnName ) );
    }

    public Long asLong(String columnName ){
        return SQLRow.longOf( get( columnName ) );
    }

    public Float asFloat( String columnName ){
        return SQLRow.floatOf( get( columnName ) );
    }

    public Double asDouble( String columnName ){
        return SQLRow.doubleOf( get( columnName ) );
    }

    public String asString( String columnName ){
        return SQLRow.stringOf( this.get( columnName ) );
    }

    public Map< String, Object > asMapJsonn( String columnName ){
        String document = SQLRow.stringOf( this.get( columnName ) );
        return this.gson.fromJson( document, Map.class );
    }

    public UUID asUUID(String columnName ){
        return SQLRow.uuidOf( get( columnName ) );
    }

    public Date asDate( String columnName ){
        return SQLRow.dateOf( get( columnName ) );
    }

    public static Boolean booleanOf(Object o) {
        if( o == null ) return null;
        if( o instanceof String ) return Boolean.valueOf( String.valueOf( o ) );
        if( o instanceof  JsonElement )  return ((JsonElement) o).getAsBoolean();
        return (Boolean) o;
    }

    public static Byte byteOf( Object o ){
        return (Byte) o;
    }

    public static Short shortOf(Object o ){
        if( o == null ) return null;
        if( o instanceof Short ) return (Short) o;
        if( o instanceof Number ) return ((Number) o).shortValue();
        if( o instanceof String && ((String) o).length() > 0) return Short.valueOf( String.valueOf( o ) );
        if( o instanceof  String ) return null;
        if( o instanceof BigDecimal ) return ((BigDecimal)o ).shortValueExact();
        if( o instanceof JsonElement ) return ((JsonElement) o).getAsShort();
        return (Short) o;
    }

    public static Integer integerOf(Object o) {
        if( o == null ) return null;
        if( o instanceof Integer ) return (Integer) o;
        if( o instanceof Number ) return ((Number) o).intValue();
        if( o instanceof String && ((String) o).length() > 0 ) return Integer.valueOf( String.valueOf( o ) );
        if( o instanceof  String ) return null;
        if( o instanceof BigDecimal ) return ((BigDecimal)o ).intValueExact();
        if( o instanceof JsonElement ) return ((JsonElement) o).getAsInt();
        return (Integer) o;
    }

    public static Long longOf( Object o) {
        if( o == null ) return null;
        if( o instanceof Long ) return (Long) o;
        if( o instanceof Number ) return  ((Number) o).longValue();
        if( o instanceof String && ((String) o).length() > 0) return Long.valueOf( String.valueOf( o ) );
        if( o instanceof  String ) return null;
        if( o instanceof BigDecimal ) return ((BigDecimal)o ).longValueExact();
        if( o instanceof JsonElement ) return ((JsonElement) o).getAsLong();
        return (Long) o;
    }

    public static Float floatOf( Object o) {
        if( o == null ) return null;
        if( o instanceof Float ) return (Float) o;
        if( o instanceof Number ) return  ((Number) o).floatValue();
        if( o instanceof String&& ((String) o).length() >0 ) return Float.valueOf( String.valueOf( o ) );
        if( o instanceof  String ) return null;
        if( o instanceof BigDecimal ) return ((BigDecimal)o ).floatValue();
        if( o instanceof JsonElement ) return ((JsonElement) o).getAsFloat();
        return (Float) o;
    }

    public static Double doubleOf(Object o) {
        if( o == null ) return null;
        if( o instanceof Double ) return (Double) o;
        if( o instanceof Number ) return  ((Number) o).doubleValue();
        if( o instanceof String && ((String) o).length()>0 ) return Double.valueOf( String.valueOf( o ) );
        if( o instanceof  String ) return null;
        if( o instanceof BigDecimal ) return ((BigDecimal)o ).doubleValue();
        if( o instanceof JsonElement ) return ((JsonElement) o).getAsDouble();
        return (Double) o;
    }

    public static UUID uuidOf(Object o) {
        if( o == null ) return null;
        if( o instanceof UUID ) return (UUID) o;
        if( o instanceof String ) return UUID.fromString((String) o);
        if( o instanceof JsonElement ) return UUID.fromString( ((JsonElement) o).getAsString() );
        return (UUID) o;
    }

    public static String stringOf(Object o) {
        if( o == null ) return null;
        if( o instanceof String )return (String) o;
        if( o instanceof JsonElement ) return ((JsonElement) o).getAsString();
        if( o instanceof PGobject ){
        }
        return String.valueOf( o );
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
            if( o instanceof  JsonElement ) return dateOf( ((JsonElement) o).getAsString() );
            return (Date) o;
        } catch ( Exception ex ){
            ex.printStackTrace();
            throw new RuntimeException( String.valueOf( o ), ex );
        }

    }

    @Override
    public int size() {
        return this.values.length;
    }

    @Override
    public boolean isEmpty() {
        return this.values.length == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        return this.headerMap.containsKey( o );
    }

    @Override
    public boolean containsValue(Object o) {
        for( Object o1: this.values ) if (o1 != null && o != null && o1.equals(o)) return true;
        return false;
    }

    @Override
    public Set<String> keySet() {
        return this.headerMap.keySet();
    }

    @Override
    public Collection<Object> values() {
        return Arrays.asList( this.values );
    }

    @Override
    public Set<Entry<String, Object>> entrySet(){
        Set< Entry< String, Object > > entries = new LinkedHashSet<>();
        for( Entry< String, Integer > item : this.headerMap.entrySet() ){
            entries.add(new Entry<String, Object>() {
                @Override
                public String getKey() {
                    return item.getKey();
                }

                @Override
                public Object getValue() {
                    return values[ item.getValue() ];
                }

                @Override
                public Object setValue(Object o) {
                    throw new UnsupportedOperationException( "this operation is not supported yet" );
                }
            });
        }
        return entries;
    }

    @Override
    public Object put(String s, Object o) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public Object remove(Object o) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public void putAll(Map<? extends String, ?> map) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super Object> biConsumer) {
        this.headerMap.forEach((s, integer) -> biConsumer.accept( s, this.values [ integer ]));
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super Object, ?> biFunction) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public Object putIfAbsent(String s, Object o) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public boolean remove(Object o, Object o1) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public boolean replace(String s, Object o, Object v1) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public Object replace(String s, Object o) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public Object computeIfAbsent(String s, Function<? super String, ?> function) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public Object computeIfPresent(String s, BiFunction<? super String, ? super Object, ?> biFunction) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public Object compute(String s, BiFunction<? super String, ? super Object, ?> biFunction) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

    @Override
    public Object merge(String s, Object o, BiFunction<? super Object, ? super Object, ?> biFunction) {
        throw new UnsupportedOperationException( "this operation is not supported yet" );
    }

}
