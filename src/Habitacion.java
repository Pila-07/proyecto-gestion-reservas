
public class Habitacion {

	private int numero;
	private int planta;
	private String tipo;
	private int maxHuespedes;
	private boolean vistas;
	private double precioNoche;

	/*Constructor que con solo el numero de habitacion rellena el resto de 
	 los valores*/
	public Habitacion(int numero) {
		String tipo = null;
		boolean vistas = false;
		if(numero % 10 == 1 || numero % 10 == 2) {
			tipo = "Doble";
			maxHuespedes = 2;
			precioNoche = 50;
		}else if (numero > 62){
			tipo = "Triple";
			maxHuespedes = 3;
			precioNoche = 70;
			vistas = true;
		}else {
			tipo = "Triple";
			maxHuespedes = 3;
			precioNoche = 60;
		}

		this.numero = numero;
		this.planta = numero / 10;
		this.tipo = tipo;
		this.vistas = vistas;
	}

	public String toString() {
		return "Habitacion (" + numero + "): Planta = " + planta + ", Tipo = " +
				tipo + ", Vistas = " + vistas + String.format(", Precio Noche = "
						+ "%.2f€", precioNoche) + "]";
	}

	public int getNumero() {
		return numero;
	}

	public double getPrecioNoche() {
		return precioNoche;
	}

	public int getMaxHuespedes() {
		return maxHuespedes;
	}
}
