package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Movimento {
    private UUID movimentoId;
    private String movimentoCodigo;
    private Conta conta;
    private TipoMovimento tipoMovimento;
    private Movimento movimentoSuper;
    private Venda venda;
    private Despesa despesa;
    private Colaborador colaborador;
    private Colaborador colaboradorAtualizacao;
    private Date movimentoData;
    private String movimentoDocumento;
    private Double movimentoMontante;
    private String movimentoLibele;
    private Integer movimentoTransferenciaNumero;
    private Boolean movimentoDevolucao;
    private Date movimentoDevolucaoUltimaData;
    private Double movimentoDevolucaoMontanteDevolvido;
    private Short movimentoEstado;
    private String movimentoEstadoDesc;
    private Date movimentoDataRegisto;
    private Date movimentoDataUltimaAtualizacao;


    private Movimento(){}

    public UUID getMovimentoId() {
        return movimentoId;
    }

    public Conta getConta() {
        return conta;
    }

    public String getMovimentoCodigo() {
        return movimentoCodigo;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public Movimento getMovimentoSuper() {
        return movimentoSuper;
    }

    public Venda getVenda() {
        return venda;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Colaborador getColaboradorAtualizacao() {
        return colaboradorAtualizacao;
    }

    public Date getMovimentoData() {
        return movimentoData;
    }

    public String getMovimentoDocumento() {
        return movimentoDocumento;
    }

    public Double getMovimentoMontante() {
        return movimentoMontante;
    }

    public String getMovimentoLibele() {
        return movimentoLibele;
    }

    public Integer getMovimentoTransferenciaNumero() {
        return movimentoTransferenciaNumero;
    }

    public Boolean getMovimentoDevolucao() {
        return movimentoDevolucao;
    }

    public Date getMovimentoDevolucaoUltimaData() {
        return movimentoDevolucaoUltimaData;
    }

    public Double getMovimentoDevolucaoMontanteDevolvido() {
        return movimentoDevolucaoMontanteDevolvido;
    }

    public Short getMovimentoEstado() {
        return movimentoEstado;
    }

    public String getMovimentoEstadoDesc() {
        return movimentoEstadoDesc;
    }

    public Date getMovimentoDataRegisto() {
        return movimentoDataRegisto;
    }

    public Date getMovimentoDataUltimaAtualizacao() {
        return movimentoDataUltimaAtualizacao;
    }

    public static class MovimentoBuilder {
        private UUID id;
        private String codigo;
        private Conta conta;
        private TipoMovimento tipoMovimento;
        private Movimento movimentoSuper;
        private Venda venda;
        private Despesa despesa;
        private Colaborador colaborador;
        private Colaborador colaboradorAtualizacao;
        private Date data;
        private String documento;
        private Double montante;
        private String libele;
        private Integer transfernciaNumero;
        private Boolean devolucao;
        private Date devolucaoUltimaData;
        private Double devolucaoMontanteDevolvido;
        private Short estado;
        private String estadoDesc;
        private Date dataRegisto;
        private Date dataUltimaAtualizacao;

        public Movimento build(){
            Movimento movimento = new Movimento();
            movimento.movimentoId = this.id;
            movimento.movimentoCodigo = this.codigo;
            movimento.conta = this.conta;
            movimento.tipoMovimento = this.tipoMovimento;
            movimento.movimentoSuper = this.movimentoSuper;
            movimento.venda = this.venda;
            movimento.despesa = this.despesa;
            movimento.colaborador = this.colaborador;
            movimento.colaboradorAtualizacao = this.colaboradorAtualizacao;
            movimento.movimentoData = this.data;
            movimento.movimentoDocumento = this.documento;
            movimento.movimentoMontante = this.montante;
            movimento.movimentoLibele = this.libele;
            movimento.movimentoTransferenciaNumero = this.transfernciaNumero;
            movimento.movimentoDevolucao = this.devolucao;
            movimento.movimentoDevolucaoUltimaData = this.devolucaoUltimaData;
            movimento.movimentoDevolucaoMontanteDevolvido = this.devolucaoMontanteDevolvido;
            movimento.movimentoEstado = this.estado;
            movimento.movimentoDataRegisto = this.dataRegisto;
            movimento.movimentoDataUltimaAtualizacao = this.dataUltimaAtualizacao;
            return movimento;
        }

        public MovimentoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public MovimentoBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public MovimentoBuilder setConta(Conta conta) {
            this.conta = conta;
            return this;
        }

        public MovimentoBuilder setTipoMovimento(TipoMovimento tipoMovimento) {
            this.tipoMovimento = tipoMovimento;
            return this;
        }

        public MovimentoBuilder setMovimentoSuper(Movimento movimentoSuper) {
            this.movimentoSuper = movimentoSuper;
            return this;
        }

        public MovimentoBuilder setVenda(Venda venda) {
            this.venda = venda;
            return this;
        }

        public MovimentoBuilder setDespesa(Despesa despesa) {
            this.despesa = despesa;
            return this;
        }

        public MovimentoBuilder setColaborador(Colaborador colaborador) {
            this.colaborador = colaborador;
            return this;
        }

        public MovimentoBuilder setColaboradorAtualizacao(Colaborador colaboradorAtualizacao) {
            this.colaboradorAtualizacao = colaboradorAtualizacao;
            return this;
        }

        public MovimentoBuilder setData(Date data) {
            this.data = data;
            return this;
        }

        public MovimentoBuilder setDocumento(String documento) {
            this.documento = documento;
            return this;
        }

        public MovimentoBuilder setMontante(Double montante) {
            this.montante = montante;
            return this;
        }

        public MovimentoBuilder setLibele(String libele) {
            this.libele = libele;
            return this;
        }

        public MovimentoBuilder setTransfernciaNumero(Integer transfernciaNumero) {
            this.transfernciaNumero = transfernciaNumero;
            return this;
        }

        public MovimentoBuilder setDevolucao(Boolean devolucao) {
            this.devolucao = devolucao;
            return this;
        }

        public MovimentoBuilder setDevolucaoUltimaData(Date devolucaoUltimaData) {
            this.devolucaoUltimaData = devolucaoUltimaData;
            return this;
        }

        public MovimentoBuilder setDevolucaoMontanteDevolvido(Double devolucaoMontanteDevolvido) {
            this.devolucaoMontanteDevolvido = devolucaoMontanteDevolvido;
            return this;
        }

        public MovimentoBuilder setEstado(Short estado) {
            this.estado = estado;
            return this;
        }

        public MovimentoBuilder setEstadoDesc(String estadoDesc) {
            this.estadoDesc = estadoDesc;
            return this;
        }

        public MovimentoBuilder setDataRegisto(Date dataRegisto) {
            this.dataRegisto = dataRegisto;
            return this;
        }

        public MovimentoBuilder setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
            this.dataUltimaAtualizacao = dataUltimaAtualizacao;
            return this;
        }

        public MovimentoBuilder load(Map<String, Object > map ){
            this.id = SQLRow.uuidOf( map.get( "movimento_id" ) );
            this.codigo = SQLRow.stringOf( map.get( "movimento_codigo" ) );
            this.tipoMovimento = TipoMovimento.valueOf( SQLRow.shortOf( map.get( "tmovimento_id" ) ) );
            this.data = SQLRow.dateOf( map.get("movimento_data") );
            this.documento = SQLRow.stringOf( map.get( "movimento_documento" ) );
            this.montante =SQLRow.doubleOf( map.get( "movimento_montante" ) );
            this.libele = SQLRow.stringOf( map.get( "movimento_libele" ) );
            this.transfernciaNumero = SQLRow.integerOf( map.get( "movimento_transferencianumero" ) );
            this.devolucao = SQLRow.booleanOf( map.get( "movimento_devolucao" ) );
            this.devolucaoUltimaData  = SQLRow.dateOf( map.get( "movimento_devolucaoultimadada" ) );
            this.devolucaoMontanteDevolvido = SQLRow.doubleOf( map.get( "movimento_devolucamontantedevolvido" ) );
            this.estado = SQLRow.shortOf( map.get("movimento_estado") );
            this.estadoDesc = SQLRow.stringOf( map.get( "movimento_estadodesc" ) );
            this.dataRegisto = SQLRow.dateOf( map.get( "movimento_dataregisto" ) );
            this.dataUltimaAtualizacao = SQLRow.dateOf( map.get( "movimento_dataatualizacao" ) );
            return this;
        }

        public MovimentoBuilder load( SQLRow row ){
            this.id = SQLRow.uuidOf( row.valueOf( "movimento_id" ) );
            this.codigo = SQLRow.stringOf( row.valueOf( "movimento_codigo" ) );
            this.tipoMovimento = TipoMovimento.valueOf( SQLRow.shortOf( row.valueOf( "tmovimento_id" ) ) );
            this.data = SQLRow.dateOf( row.valueOf("movimento_data") );
            this.documento = SQLRow.stringOf( row.valueOf( "movimento_documento" ) );
            this.montante =SQLRow.doubleOf( row.valueOf( "movimento_montante" ) );
            this.libele = SQLRow.stringOf( row.valueOf( "movimento_libele" ) );
            this.transfernciaNumero = SQLRow.integerOf( row.valueOf( "movimento_transferencianumero" ) );
            this.devolucao = SQLRow.booleanOf( row.valueOf( "movimento_devolucao" ) );
            this.devolucaoUltimaData  = SQLRow.dateOf( row.valueOf( "movimento_devolucaoultimadada" ) );
            this.devolucaoMontanteDevolvido = SQLRow.doubleOf( row.valueOf( "movimento_devolucamontantedevolvido" ) );
            this.estado = SQLRow.shortOf( row.valueOf("movimento_estado") );
            this.estadoDesc = SQLRow.stringOf( row.valueOf( "movimento_estadodesc" ) );
            this.dataRegisto = SQLRow.dateOf( row.valueOf( "movimento_dataregisto" ) );
            this.dataUltimaAtualizacao = SQLRow.dateOf( row.valueOf( "movimento_dataatualizacao" ) );
            return this;
        }
    }
}
