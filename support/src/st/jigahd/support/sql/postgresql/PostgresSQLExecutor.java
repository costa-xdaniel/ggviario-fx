package st.jigahd.support.sql.postgresql;

import javafx.util.Pair;

import java.sql.*;
import java.util.Map;

public abstract class PostgresSQLExecutor {



    PostgresSQL postgresSQL;
    CallableStatement statement;


    PostgresSQLExecutor(PostgresSQL postgresSQL) {
        this.postgresSQL = postgresSQL;

    }

    public abstract void run() throws SQLException;



    void execute() throws SQLException {
        Connection con = this.postgresSQL.getCurrentConnection();
        String query = this.postgresSQL.getProcessedQuery();
        if( this.statement != null && this.postgresSQL.autoCloseStatement() ) PostgresSQL.closeStatement( statement );
        this.statement = con.prepareCall( query );
        System.out.println( query );
        this.setParameters();
        this.statement.execute();
    }

    private void setParameters() throws SQLException {
        int count = 1;
        Map<Integer, PostgresSQLParameterManager.Setter> setters = PostgresSQLParameterManager.getSetters();
        for (Pair< Integer, Object > p: this.postgresSQL.getParameterManager().params() ){
            int key = p.getKey();
            if ( p.getValue() == null ) key = Types.NULL;
            setters.get( key ).set( this.statement, count++, p.getKey(), p.getValue() );
        }
    }

    public abstract PostgresSQLResult getResult() throws SQLException;
}
