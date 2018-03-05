package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLResource;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Produto {

    private UUID produtoId;
    private Categoria produtoCategoria;
    private Colaborador colaborador;
    private Colaborador colaboradorAtualizacao;
    private String produtoCodigo;
    private String produtoNome;
    private Double produtoStock = 0d;
    private Double produtoStockMinimo;
    private Boolean produtoServicoVenda;
    private Boolean produtoServicoCompra;
    private Boolean produtoServicoProducao;
    private Boolean produtoServicoDinamico;
    private ProdutoEstado produtoEstado;
    private Date produtoDataRegisto;
    private Date produtoDataAtualizacao;

    private Preco preco = null;
    private Double produtoQuantidadeProduzido = 0d;
    private Double produtoMontanteVendas = 0d;
    private Double produtoMontanteVendaPagas = 0d;
    private Double produtoMontanteVendaPendentes = 0d;
    private Double produtoMontanteVendaDivida = 0d;
    private Double produtoMontanteVendaVendas = 0d;
    private Double produtoMontanteCompras = 0d;
    private Double produtoMontanteCompraPagas = 0d;
    private Double produtoMontanteCompraPendentes = 0d;

    public UUID getProdutoId() {
        return produtoId;
    }

    public Categoria getProdutoCategoria() {
        return produtoCategoria;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Colaborador getColaboradorAtualizacao() {
        return colaboradorAtualizacao;
    }

    public String getProdutoCodigo() {
        return produtoCodigo;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public Double getProdutoStock() {
        return produtoStock;
    }

    public Double getProdutoStockMinimo() {
        return produtoStockMinimo;
    }

    public Boolean getProdutoServicoVenda() {
        return produtoServicoVenda;
    }

    public Boolean getProdutoServicoCompra() {
        return produtoServicoCompra;
    }

    public Boolean getProdutoServicoProducao() {
        return produtoServicoProducao;
    }

    public Boolean getProdutoServicoDinamico() {
        return produtoServicoDinamico;
    }

    public ProdutoEstado getProdutoEstado() {
        return produtoEstado;
    }

    public Date getProdutoDataRegisto() {
        return produtoDataRegisto;
    }

    public Date getProdutoDataAtualizacao() {
        return produtoDataAtualizacao;
    }

    public Preco getPreco() {
        return preco;
    }

    public Double getProdutoQuantidadeProduzido() {
        return produtoQuantidadeProduzido;
    }

    public Double getProdutoMontanteVendas() {
        return produtoMontanteVendas;
    }

    public Double getProdutoMontanteVendaPagas() {
        return produtoMontanteVendaPagas;
    }

    public Double getProdutoMontanteVendaPendentes() {
        return produtoMontanteVendaPendentes;
    }

    public Double getProdutoMontanteVendaDivida() {
        return produtoMontanteVendaDivida;
    }

    public Double getProdutoMontanteVendaVendas() {
        return produtoMontanteVendaVendas;
    }

    public Double getProdutoMontanteCompras() {
        return produtoMontanteCompras;
    }

    public Double getProdutoMontanteCompraPagas() {
        return produtoMontanteCompraPagas;
    }

    public Double getProdutoMontanteCompraPendentes() {
        return produtoMontanteCompraPendentes;
    }

    @Override
    public String toString() {
        return this.produtoNome;
    }


    public enum ProdutoEstado implements EnumTypes < ProdutoEstado, Short > {

        ATIVO( 1, "Ativo" ),
        FACHADO( 0, "Fechado" )
        ;

        private final String name;
        private short estado;

        ProdutoEstado( int estado, String name ) {
            this.estado = (short) estado;
            this.name = name;
        }

        @Override
        public ProdutoEstado[] allValues() {
            return values();
        }

        @Override
        public Short value() {
            return this.estado;
        }

        @Override
        public String getNome() {
            return this.name;
        }


    }

    public static class  ProdutoBuilder{
        private UUID id;
        private Categoria categoria;
        private Colaborador colaborador;
        private Colaborador colaboradorAtualizacao;
        private String codigo;
        private String nome;
        private Double stock = 0d;
        private Double stockMinimo;
        private Boolean servicoVenda;
        private Boolean servicoCompra;
        private Boolean servicoProducao;
        private Boolean servicoStockDinamico;
        private ProdutoEstado estado;
        private Date dataRegisto;
        private Date dataAtualizacao;
        private Preco preco = null;
        private Double producao = 0d;
        private Double montanteVendas = 0d;
        private Double montanteVendaDividas = 0d;
        private Double montanteVendaVendas = 0d;
        private Double montanteVendaPagas = 0d;
        private Double montanteVendaPendente = 0d;
        private Double montanteCompras = 0d;
        private Double montanteCompraPagas = 0d;
        private Double montanteCompraPendentes = 0d;

        public ProdutoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public ProdutoBuilder setCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public ProdutoBuilder setColaborador(Colaborador colaborador) {
            this.colaborador = colaborador;
            return this;
        }

        public ProdutoBuilder setColaboradorAtualizacao(Colaborador colaboradorAtualizacao) {
            this.colaboradorAtualizacao = colaboradorAtualizacao;
            return this;
        }

        public ProdutoBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public ProdutoBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public ProdutoBuilder setStock(Double stock) {
            this.stock = stock;
            return this;
        }

        public ProdutoBuilder setStockMinimo(Double stockMinimo) {
            this.stockMinimo = stockMinimo;
            return this;
        }

        public ProdutoBuilder setServicoVenda(Boolean servicoVenda) {
            this.servicoVenda = servicoVenda;
            return this;
        }

        public ProdutoBuilder setServicoCompra(Boolean servicoCompra) {
            this.servicoCompra = servicoCompra;
            return this;
        }

        public ProdutoBuilder setServicoProducao(Boolean servicoProducao) {
            this.servicoProducao = servicoProducao;
            return this;
        }

        public ProdutoBuilder setServicoStockDinamico(Boolean servicoStockDinamico) {
            this.servicoStockDinamico = servicoStockDinamico;
            return this;
        }

        public ProdutoBuilder setEstado(ProdutoEstado estado) {
            this.estado = estado;
            return this;
        }

        public ProdutoBuilder setDataRegisto(Date dataRegisto) {
            this.dataRegisto = dataRegisto;
            return this;
        }

        public ProdutoBuilder setDataAtualizacao(Date dataAtualizacao) {
            this.dataAtualizacao = dataAtualizacao;
            return this;
        }

        public ProdutoBuilder setPreco(Preco preco) {
            this.preco = preco;
            return this;
        }

        public ProdutoBuilder setProducao(Double producao) {
            this.producao = producao;
            return this;
        }

        public ProdutoBuilder setMontanteVendas(Double montanteVendas) {
            this.montanteVendas = montanteVendas;
            return this;
        }

        public ProdutoBuilder setMontanteVendaDividas(Double montanteVendaDividas) {
            this.montanteVendaDividas = montanteVendaDividas;
            return this;
        }

        public ProdutoBuilder setMontanteVendaVendas(Double montanteVendaVendas) {
            this.montanteVendaVendas = montanteVendaVendas;
            return this;
        }

        public ProdutoBuilder setMontanteVendaPagas(Double montanteVendaPagas) {
            this.montanteVendaPagas = montanteVendaPagas;
            return this;
        }

        public ProdutoBuilder setMontanteVendaPendente(Double montanteVendaPendente) {
            this.montanteVendaPendente = montanteVendaPendente;
            return this;
        }

        public ProdutoBuilder setMontanteCompras(Double montanteCompras) {
            this.montanteCompras = montanteCompras;
            return this;
        }

        public ProdutoBuilder setMontanteCompraPagas(Double montanteCompraPagas) {
            this.montanteCompraPagas = montanteCompraPagas;
            return this;
        }

        public ProdutoBuilder setMontanteCompraPendentes(Double montanteCompraPendentes) {
            this.montanteCompraPendentes = montanteCompraPendentes;
            return this;
        }

        public Produto build(){
            Produto produto = new Produto();
            produto.produtoId = this.id;
            produto.produtoCategoria = this.categoria;
            produto.colaborador = this.colaborador;
            produto.colaboradorAtualizacao = this.colaboradorAtualizacao;
            produto.produtoCodigo = this.codigo;
            produto.produtoNome = this.nome;
            produto.produtoStock = SQLResource.coalesce( this.stock, 0.0 );
            produto.produtoStockMinimo = this.stockMinimo;
            produto.produtoServicoVenda = this.servicoVenda;
            produto.produtoServicoCompra = this.servicoCompra;
            produto.produtoServicoProducao = this.servicoProducao;
            produto.produtoServicoDinamico = this.servicoStockDinamico;
            produto.produtoEstado = this.estado;
            produto.produtoDataRegisto = this.dataRegisto;
            produto.produtoDataAtualizacao = this.dataAtualizacao;
            produto.preco = this.preco;
            produto.produtoQuantidadeProduzido = SQLResource.coalesce( this.producao, 0.0 );
            produto.produtoMontanteVendas = SQLResource.coalesce( this.montanteVendas, 0.0 );
            produto.produtoMontanteVendaDivida = SQLResource.coalesce( this.montanteVendaDividas, 0.0 );
            produto.produtoMontanteVendaVendas = SQLResource.coalesce( this.montanteVendaVendas, 0.0 );
            produto.produtoMontanteVendaPagas = SQLResource.coalesce( this.montanteVendaPagas, 0.0 );
            produto.produtoMontanteVendaPendentes = SQLResource.coalesce( this.montanteVendaPendente, 0.0 );
            produto.produtoMontanteCompras = SQLResource.coalesce( this.montanteCompras, 0.0 );
            produto.produtoMontanteCompraPagas = SQLResource.coalesce( this.montanteCompraPagas, 0.0 );
            produto.produtoMontanteCompraPendentes = SQLResource.coalesce( this.montanteCompraPendentes, 0.0 );
            return produto;
        }


        public ProdutoBuilder load(Map<String, Object > map ) {
            this.id = SQLRow.uuidOf( map.get( "produto_id"  ) );
            this.codigo = SQLRow.stringOf( map.get( "produto_codigo"  ) );
            this.id = SQLRow.uuidOf( map.get( "produto_id"  ) );
            this.nome = SQLRow.stringOf( map.get( "produto_nome"  ) );
            this.stock = SQLRow.doubleOf( map.get( "produto_stock"  ) );
            this.stockMinimo = SQLRow.doubleOf( map.get( "produto_stockminimo"  ) );
            this.servicoVenda = SQLRow.booleanOf( map.get( "produto_servicovenda"  ) );
            this.servicoCompra = SQLRow.booleanOf( map.get( "produto_servicocompra"  ) );
            this.servicoProducao = SQLRow.booleanOf( map.get( "produto_servicoproducao"  ) );
            this.servicoStockDinamico = SQLRow.booleanOf( map.get( "produto_servicostockdinamico"  ) );
            this.estado = EnumTypes.find( ProdutoEstado.values(),  SQLRow.shortOf( map.get( "produto_estado"  ) ));
            this.dataRegisto = SQLRow.dateOf( map.get( "produto_dataregisto"  ) );
            this.dataAtualizacao = SQLRow.dateOf( map.get( "produto_dataatualizacao"  ) );
            this.producao = SQLRow.doubleOf( map.get( "produto_producao" ) );
            this.montanteVendas = SQLRow.doubleOf( map.get( "produto_montantevendas" ) );
            this.montanteVendaVendas = SQLRow.doubleOf( map.get( "produto_montantevendavendas" ) );
            this.montanteVendaDividas = SQLRow.doubleOf( map.get( "produto_montantevendadividas" ) );
            this.montanteVendaPagas = SQLRow.doubleOf( map.get( "produto_montantevendapagas" ) );
            this.montanteVendaPendente = SQLRow.doubleOf( map.get( "produto_montantevendapendentes" ) );
            this.montanteCompras = SQLRow.doubleOf( map.get( "produto_montantecompras" ) );
            this.montanteCompraPagas = SQLRow.doubleOf( map.get( "produto_montantecomprapagas" ) );
            this.montanteCompraPendentes = SQLRow.doubleOf( map.get( "produto_montantecomprapendentes" ) );
            return this;
        }
    }
}
