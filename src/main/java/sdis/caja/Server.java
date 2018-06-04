package sdis.caja;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server{
	public static void main(String[] args) throws Exception{
		Caja caja = new CajaImpl();
		Registry registro = LocateRegistry.createRegistry(1099);
		registro.rebind("MiObjetoCaja",	caja);
		System.out.println("Servidor listo!");
	}
}
