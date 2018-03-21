package st.ggviario.house.singleton;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import st.jigahd.support.sql.Configuration;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PostgresSQLSingleton {

    static{
        File file = new File("conf/database.conf.json" );
        File dir = new File("conf/" );

        Gson gson = new Gson();
        if( !file.exists() ){
            System.out.println("dir.mkdirs() = " + dir.mkdirs());
            try (FileWriter writer = new FileWriter( file )) {
                gson.toJson( new Configuration("host", 5432, "user", "database", "password" ), writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit( 0 );
        }
        try(JsonReader reader = new JsonReader( new FileReader( file ) )) {
            Configuration conf = gson.fromJson(reader, Configuration.class);
            PostgresSQLSingleton.postgresSQL = new PostgresSQL( conf );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static PostgresSQL postgresSQL;

    public static PostgresSQL getInstance() {
        return postgresSQL;
    }
}
