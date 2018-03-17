package st.jigahd.support.sql.lib;

public class SQLResource {

    public static <T> T coalesce( T ... objects) {
        for( T item : objects ){
            if( item != null ) return item;
        }
        return null;
    }

    public static <T> String coalesceText( T ... objects) {
        for( T item : objects ){
            if( item != null ) return String.valueOf( item );
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

    public static Double max( Double ... numbers) {
        Double  max = null;
        for( Double next: numbers ){
            if( next == null ) continue;
            if( max == null || ( next != null && next > max ) ){
                max = next;
            }
        }
        return max;
    }
}
