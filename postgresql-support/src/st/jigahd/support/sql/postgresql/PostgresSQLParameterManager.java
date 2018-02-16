package st.jigahd.support.sql.postgresql;

import javafx.util.Pair;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class PostgresSQLParameterManager {

    public interface Setter {
        void set ( CallableStatement statement, int index, Integer type, Object value ) throws SQLException;
    }

    public interface Getter {
        Object get (ResultSet resultSet, String columnName, Integer type ) throws SQLException;
    }

    private static Map<Integer, Setter> setters;
    private static Map<Integer, Getter> getters;

    static {
        Map<Integer, Setter > setters = new LinkedHashMap<>();
        
        setters.put(Types.NULL, (statement, index, type, value) -> statement.setNull( index,  type ));
        setters.put(Types.OTHER, (statement, index, type, value) -> statement.setObject( index,  value));
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
        setters.put(Types.NUMERIC, (statement, index, type, value) -> statement.setBigDecimal( index, (BigDecimal) value));
        setters.put(Types.CHAR, (statement, index, type, value) -> statement.setString( index, (String) value));
        setters.put(Types.VARCHAR, (statement, index, type, value) -> statement.setString( index, (String) value));
        setters.put(Types.LONGNVARCHAR, (statement, index, type, value) -> statement.setNString( index, (String) value));
        setters.put(Types.BLOB, (statement, index, type, value) -> statement.setBlob( index, (InputStream) value));
        setters.put(Types.NCHAR, (statement, index, type, value) -> statement.setCharacterStream( index, (Reader) value));
        setters.put(Types.CLOB, (statement, index, type, value) -> statement.setClob( index, (Reader) value));
        setters.put(Types.NCLOB, (statement, index, type, value) -> statement.setNClob( index, (Reader) value));

        setters.put(Types.TIME, (statement, index, type, value) -> {
            Calendar calendar = (Calendar) value;
            statement.setTime( index, getTimeOf( calendar ), calendar );
        });

        setters.put(Types.DATE, (statement, index, type, value) -> {
            Calendar calendar = (Calendar) value;
            statement.setDate( index, getDateOf( calendar ), calendar );
        });

        setters.put(Types.TIMESTAMP, (statement, index, type, value) -> {
            Calendar calendar = (Calendar) value;
            statement.setTimestamp( index, getTimeStampOf( calendar ), calendar );
        });
        
        PostgresSQLParameterManager.setters = Collections.unmodifiableMap( setters );
    }

    static {
        Map<Integer, Getter > getter = new LinkedHashMap<>();

        getter.put(Types.NULL, (resultSet, index, type) -> null);
        getter.put(Types.OTHER, (resultSet, index, type) -> resultSet.getObject( index ));

        getter.put(Types.BOOLEAN, (resultSet, columnName, type ) -> resultSet.getBoolean( columnName ));
        getter.put(Types.BIT, (resultSet, columnName, type) -> resultSet.getByte( columnName ));
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
        PostgresSQLParameterManager.getters = Collections.unmodifiableMap( getter );
    }



    private String[] mapReturns;
    private final PostgresSQL postgresSQL;
    private List<Pair< Integer, Object > > parameters;


    public PostgresSQLParameterManager(PostgresSQL postgresSQL ) {
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
            throw new RuntimeException( e );
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

    public PostgresSQLParameterManager with(Object obj) {
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

    public PostgresSQLParameterManager withBoolean(Boolean value ){
        parameters.add( new Pair<>( Types.BOOLEAN, value ) );
        return this;
    }

    public PostgresSQLParameterManager withBit(Byte value ){
        parameters.add( new Pair<>( Types.BIT, value ) );
        return this;
    }


    public PostgresSQLParameterManager withSmallint(Short value ){
        parameters.add( new Pair<>( Types.SMALLINT, value ) );
        return this;
    }

    public PostgresSQLParameterManager withInteger(Integer integer ){
        parameters.add( new Pair<>(Types.INTEGER, integer ) );
        return this;
    }


    public PostgresSQLParameterManager withBigint(Long value ){
        parameters.add( new Pair<>( Types.BIGINT, value ) );
        return this;
    }

    public PostgresSQLParameterManager withReal(Float value ){
        parameters.add( new Pair<>( Types.REAL, value ) );
        return this;
    }

    public PostgresSQLParameterManager withFloat(Float value ){
        parameters.add( new Pair<>( Types.FLOAT, value ) );
        return this;
    }

    public PostgresSQLParameterManager withDouble(Double value ){
        parameters.add( new Pair<>( Types.DOUBLE, value ) );
        return this;
    }

    public PostgresSQLParameterManager withDecimal(BigDecimal value ){
        parameters.add( new Pair<>( Types.DECIMAL, value ) );
        return this;
    }

    public PostgresSQLParameterManager withNumeric(BigDecimal value  ){
        parameters.add( new Pair<>( Types.NUMERIC, value ) );
        return this;
    }

    public PostgresSQLParameterManager withChar(Character value ){
        parameters.add( new Pair<>( Types.CHAR, value ) );
        return this;
    }

    public PostgresSQLParameterManager withVarchar(String value ){
        parameters.add( new Pair<>( Types.VARCHAR, value ) );
        return this;
    }

    public PostgresSQLParameterManager withText(String value ){
        parameters.add( new Pair<>( Types.LONGVARCHAR, value ) );
        return this;
    }

    public PostgresSQLParameterManager withTime(Date value ){
        parameters.add( new Pair<>( Types.TIME, calendarOf( value ) ) );
        return this;
    }

    public PostgresSQLParameterManager withTime(Calendar value ){
        parameters.add( new Pair<>( Types.TIME, value ) );
        return this;
    }


    public PostgresSQLParameterManager withDate(Calendar value ){
        parameters.add( new Pair<>( Types.DATE, value ) );
        return this;
    }

    public PostgresSQLParameterManager withDate(Date value ){
        parameters.add( new Pair<>( Types.DATE, calendarOf( value ) ) );
        return this;
    }

    public PostgresSQLParameterManager withTimestamp(Calendar value ){
        parameters.add( new Pair<>( Types.TIMESTAMP, value ) );
        return this;
    }

    public PostgresSQLParameterManager withTimestamp(Date value ){
        parameters.add( new Pair<>( Types.TIMESTAMP, calendarOf( value ) ) );
        return this;
    }

    public PostgresSQLParameterManager withBlob(InputStream doublePrecision ){
        parameters.add( new Pair<>( Types.BLOB, doublePrecision ) );
        return this;
    }

    public PostgresSQLParameterManager withNChar(Reader doublePrecision ){
        parameters.add( new Pair<>( Types.NCHAR, doublePrecision ) );
        return this;
    }

    public PostgresSQLParameterManager withClob(Reader doublePrecision ){
        parameters.add( new Pair<>( Types.CLOB, doublePrecision ) );
        return this;
    }


    public PostgresSQLParameterManager withNClob(Reader doublePrecision ){
        parameters.add( new Pair<>( Types.NCLOB, doublePrecision ) );
        return this;
    }




    public PostgresSQLParameterManager withOther(Object doublePrecision ){
        parameters.add( new Pair<>( Types.OTHER, doublePrecision ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Boolean ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Byte ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Short ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Integer ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Float ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Double ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Number ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Character ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(CharSequence ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(String ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Date ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Calendar ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    public PostgresSQLParameterManager withArray(Object ... args ){
        parameters.add( new Pair<>(Types.ARRAY, args ) );
        return this;
    }

    private static java.sql.Time getTimeOf(Calendar value) {
        return null;
    }

    private static java.sql.Date getDateOf(Calendar value) {
        return null;
    }

    private static Timestamp getTimeStampOf(Calendar value) {
        return null;
    }

    private Calendar calendarOf(Date date ){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        return calendar;
    }

}
