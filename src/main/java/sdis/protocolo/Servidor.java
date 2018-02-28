package sdis.protocolo;

import java.net.*;
import java.io.*;
import java.util.Queue;
import java.util.ArrayDeque;

public class Servidor{
    public static void main(String... args) throws Exception{
        ServerSocket server = new ServerSocket(5555);
	Queue<String> queue = new ArrayDeque<String>();
        while(true){
            Socket socket = server.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            ObjectInputStream input = new ObjectInputStream(is);
            ObjectOutputStream output = new ObjectOutputStream(os);
            boolean quit = false;
            while(!quit){
                MsgProtocolo msg = (MsgProtocolo)input.readObject();
                MsgProtocolo out = null;
                switch(msg.getPrimitive()){
                    case HELLO: out = new MsgProtocolo(Primitiva.HELLO,"I'm a teapot");break;
                    case PUSH: queue.add(msg.getMessage()); out = new MsgProtocolo(Primitiva.PUSH_OK); break;
                    case PULL: try{
				       String str = queue.poll();
				       out = new MsgProtocolo(Primitiva.PULL_OK,str);
			       }catch(Exception e){
					      out = new MsgProtocolo(Primitiva.NOTHING);
			       }finally{
					      break;
			       }
                    case PULL_WAIT: break;
                }
                output.writeObject(out);
            }
        }
    }
}
