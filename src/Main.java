import models.Partido;
import models.Ronda;
import utilities.LectorDeArchivos;
import utilities.LectorDB;
import utilities.LectorCSV;

import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LectorDeArchivos lda = new LectorDeArchivos(Path.of(args[0]), Path.of(args[1]));
        lda.leerResultados();
        lda.leerPronosticos();

       LectorCSV lCSV = new LectorCSV(Path.of(args[0]), Path.of(args[1]));
       System.out.println(args[1]);
       lCSV.leerResultados();
       List<String> datosDelUsuario = lCSV.leerConfig();
       
       LectorDB lDB = new LectorDB(lCSV);
       lectorDB.leerPronosticos(datosDelUsuario);
       
        int puntaje = calcularPuntaje(pronosticos, resultados);
        System.out.println("Puntaje = " + puntaje);
    }

}