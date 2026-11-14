package proyecto.edu.co.iud.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import proyecto.edu.co.iud.modelo.Estudiante;

public class ExcelExporter {

    public static void exportarEstudiantes(File archivo, List<Estudiante> estudiantes) {
        // Implementación para exportar la lista de estudiantes a un archivo Excel
        try(FileOutputStream fos = new FileOutputStream(archivo);
            // Lógica para escribir los datos de los estudiantes en el archivo Excel
            OutputStreamWriter osw=new OutputStreamWriter(fos,StandardCharsets.UTF_8);
           BufferedWriter writer = new BufferedWriter(osw)){

            fos.write(0xEF);
            fos.write(0xBB);
            fos.write(0xBF);

            writer.write("ID Estudiante;Nombre Complero;Programa Académico;Créditos Inscritos;Nota Definitiva;Estado Académico;Candidato a Beca");
            writer.newLine();
            for (Estudiante estudiante : estudiantes) {
                String linea= String.format("%s;%s;%s;%d;%.2f;%s;%s",
                        estudiante.id(),
                        estudiante.nombre(),
                        estudiante.programa(),
                        estudiante.creditos(),
                        estudiante.notaDefinitiva(),
                        estudiante.isAprobado() ? "Aprobado" : "Reprobado",
                        estudiante.isCandidatoBeca() ? "Sí" : "No");
                writer.write(linea);
                writer.newLine();
           }
           double promedioGeneral = estudiantes.stream()
                    .mapToDouble(Estudiante::notaDefinitiva)
                    .average()
                    .orElse(0.0);
            writer.newLine();
            writer.write(String.format("Promedio General: %.2f", promedioGeneral));
            writer.newLine();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
