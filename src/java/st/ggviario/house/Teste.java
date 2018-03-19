package st.ggviario.house;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Teste {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "MMMMM 'de' yyyy", Locale.forLanguageTag("pt") );
        System.out.println("simpleDateFormat = " + simpleDateFormat.format( date ));
    }
}
