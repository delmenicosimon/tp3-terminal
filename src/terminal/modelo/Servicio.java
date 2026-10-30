package terminal.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

public class Servicio {
	private String codigo;
	private String empresa;
	private String destino;
	private String horario;
	private int cantAsientos;
	private double precio;
	private List<Pasaje> pasajes;
	private Queue<Pasajero> listaDeEspera;

	public Servicio(String codigo, String empresa, String destino, String horario, int cantAsientos, double precio) {
		this.codigo = codigo.trim().toUpperCase();
		this.empresa = empresa.trim().toUpperCase();
		this.destino = destino.trim().toUpperCase();
		this.horario = horario.trim().toUpperCase();
		setCantAsientos(cantAsientos);
		setPrecio(precio);
		crearPasajes();
	}

	private void setCantAsientos(int cantAsientos) {
		if (cantAsientos <= 0) {
			this.cantAsientos = 1;
			return;
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

	public void devolverPasaje(int nroAsiento) {
		if (nroAsiento >= this.pasajes.size() || nroAsiento <= 0) {
			throw new IndexOutOfBoundsException(
					"Cantidad de asientos : " + cantAsientos + ", asiento devuelto: " + nroAsiento);
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
		throw new AsientoOcupadoException();
	}
	
	public Pasaje getPasaje(int nroAsiento) throws AsientoOcupadoException{
		for(Pasaje pasaje : this.pasajes){
			if(pasaje.getNumAsiento() == nroAsiento && pasaje.asientoLibre()){
				return pasaje;
			}
		}
		throw new AsientoOcupadoException(nroAsiento);
	}
}
