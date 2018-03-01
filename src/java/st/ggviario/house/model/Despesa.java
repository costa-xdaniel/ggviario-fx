package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Despesa {

    private UUID despesaId;
    private String despesaCodigo;
    private Fornecedor fornecedor;
    private Producto producto;
    private Unidade unidade;
    private Colaborador colaborador;
    private Colaborador colaboradorAtualizacao;
    private Date despesaData;
    private String despesaFaturaNumero;
    private Double despesaQuantidade;
    private Double despesaQuntidadeProduto;
    private Double despesaMontanteUnitario;
    private Double despesaMontanteTotal;
    private Double despesaMontanteAmortizado;
    private Date despedaDataUltimaAmortizacao;
    private Date despesaDataRegisto;
    private Date despesaDataAtualizacao;
    private DespesaEstado estado;

    public UUID getDespesaId() {
        return despesaId;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Colaborador getColaboradorAtualizacao() {
        return colaboradorAtualizacao;
    }

    public Date getDespesaData() {
        return despesaData;
    }

    public String getDespesaFaturaNumero() {
        return despesaFaturaNumero;
    }

    public Double getDespesaQuantidade() {
        return despesaQuantidade;
    }

    public Double getDespesaQuntidadeProduto() {
        return despesaQuntidadeProduto;
    }

    public Double getDespesaMontanteUnitario() {
        return despesaMontanteUnitario;
    }

    public Double getDespesaMontanteTotal() {
        return despesaMontanteTotal;
    }

    public Double getDespesaMontanteAmortizado() {
        return despesaMontanteAmortizado;
    }

    public Date getDespedaDataUltimaAmortizacao() {
        return despedaDataUltimaAmortizacao;
    }

    public Date getDespesaDataRegisto() {
        return despesaDataRegisto;
    }

    public Date getDespesaDataAtualizacao() {
        return despesaDataAtualizacao;
    }

    public DespesaEstado getEstado() {
        return estado;
    }

    public String getDespesaCodigo() {
        return despesaCodigo;
    }

    public Double getDespesaMontantePendente() {
        return this.despesaMontanteTotal - this.despesaMontanteAmortizado;
    }

    public enum DespesaEstado implements EnumTypes< DespesaEstado, Short> {

        PENDENTE ( 2, "Pendente" ),
        PAGAMENTO ( 1, "Pagamento"),
        PAGO ( 0, "Pago"),
        ANULADO( -1, "Anulado" );

        private short estado;
        private String showName;

        DespesaEstado(int estado, String showName){
            this.estado = (short) estado;
            this.showName = showName;
        }

        @Override
        public DespesaEstado[] allValues() {
            return values();
        }

        @Override
        public Short value() {
            return this.estado;
        }

        public String showName( ){
            return this.showName;
        }
    }

    public static class DespesaBuilder{
        private UUID id;
        private  String codigo;
        private Fornecedor fornecedor;
        private Producto producto;
        private Unidade unidade;
        private Colaborador colaborador;
        private Colaborador colaboradorAtualizacao;
        private Date data;
        private String faturaNumero;
        private Double quantidade;
        private Double quantidadeProduto;
        private Double montanteUnitario;
        private Double montanteTotal;
        private Double montanteAmortizado;
        private Date dataUltimaAmortizacao;
        private Date dataRegisto;
        private Date dataAtualizacao;
        private DespesaEstado estado;

        public Despesa build() {
            Despesa despesa = new Despesa();
            despesa.estado = this.estado;
            despesa.unidade = this.unidade;
            despesa.producto = this.producto;
            despesa.despesaId = this.id;
            despesa.fornecedor = this.fornecedor;
            despesa.colaborador = this.colaborador;
            despesa.despesaData = this.data;
            despesa.despesaCodigo = this.codigo;
            despesa.despesaQuantidade = this.quantidade;
            despesa.despesaDataRegisto = this.dataRegisto;
            despesa.despesaFaturaNumero = this.faturaNumero;
            despesa.despesaMontanteTotal = this.montanteTotal;
            despesa.despesaDataAtualizacao = this.dataAtualizacao;
            despesa.colaboradorAtualizacao = this.colaboradorAtualizacao;
            despesa.despesaMontanteUnitario = this.montanteUnitario;
            despesa.despesaMontanteAmortizado = this.montanteAmortizado;
            despesa.despedaDataUltimaAmortizacao = this.dataUltimaAmortizacao;
            return despesa;
        }

        public DespesaBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public DespesaBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public DespesaBuilder setFornecedor(Fornecedor fornecedor) {
            this.fornecedor = fornecedor;
            return this;
        }

        public DespesaBuilder setProducto(Producto producto) {
            this.producto = producto;
            return this;
        }

        public DespesaBuilder setUnidade(Unidade unidade) {
            this.unidade = unidade;
            return this;
        }

        public DespesaBuilder setColaborador(Colaborador colaborador) {
            this.colaborador = colaborador;
            return this;
        }

        public DespesaBuilder setColaboradorAtualizacao(Colaborador colaboradorAtualizacao) {
            this.colaboradorAtualizacao = colaboradorAtualizacao;
            return this;
        }

        public DespesaBuilder setData(Date data) {
            this.data = data;
            return this;
        }

        public DespesaBuilder setFaturaNumero(String faturaNumero) {
            this.faturaNumero = faturaNumero;
            return this;
        }

        public DespesaBuilder setQuantidade(Double quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public DespesaBuilder setQuantidadeProduto(Double quantidadeProduto) {
            this.quantidadeProduto = quantidadeProduto;
            return this;
        }

        public DespesaBuilder setMontanteUnitario(Double montanteUnitario) {
            this.montanteUnitario = montanteUnitario;
            return this;
        }

        public DespesaBuilder setMontanteTotal(Double montanteTotal) {
            this.montanteTotal = montanteTotal;
            return this;
        }

        public DespesaBuilder setMontanteAmortizado(Double montanteAmortizado) {
            this.montanteAmortizado = montanteAmortizado;
            return this;
        }

        public DespesaBuilder setDataUltimaAmortizacao(Date dataUltimaAmortizacao) {
            this.dataUltimaAmortizacao = dataUltimaAmortizacao;
            return this;
        }

        public DespesaBuilder setDataRegisto(Date dataRegisto) {
            this.dataRegisto = dataRegisto;
            return this;
        }

        public DespesaBuilder setDataAtualizacao(Date dataAtualizacao) {
            this.dataAtualizacao = dataAtualizacao;
            return this;
        }

        public DespesaBuilder setEstado(DespesaEstado estado) {
            this.estado = estado;
            return this;
        }

        public DespesaBuilder load(Map<String, Object > map ){
            this.id = SQLRow.uuidOf( map.get( "despesa_id" ) );
            this.codigo =SQLRow.stringOf( map.get( "despesa_codigo" ) );
            this.data = SQLRow.dateOf( map.get("despesa_data") );
            this.faturaNumero = SQLRow.stringOf( map.get( "despesa_numerofatura" ) );
            this.quantidade = SQLRow.doubleOf( map.get( "despesa_quatidade" ) );
            this.quantidadeProduto = SQLRow.doubleOf( map.get( "despesa_quantidadeproduto" ) );
            this.montanteUnitario =  SQLRow.doubleOf( map.get( "despesa_montanteunitario" ) );
            this.montanteTotal = SQLRow.doubleOf( map.get( "despesa_montantetotal" ) );
            this.montanteAmortizado = SQLRow.doubleOf( map.get( "despesa_montanteamortizado" ) );
            this.dataUltimaAmortizacao = SQLRow.dateOf( map.get( "despesa_dataultimamovimento" ) );
            this.dataRegisto = SQLRow.dateOf( map.get( "despesa_dataregisto" ) );
            this.dataAtualizacao = SQLRow.dateOf( map.get( "despesa_dataatualizacao" ) );
            this.estado = EnumTypes.find( DespesaEstado.values(), SQLRow.shortOf(map.get( "despesa_estado"  )) );
            return this;
        }
    }
}
