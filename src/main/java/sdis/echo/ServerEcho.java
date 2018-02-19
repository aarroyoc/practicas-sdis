package sdis.echo;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServerEcho {
    public static void main(String[] args) throws IOException{
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Started server on port "+port);
        while(true){
            try{
                Socket clientSocket = serverSocket.accept();
                Sirviente sirviente = new Sirviente(clientSocket);
                new Thread(sirviente).start();
            }catch(IOException e){
                e.printStackTrace(System.err);
            }
        }
    }
}