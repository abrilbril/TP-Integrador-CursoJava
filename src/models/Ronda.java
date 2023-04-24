package models;


import java.util.ArrayList;
import java.util.List;

public class Ronda {
	private Integer numero;
	private List <Partido> partidos = new ArrayList<>();
	
	public Ronda(Integer numero, List <Partido> partidos) {
		this.setNumero(numero);
		this.partidos = partidos;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List <Partido> partidos) {
		this.partidos = partidos;
	}
	
	public void agregarPartidos(Partido partido) {
		this.partidos.add(partido);
	}
	
	public Partido buscarPartido(Equipo equipo1, Equipo equipo2) {
	
	}
}
