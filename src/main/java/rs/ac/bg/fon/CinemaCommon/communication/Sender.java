package rs.ac.bg.fon.CinemaCommon.communication;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Sender {
    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public void send(Object object) throws Exception{
        try{
            ObjectOutputStream out=new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            out.writeObject(object);
            out.flush();
        }
        catch(Exception e){
            System.out.println("Error sending object!\n"+e.getMessage());
            e.printStackTrace();
            throw new SocketException("Server error. Please try again later");
        }
    }
}
