package terminal.modelo;

public class Pasaje {
    private int numAsiento;
    private String dni;
    
    public Pasaje(int numAsiento){
        setNumAsiento(numAsiento);
    }

    public Pasaje(int numAsiento, String dni){
        this(numAsiento);
        this.dni = dni.trim();
    }

    public int getNumAsiento() {
        return numAsiento;
    }

    private void setNumAsiento(int numAsiento) {
        if (numAsiento <= 0) {
            throw new IllegalArgumentException("Numero de asiento no valido");
        }
        this.numAsiento = numAsiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean asientoLibre(){
        if(dni == null){
            return true;
        }else{
            return false;
        }
    }
}