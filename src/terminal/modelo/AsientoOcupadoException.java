package terminal.modelo;

public class AsientoOcupadoException extends Exception{
    public AsientoOcupadoException(String dni, int nroAsiento){
        super("Asiento numero : " + nroAsiento + " Ocupado por : " + dni);
    }
}
