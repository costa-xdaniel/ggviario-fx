package st.ggviario.house.model;

import java.util.UUID;

public class Conta {
    private UUID contaId;

    public UUID getContaId() {
        return contaId;
    }

    public class ContaBuilder {
        private UUID id;

        public Conta build(){
            Conta conta = new Conta();
            conta.contaId = this.id;

            return conta;
        }

        public ContaBuilder id(UUID id) {
            this.id = id;
            return this;
        }
    }
}
