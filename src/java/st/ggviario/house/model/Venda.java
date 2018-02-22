package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Venda {

    private UUID vendaId;
    private Producto producto;
    private Unidade unidade;
    private Cliente cliente;
    private TipoVenda tipoVenda;
    private String vendaFaturaNumero;
    private Double vandaQuantidade;
    private Double vendaQuantidadeProduto;
    private Double vandaMontanteUnitario;
    private Double vendaMontanteBruto;
    private Double vendaMontanteDesconto;
    private Double vendaMontantePagar;
    private Double vendaMontanteAmortizado;
    private Date vendaData;
    private Date vendaDataFinalizar;
    private Date vendaDataFim;
    private Double vendaDataUltimaMovimentacao;

    public UUID getVendaId() {
        return vendaId;
    }

    public Producto getProducto() {
        return producto;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoVenda getTipoVenda() {
        return tipoVenda;
    }

    public String getVendaFaturaNumero() {
        return vendaFaturaNumero;
    }

    public Double getVandaQuantidade() {
        return vandaQuantidade;
    }

    public Double getVendaQuantidadeProduto() {
        return vendaQuantidadeProduto;
    }

    public Double getVendaMontenteUnidario() {
        return vandaMontanteUnitario;
    }

    public Double getVendaMontanteBruto() {
        return vendaMontanteBruto;
    }

    public Double getVendaMontanteDesconto() {
        return vendaMontanteDesconto;
    }

    public Double getVendaMontantePagar() {
        return vendaMontantePagar;
    }

    public Double getVendaMontanteAmortizado() {
        return vendaMontanteAmortizado;
    }

    public Date getVendaData() {
        return vendaData;
    }

    public Date getVendaDataFinalizar() {
        return vendaDataFinalizar;
    }

    public Date getVendaDataFim() {
        return vendaDataFim;
    }

    public Double getVendaDataUltimaMovimentacao() {
        return vendaDataUltimaMovimentacao;
    }

    public static  class VendaBuilder {

        public Venda build(){
            Venda venda = new Venda();
            venda.vendaId = this.id;
            venda.producto = this.producto;
            venda.unidade = this.unidade;
            venda.cliente = this.cliente;
            venda.tipoVenda = this.tipoVenda;
            venda.vendaFaturaNumero =this.faturaNumero;
            venda.vandaQuantidade = this.quantidade;
            venda.vendaQuantidadeProduto = this.quantidadeProduto;
            venda.vendaMontanteBruto = this.montanteBruto;
            venda.vendaMontanteDesconto = this.montanteDesconto;
            venda.vendaMontantePagar = this.montantePagar;
            venda.vendaMontanteAmortizado = this.montanteAmortizado;
            venda.vandaMontanteUnitario = this.montanteUnitario;

            venda.vendaData = this.data;
            venda.vendaDataFinalizar = this.dataFinalizar;
            venda.vendaDataFim = this.dataFim;
            venda.vendaDataUltimaMovimentacao = this.dataUltimaMovimentacao;
            return venda;
        }


        private UUID id;
        private Producto producto;
        private Unidade unidade;
        private Cliente cliente;
        private TipoVenda tipoVenda;
        private String faturaNumero;
        private Double quantidade;
        private Double quantidadeProduto;
        private Double montanteUnitario;
        private Double montanteBruto;
        private Double montanteDesconto;
        private Double montantePagar;
        private Double montanteAmortizado;
        private Date data;
        private Date dataFinalizar;
        private Date dataFim;
        private Double dataUltimaMovimentacao;

        public VendaBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public VendaBuilder produto(Producto producto) {
            this.producto = producto;
            return this;
        }

        public VendaBuilder unidade(Unidade unidade) {
            this.unidade = unidade;
            return this;
        }

        public VendaBuilder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public VendaBuilder tipoVenda(TipoVenda tipoVenda) {
            this.tipoVenda = tipoVenda;
            return this;
        }

        public VendaBuilder faturaNumaro(String faturaNumero) {
            this.faturaNumero = faturaNumero;
            return this;
        }

        public VendaBuilder quantidade(Double quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public VendaBuilder quantidadeProduto(Double quantidadeProduto) {
            this.quantidadeProduto = quantidadeProduto;
            return this;
        }

        public VendaBuilder montanteUnitario(Double montanteUnitario) {
            this.montanteUnitario = montanteUnitario;
            return this;
        }

        public VendaBuilder montanteBruto(Double montanteBruto) {
            this.montanteBruto = montanteBruto;
            return this;
        }

        public VendaBuilder montanteDesconto(Double montanteDesconto) {
            this.montanteDesconto = montanteDesconto;
            return this;
        }

        public VendaBuilder montantePagar(Double montantePagar) {
            this.montantePagar = montantePagar;
            return this;
        }

        public VendaBuilder montanteAmortizado(Double montanteAmortizado) {
            this.montanteAmortizado = montanteAmortizado;
            return this;
        }

        public VendaBuilder data(Date data) {
            this.data = data;
            return this;
        }

        public VendaBuilder dataFilanizar(Date dataFinalizar) {
            this.dataFinalizar = dataFinalizar;
            return this;
        }

        public VendaBuilder dataFim(Date dataFim) {
            this.dataFim = dataFim;
            return this;
        }

        public VendaBuilder dataUltiimaMovimentacao(Double dataUltimaMovimentacao) {
            this.dataUltimaMovimentacao = dataUltimaMovimentacao;
            return this;
        }

        public VendaBuilder load(SQLRow row) {
            return this;
        }

        public VendaBuilder load( Map<String, Object> map) {
            return this;
        }
    }
}
