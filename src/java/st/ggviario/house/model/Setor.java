package st.ggviario.house.model;

import st.jigahd.support.sql.SQLRow;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Setor {

    private UUID setorId;
    private String setorCodigo;
    private String setorNome;
    private Short setorPosicao;
    private Short setorTotalSubSetor;
    private SetorEstado setorEstado;
    private Date setordataRegisto;
    private Date setorDataAtualizacao;
    private Short setorNivel;
    private Setor setorSuper;
    private Colaborador colaborador;
    private Colaborador colaboradorAtualizacao;

    public UUID getSetorId() {
        return setorId;
    }

    public String getSetorCodigo() {
        return setorCodigo;
    }

    public String getSetorNome() {
        return setorNome;
    }

    public Short getSetorPosicao() {
        return setorPosicao;
    }

    public Short getSetorTotalSubSetor() {
        return setorTotalSubSetor;
    }

    public SetorEstado getSetorEstado() {
        return setorEstado;
    }

    public Date getSetordataRegisto() {
        return setordataRegisto;
    }

    public Date getSetorDataAtualizacao() {
        return setorDataAtualizacao;
    }

    public Short getSetorNivel() {
        return setorNivel;
    }

    public Setor getSetorSuper() {
        return setorSuper;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Colaborador getColaboradorAtualizacao() {
        return colaboradorAtualizacao;
    }

    @Override
    public String toString() {
        return this.setorNome;
    }

    public enum SetorEstado implements EnumTypes <  SetorEstado, Short >{
        PROTEGIDO( 2 ),
        ATIVO( 1 ),
        FECHADO( 0 );

        private final short estado;

        SetorEstado( int estado ){
            this.estado  = (short) estado;
        }

        @Override
        public SetorEstado[] allValues() {
            return values();
        }

        @Override
        public Short value() {
            return this.estado;
        }
    }

    public static class SetorBuilder{
        private UUID id;
        private String codigo;
        private String nome;
        private Short posicao;
        private Short totalSubSetor;
        private SetorEstado estado;
        private Date dataRegisto;
        private Date dataAtualizacao;
        private Short nivel;
        private Setor setorSuper;
        private Colaborador colaborador;
        private Colaborador colaboradorAtualizacao;

        public Setor build(){
            Setor setor = new Setor();
            setor.setorId = this.id;
            setor.setorCodigo = this.codigo;
            setor.setorPosicao = this.posicao;
            setor.setorTotalSubSetor = this.totalSubSetor;
            setor.setorEstado = this.estado;
            setor.setordataRegisto = this.dataRegisto;
            setor.setorDataAtualizacao = this.dataAtualizacao;
            setor.setorNivel = this.nivel;
            setor.setorSuper = this.setorSuper;
            setor.colaborador = this.colaborador;
            setor.colaboradorAtualizacao = this.colaboradorAtualizacao;
            return setor;
        }

        public SetorBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public SetorBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public SetorBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public SetorBuilder setPosicao(Short posicao) {
            this.posicao = posicao;
            return this;
        }

        public SetorBuilder setTotalSubSetor(Short totalSubSetor) {
            this.totalSubSetor = totalSubSetor;
            return this;
        }

        public SetorBuilder setEstado(SetorEstado estado) {
            this.estado = estado;
            return this;
        }

        public SetorBuilder setDataRegisto(Date dataRegisto) {
            this.dataRegisto = dataRegisto;
            return this;
        }

        public SetorBuilder setDataAtualizacao(Date dataAtualizacao) {
            this.dataAtualizacao = dataAtualizacao;
            return this;
        }

        public SetorBuilder setNivel(Short nivel) {
            this.nivel = nivel;
            return this;
        }

        public SetorBuilder setSetorSuper(Setor setorSuper) {
            this.setorSuper = setorSuper;
            return this;
        }

        public SetorBuilder setColaborador(Colaborador colaborador) {
            this.colaborador = colaborador;
            return this;
        }

        public SetorBuilder setColaboradorAtualizacao(Colaborador colaboradorAtualizacao) {
            this.colaboradorAtualizacao = colaboradorAtualizacao;
            return this;
        }

        public SetorBuilder load(Map< String, Object > map ){
            this.id = SQLRow.uuidOf( map.get( "setor_id" ) );
            this.codigo = SQLRow.stringOf( map.get( "setor_codigo" ) );
            this.nome = SQLRow.stringOf( map.get( "setor_nome" ) );
            this.posicao = SQLRow.shortOf( map.get( "setor_posicao" ) );
            this.nivel = SQLRow.shortOf( map.get( "setor_nivel" ) );
            this.totalSubSetor = SQLRow.shortOf( map.get( "setor_totalsubsetores" ) );
            this.estado = EnumTypes.find( SetorEstado.values(), SQLRow.shortOf( map.get( "setor_estado" ) ) );
            this.dataRegisto = SQLRow.dateOf( map.get( "setor_dataregisto" ) );
            this.dataAtualizacao = SQLRow.dateOf( map.get( "setor_dataatualizacao" ) );
            return this;
        }

    }



}
