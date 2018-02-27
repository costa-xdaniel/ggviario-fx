package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLResource;
import st.jigahd.support.sql.lib.SQLText;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Cliente {

    private UUID clienteId;
    private Sexo sexo;
    private Distrito distrito;
    private TipoDocumento tipoDocumento;
    private String clienteDocumentoNumero;
    private String clienteNome;
    private String clienteApelido;
    private String clienteTelefone;
    private String clienteMail;
    private String clienteMorada;
    private String clienteLocalTrabalho;

    private Double clienteMonatenteCompra;
    private Double clienteMontanteDivida;
    private Double clienteMontanteTotal;
    private Double clienteMontantePago;
    private Double clienteMontantePendente;
    private String clienteTelemovel;
    private Date clienteDataNascimento;


    public UUID getClienteId() {
        return clienteId;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public String getClienteDocumentoNumero() {
        return clienteDocumentoNumero;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public String getClienteApelido() {
        return clienteApelido;
    }

    public String getClienteTelefone() {
        return clienteTelefone;
    }

    public String getClienteMail() {
        return clienteMail;
    }

    public String getClienteMorada() {
        return clienteMorada;
    }

    public String getClienteLocalTrabalho() {
        return clienteLocalTrabalho;
    }

    public Double getClienteMonatenteCompra() {
        return clienteMonatenteCompra;
    }

    public Double getClienteMontanteDivida() {
        return clienteMontanteDivida;
    }

    public Double getClienteMontanteTotal() {
        return clienteMontanteTotal;
    }

    public Double getClienteMontantePago() {
        return clienteMontantePago;
    }

    public Double getClienteMontantePendente() {
        return clienteMontantePendente;
    }

    public String getClienteTelemovel() {
        return clienteTelemovel;
    }

    public Date getClienteDataNascimento() {
        return clienteDataNascimento;
    }

    public String getClienteCompletName() {
        String nomeCompleto = this.clienteNome + " " + SQLResource.coalesce( this.clienteApelido, "" );
        return SQLText.normalize( nomeCompleto );
    }

    public String getClienteContanto( String defaultContanto ) {
        return SQLResource.coalesce( this.clienteTelemovel, this.clienteTelemovel, this.clienteMail, defaultContanto );
    }

    public String getClienteLocal( String defaultLocal ) {
        return SQLResource.coalesce( this.clienteMorada, this.clienteLocalTrabalho, defaultLocal );
    }

    @Override
    public String toString() {
        return this.getClienteCompletName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if( this.clienteId == null ) return false;
        Cliente cliente = (Cliente) o;
        if( cliente.clienteId == null ) return false;
        return this.clienteId.equals( cliente.clienteId );

    }

    @Override
    public int hashCode() {
        return clienteId != null ? clienteId.hashCode() : 0;
    }

    public static class ClienteBuilder{
        private UUID id;
        private String nome;
        private String apelido;
        private Sexo sexo;
        private Distrito distrito;
        private TipoDocumento tipoDocumento;
        private String documentoNumero;
        private String telefone;
        private String telemovel;
        private String mail;
        private String morada;
        private String localTrabalho;


        private Double montanteCompra;
        private Double montanteDivida;
        private Double montanteTotal;
        private Double montantePago;
        private Double montantePendente;
        private Date dataNascimento;

        public Cliente build(){
            Cliente cliente = new Cliente();
            cliente.clienteId = id;
            cliente.clienteNome = nome;
            cliente.clienteApelido = apelido;
            cliente.sexo = sexo;
            cliente.distrito = distrito;
            cliente.tipoDocumento = tipoDocumento;
            cliente.clienteDocumentoNumero = documentoNumero;
            cliente.clienteTelefone = telefone;
            cliente.clienteTelemovel = telemovel;
            cliente.clienteMail = mail;
            cliente.clienteMorada = morada;
            cliente.clienteLocalTrabalho = localTrabalho;
            cliente.clienteDataNascimento = dataNascimento;

            cliente.clienteMonatenteCompra = montanteCompra == null? 0.0 : montanteCompra;
            cliente.clienteMontanteDivida = montanteDivida == null? 0.0 : montanteDivida;
            cliente.clienteMontanteTotal = montanteTotal == null? 0.0 : montanteTotal;
            cliente.clienteMontantePago = montantePago == null? 0.0 : montantePago;
            cliente.clienteMontantePendente = montantePendente == null? 0.0 : montantePendente;

            return cliente;
        }

        public ClienteBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ClienteBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ClienteBuilder apelido(String apelido) {
            this.apelido = apelido;
            return this;
        }

        public ClienteBuilder sexo(Sexo sexo) {
            this.sexo = sexo;
            return this;
        }

        public ClienteBuilder distrito(Distrito distrito) {
            this.distrito = distrito;
            return this;
        }

        public ClienteBuilder tipoDocumento(TipoDocumento tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
            return this;
        }

        public ClienteBuilder documentoNumero(String documentoNumero) {
            this.documentoNumero = documentoNumero;
            return this;
        }

        public ClienteBuilder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        private ClienteBuilder telemovel(String columnName) {
            this.telemovel = telefone;
            return this;
        }

        public ClienteBuilder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public ClienteBuilder morada(String morada) {
            this.morada = morada;
            return this;
        }

        public ClienteBuilder localTrabalho(String localTrabalho) {
            this.localTrabalho = localTrabalho;
            return this;
        }

        public ClienteBuilder load(SQLRow row) {
            this.id =  SQLRow.uuidOf( row.get("cliente_id" ) );
            this.nome = SQLRow.stringOf( row.get( "cliente_nome" ) );
            this.apelido = SQLRow.stringOf( row.get( "cliente_apelido" ) );
            this.telefone = SQLRow.stringOf(  row.get( "cliente_telefone" ) );
            this.telemovel = SQLRow.stringOf( row.get( "cliente_telemovel" ) );
            this.morada = SQLRow.stringOf( row.get( "cliente_morada" ) );
            this.sexo = Sexo.from( SQLRow.shortOf(  row.get( "cliente_sexo" ) ) );
            this.mail =  SQLRow.stringOf( row.get( "cliente_mail" ) );
            this.localTrabalho = SQLRow.stringOf( row.get( "cliente_localtrabalho" ) );
            this.montanteCompra = SQLRow.doubleOf( row.get( "cliente_monatntecompra" ) );
            this.montanteDivida = SQLRow.doubleOf( row.get( "cliente_montantedivida" ) );
            this.montanteTotal = SQLRow.doubleOf( row.get( "cliente_montantetotal" ) );
            this.montantePago = SQLRow.doubleOf( row.get( "cliente_montantepago" ) );
            this.montantePendente = SQLRow.doubleOf( row.get( "cliente_montantependente" ) );
            return this;
        }

        public ClienteBuilder load(Map<String, Object> map) {
            this.id =  SQLRow.uuidOf( map.get("cliente_id" ) );
            this.nome = SQLRow.stringOf( map.get( "cliente_nome" ) );
            this.apelido = SQLRow.stringOf( map.get( "cliente_apelido" ) );
            this.telefone = SQLRow.stringOf(  map.get( "cliente_telefone" ) );
            this.telemovel = SQLRow.stringOf( map.get( "cliente_telemovel" ) );
            this.morada = SQLRow.stringOf( map.get( "cliente_morada" ) );
            this.sexo = Sexo.from( SQLRow.shortOf(  map.get( "cliente_sexo" ) ) );
            this.mail =  SQLRow.stringOf( map.get( "cliente_mail" ) );
            this.localTrabalho = SQLRow.stringOf( map.get( "cliente_localtrabalho" ) );
            this.montanteCompra = SQLRow.doubleOf( map.get( "cliente_monatntecompra" ) );
            this.montanteDivida = SQLRow.doubleOf( map.get( "cliente_montantedivida" ) );
            this.montanteTotal = SQLRow.doubleOf( map.get( "cliente_montantetotal" ) );
            this.montantePago = SQLRow.doubleOf( map.get( "cliente_montantepago" ) );
            this.montantePendente = SQLRow.doubleOf( map.get( "cliente_montantependente" ) );
            return this;
        }

        public ClienteBuilder dataNascimento( Date dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }
    }

}
