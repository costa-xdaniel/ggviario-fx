package st.ggviario.house.model;

import st.jigahd.support.sql.postgresql.PostgresSQLRow;

import java.util.Map;
import java.util.UUID;

public class Unidade {

    private UUID unidadeId;
    private String unidadeNome;
    private String unidadeCodigo;
    private Short unidadeEstado;
    private String unidadeEstadoDesc;

    public UUID getUnidadeId() {
        return unidadeId;
    }

    public String getUnidadeNome() {
        return unidadeNome;
    }

    public String getUnidadeCodigo() {
        return unidadeCodigo;
    }

    public Short getUnidadeEstado() {
        return unidadeEstado;
    }

    public String getUnidadeEstadoDesc() {
        return unidadeEstadoDesc;
    }

    public static class UnidadeBuilder{

        private UUID id;
        private String nome;
        private String codigo;
        private Short estado;
        private String estadoDesc;

        public Unidade build() {
            Unidade unidade = new Unidade();
            unidade.unidadeId = this.id;
            unidade.unidadeCodigo = this.codigo;
            unidade.unidadeEstado = this.estado;
            unidade.unidadeEstadoDesc = this.estadoDesc;
            return unidade;
        }

        public UnidadeBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public UnidadeBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public UnidadeBuilder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public UnidadeBuilder estado(Short estado) {
            this.estado = estado;
            return this;
        }

        public UnidadeBuilder estadoDesc( String estadoDesc) {
            this.estadoDesc = estadoDesc;
            return this;
        }

        public UnidadeBuilder load( PostgresSQLRow row ){
            this.load( row.toMap() );
            return this;
        }

        public UnidadeBuilder load( Map<String, Object > map ){
            this.id = (UUID) map.get( "unidade_id" );
            this.nome = (String) map.get( "unidade_nome" );
            this.codigo = (String) map.get( "unidade_codigo" );
            this.estado = (Short) map.get( "unidade_estado" );
            this.estadoDesc = (String) map.get( "unidade_estadodesc" );
            return this;
        }


    }

}
