package st.jigahd.support.sql.postgresql;

import java.sql.SQLException;

public class PostgresSQLExecutorFunction extends PostgresSQLExecutor {


    public PostgresSQLExecutorFunction(PostgresSQLQueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public void run() throws SQLException {
        String functionName = this.queryBuilder.getQuery();
        if ( functionName == null ) throw new RuntimeException( "Nome da função não definida" );
        String query = mountQuery( functionName );
        this.queryBuilder.setProcessedQuery( query );
        super.execute();
    }

    @Override
    public PostgresSQLResult getResult() throws SQLException {
        return new PostgresSQLResultSigle( this.statement.getResultSet() );
    }

    protected String mountQuery(String functionName) {
        StringBuilder builder = new StringBuilder();
        String params = createAbstractParameter( this.queryBuilder.countParameters() );
        builder.append("select " ).append( functionName ).append("( ").append( params ).append( " ) as result; ");
        return builder.toString();
    }

    String createAbstractParameter(int number){
        StringBuilder s = new StringBuilder();
        for ( int i = 0; i < number; i++ ){
            s.append("?");
            if ( i+1 < number ) s.append(',');
        }
        return s.toString();
    }
}
