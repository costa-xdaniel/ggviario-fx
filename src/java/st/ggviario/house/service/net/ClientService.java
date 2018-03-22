package st.ggviario.house.service.net;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public abstract class ClientService implements Runnable{

    private DataInputStream in;
    private DataOutputStream out;
    private List<OnNextText> onNextTextList = new LinkedList<>();

    ClientService(Socket socket) {
        try {
            this.in = new DataInputStream( new BufferedInputStream( socket.getInputStream()));
            this.out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        }catch (Exception ex ){
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while ( true ) {
                if( in.available() <=  0) continue;
                String line = in.readUTF ();
                for( OnNextText nextLine : this.onNextTextList){
                    nextLine.accept( line );
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace ();
        }
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void writeUTF( SimpleIntent simpleIntent ) {
        this.writeUTF( simpleIntent.name() );
    }

    public synchronized void writeUTF( String text ) {
        try {
            out.writeUTF(text);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addOnNextLine(OnNextText onNextText) {
        return onNextTextList.add(onNextText);
    }

    public interface OnNextText {
        void accept( String text );
    }
}