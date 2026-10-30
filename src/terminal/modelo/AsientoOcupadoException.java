package terminal.modelo;

public class AsientoOcupadoException extends Exception{
    public AsientoOcupadoException( int nroAsiento){
        super("Asiento numero : " + nroAsiento + " esta ocupado.");
    }
    public AsientoOcupadoException(){
        super("No hay asientos libres.");
    }
}
