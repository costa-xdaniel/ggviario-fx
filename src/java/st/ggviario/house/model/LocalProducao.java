package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class LocalProducao {
    private UUID localProducaoId;
    private Double localProducaoQuantidade;
    private LocalProducaoEstado localProducaoEstado;
    private Date localProducaoDataRegisto;
    private Date localProducaoDataAtualizacao;
    private Colaborador colaborador;
    private Colaborador colaboradorAtualizacao;
    private Setor setor;
    private Produto produto;

    public UUID getLocalProducaoId() {
        return localProducaoId;
    }

    public Double getLocalProducaoQuantidade() {
        return localProducaoQuantidade;
    }

    public LocalProducaoEstado getLocalProducaoEstado() {
        return localProducaoEstado;
    }

    public Date getLocalProducaoDataRegisto() {
        return localProducaoDataRegisto;
    }

    public Date getLocalProducaoDataAtualizacao() {
        return localProducaoDataAtualizacao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Colaborador getColaboradorAtualizacao() {
        return colaboradorAtualizacao;
    }

    public Setor getSetor() {
        return setor;
    }

    public Produto getProduto() {
        return produto;
    }

    public enum LocalProducaoEstado implements EnumTypes< LocalProducaoEstado, Short >{
        ATIVO( 1, "Ativo" ),
        FECHADO( 0, "Fechado" ),
        HERDADO( 2, "Herdado" );

        private short estado;
        private String nome;

        LocalProducaoEstado(int estado, String nome) {
            this.estado = (short) estado;
            this.nome = nome;
        }

        @Override
        public LocalProducaoEstado[] allValues() {
            return values();
        }

        @Override
        public Short value() {
            return this.estado;
        }

        @Override
        public String getNome() {
            return this.nome;
        }
    }

    public static class LocalProducaoBuilder{
        private UUID id;
        private Double quantidade;
        private LocalProducaoEstado estado;
        private Date dataRegisto;
        private Date dataAtualizacao;
        private Colaborador colaborador;
        private Colaborador colaboradorAtualizacao;
        private Setor setor;
        private Produto produto;

        public LocalProducao build(){
            LocalProducao localProducao = new LocalProducao();
            localProducao.localProducaoId = this.id;
            localProducao.localProducaoQuantidade = this.quantidade;
            localProducao.localProducaoEstado = this.estado;
            localProducao.localProducaoDataRegisto = this.dataRegisto;
            localProducao.localProducaoDataAtualizacao = this.dataAtualizacao;
            localProducao.colaborador = this.colaborador;
            localProducao.colaboradorAtualizacao = this.colaboradorAtualizacao;
            localProducao.setor = this.setor;
            localProducao.produto = this.produto;
            return localProducao;
        }

        public LocalProducaoBuilder load(Map< String, Object > row ){
            this.id = SQLRow.uuidOf( row.get("localproducao_id"));
            this.quantidade = SQLRow.doubleOf( row.get( "localproducao_quantidade") );
            this.estado = EnumTypes.find( LocalProducaoEstado.values(), SQLRow.shortOf( row.get( "localproducao_estado" ) ));
            this.dataRegisto = SQLRow.dateOf( row.get( "localproducao_dataregisto" ) );
            this.dataAtualizacao = SQLRow.dateOf( row.get( "localproducao_dataatualizacao" ) );
            return this;
        }

        public LocalProducaoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public LocalProducaoBuilder setQuantidade(Double quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public LocalProducaoBuilder setEstado(LocalProducaoEstado estado) {
            this.estado = estado;
            return this;
        }

        public LocalProducaoBuilder setDataRegisto(Date dataRegisto) {
            this.dataRegisto = dataRegisto;
            return this;
        }

        public LocalProducaoBuilder setDataAtualizacao(Date dataAtualizacao) {
            this.dataAtualizacao = dataAtualizacao;
            return this;
        }

        public LocalProducaoBuilder setColaborador(Colaborador colaborador) {
            this.colaborador = colaborador;
            return this;
        }

        public LocalProducaoBuilder setColaboradorAtualizacao(Colaborador colaboradorAtualizacao) {
            this.colaboradorAtualizacao = colaboradorAtualizacao;
            return this;
        }

        public LocalProducaoBuilder setSetor(Setor setor) {
            this.setor = setor;
            return this;
        }

        public LocalProducaoBuilder setProduto(Produto produto) {
            this.produto = produto;
            return this;
        }
    }
}
