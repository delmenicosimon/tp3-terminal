package terminal.app;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.zip.DataFormatException;

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
    
    public static void procesarArchivo(String archivo,Terminal terminal){
        int numeroRenglon = 0;
        int cantVentas = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea; 
            try{
                while ((linea = lector.readLine()) != null) {
                    numeroRenglon++;
                    List<String> datos = procesarLinea(linea, ";");
                    Pasajero pasajero = new Pasajero(datos.get(1),datos.get(2));
                    terminal.venderPasaje(datos.get(0), pasajero, datos.get(3));
                }
            } catch(IllegalArgumentException e){
                System.out.println("Linea " + numeroRenglon + " " + e.getMessage());
            } catch(ServicioNoExisteException e){
                System.out.println("Linea " + numeroRenglon + " " + e.getMessage());
            }
        } catch (FileNotFoundException e){
            System.out.println("Error, no se encuentra el archivo : " + archivo );
        } catch (IOException e){
            System.out.println("Ocurrio un error al procesar el archivo : " + archivo);
        }

    }

    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        cargarServicios(terminal);
        procesarArchivo("ventas.txt",terminal);
    }
}
