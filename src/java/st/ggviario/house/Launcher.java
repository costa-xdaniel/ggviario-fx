package st.ggviario.house;

import com.google.gson.Gson;
import st.jigahd.support.sql.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        if( args.length > 0 && args[0].equals("conf") ){
            Scanner scanner = new Scanner( System.in );
            System.out.println( "Nome do host" );
            String hostName = scanner.nextLine();

            System.out.println( "Numero da porta" );
            int port = scanner.nextInt();

            System.out.println( "Nome do utilizador" );
            String user = scanner.nextLine();

            System.out.println( "Nome do banco de dados" );
            String database = scanner.nextLine();

            System.out.println( "Senha de conexão com o banco de dados" );
            String senha = scanner.nextLine();

            Configuration configuration = new Configuration(
                hostName,
                port,
                user,
                database,
                senha
            );

            File file = new File("conf/database.conf.json" );
            File dir = new File("conf/" );
            Gson gson = new Gson();
            if( dir.mkdirs() ) System.out.println("dir config created");

            System.out.println("writing conf/database.conf.json ...");
            try (FileWriter writer = new FileWriter( file )) {
                gson.toJson( configuration, writer);
                System.out.println("write conf/database.conf.json complete");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1 );
            }
            System.out.println( "configuration is ok");
        }
        Thread.currentThread().setPriority( Thread.MAX_PRIORITY );
        Main.launch( args );
    }
}
