import java.util.Objects;

public class Fecha {

	private int dia;
	private int mes;
	private int anyo;

	/*Constructor principal de la clase, lanza un error si la fecha no está 
	dentro de los limites y con el formato adecuado*/
	public Fecha(String fecha) {
		if(!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
			throw new IllegalArgumentException("Formato de fecha invalido (DD/MM/AAAA)");
		}
		int dia = Integer.parseInt(fecha.substring(0, 2));
		int mes = Integer.parseInt(fecha.substring(3, 5));
		int anyo = Integer.parseInt(fecha.substring(6, 10));
		if (mes < 1 || mes > 12) {
			throw new IllegalArgumentException("Mes inválido");
		}else if (anyo < 1000 || anyo > 9999) {
			throw new IllegalArgumentException("Año inválido");
		}else if (dia < 1 || dia > diasMes(mes, anyo)) {
			throw new IllegalArgumentException("Día inválido");
		}
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
	}

	public String toString() {
		return String.format("%02d/%02d/%04d", dia, mes, anyo) ;
	}

	/*Devuelve true si la fecha desde la que se llama la función es anterior a 
	 la otra y false en caso contrario o si es igual*/
	public boolean esAntes(Fecha otra) {
		boolean anterior = false;
		if (this.anyo < otra.anyo) {
			anterior = true;
		}
		if (this.anyo == otra.anyo && this.mes < otra.mes) {
			anterior = true;
		}
		if (this.anyo == otra.anyo && this.mes == otra.mes && this.dia < otra.dia) {
			anterior = true;
		}
		return anterior;
	}
	
	/*Calcula los días del mes teniendo en cuenta los meses bisiestos*/
	public int diasMes(int mes, int anyo) {
		int dias = 0;
		switch(mes) {
		case 1, 3, 5 ,7, 8, 10, 12:
			dias = 31;
		break;
		case 4, 6, 9, 11:
			dias = 30;
		break;
		case 2:
			if(esBisiesto(anyo)) {
				dias = 29;
			}else {
				dias = 28;
			}
			break;	
		}
		return dias;
	}
	
	/*Devuelve true si el año es bisiesto*/
	private static boolean esBisiesto(int anyo) {
		return (anyo % 4 == 0 && anyo % 100 != 0) || anyo % 400 == 0;
	}
	
	/*Transforma una fecha en días*/
	private int aDias() {
	    int dias = dia;

	    for(int i = 1; i < mes; i++) {
	        dias += diasMes(i, anyo);
	    }

	    for(int i = 1; i < anyo; i++) {
	        dias += esBisiesto(i) ? 366 : 365;
	    }

	    return dias;
	}
	
	/*Calcula los días que hay entre dos fechas*/
	public int diasEntre(Fecha otra) {
		return Math.abs(this.aDias() - otra.aDias());
	}

	@Override
	public int hashCode() {
		return Objects.hash(anyo, dia, mes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fecha other = (Fecha) obj;
		return anyo == other.anyo && dia == other.dia && mes == other.mes;
	}

}
