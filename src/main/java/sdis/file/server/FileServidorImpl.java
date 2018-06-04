package sdis.file.server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import sdis.file.client.FileReceptor;

public class FileServidorImpl extends java.rmi.server.UnicastRemoteObject implements FileServidor{
	public FileServidorImpl() throws RemoteException{
		super();
	}

	@Override
	public void abre(String nombre, FileReceptor receptor) throws RemoteException, FileNotFoundException {
		Lector lector = new Lector(nombre,receptor);
		new Thread(lector).start();
	}
}
