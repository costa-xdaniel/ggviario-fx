package st.ggviario.house.model;

import java.util.UUID;

public class Cliente {

    private UUID clienteId;
    private Sexo sexo;
    private Distrito distrito;
    private TipoDocumento tipoDocumento;
    private String clienteDocumentoNumero;
    private String clienteNome;
    private String clienteApelido;
    private String clienteeTelefone;
    private String clienteMail;
    private String clienteMorada;
    private String clienteLocalTrabalho;


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

    public String getClienteeTelefone() {
        return clienteeTelefone;
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


    public static class ClienteBuilder{
        private UUID id;
        private String nome;
        private String apelido;
        private Sexo sexo;
        private Distrito distrito;
        private TipoDocumento tipoDocumento;
        private String documentoNumero;
        private String telefone;
        private String mail;
        private String morada;
        private String localTrabalho;

        public Cliente build(){
            Cliente cliente = new Cliente();
            cliente.clienteId = id;
            cliente.clienteNome = nome;
            cliente.clienteApelido = apelido;
            cliente.sexo = sexo;
            cliente.distrito = distrito;
            cliente.tipoDocumento = tipoDocumento;
            cliente.clienteDocumentoNumero = documentoNumero;
            cliente.clienteeTelefone = telefone;
            cliente.clienteMail = mail;
            cliente.clienteMorada = morada;
            cliente.clienteLocalTrabalho = localTrabalho;
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

        public ClienteBuilder setTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public ClienteBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public ClienteBuilder morada(String morada) {
            this.morada = morada;
            return this;
        }

        public ClienteBuilder setLocalTrabalho(String localTrabalho) {
            this.localTrabalho = localTrabalho;
            return this;
        }

        public void setId(Cliente cliente, UUID cliente_id) {
            cliente.clienteId = cliente_id;
        }
    }

}
