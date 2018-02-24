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


    public static String capitalizeAll( String text ){
        if( text == null ) return null;
        if( text.length() <= 1 ) return text.toUpperCase();
        String texts[] = text.split( " " );
        text = "";
        int i =0;
        StringBuilder textBuilder = new StringBuilder(text);
        for ( String aux: texts ){
            textBuilder.append(capitalize(aux));
            if( i+1 < texts.length ) textBuilder.append(" ");
        }
        text = textBuilder.toString();
        return text;
    }


    public static String capitalize( String text ){
        if( text == null ) return null;
        if( text.length() <= 1 ) return text.toUpperCase();
        return text.substring( 0, 1 ).toUpperCase()
                +text.substring( 1, text.length() );
    }
}
