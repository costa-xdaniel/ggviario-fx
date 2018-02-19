package st.ggviario.house.model;

public class Categoria {

    private String categoriaId;
    private String categoriaNome;


    public String getCategoriaId() {
        return categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public static class CategoriaBuilder{
        private String id;
        private String nome;

        public Categoria build() {
            Categoria categoria = new Categoria();
            categoria.categoriaId = this.id;
            categoria.categoriaNome = this.nome;
            return categoria;
        }


        public CategoriaBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public CategoriaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }
    }

}
