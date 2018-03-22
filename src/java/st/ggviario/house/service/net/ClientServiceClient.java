package st.ggviario.house.service.net;

import java.net.Socket;

public class ClientServiceClient extends ClientService{

    public ClientServiceClient(Socket socket) {
        super( socket );
    }

}
