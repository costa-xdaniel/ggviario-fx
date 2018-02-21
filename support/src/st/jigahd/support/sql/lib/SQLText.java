package st.jigahd.support.sql.lib;

public class SQLText {
    public static String normalize( String text ){
        if( text == null ) return  null;
        int lenth = text.length();
        text = text.replace("  ", " " ).trim();
        if( lenth != text.length() )
            text = SQLText.normalize( text );
        return text;
    }
}
