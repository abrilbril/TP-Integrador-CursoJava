package utilities;

import models.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utilities.LectorDB.*;

public class LectorCSV {
	private Path rutaResultados;
	private Path rutaConfig;
	private List<Ronda> rondas = new ArrayList<>();
	private List<Equipo> equipos = new ArrayList<>();
	
	public LectorCSV(Path rutaResultados, Path rutaConfig) {
		this.rutaResultados = rutaResultados;
		this.rutaConfig = rutaConfig;
	}
	
	public List <Ronda> getRondas(){
		return rondas;
	}
	
	public Ronda buscarRonda(Integer n) {
		for (Ronda r : this.rondas) {
			if (r.getNumero() == n) {
				return r;
			}
		}
		return null;
	}
	
	public Equipo buscarEquipo(String n) {
		for (Equipo e : this.equipos) {
			if (e.getNombre().equals(n)) {
				return e;
			}
		}
		return null;
	}
	
	public void leerResultados() {
		List<String> lineasDeResultado = new ArrayList<>();
		try {
			lineasDeResultado = Files.readAllLines(rutaResultados);
		} catch (IOException e) {
			System.out.println("La línea de resultados no se pudo leer, disculpe");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		boolean primera = true;
		
		for ( String lineaDeResultado : lineasDeResultado) {
			if (primera) {
				primera = false;
			} else {
				String[] campos = lineaDeResultado.split(";");
				Equipo equipo1 = this.buscarEquipo(campos[1]);
				Equipo equipo2 = this.buscarEquipo(campos[4]);
				
				if(equipo1 == null) {
					equipo1 = new Equipo(campos[1]);
					equipos.add(equipo1);
				}
				
				if(equipo1 == null) {
					equipo1 = new Equipo(campos[4]);
					equipos.add(equipo2);
				}
				
				for ( Equipo equipo : this.equipos) {
					if(equipo.getNombre().equals(campos[1])) {
						equipo1 = equipo;
					}
					
					if(equipo.getNombre().equals(campos[4])) {
						equipo2 = equipo;
					}
				}
				
				Partido partido = new Partido(equipo1, equipo2);
				partido.setCantDeGoles1(Integer.parseInt(campos[2]));
				partido.setCantDeGoles2(Integer.parseInt(campos[3]));
				
				Ronda ronda = this.buscarRonda(Integer.parseInt(campos[0]));
				
				if (ronda != null) {
					ronda.agregarPartidos(partido);
				} else {
					ronda = new Ronda(Integer.parseInt(campos[0]), null);
					ronda.agregarPartidos(partido);
					rondas.add(ronda);
				}
			}
		}
	}
			
			public List<String> leerConfig(){
				List<String> lineasConfig = new ArrayList<>();
				List<String> datosDelUsuario = new ArrayList<>();
				try {
					lineasConfig = Files.readAllLines(rutaConfig);
				} catch ( IOException e) {
					System.out.println("Las líneas de configuraciones no se han podido leer");
					System.out.println(e.getMessage());
					System.exit(1);
				}
				boolean primera = true;
				for (String lineaConfig : lineasConfig) {
					if(primera) {
						primera = false;
					} else {
						String[] campos = lineaConfig.split(";");
						datosDelUsuario.addAll(Arrays.asList(campos).subList(0, 6));
					}
				}
				return datosDelUsuario;
			}
		}
			
	

