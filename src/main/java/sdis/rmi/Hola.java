package sdis.rmi;

public interface Hola extends java.rmi.Remote {
	public String sayHello() throws java.rmi.RemoteException;
}