package st.ggviario.house.model;

import javafx.beans.property.StringProperty;

public class Produto {

    private String produtoId;
    private String produtoCodigo;
    private String produtoNome;
    private Categoria produtoCategoria;
    private Double produtoValor = null;
    private Double produtoStock = 0d;
    private Double produtoProducao = 0d;
    private Double produtoVenda = 0d;
    private Double produtoCompra = 0d;
    private Double produtoCusto = 0d;


    public String getProdutoId() {
        return produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public Categoria getProdutoCategoria() {
        return produtoCategoria;
    }

    public Double getProdutoValor() {
        return produtoValor;
    }

    public Double getProdutoStock() {
        return produtoStock;
    }

    public Double getProdutoProducao() {
        return produtoProducao;
    }

    public Double getProdutoVenda() {
        return produtoVenda;
    }

    public Double getProdutoCompra() {
        return produtoCompra;
    }

    public Double getProdutoCusto() {
        return produtoCusto;
    }

    public String getProdutoCodigo() {
        return produtoCodigo;
    }


    public static class  ProdutoBuilder{
        private String id;
        private String codigo;
        private String nome;
        private Categoria categoria;
        private Double valor = 0d;
        private Double stock = 0d;
        private Double producao = 0d;
        private Double venda = 0d;
        private Double compra = 0d;

        public  ProdutoBuilder(){
        }

        public ProdutoBuilder(String nome, Categoria categoria) {
            this.nome = nome;
            this.categoria = categoria;
        }

        public Produto build() {
            Produto produto = new Produto();
            produto.produtoId = this.id;
            produto.produtoCodigo = codigo;
            produto.produtoNome = this.nome;
            produto.produtoCategoria = this.categoria;
            produto.produtoValor = this.valor;
            produto.produtoStock = this.stock;
            produto.produtoProducao = this.producao;
            produto.produtoVenda = this.venda;
            produto.produtoCompra = this.compra;
            return produto;
        }

        public ProdutoBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ProdutoBuilder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public ProdutoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ProdutoBuilder categoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public ProdutoBuilder setValor(Double valor) {
            this.valor = valor;
            return this;
        }

        public ProdutoBuilder setStock(Double stock) {
            this.stock = stock;
            return this;
        }


        public ProdutoBuilder setProducao(Double producao) {
            this.producao = producao;
            return this;
        }

        public ProdutoBuilder setVenda(Double venda) {
            this.venda = venda;
            return this;
        }

        public ProdutoBuilder setCompra(Double compra) {
            this.compra = compra;
            return this;
        }
    }
}
