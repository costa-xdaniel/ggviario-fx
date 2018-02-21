package st.jigahd.support.sql.lib;

public class SQLResource {

    public static <T> T coalesce( T ... objects) {
        for( T item : objects ){
            if( item != null ) return item;
        }
        return null;
    }
}
