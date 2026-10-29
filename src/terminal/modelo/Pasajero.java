package terminal.modelo;

import java.util.Objects;

public class Pasajero {
    private String dni;
    private  String nombre; 


    public Pasajero (String dni, String nombre){
        this.dni = dni.trim();
        this.nombre = nombre.trim();
    }


    public String getdni() {
        return dni;

    }
    // B.2 los dos tienen el mismo dni   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return Objects.equals(dni, pasajero.dni);
    }
        @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}




