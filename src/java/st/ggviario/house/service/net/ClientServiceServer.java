package st.ggviario.house.service.net;

import java.net.Socket;

public class ClientServiceServer extends ClientService{

    ClientServiceServer(Socket socket) {
        super( socket );
    }

}
