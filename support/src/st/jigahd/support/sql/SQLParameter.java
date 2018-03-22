package st.jigahd.support.sql;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import org.postgresql.util.PGobject;
import st.jigahd.support.sql.lib.SQLResource;

import java.io.IOException;
import java.io.StringWriter;

public class SQLParameter {
    private String name;
    private int SQLType;
    private Object value;

    public SQLParameter(String name, int SQLType, Object value) {
        this.name = name;
        this.SQLType = SQLType;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getSQLType() {
        return SQLType;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        JsonElement element = null;

        if( this.value != null && value instanceof  JsonElement ) {
            element = (JsonElement) this.value;
        }
        else if( this.value != null && this.value instanceof Number  ) {
            element = new JsonPrimitive((Number) this.value);
        }
        else if( this.value != null && this.value instanceof  Boolean ) {
            element = new JsonPrimitive( (Boolean) this.value);
        }
        else if( this.value != null && this.value instanceof  CharSequence ) {
            element = new JsonPrimitive( String.valueOf( this.value ) );
        } else if ( this.value != null && this.value instanceof PGobject ){

            if( ( (PGobject) this.value).getValue()  != null ){
                if(SQLResource.existIn( ((PGobject) this.value).getType(), "json", "jsonb" ) ){
                    element = new Gson().fromJson( ( ( PGobject) this.value).getValue(), JsonElement.class );

                } else {
                    element = new JsonPrimitive( ( (PGobject) this.value).getValue() );
                }
            }
        }
        else if( this.value != null ) {
            element = new JsonPrimitive( String.valueOf( this.value ) );
        }

        JsonObject object = new JsonObject();
        object.add( this.name, element );
        return object.toString();
    }
}
