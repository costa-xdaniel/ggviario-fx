package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Map;
import java.util.UUID;

public class Preco {

    private UUID precoId;
    private Producto producto;
    private Unidade unidade;
    private Double precoQuantidadeProduto;
    private Double precoCustoUnidade;

    public UUID getPrecoId() {
        return precoId;
    }

    public Producto getProducto() {
        return producto;
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
        if( this.producto != null ) return this.producto.toString();
        return super.toString();
    }

    public static class PrecoBuilder {

        private UUID id;
        private Producto producto;
        private Unidade unidade;
        private Double quantidadeProduto;
        private Double custoUnidade;

        public Preco build( ){
            Preco equivalencia = new Preco();
            equivalencia.precoId = this.id;
            equivalencia.producto = this.producto;
            equivalencia.unidade = this.unidade;
            equivalencia.precoQuantidadeProduto = this.quantidadeProduto;
            equivalencia.precoCustoUnidade = this.custoUnidade;
            return equivalencia;
        }

        public PrecoBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public PrecoBuilder produto(Producto producto) {
            this.producto = producto;
            return this;
        }

        public PrecoBuilder unidade(Unidade unidade) {
            this.unidade = unidade;
            return this;
        }

        public PrecoBuilder quantidadeProduto(Double quantidadeProduto) {
            this.quantidadeProduto = quantidadeProduto;
            return this;
        }

        public PrecoBuilder load(SQLRow row ) {
            this.load( row.toMap() );
            return this;
        }

        public PrecoBuilder custoUnidade(Double custoUnidade) {
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
