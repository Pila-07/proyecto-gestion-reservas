
public class Reserva {
	
	private Fecha fechaCheckIn;
	private Fecha fechaCheckOut;
	private int noches;
	private int huespedes;
	private Habitacion habitacion;
	private double precio;
	private double importePagado;
	
	//Constructor principal de la clase
	public Reserva(Fecha fechaCheckIn, Fecha fechaCheckOut, Habitacion habitacion, 
			int huespedes) {
		this.fechaCheckIn = fechaCheckIn;
		this.fechaCheckOut = fechaCheckOut;
		this.noches = fechaCheckIn.diasEntre(fechaCheckOut);
		this.habitacion = habitacion;
		this.huespedes = huespedes;
		this.precio = habitacion.getPrecioNoche() * noches;
		this.importePagado = 0;
	}

	public String toString() {
		return "Reserva [Fecha Check-In = " + fechaCheckIn.toString() + 
				", Fecha Check-Out = " + fechaCheckOut.toString() + ", Noches = " 
				+ noches + ", Huespedes = " + huespedes + ", Habitación = " + 
				habitacion.getNumero() + String.format(", Precio = %.2f€, "
						+ "Importe Pagado = %.2f€", precio, importePagado) + "]";
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public Fecha getFechaCheckIn() {
		return fechaCheckIn;
	}
}
