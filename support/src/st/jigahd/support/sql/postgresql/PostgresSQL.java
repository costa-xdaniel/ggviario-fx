package st.jigahd.support.sql.postgresql;

import st.jigahd.support.sql.Configuration;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class PostgresSQL {

    public static final String PROSTRGES_URL_MASK = "jdbc:postgresql://$host:$port/$database";

    private Configuration configuration;
    private String url;
    private Connection conn;
    private Map< ResourceType, OnNextQueryBuilder  > executors;

    public PostgresSQL( Configuration configuration ) {
        setConfiguration(configuration);
        this.executors = new LinkedHashMap<>();
        this.executors.put( ResourceType.FUNCTION, PostgresSQLExecutorFunction::new );
        this.executors.put( ResourceType.FUNCTION_TABLE, PostgresSQLExecutorFunctionTable::new );
        this.executors.put( ResourceType.QUERY, PostgresSQLExecutorQuery::new );
    }

     private void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        this.url = PROSTRGES_URL_MASK
            .replace( "$host", this.configuration.getHost() )
            .replace( "$port", String.valueOf( this.configuration.getPort() ) )
            .replace( "$database", this.configuration.getDatabase() )
        ;
    }

     public PostgresSQLQueryBuilder query(String query ){
        return new PostgresSQLQueryBuilder(this, query );
    }

     protected PostgresSQLResult processQuery( ResourceType resourceType, PostgresSQLQueryBuilder queryBuilder ) throws SQLException {
        PostgresSQLExecutor exec = this.executors.get(resourceType).createPostgresSQLExecutor( queryBuilder );
        exec.run();
        return exec.getResult();
    }

     protected Connection getCurrentConnection() {
        if( this.conn == null || this.isClosed() )
            this.createConnection();
        return this.conn;
    }

     private boolean isClosed() {

        try {
            return this.conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


     public boolean autoCloseStatement() {
        return true;
    }

     public static void closeStatement( Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public static void closeResultSet(ResultSet resultSet) {
        try {
            Statement stm = null;
            stm = resultSet.getStatement();
            PostgresSQL.closeStatement( stm );
            resultSet.close();
        } catch (SQLException ignored) { }
    }

     public void closeCurrentConnection() {
        PostgresSQL.closeConnection( this.conn );
    }

     private static void closeConnection(Connection conn) {
        try{
            conn.close();
        }catch ( Exception ex ){
        }
    }

    public enum ResourceType {
        FUNCTION,
        FUNCTION_TABLE,
        QUERY
    }

     private Connection createConnection( ) {
        try {
            if ( !org.postgresql.Driver.isRegistered() ){
                org.postgresql.Driver.register();
            }
            this.conn = DriverManager.getConnection( this.url, this.configuration.getUser(), this.configuration.getPassword() );
        } catch (SQLException e) { e.printStackTrace(); }
        return conn;
    }


    private interface OnNextQueryBuilder{
         PostgresSQLExecutor createPostgresSQLExecutor(PostgresSQLQueryBuilder queryBuilder );
    }


}
