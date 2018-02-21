package st.ggviario.house.model;

import st.jigahd.support.sql.postgresql.PostgresSQLRow;

public class Distrito {

    private short distritoId;
    private String distritoNome;

    public short getDistritoId() {
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
        private short id;
        private String nome;

        public Distrito build() {
            Distrito distrito = new Distrito();
            distrito.distritoId = id;
            distrito.distritoNome = nome;
            return distrito;
        }

        public DistritoBuilder id( short id) {
            this.id = id;
            return this;
        }

        public DistritoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public DistritoBuilder load(PostgresSQLRow row) {
            this.id = row.asShort( "distrito_id" );
            this.nome = row.asString( "distrito_nome" );
            return this;
        }
    }
}
