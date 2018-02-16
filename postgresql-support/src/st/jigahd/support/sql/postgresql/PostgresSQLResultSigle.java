package st.jigahd.support.sql.postgresql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class PostgresSQLResultSigle extends PostgresSQLResult {

    private Object value;

    public PostgresSQLResultSigle( ResultSet resultSet ) throws SQLException {
        super(resultSet);
        int countColumns = this.resultSet.getMetaData().getColumnCount();
        if( countColumns > 1 ) throw  new RuntimeException( "Numero de colunas mior que um não é um função simples");
        else if( countColumns == 1 ) value = valueOf( 1,
                this.resultSet.getMetaData().getColumnName( 1 ),
                this.resultSet.getMetaData().getColumnType( 1 )
        );
        else value = null;
        this.resultSet.close();
    }

    public Object getValue() {
        return value;
    }

    public Boolean asBoolean(){
        return (Boolean) this.value;
    }

    public Byte asByte(){
        return (Byte) this.value;
    }

    public Short asShort(){
        return (Short) this.value;
    }

    public Integer asInteger(){
        return (Integer) this.value;
    }

    public Long asLong(){
        return (Long) this.value;
    }

    public Float asFloat(){
        return (Float) this.value;
    }

    public Double asDouble(){
        return (Double) this.value;
    }

    public BigDecimal asNumber(){
        return (BigDecimal) this.value;
    }

    public Character asCharater(){
        return (Character) this.value;
    }

    public String asString(){
        return (String) this.value;
    }

    public InputStream asInputSteran(){
        return (InputStream) this.value;
    }

    public Reader asReader(){
        return (Reader) this.value;
    }

    public Date asDate(){
        return (Date) this.value;
    }

    public Calendar asCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( asDate() );
        return calendar;
    }
}
