package terminal.modelo;
import java.util.Map;
import java.util.HashMap;

public class Terminal {
    private Map<String, Servicio> servicios;
    private Map<String, Double> recaudos;

    public Terminal() {
        this.servicios = new HashMap<>();
        this.recaudos = new HashMap<>();
    }

    public void setServicio(Servicio servicio){
        this.servicios.put(servicio.getCodigo(),servicio);
    }
    
    public void venderPasaje(String codigoServicio, Pasajero pasajero, String asiento){
        Servicio servicio = servicios.get(codigoServicio);
        if(servicio == null){
            throw new IllegalArgumentException("No se puede encontrar el servicio: " + codigoServicio);
        }
        if(asiento.trim() == "*") {
            Pasaje pasaje;
            try{
                pasaje = servicio.getPasaje();
                pasaje.setDni(pasajero.getdni());
                System.out.println("Hola");
            } catch(AsientoOcupadoException e){
                System.out.println(e.getMessage());
                servicio.setEspera(pasajero);
            }
        }
    }

    public static void main(String args[]){
        Servicio servicio = new Servicio("ELPEPE", "ete sech seguros", "bolivia", "10:30", 1,10000.00);
        Pasajero pasajero = new Pasajero("30000000","Juan");
        Pasajero pasajero2 = new Pasajero("33333333","Eugenio");
        Terminal terminal = new Terminal();
        terminal.setServicio(servicio);
        terminal.venderPasaje("ELPEPE", pasajero, "*");
        terminal.venderPasaje("ELPEPE", pasajero2, "*");
    }

}
