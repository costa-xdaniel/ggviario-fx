package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Producao {

    private UUID producaoID;
    private String producaoCodigo;
    private Produto produto;
    private Setor setor;
    private Colaborador colaborador;
    private Colaborador colaboradorAtualizacao;
    private Double producaoQuantidade;
    private Date producaoData;
    private ProducaoEstado producaoEstado;
    private Date producaoDataRegisto;
    private Date producaoDataAtualizacao;

    public UUID getProducaoID() {
        return producaoID;
    }

    public String getProducaoCodigo() {
        return producaoCodigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public Setor getSetor() {
        return setor;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Colaborador getColaboradorAtualizacao() {
        return colaboradorAtualizacao;
    }

    public Double getProducaoQuantidade() {
        return producaoQuantidade;
    }

    public Date getProducaoData() {
        return producaoData;
    }

    public ProducaoEstado getProducaoEstado() {
        return producaoEstado;
    }

    public Date getProducaoDataRegisto() {
        return producaoDataRegisto;
    }

    public Date getProducaoDataAtualizacao() {
        return producaoDataAtualizacao;
    }

    public enum ProducaoEstado implements EnumTypes< ProducaoEstado, Short> {

        ATIVO( 1, "Ativo" ),

        ;
        private short estado;
        private String nome;

        ProducaoEstado(int estado, String nome ) {
            this.estado = (short) estado;
            this.nome = nome;
        }

        @Override
        public ProducaoEstado[] allValues() {
            return values();
        }

        @Override
        public Short value() {
            return this.estado;
        }

        @Override
        public String getNome() {
            return null;
        }

    }

    public static class ProducaoBuilder {
        private UUID id;
        private String codigo;
        private Produto produto;
        private Setor setor;
        private Colaborador colaborador;
        private Colaborador colaboradorAtualizacao;
        private Double quantidade;
        private Date data;
        private ProducaoEstado estado;
        private Date dataRegisto;
        private Date dataAtualizacao;

        public Producao build(){
            Producao producao = new Producao();
            producao.producaoID = this.id;
            producao.producaoCodigo = this.codigo;
            producao.produto = this.produto;
            producao.setor = this.setor;
            producao.colaborador = this.colaborador;
            producao.colaboradorAtualizacao = this.colaboradorAtualizacao;
            producao.producaoData = this.data;
            producao.producaoQuantidade = this.quantidade;
            producao.producaoEstado = this.estado;
            producao.producaoDataRegisto = this.dataRegisto;
            producao.producaoDataAtualizacao = this.dataAtualizacao;
            return producao;
        }

        public ProducaoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public ProducaoBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public ProducaoBuilder setProduto(Produto produto) {
            this.produto = produto;
            return this;
        }

        public ProducaoBuilder setSetor(Setor setor) {
            this.setor = setor;
            return this;
        }

        public ProducaoBuilder setColaborador(Colaborador colaborador) {
            this.colaborador = colaborador;
            return this;
        }

        public ProducaoBuilder setColaboradorAtualizacao(Colaborador colaboradorAtualizacao) {
            this.colaboradorAtualizacao = colaboradorAtualizacao;
            return this;
        }

        public ProducaoBuilder setQuantidade(Double quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public ProducaoBuilder setData(Date data) {
            this.data = data;
            return this;
        }

        public ProducaoBuilder setEstado(ProducaoEstado estado) {
            this.estado = estado;
            return this;
        }

        public ProducaoBuilder setDataRegisto(Date dataRegisto) {
            this.dataRegisto = dataRegisto;
            return this;
        }

        public ProducaoBuilder setDataAtualizacao(Date dataAtualizacao) {
            this.dataAtualizacao = dataAtualizacao;
            return this;
        }

        public ProducaoBuilder load(Map< String, Object > map ){
            this.id = SQLRow.uuidOf( map.get( "producao_id" ) );
            this.codigo = SQLRow.stringOf( map.get( "producao_codigo" ) );
            this.quantidade = SQLRow.doubleOf( map.get( "producao_quantidade" ) );
            this.data = SQLRow.dateOf( map.get( "producao_data" ) );
            this.estado = EnumTypes.find( ProducaoEstado.values(), SQLRow.shortOf( map.get( "producao_estado" ) ) );
            this.dataRegisto = SQLRow.dateOf( map.get( "producao_dataregisto" ) );
            this.dataAtualizacao = SQLRow.dateOf( map.get( "producao_dataatualizacao" ) );
            return this;
        }
    }
}
