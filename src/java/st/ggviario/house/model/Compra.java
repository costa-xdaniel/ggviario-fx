package st.ggviario.house.model;

import java.util.Date;
import java.util.UUID;

public class Compra {

    private UUID compraId;
    private Producto producto;
    private Unidade unidade;
    private Cliente cliente;
    private Tipocompra tipocompra;
    private String compraFaturaNumero;
    private Double compraQuantidade;
    private Double compraQuantidadeProduto;
    private Double compraMontanteUnitario;
    private Double compraMontanteBruto;
    private Double compraMontanteDesconto;
    private Double compraMontantePagar;
    private Double compraMontanteAmortizado;
    private Date compraData;
    private Date compraDataFinalizar;
    private Date compraDataFim;
    private Double compraDataUltimaMovimentacao;

    public UUID getCompraId() {
        return compraId;
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

    public Tipocompra getTipocompra() {
        return tipocompra;
    }

    public String getCompraFaturaNumero() {
        return compraFaturaNumero;
    }

    public Double getCompraQuantidade() {
        return compraQuantidade;
    }

    public Double getCompraQuantidadeProduto() {
        return compraQuantidadeProduto;
    }

    public Double getCompraMontanteUnitario() {
        return compraMontanteUnitario;
    }

    public Double getCompraMontanteBruto() {
        return compraMontanteBruto;
    }

    public Double getCompraMontanteDesconto() {
        return compraMontanteDesconto;
    }

    public Double getCompraMontantePagar() {
        return compraMontantePagar;
    }

    public Double getCompraMontanteAmortizado() {
        return compraMontanteAmortizado;
    }

    public Date getCompraData() {
        return compraData;
    }

    public Date getCompraDataFinalizar() {
        return compraDataFinalizar;
    }

    public Date getCompraDataFim() {
        return compraDataFim;
    }

    public Double getCompraDataUltimaMovimentacao() {
        return compraDataUltimaMovimentacao;
    }

    public static  class CompraBuilder{

        public Compra build(){
            Compra compra = new Compra();
            compra.compraId = this.id;
            compra.producto = this.producto;
            compra.unidade = this.unidade;
            compra.cliente = this.cliente;
            compra.tipocompra = this.tipocompra;
            compra.compraFaturaNumero =this.faturaNumero;
            compra.compraQuantidade = this.quantidade;
            compra.compraQuantidadeProduto = this.quantidadeProduto;
            compra.compraMontanteBruto = this.montanteBruto;
            compra.compraMontanteDesconto = this.montanteDesconto;
            compra.compraMontantePagar = this.montantePagar;
            compra.compraMontanteAmortizado = this.montanteAmortizado;
            compra.compraData = this.data;
            compra.compraDataFinalizar = this.dataFinalizar;
            compra.compraDataFim = this.dataFim;
            compra.compraDataUltimaMovimentacao = this.dataUltimaMovimentacao;
            return compra;
        }


        private UUID id;
        private Producto producto;
        private Unidade unidade;
        private Cliente cliente;
        private Tipocompra tipocompra;
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

        public CompraBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CompraBuilder produto(Producto producto) {
            this.producto = producto;
            return this;
        }

        public CompraBuilder unidade(Unidade unidade) {
            this.unidade = unidade;
            return this;
        }

        public CompraBuilder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public CompraBuilder tipocompra(Tipocompra tipocompra) {
            this.tipocompra = tipocompra;
            return this;
        }

        public CompraBuilder faturaNumaro(String faturaNumero) {
            this.faturaNumero = faturaNumero;
            return this;
        }

        public CompraBuilder quantidade(Double quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public CompraBuilder quantidadeProduto(Double quantidadeProduto) {
            this.quantidadeProduto = quantidadeProduto;
            return this;
        }

        public CompraBuilder monatanteUnitario(Double montanteUnitario) {
            this.montanteUnitario = montanteUnitario;
            return this;
        }

        public CompraBuilder montnateBruto(Double montanteBruto) {
            this.montanteBruto = montanteBruto;
            return this;
        }

        public CompraBuilder montanteDesconto(Double montanteDesconto) {
            this.montanteDesconto = montanteDesconto;
            return this;
        }

        public CompraBuilder montantePagar(Double montantePagar) {
            this.montantePagar = montantePagar;
            return this;
        }

        public CompraBuilder montanteAmortizado(Double montanteAmortizado) {
            this.montanteAmortizado = montanteAmortizado;
            return this;
        }

        public CompraBuilder data(Date data) {
            this.data = data;
            return this;
        }

        public CompraBuilder dataFilanizar(Date dataFinalizar) {
            this.dataFinalizar = dataFinalizar;
            return this;
        }

        public CompraBuilder dataFim(Date dataFim) {
            this.dataFim = dataFim;
            return this;
        }

        public CompraBuilder dataUltiimaMovimentacao(Double dataUltimaMovimentacao) {
            this.dataUltimaMovimentacao = dataUltimaMovimentacao;
            return this;
        }
    }
}
