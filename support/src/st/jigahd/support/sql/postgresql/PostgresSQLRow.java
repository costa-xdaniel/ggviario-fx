package st.jigahd.support.sql.postgresql;

import st.jigahd.support.sql.SQLRow;

import java.util.Map;

public class PostgresSQLRow extends SQLRow {

    protected PostgresSQLRow(Map<String, Integer> headerMap) {
        super(headerMap);
    }


    @Override
    protected void set(int index, Object value) {
        super.set(index, value);
    }
}
