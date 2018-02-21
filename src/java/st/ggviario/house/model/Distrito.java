package st.ggviario.house.model;

import st.jigahd.support.sql.postgresql.PostgresSQLRow;

import java.util.Map;

public class Distrito {

    private Short distritoId;
    private String distritoNome;

    public Short getDistritoId() {
        return distritoId;
    }

    public String getDistritoNome() {
        return distritoNome;
    }

    @Override
    public String toString() {
        return this.distritoNome;
    }

    public static class DistritoBuilder{

        private Short id;
        private String nome;

        public Distrito build() {
            Distrito distrito = new Distrito();
            distrito.distritoId = id;
            distrito.distritoNome = nome;
            return distrito;
        }

        public DistritoBuilder id( Short id) {
            this.id = id;
            return this;
        }

        public DistritoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public DistritoBuilder load( PostgresSQLRow row ){
            return this.load( row .toMap() );
        }

        public DistritoBuilder load( Map<String, Object> map) {
            this.id = (Short) map.get( "distrito_id" );
            this.nome = (String) map.get( "distrito_nome" );
            return this;
        }
    }
}
