package models;

public class Partido {
	private Equipo equipo1;
	private Equipo equipo2;
	private int cantDeGoles1;
	private int cantDeGoles2;
	
	public Partido(Equipo equipo1, Equipo equipo2) {
		this.equipo1 = equipo1;
		this.equipo1 = equipo1;
	}
	
	public Equipo getEquipo1() {
		return equipo1;
	}
	
	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}
	public Equipo getEquipo2() {
		return equipo2;
	}
	
	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
	
	public int getCantDeGoles1() {
		return cantDeGoles1;
	}
	
	public void setCantDeGoles1(int cantDeGoles1) {
		this.cantDeGoles1 = cantDeGoles1;
	}
	
	public int getCantDeGoles2() {
		return cantDeGoles2;
	}
	
	public void setCantDeGoles2(int cantDeGoles2) {
		this.cantDeGoles2 = cantDeGoles2;
	}
	
	public ResultadoE obtenerResultadoFinal(Equipo equipo) {
		if (cantDeGoles1 == cantDeGoles2) {
			return ResultadoE.EMPATE;
		}
		if(equipo.getNombre().equals(equipo1.getNombre())) {
			if ( cantDeGoles1 > cantDeGoles2) {
				return ResultadoE.GANADOR;
			} else {
				return ResultadoE.PERDEDOR;
			}
		} else {
			if (cantDeGoles1 < cantDeGoles2) {
				return ResultadoE.GANADOR;
			} else {
				return ResultadoE.PERDEDOR;
			}
		}
	}
	
}
