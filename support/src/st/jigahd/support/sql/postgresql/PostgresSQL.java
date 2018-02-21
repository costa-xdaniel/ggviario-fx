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
        if( this.conn == null ) this.createConnection();
        if( isClosed()) this.createConnection();
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

    public String getProcessedQuery() {
        return processedQuery;
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
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


}
