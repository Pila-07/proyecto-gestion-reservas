import java.util.ArrayList;
import java.util.List;


public class Cliente {

	private String dni;
	private String nombre;
	private String direccion;
	private List<Reserva> historialReservas;

	//Constructor principal de la clase
	public Cliente(String dni, String nombre, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.historialReservas = new ArrayList<Reserva>();
	}

	public String getDni() {
		return dni;
	}

	public String toString() {
		return "Cliente [DNI = " + dni + ", Nombre = " + nombre + ", Dirección = " 
				+ direccion + "]";
	}

}
