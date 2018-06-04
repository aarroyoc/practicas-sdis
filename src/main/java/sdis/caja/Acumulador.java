package sdis.caja;

import java.io.Serializable;

public class Acumulador implements Serializable {
        private int acumulado;
        public Acumulador(int valorInicial){
                this.acumulado = valorInicial;
        }
        public int getAcumulado(){
                return acumulado;
        }
        public int incrementa(){
                return ++this.acumulado;
        }
}
