package terminal.modelo;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Optional;

public class ListaDeEspera<T>{
    private int capacidad;
    private ArrayDeque<T> lista;

    public ListaDeEspera(int capacidad){
        this.lista = new ArrayDeque<T>();
        this.setCapacidad(capacidad); 
    }

    private void setCapacidad(int capacidad){
        if(capacidad <= 0){
            throw new IllegalArgumentException("La capacidad de una lista tiene que ser mayor a 0");
        }
        this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Optional<T> getPrimeroEnLista(){
        if(this.lista.peek() == null){
            return Optional.empty();
        }
        return Optional.ofNullable(this.lista.poll());
    }

    public Optional<T> peekPrimeroEnLista(){
        return Optional.ofNullable(this.lista.peek());
    }

    public void agregarEnLista(T elemento){
        if(this.lista.size() == capacidad){
            throw new ListaLlenaException("La lista esta llena, no se pudo agregar elemento : " + elemento);
        }
        this.lista.offer(elemento);
    }

    public int cantEnLista(){
        if(this.lista.peek() == null){
            return 0;
        }
        return this.lista.size();
    }

    public Iterator<T> getLista(){
        return this.lista.iterator();
    }

    @Override 
    public String toString(){
        return "Lista de espera de tamaño : " + capacidad + " con : " + this.cantEnLista() + " elementos actualmente.";
    }
    
}
