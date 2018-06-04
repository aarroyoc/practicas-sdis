package sdis.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

public class Cliente {
	private Cliente() {}

	public static void main(String[] args){
		String host = "localhost";
		try{
			Hola stub = (Hola) Naming.lookup("rmi://localhost:1099/sdis.rmi.Hola");
			String respuesta = stub.sayHello();
			System.out.println("Respuesta: "+respuesta);
		} catch (Exception e){
			System.err.println("Excepcion: "+e.toString());
			e.printStackTrace();
		}
	}
}