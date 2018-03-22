package st.ggviario.house.singleton;

import st.ggviario.house.service.ClientServiceClient;
import st.ggviario.house.service.Service;
import st.ggviario.house.service.ServiceServer;

import java.net.Socket;

public class APP implements Service {

    private static APP instance;
    private ServiceServer server;

    private APP(){
        try{
            this.server = new ServiceServer();
            if( this.server.isSuccess() ){
                Thread thread = new Thread( server );
                thread.start();
            } else {
                Socket socket = new Socket("127.0.0.1", SERVICE_PORT);
                ClientServiceClient cli = new ClientServiceClient( socket );
                Thread thread = new Thread( cli );
                thread.start();
                cli.addOnNextLine( line -> {
                    if( line.equals( REQUIRE_FOCUS ) ){
                        System.exit( -1 );
                    }
                });
                cli.writeUTF( REQUIRE_FOCUS );

            }
        }catch ( Exception ex ){
            ex.printStackTrace();
        }
    }


    public ServiceServer getServer() {
        return server;
    }

    public static APP getInstance(){
        if( APP.instance == null ){
            APP.instance = new APP( );
        }
        return APP.instance;
    }
}
