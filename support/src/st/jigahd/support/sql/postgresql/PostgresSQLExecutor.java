package st.jigahd.support.sql.postgresql;

import javafx.util.Pair;

import java.sql.*;
import java.util.Map;

public abstract class PostgresSQLExecutor {



    PostgresSQLQueryBuilder queryBuilder;
    CallableStatement statement;
    private Connection con;

    PostgresSQLExecutor( PostgresSQLQueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;

    }

    public abstract void run() throws SQLException;



    void execute() throws SQLException {
        if( this.con == null || con.isClosed() ){
            this.con = this.queryBuilder.getPostgresSQL().getCurrentConnection();
        }
        String query = this.queryBuilder.getProcessedQuery();
        if( this.statement != null && this.queryBuilder.getPostgresSQL().autoCloseStatement() ) PostgresSQL.closeStatement( statement );
        this.statement = this.con.prepareCall( query );
        this.setParameters();
        this.statement.execute();
    }

    private void setParameters() throws SQLException {
        int count = 1;
        Map<Integer, PostgresSQLQueryBuilder.Setter> setters = PostgresSQLQueryBuilder.getSetters();
        for (Pair< Integer, Object > p: this.queryBuilder.params() ){
            int key = p.getKey();
            if ( p.getValue() == null ) key = Types.NULL;
            setters.get( key ).set( this.statement, count++, p.getKey(), p.getValue() );
        }
    }

    public abstract PostgresSQLResult getResult() throws SQLException;
}
