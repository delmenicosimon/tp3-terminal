package terminal.modelo;

public class Pasaje {
    private int numAsiento;
    private Pasajero pasajero;
    
    public Pasaje(int numAsiento){
        setNumAsiento(numAsiento);
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

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public boolean asientoLibre(){
        if(pasajero == null){
            return true;
        }else{
            return false;
        }
    }
}