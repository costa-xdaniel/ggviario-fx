package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Categoria {

    private UUID categoriaId;
    private String categoriaCodigo;
    private String categoriaNome;
    private Short categoriaPosicao;
    private CategoriaEstado categoriaEstado;
    private Date categoriaDataRegisto;
    private Date categoriaDaraAtualizacao;
    private Short categoriaNivel;
    private Categoria categoriaSuper;
    private Colaborador colaborador;
    private Colaborador colaboradorAtualizacao;

    public UUID getCategoriaId() {
        return categoriaId;
    }

    public String getCategoriaCodigo() {
        return categoriaCodigo;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public Short getCategoriaPosicao() {
        return categoriaPosicao;
    }

    public CategoriaEstado getCategoriaEstado() {
        return categoriaEstado;
    }

    public Date getCategoriaDataRegisto() {
        return categoriaDataRegisto;
    }

    public Date getCategoriaDaraAtualizacao() {
        return categoriaDaraAtualizacao;
    }

    public Short getCategoriaNivel() {
        return categoriaNivel;
    }

    public Categoria getCategoriaSuper() {
        return categoriaSuper;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Colaborador getColaboradorAtualizacao() {
        return colaboradorAtualizacao;
    }

    @Override
    public String toString() {
        return this.categoriaNome;
    }

    public enum CategoriaEstado implements EnumTypes <CategoriaEstado, Short >{
        ATIVO( 1 ),
        FECHADO( 0 );

        private final short estado;

        CategoriaEstado(int estado ){
            this.estado  = (short) estado;
        }

        @Override
        public CategoriaEstado[] allValues() {
            return values();
        }

        @Override
        public Short value() {
            return this.estado;
        }
    }

    public static class CategoriaBuilder {
        private UUID id;
        private String codigo;
        private String nome;
        private Short posicao;
        private Short totalSubSetor;
        private CategoriaEstado estado;
        private Date dataRegisto;
        private Date dataAtualizacao;
        private Short nivel;
        private Categoria categoriaSuper;
        private Colaborador colaborador;
        private Colaborador colaboradorAtualizacao;

        public Categoria build(){
            Categoria setor = new Categoria();
            setor.categoriaId = this.id;
            setor.categoriaNome = this.nome;
            setor.categoriaCodigo = this.codigo;
            setor.categoriaPosicao = this.posicao;
            setor.categoriaEstado = this.estado;
            setor.categoriaDataRegisto = this.dataRegisto;
            setor.categoriaDaraAtualizacao = this.dataAtualizacao;
            setor.categoriaNivel = this.nivel;
            setor.categoriaSuper = this.categoriaSuper;
            setor.colaborador = this.colaborador;
            setor.colaboradorAtualizacao = this.colaboradorAtualizacao;
            return setor;
        }

        public CategoriaBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public CategoriaBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public CategoriaBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public CategoriaBuilder setPosicao(Short posicao) {
            this.posicao = posicao;
            return this;
        }

        public CategoriaBuilder setTotalSubSetor(Short totalSubSetor) {
            this.totalSubSetor = totalSubSetor;
            return this;
        }

        public CategoriaBuilder setEstado(CategoriaEstado estado) {
            this.estado = estado;
            return this;
        }

        public CategoriaBuilder setDataRegisto(Date dataRegisto) {
            this.dataRegisto = dataRegisto;
            return this;
        }

        public CategoriaBuilder setDataAtualizacao(Date dataAtualizacao) {
            this.dataAtualizacao = dataAtualizacao;
            return this;
        }

        public CategoriaBuilder setNivel(Short nivel) {
            this.nivel = nivel;
            return this;
        }

        public CategoriaBuilder setCategoriaSuper(Categoria categoriaSuper) {
            this.categoriaSuper = categoriaSuper;
            return this;
        }

        public CategoriaBuilder setColaborador(Colaborador colaborador) {
            this.colaborador = colaborador;
            return this;
        }

        public CategoriaBuilder setColaboradorAtualizacao(Colaborador colaboradorAtualizacao) {
            this.colaboradorAtualizacao = colaboradorAtualizacao;
            return this;
        }

        public CategoriaBuilder load(Map< String, Object > map ){
            this.id = SQLRow.uuidOf( map.get( "categoria_id" ) );
            this.nome = SQLRow.stringOf( map.get( "categoria_nome" ) );
            this.codigo = SQLRow.stringOf( map.get( "categoria_codigo" ) );
            this.posicao = SQLRow.shortOf( map.get( "categoria_posicao" ) );
            this.nivel = SQLRow.shortOf( map.get( "categoria_nivel" ) );
            this.estado = EnumTypes.find( CategoriaEstado.values(), SQLRow.shortOf( map.get( "categoria_estado" ) ) );
            this.dataRegisto = SQLRow.dateOf( map.get( "catagoria_dataregisto" ) );
            this.dataAtualizacao = SQLRow.dateOf( map.get( "categoria_dataatualizacao" ) );
            return this;
        }
    }



}
