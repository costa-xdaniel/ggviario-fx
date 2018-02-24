package st.jigahd.support.sql.lib;

public class SQLResource {

    public static <T> T coalesce( T ... objects) {
        for( T item : objects ){
            if( item != null ) return item;
        }
        return null;
    }

    public static <T> boolean existIn( T item, T ... items  ){
        if( item == null ) return false;
        if( items == null || items.length == 0 ) return false;
        for( T t : items ) if( t != null && t.equals( item ) ) return true;
        return false;
    }

    public static <T> boolean notExistIn( T item, T ... items  ){
        return !existIn( item, items );
    }

    public static <T> boolean equalsAll( T item, T ... items  ){
        if( item == null ) return false;
        if( items == null || items.length == 0 ) return false;
        for( T t : items ) if( t == null || !t.equals( item ) ) return false;
        return true;
    }
}
