package st.jigahd.support.sql.postgresql;

import java.sql.SQLException;

public class PostgresSQLExecutorQuery extends PostgresSQLExecutor {

    public PostgresSQLExecutorQuery(PostgresSQL postgresSQL) {
        super(postgresSQL);
    }

    @Override
    public void run() throws SQLException {
        this.postgresSQL.processedQuery( this.postgresSQL.getQuery() );
        super.execute();
    }

    @Override
    public PostgresSQLResult getResult() throws SQLException {
        return new PostgresSQLResultSet( this.statement.getResultSet() );
    }
}
