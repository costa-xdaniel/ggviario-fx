package st.jigahd.support.sql.postgresql;

import javafx.util.Pair;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.*;

public class PostgresSQLParameterManager {

    private String[] mapReturns;

    public Map<Integer, Setter> getSetters() {
        return Collections.unmodifiableMap( setters );
    }

    public boolean hasMapReturns() {
        return this.mapReturns != null && this.mapReturns.length > 0;
    }

    public interface Setter {
        void set ( CallableStatement statement, int index, Integer type, Object value ) throws SQLException;
    }

    private Map<Integer, Setter> setters;
    private final PostgresSQL postgresSQL;
    private List<Pair< Integer, Object > > parrameters;

    public PostgresSQLParameterManager(PostgresSQL postgresSQL ) {
        this.parrameters = new LinkedList<>();
        this.postgresSQL = postgresSQL;
        this.setters = new LinkedHashMap<>();
        this.registerTypes();
    }

    public PostgresSQLCursor function(){
        try {
            return this.postgresSQL.processQuery( PostgresSQL.ResourceType.FUNCTION   );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public PostgresSQLCursor processTable(String ... mapReturs ){
        try {
            this.mapReturns = mapReturs;
            return this.postgresSQL.processQuery( PostgresSQL.ResourceType.FUNCTION_TABLE  );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public String[] getMapReturns() {
        return this.mapReturns;
    }

    protected void clear() {
        this.parrameters.clear();
    }

    public int countParameters() {
        return this.parrameters.size();
    }

    public Iterable<? extends Pair<Integer, Object>> params() {
        return Collections.unmodifiableCollection( this.parrameters );
    }

    public void args( Object ... args ){
        if ( args == null || args.length == 0 ) return;
        for( int i = 0; i< args.length; i++ ){
            Object obj = args[ i ];
            if( obj instanceof  Boolean ) this.bool((Boolean) obj);
            else if ( obj instanceof Byte ) this.bit((Byte) obj);
            else if ( obj instanceof Integer ) this.integer((Integer) obj);
            else if ( obj instanceof Short ) this.smallint((Short) obj);
            else if ( obj instanceof Long ) this.bigint((Long) obj);
            else if ( obj instanceof Float ) this.pfloat((Float) obj);
            else if ( obj instanceof Double ) this.doublePrecison((Double) obj);
            else if ( obj instanceof Number ) this.numeric( (BigDecimal) obj);
            else if ( obj instanceof Character ) this.pchar((Character) obj);
            else if ( obj instanceof String ) this.varchar((String) obj);
            else if ( obj instanceof CharSequence ) this.varchar( String.valueOf( obj ));
            else if ( obj instanceof Date ) this.timestamp((Date) obj);


            else if( obj instanceof  Boolean[] ) this.array( (Boolean[]) obj );
            else if ( obj instanceof Byte [] ) this.array((Byte[]) obj);
            else if ( obj instanceof Integer[] ) this.array((Integer[]) obj);
            else if ( obj instanceof Short []) this.array((Short []) obj);
            else if ( obj instanceof Long[] ) this.array((Long []) obj);
            else if ( obj instanceof Float[]) this.array((Float []) obj);
            else if ( obj instanceof Double[] ) this.array((Double []) obj);
            else if ( obj instanceof Number[] ) this.array( (Number []) obj);
            else if ( obj instanceof Character[] ) this.array((Character []) obj);
            else if ( obj instanceof String[] ) this.array((String []) obj);
            else if ( obj instanceof CharSequence[] ) this.array( String.valueOf( obj ));
            else if ( obj instanceof Date[] ) this.array((Date []) obj);
            else if ( obj instanceof Object[] ) this.array((Object []) obj);
            else this.other( obj );
        }
    }

    public PostgresSQLParameterManager bool(Boolean value ){
        parrameters.add( new Pair<>( Types.BOOLEAN, value ) );
        return this;
    }

    public PostgresSQLParameterManager bit(Byte value ){
        parrameters.add( new Pair<>( Types.BIT, value ) );
        return this;
    }


    public PostgresSQLParameterManager smallint(Short value ){
        parrameters.add( new Pair<>( Types.SMALLINT, value ) );
        return this;
    }

    public PostgresSQLParameterManager integer(Integer integer ){
        parrameters.add( new Pair<>(Types.INTEGER, integer ) );
        return this;
    }


    public PostgresSQLParameterManager bigint(Long value ){
        parrameters.add( new Pair<>( Types.BIGINT, value ) );
        return this;
    }

    public PostgresSQLParameterManager real(Float value ){
        parrameters.add( new Pair<>( Types.REAL, value ) );
        return this;
    }

    public PostgresSQLParameterManager pfloat(Float value ){
        parrameters.add( new Pair<>( Types.FLOAT, value ) );
        return this;
    }

    public PostgresSQLParameterManager doublePrecison(Double value ){
        parrameters.add( new Pair<>( Types.DOUBLE, value ) );
        return this;
    }

    public PostgresSQLParameterManager decimal( BigDecimal value ){
        parrameters.add( new Pair<>( Types.DECIMAL, value ) );
        return this;
    }

    public PostgresSQLParameterManager numeric( BigDecimal value  ){
        parrameters.add( new Pair<>( Types.NUMERIC, value ) );
        return this;
    }

    public PostgresSQLParameterManager pchar(Character value ){
        parrameters.add( new Pair<>( Types.CHAR, value ) );
        return this;
    }

    public PostgresSQLParameterManager varchar(String value ){
        parrameters.add( new Pair<>( Types.VARCHAR, value ) );
        return this;
    }

    public PostgresSQLParameterManager text(String value ){
        parrameters.add( new Pair<>( Types.LONGVARCHAR, value ) );
        return this;
    }

    public PostgresSQLParameterManager time(Date value ){
        parrameters.add( new Pair<>( Types.TIME, calendarOf( value ) ) );
        return this;
    }

    public PostgresSQLParameterManager time(Calendar value ){
        parrameters.add( new Pair<>( Types.TIME, value ) );
        return this;
    }


    public PostgresSQLParameterManager date(Calendar value ){
        parrameters.add( new Pair<>( Types.DATE, value ) );
        return this;
    }

    public PostgresSQLParameterManager date(Date value ){
        parrameters.add( new Pair<>( Types.DATE, calendarOf( value ) ) );
        return this;
    }

    public PostgresSQLParameterManager timestamp(Calendar value ){
        parrameters.add( new Pair<>( Types.TIMESTAMP, value ) );
        return this;
    }

    public PostgresSQLParameterManager timestamp(Date value ){
        parrameters.add( new Pair<>( Types.TIMESTAMP, calendarOf( value ) ) );
        return this;
    }

    public PostgresSQLParameterManager blob( InputStream doublePrecision ){
        parrameters.add( new Pair<>( Types.BLOB, doublePrecision ) );
        return this;
    }

    public PostgresSQLParameterManager nchar( Reader doublePrecision ){
        parrameters.add( new Pair<>( Types.NCHAR, doublePrecision ) );
        return this;
    }

    public PostgresSQLParameterManager clob( Reader doublePrecision ){
        parrameters.add( new Pair<>( Types.CLOB, doublePrecision ) );
        return this;
    }


    public PostgresSQLParameterManager nclob( Reader doublePrecision ){
        parrameters.add( new Pair<>( Types.NCLOB, doublePrecision ) );
        return this;
    }




    public PostgresSQLParameterManager other(Object doublePrecision ){
        parrameters.add( new Pair<>( Types.OTHER, doublePrecision ) );
        return this;
    }

    public PostgresSQLParameterManager array (Boolean ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Byte ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Short ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Integer ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Float ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Double ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Number ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Character ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (CharSequence ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (String ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Date ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Calendar ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager array (Object ... args ){
        parrameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }


    protected void registerTypes(){

        this.setters.put(Types.NULL, (statement, index, type, value) -> { statement.setNull( index,  type ); });
        this.setters.put(Types.OTHER, (statement, index, type, value) -> {  statement.setObject( index,  value); });

        this.setters.put(Types.BOOLEAN, (statement, index, type, value) -> { statement.setBoolean( index, (Boolean) value); });
        this.setters.put(Types.BIT, (statement, index, type, value) -> { statement.setByte( index, (Byte) value); });
        this.setters.put(Types.TINYINT, (statement, index, type, value) -> { statement.setShort( index, (Short) value); });
        this.setters.put(Types.SMALLINT, (statement, index, type, value) -> { statement.setShort( index, (Short) value); });
        this.setters.put(Types.INTEGER, (statement, index, type, value) -> { statement.setInt( index, (Integer) value); });
        this.setters.put(Types.BIGINT, (statement, index, type, value) -> { statement.setLong( index, (Long) value); });
        this.setters.put(Types.REAL, (statement, index, type, value) -> { statement.setFloat( index, (Float) value); });
        this.setters.put(Types.FLOAT, (statement, index, type, value) -> { statement.setFloat( index, (Float) value); });
        this.setters.put(Types.DOUBLE, (statement, index, type, value) -> {  statement.setDouble( index, (Double) value); });
        this.setters.put(Types.DECIMAL, (statement, index, type, value) -> { statement.setBigDecimal( index, (BigDecimal) value); });
        this.setters.put(Types.NUMERIC, (statement, index, type, value) -> { statement.setBigDecimal( index, (BigDecimal) value); });
        this.setters.put(Types.CHAR, (statement, index, type, value) -> { statement.setString( index, (String) value); });
        this.setters.put(Types.VARCHAR, (statement, index, type, value) -> { statement.setString( index, (String) value); });
        this.setters.put(Types.LONGNVARCHAR, (statement, index, type, value) -> {  statement.setNString( index, (String) value); });
        this.setters.put(Types.BLOB, (statement, index, type, value) -> {  statement.setBlob( index, (InputStream) value); });
        this.setters.put(Types.NCHAR, (statement, index, type, value) -> {  statement.setCharacterStream( index, (Reader) value); });
        this.setters.put(Types.CLOB, (statement, index, type, value) -> {  statement.setClob( index, (Reader) value); });
        this.setters.put(Types.NCLOB, (statement, index, type, value) -> {  statement.setNClob( index, (Reader) value); });

        this.setters.put(Types.TIME, (statement, index, type, value) -> {
            Calendar calendar = (Calendar) value;
            statement.setTime( index, getTimeOf( calendar ), calendar );
        });

        this.setters.put(Types.DATE, (statement, index, type, value) -> {
            Calendar calendar = (Calendar) value;
            statement.setDate( index, getDateOf( calendar ), calendar );
        });

        this.setters.put(Types.TIMESTAMP, (statement, index, type, value) -> {
            Calendar calendar = (Calendar) value;
            statement.setTimestamp( index, getTimeStampOf( calendar ), calendar );
        });
    }

    private java.sql.Time getTimeOf( Calendar value ) {
        return null;
    }

    private java.sql.Date getDateOf( Calendar value ) {
        return null;
    }

    private Timestamp getTimeStampOf( Calendar value ) {
        return null;
    }

    private Calendar calendarOf(Date date ){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        return calendar;
    }

}
