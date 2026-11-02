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
                    listaMensajes.agregarEnLista(("Linea " + numeroRenglon + " " + e.getMessage()));
                    renglonesRechazados++;
                } catch(ServicioNoExisteException e){
                    listaMensajes.agregarEnLista(("Linea " + numeroRenglon + " " + e.getMessage()));
                    renglonesRechazados++;
                } catch(AsientoOcupadoException e){
                    listaMensajes.agregarEnLista(("Linea " + numeroRenglon + " " + e.getMessage()));
                    renglonesRechazados++;
                }
            }
        } catch (FileNotFoundException e){
            listaMensajes.agregarEnLista("Error, no se encuentra el archivo : " + archivo );
            return;
        } catch (IOException e){
            listaMensajes.agregarEnLista("Ocurrio un error al procesar el archivo : " + archivo);
            return;
        }
        cantVentas -= terminal.cantEnEspera();
        listaMensajes.agregarEnLista("Renglones leidos : " + numeroRenglon);
        listaMensajes.agregarEnLista("Ventas realizadas : " + cantVentas);
        listaMensajes.agregarEnLista("Ventas rechazadas : " + renglonesRechazados);
        listaMensajes.agregarEnLista("Pasajeros en espera : " + terminal.cantEnEspera());
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
        procesarArchivo("ventas.txt",terminal,listaDeMensajes);
        procesarArchivo("vienentas.txt",terminal,listaDeMensajes);
        imprimirLista(listaDeMensajes);
        terminal.devolverPasajePorAsiento("NEC-MDP-0700", 2);
        terminal.showPasajeros("NEC-MDP-0700");
        terminal.showRecaudos();
        Optional<Pasaje> pasajeAna = terminal.buscarPasaje("NEC-TRA-1400", "30111222");
        Optional<Pasaje> pasajeBruno = terminal.buscarPasaje("NEC-TRA-1400", "40123456");
        imprimirPasaje(pasajeAna);
        imprimirPasaje(pasajeBruno);
    }   
}
