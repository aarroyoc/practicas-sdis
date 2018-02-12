package sdis.echo;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.PrintWriter;

public class ServerEcho {
    public static void main(String[] args) throws Exception{
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Started server on port "+port);
        boolean quit = false;
        while(true){
            Socket clientSocket = serverSocket.accept();
            quit = false;
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            PrintWriter pw = new PrintWriter(os);
            while(!quit){
                String word = in.readLine();
                if(word.equals("bye")){
                    quit = true;
                }
                pw.println(word);
                pw.flush();
            }
            in.close();
            os.close();
            is.close();
            clientSocket.close();
        }
        //serverSocket.close();

    }
}