package terminal.modelo;

public class AsientoOcupadoException extends Exception{
    public AsientoOcupadoException(String codigoServicio,int nroAsiento){
        super("Asiento numero : " + nroAsiento + " del servicio : " + codigoServicio + " esta ocupado.");
    }
    public AsientoOcupadoException(String codigoServicio){
        super("No hay asientos libres en el servicio : " + codigoServicio);
    }
}
