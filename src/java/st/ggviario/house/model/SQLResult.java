package st.ggviario.house.model;

import com.google.gson.Gson;
import st.jigahd.support.sql.SQLRow;

import java.security.InvalidParameterException;
import java.util.Map;

public class SQLResult {

    private boolean result;
    private String message;
    private Map< String, Object > data;

    public SQLResult(SQLRow row ) {
        if( row.size() == 2 && row.containsKey( "result" ) && row.containsKey( "message" ) ){
            this.result = row.asBoolean( "result" );
            String documentResult = row.asString( "message" );
            this.data = new Gson().fromJson( documentResult, Map.class );
            if( !this.result ){
                this.message = SQLRow.stringOf( this.data.get( "text" ) );
            }
            return;
        }

        throw new InvalidParameterException( "Invalid SQLRow" );
    }

    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
