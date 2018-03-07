package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Map;
import java.util.UUID;

public class Unidade {

    private UUID unidadeId;
    private String unidadeNome;
    private String unidadeCodigo;
    private UnidadeEstado unidadeEstado;
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

    public UnidadeEstado getUnidadeEstado() {
        return unidadeEstado;
    }

    public String getUnidadeEstadoDesc() {
        return unidadeEstadoDesc;
    }


    @Override
    public String toString() {
        return this.unidadeNome;
    }

    public enum UnidadeEstado implements EnumTypes < UnidadeEstado, Short > {

        ATIVO( 1, "Ativo" ),
        FECHADO( 0, "Fechado" ) ;

        private Short estado;
        private String name;

        UnidadeEstado(Integer estado, String name) {
            this.estado = estado.shortValue();
            this.name = name;
        }

        @Override
        public UnidadeEstado[] allValues() {
            return UnidadeEstado.values();
        }

        @Override
        public Short value() {
            return this.estado;
        }

        @Override
        public String getNome() {
            return this.name;
        }
    }

    public static class UnidadeBuilder{

        private UUID id;
        private String nome;
        private String codigo;
        private UnidadeEstado estado;
        private String estadoDesc;

        public Unidade build() {
            Unidade unidade = new Unidade();
            unidade.unidadeId = this.id;
            unidade.unidadeNome = this.nome;
            unidade.unidadeCodigo = this.codigo;
            unidade.unidadeEstado = this.estado;
            unidade.unidadeEstadoDesc = this.estadoDesc;
            return unidade;
        }

        public UnidadeBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public UnidadeBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public UnidadeBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public UnidadeBuilder estado(UnidadeEstado estado) {
            this.estado = estado;
            return this;
        }

        public UnidadeBuilder estadoDesc( String estadoDesc) {
            this.estadoDesc = estadoDesc;
            return this;
        }

        public UnidadeBuilder load( Map<String, Object > map ){
            this.id = SQLRow.uuidOf( map.get( "unidade_id" ) );
            this.nome = SQLRow.stringOf( map.get( "unidade_nome" ) );
            this.codigo = SQLRow.stringOf( map.get( "unidade_codigo" ) );
            this.estado = EnumTypes.find( UnidadeEstado.values(),  SQLRow.shortOf( map.get("unidade_estado") ) );
            return this;
        }

    }

}
