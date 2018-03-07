package st.ggviario.house.model;

import java.util.LinkedList;
import java.util.List;

public interface EnumTypes< E extends EnumTypes,  N extends Number > {


     E[ ] allValues();

     N value();

     public String getNome();

     static < N extends Number, E extends EnumTypes< E, N > > E find( E [] enumTypes, N value ){
         if( value == null ) return null;
         if( enumTypes == null || enumTypes.length == 0 ) return null;
         for( E types: enumTypes) if( value.equals( types.value() ) ) return types;
         return null;
     }

    default List<E> others() {
        List<E> list = new LinkedList<>();
        for( E etNum : this.allValues() ){
            if( !this.equals( etNum ) )  list.add( etNum );

        }
        return list;
    }

}
