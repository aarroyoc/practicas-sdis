package protocol.cliente;

import protocol.common.*;
import java.io.*;
import java.net.*;

public class Cliente {
    final static private int PUERTO = 2000;

    public static void main(String[] args) throws IOException {
        String host = args[0];

        Socket sock = new Socket(host, PUERTO); /* creación del socket */
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            /** Creación de los canales de serialización de objetos */      
            oos = new ObjectOutputStream(sock.getOutputStream());
            ois = new ObjectInputStream(sock.getInputStream());

            /* no vamos a utilizar teclado, y vamos a probar las primitivas por programa */
            System.out.println("Pulsa <Enter> para comenzar"); System.in.read();

            /* Escenario 1 */
            oos.writeObject(new MensajeProtocolo(Primitive.HELLO, "Pedro"));
            System.out.println((MensajeProtocolo) ois.readObject());
            System.out.println("Pulsa <Enter> para continuar"); System.in.read();

            oos.writeObject(new MensajeProtocolo(Primitive.PUSH, "Estamos en Estambul."));
            System.out.println((MensajeProtocolo) ois.readObject());
            System.out.println("Pulsa <Enter> para continuar"); System.in.read();

            /* Escenario del taller */

            oos.writeObject(new MensajeProtocolo(Primitive.PULL_PERO_NO_ME_LO_BORRES));
            System.out.println((MensajeProtocolo) ois.readObject());
            System.out.println("Pulsa <Enter> para continuar"); System.in.read();
            
            /* FIN escenario del taller */

            oos.writeObject(new MensajeProtocolo(Primitive.PULL));
            System.out.println((MensajeProtocolo) ois.readObject());
            System.out.println("Pulsa <Enter> para continuar"); System.in.read();

            oos.writeObject(new MensajeProtocolo(Primitive.PULL_WAIT));
            System.out.println((MensajeProtocolo) ois.readObject());
            System.out.println("Pulsa <Enter> para finalizar"); System.in.read();

            /*** aquí se supone que tiene que llegar otro cliente e insertar
            *    un mensaje en la cola */

            /* FIN del escenario 1 */
        } catch (IOException e) {
            System.err.println("Cliente: Error de apertura o E/S sobre objetos: "+e);
        } catch (Exception e) {
            System.err.println("Cliente: Excepción. Cerrando Sockets: "+e);
        } finally {
            ois.close();
            oos.close();
            sock.close();
        }
    }
}