package sdis.rmi;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements Hola{
	public Servidor() throws RemoteException {}

	public String sayHello() throws RemoteException {
		return "Hola, mundo!";
	}

	public static void main(String[] args){
		try{
			Servidor oRemoto = new Servidor();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("sdis.rmi.Hola",oRemoto);
			System.out.println("Servidor OK");
		}catch(Exception e){
			System.err.println("ERROR: "+e.toString());
			e.printStackTrace();
		}
	}
}