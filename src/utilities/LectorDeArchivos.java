package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import models.*;

public class LectorDeArchivos {
	 private Path rutaResultados;
	    private Path rutaPronostico;
	    private List<Ronda> rondas = new ArrayList<>();
	    private List<Equipo> equipos = new ArrayList<>();
	    private List<Pronostico> pronosticos = new ArrayList<>();
	    private List<Persona> personas = new ArrayList<>();

	    public void LectordeArchivos(Path rutaResultados, Path rutaPronostico) {
	        this.rutaResultados = rutaResultados;
	        this.rutaPronostico = rutaPronostico;
	    }

	    public Ronda buscarRonda(Integer i) {
	        for (Ronda r : this.rondas) {
	            if (r.getNumero() == i) {
	                return r;
	            }
	        }
	        return null;
	    }
	    
	    public Equipo buscarEquipo(String i) {
	        for (Equipo e : this.equipos) {
	            if (e.getNombre().equals(i)) {
	                return e;
	            }
	        }
	        return null;
	    }
	    
	    public Persona buscarPersona(String i) {
	        for (Persona p : this.personas) {
	            if (p.getNombre().equals(i)) {
	                return p;
	            }
	        }
	        return null;
	    }


	    public void leerResultados(){
	        List<String> lineasResultado = new ArrayList<>();
	        try {
	            lineasResultado = Files.readAllLines(rutaResultados);
	        } catch (
	                IOException e) {
	            System.out.println("No se pudo leer esta linea de resultados");
	            System.out.println(e.getMessage());
	            System.exit(1);
	        }
	        
	        boolean primera = true;
	        for (String lineaResultado : lineasResultado) {
	            if (primera) {
	                primera = false;
	            } else {
	                String[] campos = lineaResultado.split(";");
	                Equipo equipo1 = this.buscarEquipo(campos[1]);
	                Equipo equipo2 = this.buscarEquipo(campos[4]);

	                if (equipo1 == null) {
	                    equipo1 = new Equipo(campos[1]);
	                    equipos.add(equipo1);
	                }
	                if (equipo2 == null) {
	                    equipo2 = new Equipo(campos[4]);
	                    equipos.add(equipo2);
	                }

	                for (Equipo equipo : this.equipos) {
	                    if (equipo.getNombre().equals(campos[1])) {
	                        equipo1 = equipo;
	                    }

	                    if (equipo.getNombre().equals(campos[4])) {
	                        equipo2 = equipo;
	                    }
	                }
	                Partido partido = new Partido(equipo1, equipo2);
	                partido.setCantDeGoles1(Integer.parseInt(campos[2]));
	                partido.setCantDeGoles2(Integer.parseInt(campos[2]));

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

	    public void leerPronosticos() {
	        List<String> lineasPronostico = new ArrayList<>();

	        try {
	            lineasPronostico = Files.readAllLines(rutaPronostico);
	        } catch (IOException e) {
	            System.out.println("No se pudo leer la linea de pronosticos...");
	            System.out.println(e.getMessage());
	            System.exit(1);
	        }
	        
	        boolean primera = true;
	        for (String lineaPronostico : lineasPronostico) {
	            if (primera) {
	                primera = false;
	            } else {
	                String[] campos = lineaPronostico.split(";");
	                Equipo equipo1 = this.buscarEquipo(campos[2]);
	                Equipo equipo2 = this.buscarEquipo(campos[6]);

	                if (equipo1!=null){
	                    equipo1 = new Equipo(campos[2]);
	                }
	                if (equipo2!=null){
	                    equipo2 = new Equipo(campos[6]);
	                }

	                Ronda ronda = new Ronda(Integer.parseInt(campos[0]), null);
	                for (Ronda rondaLista : rondas) {
	                    if (rondaLista.getNumero().equals(ronda.getNumero())){
	                        ronda=rondaLista;
	                    }
	                }

	                Persona persona = this.buscarPersona(campos[1]);

	                if(persona==null){
	                   persona= new Persona(campos[1], 0, 0);
	                   this.personas.add(persona);
	                }

	                Partido partido = ronda.buscarPartido(equipo1, equipo2);
	                Equipo equipoPred = null;
	                ResultadoE result = null;

	                if("X".equals(campos[3])){
	                    equipoPred = partido.getEquipo1();
	                    result = ResultadoE.GANADOR;

	                }
	                if("X".equals(campos[4])){
	                    equipoPred = partido.getEquipo1();
	                    result = ResultadoE.EMPATE;
	                }

	                if("X".equals(campos[5])){
	                    equipoPred = partido.getEquipo1();
	                    result = ResultadoE.PERDEDOR;
	                }
	                
	                Pronostico pronostico = new Pronostico(partido, equipoPred, persona);
	                this.pronosticos.add(pronostico);
	           }
	        }
	    }
}
