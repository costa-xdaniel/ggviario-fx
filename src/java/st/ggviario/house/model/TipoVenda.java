package st.ggviario.house.model;

public enum TipoVenda {

    VENDA( (short) 1 ),
    DIVIDA( (short) 2);

    public static TipoVenda valueOf(Short tipoVendaId) {
        if ( tipoVendaId == null ) return null;
        for( TipoVenda tv: TipoVenda.values() ){
            if( tv.tipoVendaId == tipoVendaId ) return tv;
        }
        return null;
    }

    public TipoVenda other() {
        return this.equals( VENDA )? DIVIDA : VENDA;
    }


    private short tipoVendaId;

    TipoVenda( short tipoVendaId ){
        this.tipoVendaId = tipoVendaId;
    }

}
