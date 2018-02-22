package st.ggviario.house.model;

import java.util.Map;

public class Movimento {

    private Movimento(){}


    public static class MovimentoBuilder {


        public Movimento build(){
            Movimento movimento = new Movimento();
            return movimento;
        }

        public MovimentoBuilder load(Map<String, Object > map ){
            return this;
        }
    }
}
