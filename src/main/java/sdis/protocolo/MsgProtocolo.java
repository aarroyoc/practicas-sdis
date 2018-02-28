package sdis.protocolo;

import java.util.*;
import java.io.*;

public class MsgProtocolo implements Serializable{
    private Primitiva primitiva;
    private String mensaje ;

    public MsgProtocolo(Primitiva p) {
        /* hemos incluido dos assert por la peligrosidad del constructor
        * recordad que hay que activar expresamente la evaluación
        * de asserts en la JVM */
        assert p.isCompound() == false;

        this.primitiva = p;
        this.mensaje = "";
    }

    public MsgProtocolo(Primitiva p, String m) {
        assert p.isCompound() == true && m != null ;
        this.primitiva = p;
        this.mensaje = m;
    }

    Primitiva getPrimitive() { return this.primitiva; }
    String getMessage() { return this.mensaje; }
}