import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entrada.Teclado;

public class Main {

	/*Muestra en consola el menú del programa*/
	private static void menu() {
		System.out.println("(0) Salir");
		System.out.println("(1) Insertar un cliente");
		System.out.println("(2) Crear reserva");
		System.out.println("(3) Ver reservas");
		System.out.println("(4) Cancelar reserva");
		System.out.println("(5) Ver habitaciones disponibles");
	}

	/*Crea las habitaciones automaticamente de tres en tres por cada decena*/
	private static void crearHabitaciones(List <Habitacion> habitaciones) {
		int unidad = 1;
		for(int decena = 10; decena <= 90;) {
			habitaciones.add(new Habitacion (decena + unidad));
			unidad++;
			if(unidad > 3) {
				unidad = 1;
				decena = decena + 10;
			}
		}
	}
	/*Busca una habitación en la lista usando como criterio el número de esta, si
	  se encuentra la devuelve*/
	public static Habitacion buscarHabitacion(List<Habitacion> habitaciones, int numero) {
		Habitacion resultado = null;
		for(Habitacion h : habitaciones) {
			if(h.getNumero() == numero) {
				resultado = h;
			}
		}
		return resultado;
	}

	public static Reserva buscarReserva(List<Reserva> reserva, int numero, Fecha fecha) {
		Reserva resultado = null;
		for(Reserva r : reserva) {
			if(r.getHabitacion().getNumero() == numero && fecha.equals(r.getFechaCheckIn())) {
				resultado = r;
			}
		}
		return resultado;
	}

	public static void main(String[] args) {
		int opcion = 0;
		int numero = 0;
		boolean valido;
		String fecha = null;
		Fecha fechaAux = null;
		List <Cliente> clientes = new ArrayList <Cliente>();
		List <Habitacion> habitaciones = new ArrayList <Habitacion>();
		List <Reserva> reservas = new ArrayList <Reserva>();
		crearHabitaciones(habitaciones);
		do {
			menu();
			opcion = Teclado.leerEntero("Elige una opción (0-5): ");
			switch(opcion) {
			case 0:
				System.out.println("\nPrograma finalizado");
				break;
			case 1:
				boolean coincidencia = false;

				String dni = Teclado.leerCadena("\nDNI: ");

				/*Se recorre la lista para saber si existe otro cliente con el 
				mismo DNI*/
				Iterator <Cliente> it = clientes.iterator();
				while(it.hasNext() && !coincidencia) {
					if(it.next().getDni().equals(dni)) {
						coincidencia = true;
					}
				}
				/*En caso de que haya otro cliente con el mismo DNI este no se crea*/
				if(coincidencia) {
					System.err.println("\nYa existe un cliente con el mismo DNI\n");
				}else {
					String nombre = Teclado.leerCadena("Nombre: ");
					String direccion = Teclado.leerCadena("Dirección: ");
					clientes.add(new Cliente(dni, nombre, direccion));
					System.out.println("\nCliente insertado\n");
				}
				break;
			case 2:
				Fecha fechaCheckIn = null;
				Fecha fechaCheckOut = null;
				Habitacion h;
				
				/*Bucle que da dos vueltas para recoger la fecha del check-in
				 y de check-out*/
				for(int i = 0; i < 2; i++) {
					valido = false;
					while(!valido) {
						if(i == 0) {
							fecha = Teclado.leerCadena("\nFecha check-in(DD/MM/AAAA): ");
						}else {
							fecha = Teclado.leerCadena("\nFecha check-out(DD/MM/AAAA): ");
						}
						try {
							if(i == 0) {
								fechaCheckIn = new Fecha(fecha);
								valido = true;
							}else {
								fechaCheckOut = new Fecha(fecha);
								if(fechaCheckIn.esAntes(fechaCheckOut)) {
									valido = true;
								}else {
									System.err.println("La fecha introducida es "
											+ "anterior o igual a la fecha de check-in");
								}
							}
						}catch(IllegalArgumentException iae) {
							System.err.println(iae.getMessage());
							valido = false;
						}
					}
				}
				/*Bucle que en caso de recibir un número de habitación que no 
				 existe vuelve a pedir el valor*/
				do {
					numero = Teclado.leerEntero("\nNúmero de habitación: ");
					h = buscarHabitacion(habitaciones, numero);
					if(h == null) {
						System.err.println("La habitación no existe");
					}
				}while(h == null);
				int huespedes = 0;

				/*Bucle que se encarga de que la cantidad de huespedes sea valida*/
				while(huespedes < 1 || huespedes > h.getMaxHuespedes()) {
					huespedes = Teclado.leerEntero("\nNúmero de huespedes: ");
					if(huespedes < 1 || huespedes > h.getMaxHuespedes()) {
						System.err.println("La cantidad de huespedes es erronea"
								+ " (1-" + h.getMaxHuespedes() + ")");
					}
				}
				reservas.add(new Reserva(fechaCheckIn, fechaCheckOut, h, huespedes));
				System.out.println("\nReserva creada\n");
				break;
			case 3:
				System.out.println();
				for(Reserva re : reservas) {
					System.out.println(re.toString() + "\n");
				}
				break;
			case 4:
				numero = Teclado.leerEntero("\nNúmero de habitación: ");
				
				/*Bucle que recibe el valor de la fecha de check-in*/
				valido = false;
				while(!valido){
					try {
						fecha = Teclado.leerCadena("\nFecha check-in(DD/MM/AAAA): ");
						fechaAux = new Fecha(fecha);
						valido = true;
					}catch(IllegalArgumentException iae) {
						System.err.println(iae.getMessage());
						valido = false;
					}
				}
				
				Reserva r = buscarReserva(reservas, numero, fechaAux);
				if(r == null) {
					System.err.println("\nLa reserva no existe\n");
				}else {
					reservas.remove(r);
					System.out.println("\nLa reserva ha sido cancelada\n");
				}
				break;
			case 5:
				System.out.println();
				for(Habitacion ha : habitaciones) {
					System.out.println(ha.toString() + "\n");
				}
				break;
			default:
				System.out.println("\nOpción no valida (0-5)\n");
			}
		}while(opcion != 0);
	}

}
