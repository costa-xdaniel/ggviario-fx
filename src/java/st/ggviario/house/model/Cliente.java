package st.ggviario.house.model;

import st.jigahd.support.sql.postgresql.PostgresSQLRow;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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

        public void id(Cliente cliente, UUID cliente_id) {
            cliente.clienteId = cliente_id;
        }

        public ClienteBuilder load(PostgresSQLRow row) {
            Distrito.DistritoBuilder distritoBuilder = new Distrito.DistritoBuilder();

            this.id = ( row.asUUID("cliente_id" ) );
            this.nome = ( row.asString( "cliente_nome" ) );
            this.apelido = ( row.asString( "cliente_apelido" ) );
            this.telefone = (row.asString( "cliente_telefone" ) );
            this.telemovel = (row.asString( "cliente_telemovel" ) );
            this.distrito = ( distritoBuilder.load( row ).build() );
            this.morada  =( row.asString( "cliente_morada" ) );
            this.sexo = ( Sexo.from( row.asShort( "cliente_sexo" ) ) );
            this.mail = ( row.asString( "cliente_mail" ) );
            this.localTrabalho = ( row.asString( "cliente_localtrabalho" ) );

            this.montanteCompra = row.asDouble( "cliente_monatntecompra" );
            this.montanteDivida = row.asDouble( "cliente_montantedivida" );
            this.montanteTotal = row.asDouble( "cliente_montantetotal" );
            this.montantePago = row.asDouble( "cliente_montantepago" );
            this.montantePendente = row.asDouble( "cliente_montantependente" );
            return this;
        }

        public ClienteBuilder dataNascimento( Date dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }
    }

}
