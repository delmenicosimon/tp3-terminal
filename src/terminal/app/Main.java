package terminal.app;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import terminal.modelo.*;

public class Main {
    public static void main(String[] args) {
        int numeroRenglon = 0;
        int cantVentas = 0;
        String archivo = "ventas.txt";

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea; 

            while ((linea = lector.readLine()) != null) {
                numeroRenglon++;

            }
            
        } catch (IOException e){
            System.out.println("Error con la lectura del archivo: " + archivo );
        }
        Servicio servicio = new Servicio("ELPEPE", "ete sech seguros", "bolivia", "10:30",1,10000.00);
        Pasajero pasajero = new Pasajero("30000000","Juan");
        Pasajero pasajero2 = new Pasajero("33333333","Eugenio");
        Terminal terminal = new Terminal();
        terminal.setServicio(servicio);
        terminal.venderPasaje("ELPEPE", pasajero, "1");
        terminal.venderPasaje("ELPEPE", pasajero2, "*");
        terminal.showPasajeros("ELPEPE");
        terminal.showRecaudos();

    }
}
