package terminal.app;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import terminal.modelo.*;

public class Main {
    public static void main(String[] args) {

        Terminal terminal = new Terminal();
        terminal.setServicio(new Servicio("NEC-MDP-0700", "Costera", "Mar del Plata", "07:00", 4, 12000.00));
        terminal.setServicio(new Servicio("NEC-BA-2200", "Atlántica", "Buenos Aires", "22:00", 6, 38000.00));
        terminal.setServicio(new Servicio("NEC-TAN-0930", "Costera", "Tandil", "09:30", 3, 9500.00));
        terminal.setServicio(new Servicio("NEC-TRA-1400", "Atlántica", "Tres Arroyos", "14:00", 5, 21000.00));

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
    }
}
