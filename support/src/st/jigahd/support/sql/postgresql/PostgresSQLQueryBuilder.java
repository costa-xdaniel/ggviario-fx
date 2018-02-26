package st.jigahd.support.sql.postgresql;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.util.Pair;
import org.json.JSONObject;
import org.postgresql.core.Oid;
import org.postgresql.util.PGobject;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.Date;

public class PostgresSQLQueryBuilder {

    public interface Setter {
        void set(CallableStatement statement, int index, Integer type, Object value) throws SQLException;
    }

    public interface Getter {
        Object get(ResultSet resultSet, String columnName, Integer type) throws SQLException;
    }

    private static Map<Integer, Setter> setters;
    private static Map<Integer, Getter> getters;

    static {
        Map<Integer, Setter > setters = new LinkedHashMap<>();

        setters.put( Types.OTHER, (statement, index, type, value ) -> {
            statement.setObject( index,  value);
        });


        setters.put(Types.NULL, (statement, index, type, value) -> statement.setNull( index,  type ));
        setters.put(Types.BOOLEAN, (statement, index, type, value) -> statement.setBoolean( index, (Boolean) value));
        setters.put(Types.BIT, (statement, index, type, value) -> statement.setByte( index, (Byte) value));
        setters.put(Types.TINYINT, (statement, index, type, value) -> statement.setShort( index, (Short) value));
        setters.put(Types.SMALLINT, (statement, index, type, value) -> statement.setShort( index, (Short) value));
        setters.put(Types.INTEGER, (statement, index, type, value) -> statement.setInt( index, (Integer) value));
        setters.put(Types.BIGINT, (statement, index, type, value) -> statement.setLong( index, (Long) value));
        setters.put(Types.REAL, (statement, index, type, value) -> statement.setFloat( index, (Float) value));
        setters.put(Types.FLOAT, (statement, index, type, value) -> statement.setFloat( index, (Float) value));
        setters.put(Types.DOUBLE, (statement, index, type, value) -> statement.setDouble( index, (Double) value));
        setters.put(Types.DECIMAL, (statement, index, type, value) -> statement.setBigDecimal( index, (BigDecimal) value));
        setters.put(Types.NUMERIC, (statement, index, type, value) -> statement.setBigDecimal( index,  bigDecimalOf( value )));
        setters.put(Types.CHAR, (statement, index, type, value) -> statement.setString( index, (String) value));
        setters.put(Types.VARCHAR, (statement, index, type, value) -> statement.setString( index, (String) value));
        setters.put(Types.LONGNVARCHAR, (statement, index, type, value) -> statement.setNString( index, (String) value));
        setters.put(Types.BLOB, (statement, index, type, value) -> statement.setBlob( index, (InputStream) value));
        setters.put(Types.NCHAR, (statement, index, type, value) -> statement.setCharacterStream( index, (Reader) value));
        setters.put(Types.CLOB, (statement, index, type, value) -> statement.setClob( index, (Reader) value));
        setters.put(Types.NCLOB, (statement, index, type, value) -> statement.setNClob( index, (Reader) value));

        setters.put(Types.TIME, (statement, index, type, value) -> {
            statement.setTime( index, (Time) value);
        });

        setters.put(Types.DATE, (statement, index, type, value) -> {
            statement.setDate( index, (java.sql.Date) value);
        });

        setters.put(Types.TIMESTAMP, (statement, index, type, value) -> {
            statement.setTimestamp( index, (Timestamp) value);
        });
        
        PostgresSQLQueryBuilder.setters = Collections.unmodifiableMap( setters );
    }

    private static BigDecimal bigDecimalOf(Object value) {
        if( value == null ) return null;
        if( value instanceof  BigDecimal ) return (BigDecimal) value;
        if( value instanceof Number ) return BigDecimal.valueOf( ((Number) value).doubleValue() );
        return (BigDecimal) value;
    }

    static {
        Map<Integer, Getter > getter = new LinkedHashMap<>();

        getter.put(Types.NULL, (resultSet, index, type) -> null);
        getter.put(Types.OTHER, (resultSet, index, type) -> resultSet.getObject( index ));

        getter.put(Types.BOOLEAN, (resultSet, columnName, type ) -> resultSet.getBoolean( columnName ));
        getter.put(Types.BIT, (resultSet, columnName, type) -> resultSet.getBoolean( columnName ));
        getter.put(Types.TINYINT, (resultSet, columnName, type) -> resultSet.getShort( columnName ));
        getter.put(Types.SMALLINT, (resultSet, columnName, type) -> resultSet.getShort( columnName ));
        getter.put(Types.INTEGER, (resultSet, columnName, type) -> resultSet.getInt( columnName ));
        getter.put(Types.BIGINT, (resultSet, columnName, type) -> resultSet.getByte( columnName ));
        getter.put(Types.REAL, (resultSet, columnName, type) -> resultSet.getFloat( columnName ));
        getter.put(Types.FLOAT, (resultSet, columnName, type) -> resultSet.getFloat( columnName ));
        getter.put(Types.DOUBLE, (resultSet, columnName, type) -> resultSet.getDouble( columnName ));
        getter.put(Types.DECIMAL, (resultSet, columnName, type) -> resultSet.getBigDecimal( columnName ));
        getter.put(Types.NUMERIC, (resultSet, columnName, type) -> resultSet.getBigDecimal( columnName ));
        getter.put(Types.CHAR, (resultSet, columnName, type) -> resultSet.getString( columnName ).charAt(0));
        getter.put(Types.VARCHAR, (resultSet, columnName, type) -> resultSet.getString( columnName ));
        getter.put(Types.LONGNVARCHAR, (resultSet, columnName, type) -> resultSet.getNString( columnName ));
        getter.put(Types.BLOB, (resultSet, columnName, type) -> resultSet.getBlob( columnName ).getBinaryStream());
        getter.put(Types.NCHAR, (resultSet, columnName, type) -> resultSet.getNCharacterStream( columnName ));;
        getter.put(Types.CLOB, (resultSet, columnName, type) -> resultSet.getClob( columnName ));
        getter.put(Types.NCLOB, (resultSet, columnName, type) -> resultSet.getNClob( columnName ).getCharacterStream());
        getter.put(Types.TIME, (resultSet, columnName, type) -> resultSet.getTime( columnName ));
        getter.put(Types.DATE, (resultSet, columnName, type) -> resultSet.getDate( columnName ));
        getter.put(Types.TIMESTAMP, (resultSet, columnName, type) -> resultSet.getTimestamp( columnName ));
        PostgresSQLQueryBuilder.getters = Collections.unmodifiableMap( getter );
    }



    private String[] mapReturns;
    private final PostgresSQL postgresSQL;
    private List<Pair< Integer, Object > > parameters;


    public PostgresSQLQueryBuilder(PostgresSQL postgresSQL ) {
        this.parameters = new LinkedList<>();
        this.postgresSQL = postgresSQL;
    }


    public static Map<Integer, Setter> getSetters() {
        return setters;
    }

    public static Map<Integer, Getter> getGetters() {
        return getters;
    }


    public boolean hasMapReturns() {
        return this.mapReturns != null && this.mapReturns.length > 0;
    }


    public PostgresSQLResultSigle callFunction(){
        try {
            this.mapReturns = null;
            return (PostgresSQLResultSigle) this.postgresSQL.processQuery( PostgresSQL.ResourceType.FUNCTION );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public PostgresSQLResultSet callFunctionTable(String ... mapReturs ){
        try {
            this.mapReturns = mapReturs;
            return (PostgresSQLResultSet) this.postgresSQL.processQuery( PostgresSQL.ResourceType.FUNCTION_TABLE  );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException( );
        }
    }

    public PostgresSQLResultSet execute(){
        try {
            this.mapReturns = null;
            return (PostgresSQLResultSet) this.postgresSQL.processQuery( PostgresSQL.ResourceType.QUERY );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public String[] getMapReturns() {
        return this.mapReturns;
    }

    protected void clear() {
        this.parameters.clear();
    }

    public int countParameters() {
        return this.parameters.size();
    }

    public Iterable<? extends Pair<Integer, Object>> params() {
        return Collections.unmodifiableCollection( this.parameters);
    }

    public void with(Object ... args ){
        if ( args == null || args.length == 0 ) return;
        for (Object obj : args) {
            with(obj);
        }
    }

    public PostgresSQLQueryBuilder with(Object obj) {
        if (obj instanceof Boolean) this.withBoolean((Boolean) obj);
        else if (obj instanceof Byte) this.withBit((Byte) obj);
        else if (obj instanceof Integer) this.withInteger((Integer) obj);
        else if (obj instanceof Short) this.withSmallint((Short) obj);
        else if (obj instanceof Long) this.withBigint((Long) obj);
        else if (obj instanceof Float) this.withFloat((Float) obj);
        else if (obj instanceof Double) this.withDouble((Double) obj);
        else if (obj instanceof Number) this.withNumeric((BigDecimal) obj);
        else if (obj instanceof Character) this.withChar((Character) obj);
        else if (obj instanceof String) this.withVarchar((String) obj);
        else if (obj instanceof CharSequence) this.withVarchar(String.valueOf(obj));
        else if (obj instanceof Date) this.withTimestamp((Date) obj);


        else if (obj instanceof Boolean[]) this.withArray((Boolean[]) obj);
        else if (obj instanceof Byte[]) this.withArray((Byte[]) obj);
        else if (obj instanceof Integer[]) this.withArray((Integer[]) obj);
        else if (obj instanceof Short[]) this.withArray((Short[]) obj);
        else if (obj instanceof Long[]) this.withArray((Long[]) obj);
        else if (obj instanceof Float[]) this.withArray((Float[]) obj);
        else if (obj instanceof Double[]) this.withArray((Double[]) obj);
        else if (obj instanceof Number[]) this.withArray((Number[]) obj);
        else if (obj instanceof Character[]) this.withArray((Character[]) obj);
        else if (obj instanceof String[]) this.withArray((String[]) obj);
        else if (obj instanceof CharSequence[]) this.withArray(String.valueOf(obj));
        else if (obj instanceof Date[]) this.withArray((Date[]) obj);
        else if (obj instanceof Object[]) this.withArray((Object[]) obj);
        else this.withOther(obj);
        return this;
    }

    public PostgresSQLQueryBuilder withBoolean(Boolean value ){
        parameters.add( new Pair<>( Types.BOOLEAN, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withBit(Byte value ){
        parameters.add( new Pair<>( Types.BIT, value ) );
        return this;
    }


    public PostgresSQLQueryBuilder withSmallint(Short value ){
        parameters.add( new Pair<>( Types.SMALLINT, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withInteger(Integer integer ){
        parameters.add( new Pair<>(Types.INTEGER, integer ) );
        return this;
    }


    public PostgresSQLQueryBuilder withBigint(Long value ){
        parameters.add( new Pair<>( Types.BIGINT, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withReal(Float value ){
        parameters.add( new Pair<>( Types.REAL, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withFloat(Float value ){
        parameters.add( new Pair<>( Types.FLOAT, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withDouble(Double value ){
        parameters.add( new Pair<>( Types.DOUBLE, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withDecimal(BigDecimal value ){
        parameters.add( new Pair<>( Types.DECIMAL, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withNumeric(BigDecimal value  ){
        parameters.add( new Pair<>( Types.NUMERIC, value ) );
        return this;
    }
    public PostgresSQLQueryBuilder withNumeric(Number value  ){
        parameters.add( new Pair<>( Types.NUMERIC, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withChar(Character value ){
        parameters.add( new Pair<>( Types.CHAR, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withVarchar(String value ){
        parameters.add( new Pair<>( Types.VARCHAR, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withText(String value ){
        parameters.add( new Pair<>( Types.LONGVARCHAR, value ) );
        return this;
    }

    public PostgresSQLQueryBuilder withTime(Date value ){
        parameters.add( new Pair<>( Types.TIME, sqlTimeOf( value ) ) );
        return this;
    }

    public PostgresSQLQueryBuilder withTime(Calendar value ){
        parameters.add( new Pair<>( Types.TIME, sqlTimeOf( value ) ) );
        return this;
    }
    public PostgresSQLQueryBuilder withTime(LocalTime value ){
        parameters.add( new Pair<>( Types.TIME, sqlTimeOf( value ) ) );
        return this;
    }


    public PostgresSQLQueryBuilder withDate(Calendar value ){
        parameters.add( new Pair<>( Types.DATE, sqlDateOf( value ) ) );
        return this;
    }

    public PostgresSQLQueryBuilder withDate(Date value ){
        parameters.add( new Pair<>( Types.DATE, sqlDateOf( value ) ) );
        return this;
    }

    public PostgresSQLQueryBuilder withDate(LocalDate value ){
        parameters.add( new Pair<>( Types.DATE, sqlDateOf( value ) ) );
        return this;
    }

    public PostgresSQLQueryBuilder withTimestamp(Calendar value ){
        parameters.add( new Pair<>( Types.TIMESTAMP, sqlTimestampOf( value ) ) );
        return this;
    }

    public PostgresSQLQueryBuilder withTimestamp(Date value ){
        parameters.add( new Pair<>( Types.TIMESTAMP, sqlTimestampOf( value )  ) );
        return this;
    }

    public PostgresSQLQueryBuilder withTimestamp(LocalDateTime value ){
        parameters.add( new Pair<>( Types.TIMESTAMP, sqlTimestampOf( value )  ) );
        return this;
    }

    public PostgresSQLQueryBuilder withBlob(InputStream doublePrecision ){
        parameters.add( new Pair<>( Types.BLOB, doublePrecision ) );
        return this;
    }

    public PostgresSQLQueryBuilder withNChar(Reader doublePrecision ){
        parameters.add( new Pair<>( Types.NCHAR, doublePrecision ) );
        return this;
    }

    public PostgresSQLQueryBuilder withClob(Reader doublePrecision ){
        parameters.add( new Pair<>( Types.CLOB, doublePrecision ) );
        return this;
    }


    public PostgresSQLQueryBuilder withNClob(Reader doublePrecision ){
        parameters.add( new Pair<>( Types.NCLOB, doublePrecision ) );
        return this;
    }




    public PostgresSQLQueryBuilder withOther(Object doublePrecision ){
        parameters.add( new Pair<>( Types.OTHER, doublePrecision ) );
        return this;
    }

    public PostgresSQLQueryBuilder withUUID(UUID doublePrecision ){
        parameters.add( new Pair<>( Types.OTHER, doublePrecision ) );
        return this;
    }

    public PostgresSQLQueryBuilder withJson(Object doublePrecision ){
        parameters.add( new Pair<>( Types.OTHER, doublePrecision ) );
        return this;
    }

    public PostgresSQLQueryBuilder withJsonb( JsonElement jsonb ){
        return this.withJsonb( jsonb == null? null : jsonb.toString() );
    }

    public PostgresSQLQueryBuilder withJsonb( String jsonb ){
        try {
            PGobject jsonObject = new PGobject();
            jsonObject.setType("::jsonb");
            jsonObject.setValue( jsonb );
            parameters.add( new Pair<>( Types.OTHER, jsonb ) );
        } catch ( Exception ex  ) {
            throw new RuntimeException( ex );
        }
        return this;
    }

//    public PostgresSQLQueryBuilder withJsonb( String jsonb ){
//        parameters.add( new Pair<>( Oid.JSON, jsonb ) );
//        return this;
//    }

    public PostgresSQLQueryBuilder withJson(String doublePrecision ){
        parameters.add( new Pair<>( Types.OTHER, doublePrecision ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Boolean ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Byte ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Short ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Integer ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Float ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Double ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Number ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Character ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(CharSequence ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(String ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Date ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Calendar ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLQueryBuilder withArray(Object ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    private static Time getTimeOf(Calendar value) {
        return null;
    }

    private static java.sql.Date getDateOf(Calendar value) {
        return null;
    }

    private static Timestamp getTimeStampOf(Calendar value) {
        return null;
    }



    private java.sql.Date sqlDateOf(Date date ){
        if( date == null ) return null;
        if( date instanceof java.sql.Date ) return (java.sql.Date) date;
        return new java.sql.Date( date.getTime() );
    }

    private java.sql.Date sqlDateOf(Calendar date) {
        if( date == null ) return null;
        return new java.sql.Date( date.getTimeInMillis() );
    }

    private java.sql.Date sqlDateOf(LocalDate date ){
        if( date == null ) return null;
        return java.sql.Date.valueOf( date );
    }

    private java.sql.Time sqlTimeOf(Date time ){
        if( time == null ) return null;
        if( time instanceof Time ) return (Time) time;
        return  new java.sql.Time(time.getTime());
    }

    private java.sql.Time sqlTimeOf(Calendar time) {
        if( time == null ) return null;
        return new java.sql.Time( time.getTimeInMillis() );
    }

    private java.sql.Time sqlTimeOf(LocalTime time ){
        if( time == null ) return null;
        return  java.sql.Time.valueOf( time );
    }


    private java.sql.Timestamp sqlTimestampOf(Date timestamp ){
        if( timestamp == null ) return null;
        if( timestamp instanceof java.sql.Timestamp ) return (Timestamp) timestamp;
        return new java.sql.Timestamp( timestamp.getTime() );
    }

    private java.sql.Timestamp sqlTimestampOf(Calendar timestamp) {
        if( timestamp == null ) return null;
        return new java.sql.Timestamp( timestamp.getTimeInMillis() );
    }

    private java.sql.Timestamp sqlTimestampOf(LocalDateTime timestamp ){
        if( timestamp == null ) return null;
        return java.sql.Timestamp.valueOf( timestamp );
    }
}
