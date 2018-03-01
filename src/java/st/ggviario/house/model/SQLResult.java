package st.ggviario.house.model;

import com.google.gson.Gson;
import com.sun.istack.internal.NotNull;
import st.jigahd.support.sql.SQLRow;

import java.security.InvalidParameterException;
import java.util.Map;

public class SQLResult {

    private boolean success;
    private String message;
    private Map< String, Object > data;

    public SQLResult( @NotNull SQLRow row ) {
        if( row.size() == 2 && row.containsKey( "result" ) && row.containsKey( "message" ) ){
            this.success = row.asBoolean( "result" );
            String documentResult = row.asString( "message" );
            this.data = new Gson().fromJson( documentResult, Map.class );
            if( !this.success){
                this.message = SQLRow.stringOf( this.data.get( "text" ) );
            }
            return;
        }

        throw new InvalidParameterException( "Invalid SQLRow"  + row.toJson());
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
