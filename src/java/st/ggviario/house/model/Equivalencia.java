package st.ggviario.house.model;

import st.jigahd.support.sql.postgresql.PostgresSQLRow;

import java.util.Map;
import java.util.UUID;

public class Equivalencia {

    private UUID equivalenciaId;
    private Producto producto;
    private Unidade unidade;
    private Double equivalenciaQuantidade;

    public UUID getEquivalenciaId() {
        return equivalenciaId;
    }

    public Producto getProducto() {
        return producto;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Double getEquivalenciaQuantidade() {
        return equivalenciaQuantidade;
    }

    public static class EquivalenciaBuilder{

        private UUID id;
        private Producto producto;
        private Unidade unidade;
        private Double quantidade;

        public Equivalencia build( ){
            Equivalencia equivalencia = new Equivalencia();
            equivalencia.equivalenciaId  = this.id;
            equivalencia.producto = this.producto;
            equivalencia.unidade = this.unidade;
            equivalencia.equivalenciaQuantidade = this.quantidade;
            return equivalencia;
        }

        public EquivalenciaBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public EquivalenciaBuilder produto(Producto producto) {
            this.producto = producto;
            return this;
        }

        public EquivalenciaBuilder unidade(Unidade unidade) {
            this.unidade = unidade;
            return this;
        }

        public EquivalenciaBuilder quantidade(Double quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public EquivalenciaBuilder load( PostgresSQLRow row ) {
            this.load( row.toMap() );
            return this;
        }

        public EquivalenciaBuilder load( Map< String, Object > map ) {
            this.id = (UUID) map.get( "equivalencia_id" );
            this.quantidade = (Double) map.get( "equivalencia_quantidade" );
            return this;
        }
    }
}
