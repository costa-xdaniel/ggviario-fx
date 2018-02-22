package st.ggviario.house.model;

import com.google.gson.annotations.Expose;
import st.jigahd.support.sql.SQLRow;

import java.util.Map;
import java.util.UUID;

public class Categoria {

    @Expose
    private UUID categoriaId;

    @Expose
    private String categoriaNome;


    public UUID getCategoriaId() {
        return categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    @Override
    public String toString() {
        return this.categoriaNome;
    }

    public static class CategoriaBuilder{
        private UUID id;
        private String nome;

        public Categoria build() {
            Categoria categoria = new Categoria();
            categoria.categoriaId = this.id;
            categoria.categoriaNome = this.nome;
            return categoria;
        }


        public CategoriaBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CategoriaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public CategoriaBuilder load(SQLRow row ){
            return this.load( row.toMap() );
        }

        public CategoriaBuilder load(Map< String, Object > map ){
            this.id = (UUID) map.get( "categoria_id" );
            this.nome = (String) map.get( "categoria_nome" );
            return this;
        }
    }

}
