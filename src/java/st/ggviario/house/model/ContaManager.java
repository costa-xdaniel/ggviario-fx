package st.ggviario.house.model;

import com.sun.istack.internal.Nullable;

public class ContaManager {

    @Nullable
    public Conta getContaFor(ContaOperacao contaIdentifier ){
        return null;
    }



    public enum ContaOperacao{
        PAGAMENTO_VENDA,
        PAGAMENTO_DIVIA,
        PAGAMENTO_DESPESA
    }
}
