package st.ggviario.house.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sexo {

    public static final Sexo UNDIFINED = new Sexo();
    public static final Sexo MALE = new Sexo();
    public static final Sexo FEMALE = new Sexo();

    public static final List<Sexo> LIST = Collections.unmodifiableList(
            Arrays.asList( UNDIFINED, MALE, FEMALE)
    );

    static {
        MALE.sexId = 1;
        MALE.sexDesc = "Masculino";
        FEMALE.sexId = 2;
        FEMALE.sexDesc = "Feminino";

        UNDIFINED.sexDesc = "Não definido";
    }

    private short sexId;
    private String sexDesc;

    public short getSexId() {
        return sexId;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    @Override
    public String toString() {
        return this.sexDesc;
    }

    public static Sexo from(Short sexoId) {
        return sexoId == null? UNDIFINED
                : sexoId == MALE.sexId ? MALE
                : FEMALE;
    }
}
