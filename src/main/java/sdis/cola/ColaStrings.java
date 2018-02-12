package sdis.cola;

import java.util.*;

public class ColaStrings {

    private List<String> lista = new ArrayList<String>();

    public void push(final String p) {
        synchronized(this){
            lista.add(p);
            this.notify(); // hace saber que ha llegado un String
        }
    }

    public synchronized String pop() {
        while (lista.size() == 0)
            try { this.wait(); // espera la llegada de un String
            } catch (final InterruptedException e) { }
        return lista.remove(0);
    }
}
