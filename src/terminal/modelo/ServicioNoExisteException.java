package terminal.modelo;

public class ServicioNoExisteException extends RuntimeException {
    public ServicioNoExisteException(String codigo) {
        super("El servicio de codigo : " + codigo + " no existe.");
    }
}
