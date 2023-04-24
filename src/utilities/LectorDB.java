package utilities;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LectorDB {
	Connection cone = null;
	Statement cons = null;
	LectorCSV lCSV = new LectorCSV(null, null);
	private List<Pronostico> pronosticos = new ArrayList<>();
	private List<Persona> personas = new ArrayList<>();
	
	public LectorDB(LectorCSV lCSV) {
		this.lCSV = lCSV;
	}
	
	public Persona buscarPersona (String e) {
		for (Persona p: personas) {
			if(p.getNombre().equals(e)) {
				return p;
			}
		}
		return null;
	}
	
	public void leerPronosticos(List<String> datosDelUsuario) {
		int puntos = 0;
		int cantDeAcertados = 0;
		List<Partido> p = null;
		Ronda rondaP = new Ronda(1, null);
		try {
			cone = DriverManager.getConnection(datosDelUsuario.get(0), datosDelUsuario.get(1), datosDelUsuario.get(2));
			cons = cone.createStatement();
			
			String sql;
			sql = "select * from TP-Integrador.pronostico";
			ResultSet result = cons.executeQuery(sql);
			
			while (result.next()) {
				int rondaDB = result.getInt("Ronda");
				String participDB = result.getString("Participante");
				String equipo1DB = result.getString("Equipo 1");
				String ganoEquipo1DB = result.getString("Ganó el equipo 1");
				String empateDB = result.getString("Empataron");
				String ganoEquipo2DB = result.getString("Ganó el equipo 2");
				String equipo2DB = result.getString("Equipo 2");
				
				Equipo equipo1 = lCSV.buscarEquipo(equipo1DB);
				Equipo equipo2 = lCSV.buscarEquipo(equipo2DB);
				
				if ( equipo1 != null) {
					equipo1 = new Equipo(equipo1DB);
				}
				
				if ( equipo2 != null) {
					equipo2 = new Equipo(equipo2DB);
				}
				
				Ronda ronda = new Ronda(rondaDB, p);
				
				for(Ronda rondaL : lCSV.getRondas()) {
					if ( rondaL.getNumero().equals(rondaL.getNumero())) {
						ronda = rondaL;
					}
				}
				
				if(!Objects.equals(rondaP.getNumero(), ronda.getNumero())) {
					System.out.println("Los resultados de esta ronda son : " + ronda.getNumero());
					for ( Persona pers : this.personas) {
						System.out.println(pers.getNombre() + " obtuvo el siguiente puntaje en esta ronda " + pers.getPuntaje() + " y acertó en los siguientes pronósticos" + pers.getCantDePronAcertados() );	
					}
					rondaP.setNumero(ronda.getNumero());
				}
				
				Persona persona = buscarPersona(participDB);
				
				if ( persona == null) {
					persona = new Persona(participDB, ronda.getNumero(), rondaDB);
					this.personas.add(persona);
					puntos = 0;
					cantDeAcertados = 0;
				}
				
				if ( persona.getnRonda() != ronda.getNumero()) {
					persona.setnRonda(ronda.getNumero());
					puntos = 0;
					cantDeAcertados = 0;
				}
				
				Partido partido = ronda.buscarPartido(equipo1, equipo2);
				Equipo equipoP = null;
				ResultadoE resultadoP = null;
				
				if( "X".equals(ganoEquipo1DB)) {
					equipoP = partido.getEquipo1();
					resultadoP = ResultadoE.GANADOR;
				}
				
				if("X".equals(empateDB)) {
					equipoP = partido.getEquipo1();
					resultadoP = ResultadoE.EMPATE;
				}
				
				if("X".equals(ganoEquipo2DB)) {
					equipoP = partido.getEquipo1();
					resultadoP = ResultadoE.PERDEDOR;
				}
				
				Pronostico pronostico = new Pronostico( partido, equipoP, persona);
					puntos += pronostico.puntos(resultadoP);
					pronostico.getPersona().setCantDePronAcertados(cantDeAcertados);
					
					this.pronosticos.add(pronostico);
			} 
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if ( cons != null) {
					cons.close();
				} 	
			}catch (SQLException se2) {
			}
		} try {
			if ( cone != null) {
				cone.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		
		System.out.println("Resultado de la ronda : " + rondaP.getNumero());
		for ( Persona persona : this.personas) {
			System.out.println(persona.getNombre() + " obtuvó un puntaje de " + persona.getPuntaje() + " y acertó en los pronósticos " + persona.getCantDePronAcertados());
		}
	}
}
