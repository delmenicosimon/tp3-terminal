package terminal.modelo;

import java.util.List;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Queue;
import java.util.ArrayDeque;
=======
import java.util.Optional;
>>>>>>> 58da7ce (Servicio v1)

public class Servicio {
	private String codigo;
	private String empresa;
	private String destino;
	private String horario;
	private int cantAsientos;
	private double precio;
	private List<Pasaje> pasajes;
<<<<<<< HEAD
	private Queue<Pasajero> listaDeEspera;
=======
>>>>>>> 58da7ce (Servicio v1)

	public Servicio(String codigo, String empresa, String destino, String horario, int cantAsientos, double precio) {
		this.codigo = codigo.trim().toUpperCase();
		this.empresa = empresa.trim().toUpperCase();
		this.destino = destino.trim().toUpperCase();
		this.horario = horario.trim().toUpperCase();
		setCantAsientos(cantAsientos);
		setPrecio(precio);
		crearPasajes();
<<<<<<< HEAD
		this.listaDeEspera = new ArrayDeque<>();
=======
>>>>>>> 58da7ce (Servicio v1)
	}

	private void setCantAsientos(int cantAsientos) {
		if (cantAsientos <= 0) {
<<<<<<< HEAD
			throw new IllegalArgumentException("No se pueden tener 0 o menos asientos");
=======
			this.cantAsientos = 1;
			return;
>>>>>>> 58da7ce (Servicio v1)
		}
		this.cantAsientos = cantAsientos;
	}

	private void setPrecio(double precio) {
		if (precio < 0) {
			this.precio = 0;
			return;
		}
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public String getDestino() {
		return destino;
	}

	public String getHorario() {
		return horario;
	}

	public int getCantAsientos() {
		return cantAsientos;
	}

	public double getPrecio() {
		return precio;
	}

	private void crearPasajes() {
		this.pasajes = new ArrayList<>();
		for (int i = 0; i < this.cantAsientos; i++) {
			this.pasajes.add(new Pasaje(i + 1));
		}
	}

<<<<<<< HEAD
	public void devolverPasaje(int nroAsiento) {
		if (nroAsiento >= this.pasajes.size() || nroAsiento <= 0) {
			throw new IndexOutOfBoundsException(
					"El asiento : " + nroAsiento + " no existe en el servicio : " + this.codigo);
		}
		Pasaje pasaje = this.pasajes.get(nroAsiento - 1);
		if (this.listaDeEspera.peek() != null) {
			Pasajero pasajero = this.listaDeEspera.poll();
			pasaje.setDni(pasajero.getdni());
		} else {
			pasaje.setDni(null);
		}
	}

	public Pasaje getPasaje() throws AsientoOcupadoException{
		for(Pasaje pasaje : this.pasajes){
			if(pasaje.asientoLibre()){
				return pasaje;
			}
		}
		throw new AsientoOcupadoException(this.codigo);
	}
	
	public Pasaje getPasaje(int nroAsiento) throws AsientoOcupadoException{
		for(Pasaje pasaje : this.pasajes){
			if(pasaje.getNumAsiento() == nroAsiento && pasaje.asientoLibre()){
				return pasaje;
			}
		}
		throw new AsientoOcupadoException(this.codigo,nroAsiento);
	}

	public Pasaje getPasajePorDni(String dni){
		Pasaje resultado;
		for(Pasaje pasaje : this.pasajes){
			if(dni.equals(pasaje.getPasajero()){
				resultado = pasaje;
			}
		}
		return resultado;
	}

	public List<Pasaje> getPasajes(){
		return this.pasajes;
	}

	public void setEspera(Pasajero pasajero){
		try{
			this.listaDeEspera.offer(pasajero);
		}catch(IndexOutOfBoundsException e){
			System.out.println(e.getMessage());
		}
=======
	public Optional<Pasaje> devolverPasaje(int nroAsiento) {
		return Optional.ofNullable(this.pasajes.get(nroAsiento - 1));
>>>>>>> 58da7ce (Servicio v1)
	}
}
