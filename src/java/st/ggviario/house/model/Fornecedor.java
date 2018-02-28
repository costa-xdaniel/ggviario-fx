package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Fornecedor {

    private UUID fornecedorId;
    private Distrito distrito;
    private Colaborador colaborador;
    private String fornecedorNome;
    private String fornecedorNif;
    private String fornecedorTelefone;
    private String fornecedorTelemovel;
    private String fornecedorMail;
    private String fornecedorLocal;
    private FornecedorEstado fornecedorEstado;
    private Date fornecedorDataregisto;
    private Double fornecedorMontanteCompras;
    private Double fornecedorMontantePagos;
    private Double fornecedorMonntantePendentes;

    public UUID getFornecedorId() {
        return fornecedorId;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public String getFornecedorNome() {
        return fornecedorNome;
    }

    public String getFornecedorNif() {
        return fornecedorNif;
    }

    public String getFornecedorTelefone() {
        return fornecedorTelefone;
    }

    public String getFornecedorTelemovel() {
        return fornecedorTelemovel;
    }

    public String getFornecedorMail() {
        return fornecedorMail;
    }

    public String getFornecedorLocal() {
        return fornecedorLocal;
    }

    public FornecedorEstado getFornecedorEstado() {
        return fornecedorEstado;
    }

    public Date getFornecedorDataregisto() {
        return fornecedorDataregisto;
    }

    public Double getFornecedorMontanteCompras() {
        return fornecedorMontanteCompras;
    }

    public Double getFornecedorMontantePagos() {
        return fornecedorMontantePagos;
    }

    public Double getFornecedorMonntantePendentes() {
        return fornecedorMonntantePendentes;
    }

    public enum FornecedorEstado {

        ;

        private final int estado;
        private final String showName;

        FornecedorEstado(int estado, String showName){
            this.estado = estado;
            this.showName = showName;
        }

        public int getEstado() {
            return estado;
        }

        public String getShowName() {
            return showName;
        }

        public static FornecedorEstado find(Short estado) {
            for( FornecedorEstado fe : FornecedorEstado.values() ){
                if( fe.estado == estado ) return fe;
            }
            return null;
        }
    }

    public static class FornecedorBuilder{
        private UUID id;
        private Distrito distrito;
        private Colaborador colaborador;
        private String nome;
        private String nif;
        private String telefone;
        private String telemovel;
        private String mail;
        private String local;
        private Short estado;
        private String estadoDesc;
        private Date dataRegisto;
        private Double montanteCompras;
        private Double montantePagos;
        private Double montantePendentes;

        public Fornecedor build(){
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.fornecedorId = this.id;
            fornecedor.distrito = this.distrito;
            fornecedor.colaborador = this.colaborador;
            fornecedor.fornecedorNome = this.nome;
            fornecedor.fornecedorNif = this.nif;
            fornecedor.fornecedorTelefone = this.telefone;
            fornecedor.fornecedorTelemovel = this.telemovel;
            fornecedor.fornecedorMail = this.mail;
            fornecedor.fornecedorLocal = this.local;
            fornecedor.fornecedorEstado = FornecedorEstado.find( this.estado );
            fornecedor.fornecedorDataregisto = this.dataRegisto;
            fornecedor.fornecedorMontanteCompras = this.montanteCompras;
            fornecedor.fornecedorMontantePagos = this.montantePagos;
            fornecedor.fornecedorMonntantePendentes = this.montantePendentes;
            return fornecedor;
        }

        public FornecedorBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public FornecedorBuilder setDistrito(Distrito distrito) {
            this.distrito = distrito;
            return this;
        }

        public FornecedorBuilder setColaborador(Colaborador colaborador) {
            this.colaborador = colaborador;
            return this;
        }

        public FornecedorBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public FornecedorBuilder setNif(String nif) {
            this.nif = nif;
            return this;
        }

        public FornecedorBuilder setTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public FornecedorBuilder setTelemovel(String telemovel) {
            this.telemovel = telemovel;
            return this;
        }

        public FornecedorBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public FornecedorBuilder setLocal(String local) {
            this.local = local;
            return this;
        }

        public FornecedorBuilder setEstado(Short estado) {
            this.estado = estado;
            return this;
        }

        public FornecedorBuilder setEstadoDesc(String estadoDesc) {
            this.estadoDesc = estadoDesc;
            return this;
        }

        public FornecedorBuilder setDataRegisto(Date dataRegisto) {
            this.dataRegisto = dataRegisto;
            return this;
        }

        public FornecedorBuilder setMontanteCompras(Double montanteCompras) {
            this.montanteCompras = montanteCompras;
            return this;
        }

        public FornecedorBuilder setMontantePagos(Double montantePagos) {
            this.montantePagos = montantePagos;
            return this;
        }

        public FornecedorBuilder setMontantePendentes(Double montantePendentes) {
            this.montantePendentes = montantePendentes;
            return this;
        }

        public FornecedorBuilder load(SQLRow  row ){
            return this.load(( Map<String, Object>) row );
        }

        public FornecedorBuilder load(Map< String, Object > map ){
            this.id = SQLRow.uuidOf( map.get( "fornecedor_id" ) );
            this.nome = SQLRow.stringOf( map.get("fornecedor_nome" ));
            this.nif = SQLRow.stringOf( map.get( "fornecedor_nif"  ));
            this.telefone = SQLRow.stringOf( map.get("fornecedor_telefone") );
            this.telemovel = SQLRow.stringOf( map.get( "fornecedor_telemovel" ) );
            this.mail = SQLRow.stringOf( map.get( "fornecedor_mail" ) );
            this.local = SQLRow.stringOf( map.get( "fornecedor_local" ));
            this.montanteCompras = SQLRow.doubleOf( map.get( "fornecedor_compras" ) );
            this.montantePagos = SQLRow.doubleOf( map.get( "fornecedor_compraspago" ) );
            this.montantePendentes = SQLRow.doubleOf( map.get( "fornecedor_compraspendentes" ) );
            return this;
        }
    }
}
