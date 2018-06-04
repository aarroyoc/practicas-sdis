package sdis.file.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface FileReceptor extends Remote {
	public void recibeLinea(String linea) throws RemoteException;
}
