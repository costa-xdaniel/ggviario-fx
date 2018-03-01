package st.ggviario.house.model;

import java.util.LinkedList;
import java.util.List;

public interface EnumTypes< Enum extends EnumTypes,  NumberClass extends Number > {


     Enum [ ] allValues();

     NumberClass value();

     static < E extends EnumTypes, N extends Number> E find( E [ ] enumTypes, N value ){
         if( value == null ) return null;
         if( enumTypes == null || enumTypes.length == 0 ) return null;
         for( E types: enumTypes) if( value.equals( types.value() ) ) return types;
         return null;
     }

    default List<Enum> others() {
        List< Enum > list = new LinkedList<>();
        for( Enum etNum : this.allValues() ){
            if( !this.equals( etNum ) )  list.add( etNum );

        }
        return list;
    }

}
