package rs.ac.bg.fon.CinemaCommon.communication;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class Receiver {
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }
    
    public Object receive() throws Exception{
        try{
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        }catch(Exception e){
            System.out.println("Error receiving object!\n"+e.getMessage());
            e.printStackTrace();
            throw new SocketException("Server error. Please try again later");
        }
    }
}
