package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLResource;

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
    private Double vendaMontanteUnitario;
    private Double vendaMontanteBruto;
    private Double vendaMontanteDesconto;
    private Double vendaMontantePagar;
    private Double vendaMontanteAmortizado;
    private Date vendaData;
    private Date vendaDataFinalizar;
    private Date vendaDataFim;
    private Date vendaDataUltimaMovimentacao;
    private Date vendaDataRegisto;
    private Date vaendaDataUltimaAtualizacao;
    private VendaEstado vendaEstado;


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

    public Double getVendaMontanteUnitario() {
        return vendaMontanteUnitario;
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

    public Date getVendaDataUltimaMovimentacao() {
        return vendaDataUltimaMovimentacao;
    }

    public Date getVendaDataRegisto() {
        return vendaDataRegisto;
    }

    public Date getVaendaDataUltimaAtualizacao() {
        return vaendaDataUltimaAtualizacao;
    }

    public VendaEstado getVendaEstado() {
        return vendaEstado;
    }

    public Double getVendaMontantePendente() {
        return this.vendaMontantePagar - this.vendaMontanteAmortizado;
    }

    public enum VendaEstado {

        PENDENTE( 2, "Pendente" ),
        PAGAMENTO(1, "Pagamento" ),
        PAGO(0, "Pago" ),
        ANULADO(-1, "Anulado" );

        private final int estado;
        private final String showName;

        VendaEstado(int estado, String showName ) {
            this.estado = estado;
            this.showName = showName;
        }

        public static VendaEstado find( int estado ) {
            for( VendaEstado ve: values() ){
                if( ve.estado == estado ) return ve;
            }
            return null;
        }

        public int getEstado() {
            return estado;
        }

        public String getShowName() {
            return showName;
        }
    }

    public String getTipoVendaCod() {
        return this.tipoVenda.name().toLowerCase();
    }

    public static  class VendaBuilder {

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
        private Date dataUltimaMovimentacao;
        private Short estado;
        private Date dataRegisto;
        private Date dataUltimaAtualizacao;

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
            if( venda.vendaMontanteDesconto == null ) venda.vendaMontanteDesconto = 0x0.0p0;
            venda.vendaMontantePagar = this.montantePagar;
            venda.vendaMontanteAmortizado = this.montanteAmortizado;
            venda.vendaMontanteUnitario = this.montanteUnitario;

            venda.vendaData = this.data;
            venda.vendaDataFinalizar = this.dataFinalizar;
            venda.vendaDataFim = this.dataFim;
            venda.vendaDataUltimaMovimentacao = this.dataUltimaMovimentacao;
            venda.vendaEstado = VendaEstado.find( this.estado );
            venda.vendaDataRegisto = this.dataRegisto;
            venda.vaendaDataUltimaAtualizacao = this.dataRegisto;
            return venda;
        }

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
            if( montanteDesconto == null ) montanteDesconto = 0x0.0p0;
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

        public VendaBuilder dataFim( Date dataFim) {
            this.dataFim = dataFim;
            return this;
        }

        public VendaBuilder dataUltiimaMovimentacao( Date dataUltimaMovimentacao) {
            this.dataUltimaMovimentacao = dataUltimaMovimentacao;
            return this;
        }

        public VendaBuilder load(SQLRow row) {

            this.id = row.asUUID( "venda_id" );
            this.tipoVenda = TipoVenda.valueOf( row.asShort( "tvenda_id" ) );
            if( tipoVenda == null ) throw  new RuntimeException("Tipo de venda esta nulo");
            this.faturaNumero = row.asString( "venda_faturanumero" );
            this.quantidade = row.asDouble( "venda_quantidade" );
            this.quantidadeProduto = row.asDouble( "venda_quantidadeproduto" );
            this.montanteUnitario = row.asDouble( "venda_montanteunitario" );
            this.montanteBruto = row.asDouble( "venda_montantebruto" );
            this.montanteDesconto = row.asDouble( "venda_montantedesconto" );
            this.montantePagar = row.asDouble( "venda_montantepagar" );
            this.montanteAmortizado = row.asDouble( "venda_montanteamortizado" );
            this.data = row.asDate( "venda_data" );
            this.dataFinalizar = row.asDate(  "venda_datafinalizar" );
            this.dataFim = row.asDate( "venda_datafim" );
            this.dataUltimaMovimentacao = row.asDate( "venda_dataultimamovimentacao" );
            this.estado = row.asShort( "venda_estado" );
            this.dataRegisto = row.asDate( "venda_dataregisto" );
            this.dataUltimaAtualizacao = row.asDate( "venda_dataatualizacao" );
            return this;
        }

        public VendaBuilder load( Map<String, Object> map) {
            this.id = SQLRow.uuidOf( map.get( "venda_id" ) );
            this.tipoVenda = TipoVenda.valueOf( SQLRow.shortOf(SQLResource.coalesce( map.get( "tvenda_id" ), map.get("venda_tvenda_id" ) ) ) );
            this.faturaNumero = SQLRow.stringOf( map.get( "venda_faturanumero" ) );
            this.quantidade = SQLRow.doubleOf( map.get( "venda_quantidade" ) );
            this.quantidadeProduto = SQLRow.doubleOf( map.get( "venda_quantidadeproduto" ) );
            this.montanteUnitario = SQLRow.doubleOf( map.get( "venda_montanteunitario" ) );
            this.montanteBruto = SQLRow.doubleOf( map.get( "venda_montantebruto" ) );
            this.montanteDesconto = SQLRow.doubleOf( map.get( "venda_montantedesconto" ) );
            this.montantePagar = SQLRow.doubleOf( map.get( "venda_montantepagar" ) );
            this.montanteAmortizado = SQLRow.doubleOf( map.get( "venda_montanteamortizado" ) );
            this.data = SQLRow.dateOf( map.get( "venda_data" ) );
            this.dataFinalizar = SQLRow.dateOf( map.get( "venda_datafinalizar" ) );
            this.dataFim = SQLRow.dateOf( map.get( "venda_datafim" ) );
            this.dataUltimaMovimentacao = SQLRow.dateOf( map.get("venda_dataultimamovimentacao" ) );
            this.estado = SQLRow.shortOf( map.get( "venda_estado" ) );
            this.dataRegisto = SQLRow.dateOf( map.get( "venda_estadodesc" ) );
            this.dataUltimaAtualizacao = SQLRow.dateOf( map.get("venda_estadodesc" ) );
            return this;
        }
    }
}
