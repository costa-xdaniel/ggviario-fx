package st.ggviario.house.model;

import st.jigahd.support.sql.postgresql.PostgresSQLRow;

public class TipoDocumento {

    private Short tipoDocumentoId;
    private String tipoDocumentoDesc;

    public Short getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public String getTipoDocumentoDesc() {
        return tipoDocumentoDesc;
    }

    @Override
    public String toString() {
        return this.tipoDocumentoDesc;
    }

    public static class TipoDocumentoBuilder{
        private Short id;
        private String desc;

        public TipoDocumento build(){
            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento.tipoDocumentoId = id;
            tipoDocumento.tipoDocumentoDesc = desc;
            return tipoDocumento;
        }

        public TipoDocumentoBuilder id( Short id) {
            this.id = id;
            return this;
        }

        public TipoDocumentoBuilder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public TipoDocumentoBuilder load(PostgresSQLRow row) {
            this.id = row.asShort( "tdocumento_id" );
            this.desc = row.asString( "tdocumento_desc" );
            return this;
        }
    }




}
