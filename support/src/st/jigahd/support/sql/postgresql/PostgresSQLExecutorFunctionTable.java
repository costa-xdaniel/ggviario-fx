package st.jigahd.support.sql.postgresql;

import java.sql.SQLException;

public class PostgresSQLExecutorFunctionTable extends PostgresSQLExecutorFunction {

    public PostgresSQLExecutorFunctionTable( PostgresSQLQueryBuilder queryBuilder ) {
        super(queryBuilder);
    }

    protected String mountQuery( String functionName ) {
        StringBuilder builder = new StringBuilder();
        String params = createAbstractParameter( this.queryBuilder.countParameters() );
        builder.append("select * from " ).append( functionName ).append("( ").append( params ).append( ") t");
        if ( this.queryBuilder.hasMapReturns() ){
            builder.append("(");
            String[] columns = this.queryBuilder.getMapReturns();

            for( int i = 0; i< columns.length; i++ ){
                builder.append( columns[ i ] );
                if( i+1 < columns.length ) {
                    builder.append( "," );
                }
            }
            builder.append(")");
        }
        return builder.toString();
    }


    @Override
    public PostgresSQLResult getResult() throws SQLException {
        return new PostgresSQLResultSet( this.statement.getResultSet() );
    }
}
