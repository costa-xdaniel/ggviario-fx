package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Map;
import java.util.UUID;

public class Colaborador {

    private UUID colaboradorId;
    private String colaboradorNome;
    private String colaboradorApelido;

    public UUID getColaboradorId() {
        return colaboradorId;
    }

    public String getColaboradorNome() {
        return colaboradorNome;
    }

    public String getColaboradorApelido() {
        return colaboradorApelido;
    }

    public static class ColaboradorBuilder{

        private UUID id;
        private String nome;
        private String apelido;

        public Colaborador build(){
            Colaborador colaborador = new Colaborador();
            colaborador.colaboradorId = this.id;
            colaborador.colaboradorNome = this.nome;
            colaborador.colaboradorApelido = this.apelido;
            return colaborador;
        }

        public ColaboradorBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ColaboradorBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ColaboradorBuilder apelido(String apelido) {
            this.apelido = apelido;
            return this;
        }

        public ColaboradorBuilder load(SQLRow row ){
            return load( row.toMap() );
        }

        public ColaboradorBuilder load(Map<String, Object > map ){
            this.id = (UUID) map.get( "colaborador_id" );
            this.nome = (String) map.get( "colaborador_nome" );
            this.apelido = (String) map.get( "colaborador_apelido" );
            return this;
        }
    }

}
