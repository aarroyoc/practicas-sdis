package sdis.file.server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class RunFileServidor {
	public static void main(String[] args){
		try{
			FileServidor cc = new FileServidorImpl();
			Registry registro = LocateRegistry.createRegistry(1099);
		
			registro.rebind("ObjetoFileServidor",cc);

		}catch(RemoteException re){
			re.printStackTrace(System.err);
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
}
