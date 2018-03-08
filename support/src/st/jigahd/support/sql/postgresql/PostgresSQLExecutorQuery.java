package st.jigahd.support.sql.postgresql;

import java.sql.SQLException;

public class PostgresSQLExecutorQuery extends PostgresSQLExecutor {

    public PostgresSQLExecutorQuery(PostgresSQLQueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public void run() throws SQLException {
        this.queryBuilder.setProcessedQuery( this.queryBuilder.getQuery() );
        super.execute();
    }

    @Override
    public PostgresSQLResult getResult() throws SQLException {
        return new PostgresSQLResultSet( this.statement.getResultSet() );
    }
}
