package st.ggviario.house.service.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ServiceServer implements Service, Runnable {

    private ServerSocket serverSocket;
    private List< OnNextClientService > onNextClientServiceList = new LinkedList<>();
    private boolean success;
    public ServiceServer() {
        try {
            this.serverSocket = new ServerSocket( SERVICE_PORT );

            this.success = true;
        } catch (IOException e) {
            this.success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public void run() {
        while ( true ){
            try {
                Socket socket = this.serverSocket.accept();
                ClientServiceServer clientService = new ClientServiceServer( socket );
                Thread thread = new Thread( clientService );
                thread.start();

                for( OnNextClientService next : this.onNextClientServiceList ){
                    next.accept(clientService);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addOnNextClient( OnNextClientService onNextClientService ) {
        return onNextClientServiceList.add(onNextClientService);
    }

    public interface OnNextClientService{
        void accept( ClientServiceServer clientService);
    }
}
