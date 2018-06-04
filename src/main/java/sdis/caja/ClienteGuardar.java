package sdis.caja;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

public class ClienteGuardar {
	public static void main(String[] args) throws Exception{
		//Caja stub = (Caja) Naming.lookup("rmi://l106r1.lab.inf.uva.es/MiObjetoCaja");
		Caja stub = (Caja) Naming.lookup("rmi://localhost:1099/MiObjetoCaja");
		Acumulador ac = new Acumulador(42);
		stub.guarda(ac);
	}
}
