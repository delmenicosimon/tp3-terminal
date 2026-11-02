package terminal.modelo;
import java.util.Map;
import java.util.Optional;
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
                pasaje.setPasajero(pasajero);
                this.recaudos.put(servicio.getEmpresa(),this.recaudos.getOrDefault(servicio.getEmpresa(),0.00) + servicio.getPrecio());
            } catch(AsientoOcupadoException e){
                System.out.println(e.getMessage());
                servicio.setEspera(pasajero);
            }
        }
    }

    public Optional<Pasaje> buscarPasaje(String codigoServicio, String dni){
        Servicio servicio = servicios.get(codigoServicio);
        if (servicio == null) {
            return Optional.empty();
        }
        return servicio.getPasajePorDni(dni);
    }

    public void showPasajeros(String codigoServicio) {
        Servicio servicio = servicios.get(codigoServicio);
        if (servicio == null) {
            System.out.println("No se encotro el servicio con el codigo: " + codigoServicio);
            return;
        }

        System.out.println("Pasajeros del servicio : " + codigoServicio);
        for(Pasaje pasaje : servicio.getPasajes()){
            if(!pasaje.asientoLibre()) {
                System.out.println("Asiento : " + pasaje.getNumAsiento() + "\t DNI Pasajero : " + pasaje.getPasajero().getDni() +  " \tNombre Pasajero : " + pasaje.getPasajero().getNombre());
            }
        }

        if (servicio.getListaDeEspera().cantEnLista() == 0){
            System.out.println("La lista de espera esta vacia");
        }else{
            for(Pasajero pasajero : servicio.getListaDeEspera().getLista()){
                System.out.println("DNI : " + pasajero.getDni() + " Nombre : " + pasajero.getNombre());
            }
        }
    }

    public void showRecaudos(){
        for(Map.Entry<String,Double> empresa: this.recaudos.entrySet()){
            System.out.println("Empresa : " + empresa.getKey() + "\t recaudo : " + empresa.getValue());
        }
    }
}
