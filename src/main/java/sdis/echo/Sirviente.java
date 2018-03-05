package sdis.echo;

import java.net.Socket;
import java.io.*;


public class Sirviente implements Runnable{

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter pw;

    public Sirviente(Socket socket) throws IOException{
        this.socket = socket;
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(is));
        pw = new PrintWriter(os);
    }
    public void run(){
        boolean quit = false;
        try{
            while(!quit){
                String word = reader.readLine();
                if(word == null | word.equals("bye")){
                    quit = true;
                }
                System.out.println("ECHO: "+word);
                pw.println(word);
                pw.flush();
            }
            reader.close();
            pw.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace(System.err);
        }
    }
}