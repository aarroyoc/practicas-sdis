package sdis.caja;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class CajaImpl extends UnicastRemoteObject implements Caja {
	
	private Acumulador acumulador = null;

	public CajaImpl() throws RemoteException{
		super();
	}

	public void guarda(Acumulador ac) throws RemoteException{
		this.acumulador = ac;
		try{
			String ip = getClientHost();
			System.out.println("["+ip+"] Guardado "+ac.getAcumulado());
		}catch(Exception e){

		}
	}

	public Acumulador lee() throws RemoteException{
		try{
			String ip = getClientHost();
			System.out.println("[" +ip+"] Leído "+acumulador.getAcumulado());
		}catch(Exception e){

		}
		return acumulador;
	}
}
