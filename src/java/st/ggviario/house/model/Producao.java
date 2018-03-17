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
    private Double producaoQuantidadeTotal;
    private Double producaoQuantidadeComercial;
    private Double producaoQuantidadeDefeituosa;
    private Double producaoMontantePrevisto;
    private Integer producaoLancamento;
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

    public Double getProducaoQuantidadeTotal() {
        return producaoQuantidadeTotal;
    }

    public Double getProducaoQuantidadeComercial() {
        return producaoQuantidadeComercial;
    }

    public Double getProducaoQuantidadeDefeituosa() {
        return producaoQuantidadeDefeituosa;
    }

    public Double getProducaoMontantePrevisto() {
        return producaoMontantePrevisto;
    }

    public Integer getProducaoLancamento() {
        return producaoLancamento;
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
        private Double quantidadeTotal;
        private Double quantidadeComericial;
        private Double quantidadeDefeituosa;
        private Double montantePrevisto;
        private Integer lancamento;
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
            producao.producaoQuantidadeTotal = this.quantidadeTotal;
            producao.producaoQuantidadeComercial = this.quantidadeComericial;
            producao.producaoQuantidadeDefeituosa = this.quantidadeDefeituosa;
            producao.producaoMontantePrevisto = this.montantePrevisto;
            producao.producaoLancamento = this.lancamento;
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

        public ProducaoBuilder setQuantidadeTotal(Double quantidadeTotal) {
            this.quantidadeTotal = quantidadeTotal;
            return this;
        }

        public ProducaoBuilder setQuantidadeComericial(Double quantidadeComericial) {
            this.quantidadeComericial = quantidadeComericial;
            return this;
        }

        public ProducaoBuilder setQuantidadeDefeituosa(Double quantidadeDefeituosa) {
            this.quantidadeDefeituosa = quantidadeDefeituosa;
            return this;
        }

        public ProducaoBuilder setMontantePrevisto(Double montantePrevisto) {
            this.montantePrevisto = montantePrevisto;
            return this;
        }

        public ProducaoBuilder setLancamento(Integer lancamento) {
            this.lancamento = lancamento;
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
            this.quantidadeTotal = SQLRow.doubleOf( map.get( "producao_quantidadetotla" ) );
            this.quantidadeComericial = SQLRow.doubleOf( map.get( "producao_quantidadecomercial" ) );
            this.quantidadeDefeituosa = SQLRow.doubleOf( map.get( "producao_quantidadedefeituosa" ) );
            this.montantePrevisto = SQLRow.doubleOf( map.get( "producao_montanteprevisto" ) );
            this.lancamento = SQLRow.integerOf( map.get( "producao_lancamento" ) );
            this.data = SQLRow.dateOf( map.get( "producao_data" ) );
            this.estado = EnumTypes.find( ProducaoEstado.values(), SQLRow.shortOf( map.get( "producao_estado" ) ) );
            this.dataRegisto = SQLRow.dateOf( map.get( "producao_dataregisto" ) );
            this.dataAtualizacao = SQLRow.dateOf( map.get( "producao_dataatualizacao" ) );
            return this;
        }
    }
}
