package st.ggviario.house.model;

import com.google.gson.JsonObject;
import st.jigahd.support.sql.SQLRow;

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


    @Override
    public String toString() {
        return this.unidadeNome;
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

        public UnidadeBuilder load( SQLRow row ){
            this.load( row.toMap() );
            return this;
        }

        public UnidadeBuilder load( Map<String, Object > map ){
            return loader(
                    map.get("unidade_id"),
                    map.get("unidade_nome"),
                    map.get("unidade_codigo"),
                    map.get("unidade_estado")
            );
        }

        public UnidadeBuilder load(JsonObject element){
            return loader(
                    element.get("unidade_id"),
                    element.get( "unidade_nome" ),
                    element.get( "unidade_codigo" ),
                    element.get(  "unidade_estado" )
            );
        }

        private UnidadeBuilder loader(Object id, Object nome, Object codigo, Object estado ){
            this.id = SQLRow.uuidOf(id);
            this.nome = SQLRow.stringOf(nome);
            this.codigo = SQLRow.stringOf(codigo);
            this.estado = SQLRow.shortOf(estado);
            return this;
        }




    }

}
