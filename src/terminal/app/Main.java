package terminal.app;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

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
    }
}
