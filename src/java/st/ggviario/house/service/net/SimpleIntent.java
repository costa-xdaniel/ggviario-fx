package st.ggviario.house.service.net;

public enum SimpleIntent {

    REQUIRE_FOCUS,
    REGISTERED_CLIENT;

    public static SimpleIntent find(String name ) {
        for( SimpleIntent intent: values() ){
            if( intent.name().equals( name ) ) return intent;
        }
        return null;
    }

    public boolean equal( String name ){
        return this.name().equals( name );
    }
}
