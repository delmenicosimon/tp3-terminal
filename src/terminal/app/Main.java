package terminal.app;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.List;

import terminal.modelo.*;

public class Main {

    public static void cargarServicios(Terminal terminal){
        terminal.setServicio(new Servicio("NEC-MDP-0700", "Costera", "Mar del Plata", "07:00", 4, 12000.00));
        terminal.setServicio(new Servicio("NEC-BA-2200", "Atlántica", "Buenos Aires", "22:00", 6, 38000.00));
        terminal.setServicio(new Servicio("NEC-TAN-0930", "Costera", "Tandil", "09:30", 3, 9500.00));
        terminal.setServicio(new Servicio("NEC-TRA-1400", "Atlántica", "Tres Arroyos", "14:00", 5, 21000.00));
    }

    public static List<String> procesarLinea(String linea,String caracterDelimitador) throws IllegalArgumentException{
        List<String> datos = List.of(linea.split(caracterDelimitador));
        if(datos.size() < 4){
            throw new IllegalArgumentException("La linea tiene menos datos de lo esperado.");
        }
        return datos;
    }

    public static void agregarMensajeLista(ListaDeEspera<String> lista, String mensaje){
        if(lista.getCapacidad() != lista.cantEnLista()){
            lista.agregarEnLista(mensaje);
        }
    }

    public static void procesarArchivo(String archivo,Terminal terminal,ListaDeEspera<String> listaMensajes){
        int numeroRenglon = 0;
        int cantVentas = 0;
        int renglonesRechazados = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea; 
            while ((linea = lector.readLine()) != null) {
                numeroRenglon++;
                try{
                    List<String> datos = procesarLinea(linea, ";");
                    Pasajero pasajero = new Pasajero(datos.get(1),datos.get(2));
                    terminal.venderPasaje(datos.get(0), pasajero, datos.get(3));
                    cantVentas++;
                } catch(IllegalArgumentException e){
                    agregarMensajeLista(listaMensajes,"Linea " + numeroRenglon + " " + e.getMessage());
                    renglonesRechazados++;
                } catch(ServicioNoExisteException e){
                    agregarMensajeLista(listaMensajes,"Linea " + numeroRenglon + " " + e.getMessage());
                    renglonesRechazados++;
                } catch(AsientoOcupadoException e){
                    agregarMensajeLista(listaMensajes,"Linea " + numeroRenglon + " " + e.getMessage());
                    renglonesRechazados++;
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Error, no se encuentra el archivo : " + archivo );
            return;
        } catch (IOException e){
            System.out.println("Ocurrio un error al procesar el archivo : " + archivo);
            return;
        }
        cantVentas -= terminal.cantEnEspera();
        try{
            agregarMensajeLista(listaMensajes,"Renglones leidos : " + numeroRenglon);
            agregarMensajeLista(listaMensajes,"Ventas realizadas : " + cantVentas);
            agregarMensajeLista(listaMensajes,"Ventas rechazadas : " + renglonesRechazados);
            agregarMensajeLista(listaMensajes,"Pasajeros en espera : " + terminal.cantEnEspera());
        } catch(ListaLlenaException e){
            System.out.println(e.getMessage());
        }
      }

    public static void imprimirPasaje(Optional<Pasaje> pasaje){
        if(pasaje.isPresent()){
            System.out.println(pasaje.get());
        }else{
            System.out.println("el pasajero no tiene este pasaje");
        }
    }

    public static void imprimirLista(ListaDeEspera<String> listaMensajes){
        while(listaMensajes.peekPrimeroEnLista().isPresent()){
            System.out.println(listaMensajes.getPrimeroEnLista().get());
        }
    }

    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        cargarServicios(terminal);
        ListaDeEspera<String> listaDeMensajes = new ListaDeEspera<>(50);
        System.out.println("--------------------PROCESANDO VENTAS--------------------");
        procesarArchivo("ventas.txt",terminal,listaDeMensajes);
        procesarArchivo("vienentas.txt",terminal,listaDeMensajes);
        System.out.println("--------------------INFORME DE CARGA--------------------");
        imprimirLista(listaDeMensajes);
        System.out.println("--------------------BRUNO DEVUELVE SU PASAJE--------------------");
        terminal.devolverPasajePorAsiento("NEC-MDP-0700", 2);
        System.out.println("--------------------MOSTRANDO PASAJEROS--------------------");
        terminal.showPasajeros("NEC-MDP-0700");
        System.out.println("--------------------MOSTRANDO RECAUDOS--------------------");
        terminal.showRecaudos();
        System.out.println("--------------------BUSCANDO PASAJES--------------------");
        Optional<Pasaje> pasajeAna = terminal.buscarPasaje("NEC-TRA-1400", "30111222");
        Optional<Pasaje> pasajeBruno = terminal.buscarPasaje("NEC-TRA-1400", "40123456");
        imprimirPasaje(pasajeAna);
        imprimirPasaje(pasajeBruno);
    }   
}
