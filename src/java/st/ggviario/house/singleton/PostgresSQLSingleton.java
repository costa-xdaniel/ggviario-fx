package st.ggviario.house.singleton;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import st.jigahd.support.sql.Configuration;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PostgresSQLSingleton {

    static{
        File file = new File("conf/database.conf.json" );
        Gson gson = new Gson();
        try(JsonReader reader = new JsonReader( new FileReader( file ) )) {
            Configuration conf = gson.fromJson(reader, Configuration.class);
            PostgresSQLSingleton.postgresSQL = new PostgresSQL( conf );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "init");
    }

    private static PostgresSQL postgresSQL;

    public static PostgresSQL getInstance() {
        return postgresSQL;
    }
}
