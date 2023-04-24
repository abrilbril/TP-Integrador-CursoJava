package models;

public class Persona {
	private String nombre;
	private int puntaje = 0;
	private int cantDePronAcertados = 0;
	private int nRonda = 0;
	
	public Persona(String nombre, int puntaje, int cantDePronAcertados) {
		this.nombre = nombre;
		this.puntaje = puntaje;
		this.cantDePronAcertados = cantDePronAcertados;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public int getCantDePronAcertados() {
		return cantDePronAcertados;
	}
	
	public void setCantDePronAcertados(int cantDePronAcertados) {
		this.cantDePronAcertados = cantDePronAcertados;
	}
	
	public int getnRonda() {
		return nRonda;
	}
	
	public void setnRonda(int nRonda) {
		this.nRonda = nRonda;
	}
}
