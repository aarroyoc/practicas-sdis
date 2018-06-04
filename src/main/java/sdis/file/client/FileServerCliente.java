package sdis.file.client;

import java.io.*;
import java.rmi.Naming;
import sdis.file.server.FileServidor;

public class FileServerCliente extends java.rmi.server.UnicastRemoteObject implements FileReceptor {

	public static void main(String[] args){
		String nombreArchivo = "Hola.txt";
		FileServidor fs;
		FileReceptor fr;
		String linea;

		try{
			fs = (FileServidor) Naming.lookup("rmi://localhost/ObjetoFileServidor");
			fr = new FileServerCliente();
			try{
				fs.abre(nombreArchivo,fr);
			}catch(FileNotFoundException fnfe){
				System.err.println("El archivo "+nombreArchivo+" no existe");
			}catch(Exception ex){
				ex.printStackTrace(System.err);
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}

	public FileServerCliente() throws java.rmi.RemoteException{

	}

	public void recibeLinea(String linea) throws java.rmi.RemoteException{
		System.out.println("RECIBIDO: "+linea);
	}
}
