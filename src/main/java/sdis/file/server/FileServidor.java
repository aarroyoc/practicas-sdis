package sdis.file.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.FileNotFoundException;
import sdis.file.client.FileReceptor;

public interface FileServidor extends Remote {
	public void abre(String nombre, FileReceptor receptor) throws RemoteException, FileNotFoundException;
}
