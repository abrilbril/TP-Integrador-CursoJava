package models;

public class Pronostico {
	private Partido partido;
	private Equipo equipo;
	private Persona persona;
	
	public Pronostico(Partido partido, Equipo equipo, Persona persona) {
		this.partido = partido;
		this.equipo = equipo;
		this.persona = persona;
	}
	
	public Partido getPartido() {
		return partido;
	}
	
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public int puntos(ResultadoE resultado) {
		ResultadoE resultadoFinal = partido.obtenerResultadoFinal(equipo);
		if(resultado.equals(resultadoFinal)) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
