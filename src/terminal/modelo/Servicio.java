package terminal.modelo;

import java.util.ArrayList;
import java.util.Optional;

public class Servicio {
	private String codigo;
	private String empresa;
	private String destino;
	private String horario;
	private int cantAsientos;
	private double precio;
	private ArrayList<Pasaje> pasajes;
	private ListaDeEspera<Pasajero> listaDeEspera;

	public Servicio(String codigo, String empresa, String destino, String horario, int cantAsientos, double precio) {
		this.codigo = codigo.trim().toUpperCase();
		this.empresa = empresa.trim().toUpperCase();
		this.destino = destino.trim().toUpperCase();
		this.horario = horario.trim().toUpperCase();
		setCantAsientos(cantAsientos);
		setPrecio(precio);
		crearPasajes();
		this.listaDeEspera = new ListaDeEspera<Pasajero>(5);
	}

	public Servicio(String codigo, String empresa, String destino, String horario, int cantAsientos, double precio,int capacidad) {
		this(codigo,empresa,destino,horario,cantAsientos,precio);	
		this.listaDeEspera = new ListaDeEspera<Pasajero>(capacidad);
	}

	private void setCantAsientos(int cantAsientos) {
		if (cantAsientos <= 0) {
			throw new IllegalArgumentException("No se pueden tener 0 o menos asientos");
		} this.cantAsientos = cantAsientos;
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
					"El asiento : " + nroAsiento + " no existe en el servicio : " + this.codigo);
		}
		Pasaje pasaje = this.pasajes.get(nroAsiento - 1);
		Optional<Pasajero> optPasajero= this.listaDeEspera.getPrimeroEnLista();
		if(optPasajero.isEmpty()){
			pasaje.setPasajero(null);
		}else{
			pasaje.setPasajero(optPasajero.get());
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
	
	public Optional<Pasaje> getPasaje(int nroAsiento) throws AsientoOcupadoException{
		for(Pasaje pasaje : this.pasajes){
			if(pasaje.getNumAsiento() == nroAsiento && pasaje.asientoLibre()){
				return Optional.ofNullable(pasaje);
			}
		}
		if(this.pasajes.size() < nroAsiento){
			return Optional.empty();
		}
		throw new AsientoOcupadoException(this.codigo,nroAsiento);
	}

	public Optional<Pasaje> getPasajePorDni(String dni){
		for(Pasaje pasaje : this.pasajes){
			if(dni.equals(pasaje.getPasajero().getDni())){
				return Optional.ofNullable(pasaje);
			}
		}
		return Optional.empty();
	}

	public ListaDeEspera<Pasajero> getListaDeEspera(){
		return this.listaDeEspera;
	}

	public ArrayList<Pasaje> getPasajes(){
		return this.pasajes;
	}

	public void setEspera(Pasajero pasajero){
		try{
			this.listaDeEspera.agregarEnLista(pasajero);
		}catch(ListaLlenaException e){
			System.out.println(e.getMessage());
		}
	}
}
