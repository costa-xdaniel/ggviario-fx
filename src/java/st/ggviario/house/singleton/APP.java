package st.ggviario.house.singleton;

import st.ggviario.house.service.net.ClientServiceClient;
import st.ggviario.house.service.net.Service;
import st.ggviario.house.service.net.ServiceServer;
import st.ggviario.house.service.net.SimpleIntent;

import java.io.IOException;
import java.net.Socket;

public class APP implements Service {

    private static APP instance;
    private ClientServiceClient localClient;
    private ServiceServer server;

    private APP(){
        try{
            this.server = new ServiceServer();
            if( this.server.isSuccess() ){
                Thread thread = new Thread( server );
                thread.start();
                this.localClient = getClientServiceClient();
            } else {
                this.localClient = getClientServiceClient();
                this.localClient.addOnNextLine(text -> {
                    if( SimpleIntent.REQUIRE_FOCUS.equal( text ) ){
                        System.exit( -1 );
                    }
                });
                this.localClient.writeUTF(  SimpleIntent.REQUIRE_FOCUS );

            }
        }catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    private ClientServiceClient getClientServiceClient() throws IOException {
        Socket socket = new Socket("127.0.0.1", SERVICE_PORT);
        ClientServiceClient cli = new ClientServiceClient( socket );
        Thread thread = new Thread( cli );
        thread.start();
        return cli;
    }


    public ServiceServer getServer() {
        return server;
    }

    public ClientServiceClient getLocalClient() {
        return localClient;
    }

    public static APP getInstance(){
        if( APP.instance == null ){
            APP.instance = new APP( );
        }
        return APP.instance;
    }
}
