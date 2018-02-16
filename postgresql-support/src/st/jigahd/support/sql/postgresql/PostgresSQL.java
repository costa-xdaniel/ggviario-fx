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
    private PostgresSQLParameterManager parameterManager;
    private String url;
    private Connection conn;
    private Map< ResourceType, PostgresSQLExecutor > executors;
    private String query;
    private String processedQuery;


    public PostgresSQL( Configuration configuration ) {
        setConfiguration(configuration);
        this.parameterManager = new PostgresSQLParameterManager( this );
        this.executors = new LinkedHashMap<>();
        this.executors.put( ResourceType.FUNCTION, new PostgresSQLExecutorFunction( this ) );
        this.executors.put( ResourceType.FUNCTION_TABLE, new PostgresSQLExecutorFunctionTable( this ) );
        this.executors.put( ResourceType.QUERY, new PostgresSQLExecutorQuery( this ) );
    }

    private void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        this.url = PROSTRGES_URL_MASK
            .replace( "$host", this.configuration.getHost() )
            .replace( "$port", String.valueOf( this.configuration.getPort() ) )
            .replace( "$database", this.configuration.getDatabase() )
        ;
    }

    public PostgresSQLParameterManager query(String query ){
        this.parameterManager.clear();
        this.query = query;
        return this.parameterManager;
    }

    protected PostgresSQLResult processQuery(ResourceType resourceType ) throws SQLException {
        PostgresSQLExecutor exec = this.executors.get(resourceType);
        exec.run();
        return exec.getResult();
    }

    public String getQuery() {
        return query;
    }

    public int countParameters() {
        return this.parameterManager.countParameters();
    }

    protected PostgresSQLParameterManager getParameterManager() {
        return this.parameterManager;
    }

    public void processedQuery(String processedQuery) {
        this.processedQuery = processedQuery;
    }

    public Connection getCurrentConnection() {
        return this.conn;
    }

    public String getProcessedQuery() {
        return processedQuery;
    }

    public boolean autoCloseStatement() {
        return true;
    }

    public static void closeStatement(CallableStatement statement) {

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


    public static void main(String[] args) {
        String url = PostgresSQL.PROSTRGES_URL_MASK
                .replace("$host", "127.0.0.1" )
                .replace( "$port", String.valueOf( 123 ) )
                .replace( "$database", "saoferias")
                ;

        System.out.println( url );
    }

}
