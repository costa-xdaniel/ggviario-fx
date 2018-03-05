package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Map;
import java.util.UUID;

public class Preco {

    private UUID precoId;
    private Produto produto;
    private Unidade unidade;
    private Double precoQuantidadeProduto;
    private Double precoCustoUnidade;

    public UUID getPrecoId() {
        return precoId;
    }

    public Produto getProduto() {
        return produto;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Double getPrecoQuantidadeProduto() {
        return precoQuantidadeProduto;
    }

    public Double getPrecoCustoUnidade() {
        return precoCustoUnidade;
    }

    @Override
    public String toString() {
        if( this.unidade != null ) return this.unidade.toString();
        if( this.produto != null ) return this.produto.toString();
        return super.toString();
    }

    public static class PrecoBuilder {

        private UUID id;
        private Produto produto;
        private Unidade unidade;
        private Double quantidadeProduto;
        private Double custoUnidade;

        public Preco build( ){
            Preco equivalencia = new Preco();
            equivalencia.precoId = this.id;
            equivalencia.produto = this.produto;
            equivalencia.unidade = this.unidade;
            equivalencia.precoQuantidadeProduto = this.quantidadeProduto;
            equivalencia.precoCustoUnidade = this.custoUnidade;
            return equivalencia;
        }

        public PrecoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public PrecoBuilder setProduto(Produto produto) {
            this.produto = produto;
            return this;
        }

        public PrecoBuilder setUnidade(Unidade unidade) {
            this.unidade = unidade;
            return this;
        }

        public PrecoBuilder setQuantidadeProduto(Double quantidadeProduto) {
            this.quantidadeProduto = quantidadeProduto;
            return this;
        }

        public PrecoBuilder setCustoUnidade(Double custoUnidade) {
            this.custoUnidade = custoUnidade;
            return this;
        }

        public PrecoBuilder load(Map< String, Object > map ) {
            this.id = SQLRow.uuidOf( map.get("preco_id") );
            this.quantidadeProduto = SQLRow.doubleOf( map.get( "preco_quantidadeproduto" ) );
            this.custoUnidade = SQLRow.doubleOf( map.get( "preco_custounidade" ) );
            return this;
        }
    }
}
